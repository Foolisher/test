package org.gver.lucene;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

/**
 * @author wanggen on 14-8-10.
 */
public class Indexer {

    private static final String indexDir = "/usr/dev/workspace-luna/test/test.lucene/src/main/resources/indexes/";

    private static final String dataDir = "/usr/dev/workspace-luna/test/test.lucene/src/main/files/";

    @Test
    public void index() throws Exception {

        Resources.getResource("").getFile();

        Directory dir = FSDirectory.open(new File(indexDir));
        Analyzer analyser = new StandardAnalyzer(Version.LUCENE_46);
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_46, analyser);
        IndexWriter indexWriter = new IndexWriter(dir, conf);

        Document document = new Document();
        document.add(new TextField("language", "C C++ Java C# javascript ruby html coffeescript", Field.Store.YES));
        indexWriter.addDocument(document);

        document = new Document();
        document.add(new TextField("language", "PHP python jsp ", Field.Store.YES));
        indexWriter.addDocument(document);

        indexWriter.commit();

    }


    @Test
    public void indexHTML() throws Exception {

        Resources.getResource("").getFile();

        Directory dir = FSDirectory.open(new File(indexDir+"news/"));
        Analyzer analyser = new StandardAnalyzer(Version.LUCENE_46);
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_46, analyser);
        IndexWriter indexWriter = new IndexWriter(dir, conf);

        File dataFileDir = new File(dataDir);
        Document document;
        for(File file: dataFileDir.listFiles()){
            document = new Document();
            document.add(new TextField("title", file.getName(), Field.Store.YES));
            indexWriter.addDocument(document);
            document = new Document();
            document.add(new TextField("content", Files.toString(file, Charset.forName("UTF-8")), Field.Store.YES));
            indexWriter.addDocument(document);
        }
        indexWriter.commit();
    }

    @Test
    public void indexHTML_CN() throws Exception {

        Resources.getResource("").getFile();


        Directory dir = FSDirectory.open(new File(indexDir + "news/"));
        Analyzer analyser = new StandardAnalyzer(Version.LUCENE_46);
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_46, analyser);
        IndexWriter indexWriter = new IndexWriter(dir, conf);
        indexWriter.deleteAll();

        File dataFileDir = new File(dataDir);
        Document document;
        for (File file : dataFileDir.listFiles()) {
            document = new Document();
            document.add(new TextField("title", file.getName(), Field.Store.YES));
            indexWriter.addDocument(document);
            document = new Document();
            document.add(new TextField("content", Files.toString(file, Charset.forName("UTF-8")), Field.Store.YES));
            indexWriter.addDocument(document);
        }
        indexWriter.commit();
    }



}
