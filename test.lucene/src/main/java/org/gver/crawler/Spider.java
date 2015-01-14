package org.gver.crawler;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wanggen on 14-8-10.
 */
public class Spider {

    static String dir = "/usr/dev/workspace-luna/test/test.lucene/src/main/files/";

    final Queue<Document> _links = new LinkedBlockingQueue<Document>(100000);

    final List indent = Lists.newArrayList("\t");

    @Test
    public void crawl() throws IOException {

        for (int i = 1; i <= 100; i++)
            indent.add(indent.get(i - 1) + "\t");
        int level = 0;

        Document document = Jsoup.connect("http://news.sina.com.cn/").get();
        Elements title = Selector.select("title", document);
        System.out.println(title.html());

        _links.add(document);

        Elements links = Selector.select("a", document);

//        for(Element element: links){
//            _links.add(element.attr("href"));
//        }

        Document currDoc = _links.poll();
        while (currDoc != null) {
            Elements _a = Selector.select("a", currDoc);
            Elements title1 = Selector.select("title", currDoc);

            Elements meta = Selector.select("meta[http-equiv=\"Content-Type\"]", currDoc);
            if (meta.size() <= 0)
                meta = Selector.select("meta[http-equiv=\"content-type\"]", currDoc);
            String charset = meta.attr("content").replaceAll("text/html;\\s+charset=", "");
            if (charset == null || charset.length() <= 0)
                charset = "UTF-8";

            String content = currDoc.text();
            if (content.length() > 1)
                Files.write(content, new File(dir + title1.html().replace('/', '-') + ".htm"), Charset.forName(charset.toUpperCase()));

            for (Element ele : _a) {
                try {
                    if (level >= 1)
                        continue;
                    String href = ele.attr("href");
                    if (!href.contains("news.sina"))
                        continue;
                    _links.add(Jsoup.connect(href).get());
                    System.out.println(indent.get(level) + "" + href);
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
            currDoc = _links.poll();
            level++;
        }


    }


    @Test
    public void jsoupCrawl() throws Exception {

        Document document = Jsoup.connect("http://www.sina.com").get();

        Queue<Elements> elementQueue = new LinkedList<Elements>();

        Elements elements = document.children();
        while(elements!=null && elements.size()>=1) {
            for (Element element : elements) {
                Elements children = element.children();
                if (children.size() == 0) {
                    String text = element.text().replaceAll("<!--.*?-->", "").replaceAll("[\\n\\t]*", "").trim();
                    if(!"".equals(text))
                        System.out.println(text);
                }else {
                    elementQueue.add(children);
                }
            }
            elements = elementQueue.poll();
        }

    }


}
