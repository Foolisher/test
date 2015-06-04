package test.mybator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerUtil {

    private static Configuration cfg;

    private static String classpath=FreeMarkerUtil.class.getClassLoader().getResource("").getFile();

    static {
        cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new File(Thread.currentThread().getContextClassLoader().getResource("templates").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        Map<String, Object> root = new HashMap<String, Object>();

        // TODO table_name
        String tableName    = "snz_supplier_tqrdc_infos_tmp";
        String model        = "SupplierTQRDCInfoTmp";
        String desc         = "TQRDC 中间表";
        root.put("package",   "io.terminus.snz.user");
        root.put("schema",    "snz");

        // TODO additional method
        List<List<String>> addQueryFields=Lists.newArrayList();
        addQueryFields.add(ImmutableList.of("date"));
//        addQueryFields.add(ImmutableList.of("mould_number", "supplier_code", "step"));
//        addQueryFields.add(ImmutableList.of("mould_number", "supplier_name"));

        root.put("tableName",   tableName);
        root.put("desc",        desc);
        root.put("model", model);

        TableMetaDataHelper.buildColumnMeta(root);

        List<List<Map<String, Object>>> addQueryConds=Lists.newArrayList();
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


        generateFiles(root);

        StringBuilder conf = new StringBuilder();

        conf.append("\n\n\n#异常信息\n");
        conf.append(root.get("tableName") + ".insert.failed=" + root.get("desc") + "新增错误\n");
        conf.append(root.get("tableName") + ".select.failed=未查询到" + root.get("desc")).append(" 数据\n");
        conf.append(root.get("tableName") + ".update.failed=" + root.get("desc") + "更新错误\n");
        conf.append(root.get("tableName") + ".delete.failed=" + root.get("desc") + "删除错误\n");
        conf.append('\n');

        conf.append("        <!-- alias -->\n");
        conf.append("        <typeAlias alias=\"" + root.get("model") + "\" type=\"" + root.get("package") + ".model." + root.get("model") + "\"/>\n\n");
        conf.append('\n');

        conf.append("    <!-- " + root.get("model") + "Service dubbo service -->\n");
        conf.append("    <dubbo:service interface=\"" + root.get("package") + ".service." + root.get("model") + "Service\" ref=\"" + Strings2.lowerFirst(root.get("model") + "") + "ServiceImpl\" retries=\"0\" />\n\n");
        conf.append("    <dubbo:reference interface=\"" + root.get("package") + ".service." + root.get("model") + "Service\" id=\"" + Strings2.lowerFirst(root.get("model") + "\"") +"/>\n\n");
        conf.append('\n');

        File confF = new File(classpath.replace("classes/",model+"/")+"conf");
        Files.write(conf, confF, Charset.forName("UTF-8"));

       }


    public static void generateFiles(Map<String, Object> root) throws Exception {

        String model = root.get("model").toString();

        root.put("modelAlias", model);
        root.put("namespace", model);

        Template template = cfg.getTemplate("mapper.flt.xml");
        buildTemplate(root, classpath.replace("classes/", model+"/"), Strings2.lowerFirst(model) + "Mapper.xml", template);

        template = cfg.getTemplate("Dao.flt.java");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"Dao.java", template);

        template = cfg.getTemplate("DaoTest.flt.java");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"DaoTest.java", template);

        template = cfg.getTemplate("Service.flt.java");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"Service.java", template);

        template = cfg.getTemplate("ServiceImpl.flt.java");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+"ServiceImpl.java", template);

        template = cfg.getTemplate("Model.flt.java");
        buildTemplate(root, classpath.replace("classes/",model+"/"),model+".java", template);

        template = cfg.getTemplate("ServiceImplTest.flt.java");
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

}
