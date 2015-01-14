package test.mybator.generator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.joda.time.DateTime;
import test.mybator.Strings2;
import test.mybator.TableMetaDataHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _16_Purifier_FreeMarkerUtil {

    private static Configuration cfg;

    private static String classpath=Thread.currentThread().getContextClassLoader().getResource("").getFile();

    static {
        cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new File(Thread.currentThread().getContextClassLoader().getResource("templates").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int len=400;
    static String[] indent=new String[len];
    static {
        indent[0]="";
        for(int i=1;i<len;i++)
            indent[i] = indent[i-1]+" ";
    }

    public static void main(String[] args) throws Exception {

        Map<String, Object> root = new HashMap<String, Object>();

        // TODO 表名称、实体名称、描述信息、包路径、数据库schema
        String tableName    = "rrs_purifiers";
        String model        = "Purifier";
        String desc         = "净水机";
        root.put("package", "com.aixforce.rrs.purify");
        root.put("schema",  "rrs");

        root.put("propertyWidth", 25);
        root.put("typeWidth", 10);
        root.put("propertyDeclareWidth", 16+30);

        // TODO 查询字段
        List<List<String>> addQueryFields=Lists.newArrayList();
        addQueryFields.add(ImmutableList.of("supplier_code"));


        root.put("tableName",   tableName);
        root.put("desc",        desc);
        root.put("model",       model);
        root.put("indent",      indent);
        root.put("now",         DateTime.now().toDate());

        // NO.1 初始化表结构信息
        TableMetaDataHelper.buildColumnMeta(root);

        // NO.2 配置拓展查询
        buildExtQueries(root, addQueryFields);

        // NO.3 生成代码
        generateFiles(root);

        // NO.4 生成配置
        buildConf(root, model);

       }

    private static void buildExtQueries(Map<String, Object> root, List<List<String>> addQueryFields) {
        List<List<Map<String, Object>>> addQueryConds= Lists.newArrayList();
        List<Map<String, Object>> properties= (List<Map<String, Object>>) root.get("properties");
        int i=0;
        for(List<String> fields: addQueryFields){
            if(addQueryConds.size()<i+1)
                addQueryConds.add(new ArrayList<Map<String, Object>>());
            for(String field: fields){
                for(Map<String, Object> prop: properties){
                    if(field.equals(prop.get("dbName")+"")){
                        addQueryConds.get(i).add(prop);
                    }
                }
            }
            i++;
        }
        root.put("addQueryConds", addQueryConds);
    }

    public static void generateFiles(Map<String, Object> root) throws Exception {

        String model = root.get("model").toString();

        root.put("modelAlias", model);
        root.put("namespace", model);

        Template template = cfg.getTemplate("mapper.flt");
        buildTemplate(root, classpath.replace("classes/", model+"/"), model + "Mapper.xml", template);

        template = cfg.getTemplate("Dao.flt");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"Dao.java", template);

        template = cfg.getTemplate("DaoTest.flt");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"DaoTest.java", template);

        template = cfg.getTemplate("Service.flt");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"Service.java", template);

        template = cfg.getTemplate("ServiceImpl.flt");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"ServiceImpl.java", template);

        template = cfg.getTemplate("Model.flt");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+".java", template);

        template = cfg.getTemplate("ServiceImplTest.flt");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"ServiceImplTest.java", template);


    }


    public static void buildTemplate(Map root, String targetPath, String fileName, Template template) {
        File newsDir = new File(targetPath);
        if (!newsDir.exists())
            newsDir.mkdirs();
        try {
            Writer out = new OutputStreamWriter(new FileOutputStream(targetPath+"/"+fileName), "UTF-8");
            template.process(root, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void buildConf(Map<String, Object> root, String model) throws IOException {
        StringBuilder conf = new StringBuilder();

        conf.append("\n\n\n#异常信息\n");
        conf.append(root.get("tableName") + ".insert.failed=" + root.get("desc") + "新增错误\n");
        conf.append(root.get("tableName") + ".select.failed=未查询到" + root.get("desc")).append("数据\n");
        conf.append(root.get("tableName") + ".update.failed=" + root.get("desc") + "更新错误\n");
        conf.append(root.get("tableName") + ".delete.failed=" + root.get("desc") + "删除错误\n");
        conf.append('\n');

        conf.append("        <!-- alias -->\n");
        conf.append("        <typeAlias alias=\"" + root.get("model") + "\" type=\"" + root.get("package") + ".model." + root.get("model") + "\"/>\n\n");
        conf.append('\n');

        conf.append("    <!-- " + root.get("model") + "Service dubbo service -->\n");
        conf.append("    <dubbo:service interface=\"" + root.get("package") + ".service." + root.get("model") + "Service\" ref=\"" + Strings2.lowerFirst(root.get("model") + "") + "ServiceImpl\" retries=\"0\" />\n\n");
        conf.append("    <dubbo:reference interface=\"" + root.get("package") + ".service." + root.get("model") + "Service\" id=\"" + Strings2.lowerFirst(root.get("model") + "Service\"") +"/>\n\n");
        conf.append('\n');

        File confF = new File(classpath.replace("classes/",model+"/")+"conf");
        Files.write(conf, confF, Charset.forName("UTF-8"));
    }



}
