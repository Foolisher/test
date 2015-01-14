package xml;

import com.google.common.io.Files;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-8-29.
 */
public class JsoupParser {

    DateTimeFormatter _DT = DateTimeFormat.forPattern("YYYYMMDD-HHmmss");

    @Test
    public void parseUserXML() throws IOException {

        Document document = Jsoup.parse(new File("/Users/wanggen/Downloads/user.xml"), "UTF-8");


        for (Element element : document.select("service[interface]")) {
            element.prepend("<description></description>");
        }


        for (Element element : document.getElementsByTag("method")) {
            element.after("<properties></properties>");
        }

        for (Element element : document.getElementsByTag("args")) {
            element.after("<description></description>");
            element.after("<properties></properties>");
            element.after("<example><![CDATA[\n\n]]></example>");
        }


        for (Element element : document.getElementsByTag("arg")) {
            element.html("<description></description><example></example>");
        }


        for (Element element : document.select("field[name]")) {
            element.html("<description></description><example></example>");
        }

        //        System.out.println(document.getElementsByTag("body").html());

        Files.write(document.getElementsByTag("body").html(),
                new File("/Users/wanggen/Downloads/user-"+_DT.print(DateTime.now())+".xml"), Charset.forName("UTF-8"));

    }


    @Test
    public void messageXML() throws IOException {

        Document document = Jsoup.parse(new File("/Users/wanggen/Downloads/message.xml"), "UTF-8");


        for (Element element : document.select("service[interface]")) {
            element.prepend("<description></description>");
            element.append("<properties></properties>");
        }


        for (Element element : document.getElementsByTag("args")) {
            element.after("<description></description>");
            element.after("<properties></properties>");
            element.after("<example><![CDATA[\n\n]]></example>");
        }


        for (Element element : document.getElementsByTag("arg")) {
            element.append("<description></description><example></example>");
        }


        for (Element element : document.select("field[name]")) {
            element.append("<description></description><example></example>");
        }

        //        System.out.println(document.getElementsByTag("body").html());

        Files.write(document.getElementsByTag("body").html(),
                new File("/Users/wanggen/Downloads/message-"+_DT.print(DateTime.now())+".xml"), Charset.forName("UTF-8"));

    }



    public void commonXML(String pck) throws IOException {

        Document document = Jsoup.parse(new File("/Users/wanggen/Downloads/"+pck+".xml"), "UTF-8");


        for (Element element : document.select("service[interface]")) {
            element.prepend("<description></description>");
            element.append("<properties></properties>");
        }


        for (Element element : document.getElementsByTag("args")) {
            element.after("<description></description>");
            element.after("<properties></properties>");
            element.after("<example><![CDATA[\n\n]]></example>");
        }


        for (Element element : document.getElementsByTag("arg")) {
            element.append("<description></description><example></example>");
        }


        for (Element element : document.select("field[name]")) {
            element.append("<description></description><example></example>");
        }

        //        System.out.println(document.getElementsByTag("body").html());

        Files.write(document.getElementsByTag("body").html(),
                new File("/Users/wanggen/Downloads/"+pck+"-"+_DT.print(DateTime.now())+".xml"), Charset.forName("UTF-8"));

    }



    @Test
    public void jsoupTestXML() throws IOException {

        commonXML("statistic");

    }


}
