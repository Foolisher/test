package org.gver.search;

import com.google.common.collect.Lists;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author wanggen on 14-8-12.
 */
@Service
public class BaiduZhidaoSearch {



    private static final String indexDir = "/usr/dev/workspace-luna/test/test.lucene/src/main/resources/indexes/zhidao/";

    private static final String dataDir = "/usr/dev/workspace-luna/test/test.lucene/src/main/files/zhidao/";


    public List<String> search(String q) throws  Exception{

        List<String> pages = Lists.newArrayList();

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(indexDir)));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Analyzer analyser = new StandardAnalyzer(Version.LUCENE_46);

        QueryParser queryParser = new QueryParser(Version.LUCENE_46, "content", analyser);

        Query query;

        query = queryParser.parse(q);
        //query = queryParser.createPhraseQuery("content", q);


        TopDocs docs = indexSearcher.search(query, 100);

        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query, "content");
        Highlighter highlighter = new Highlighter(formatter, scorer);

        System.out.println("总共搜索到 [" + docs.scoreDocs.length + "] 文档");
        for(int i=0;i<docs.scoreDocs.length;i++){
            ScoreDoc scoreDoc = docs.scoreDocs[i];
            Document doc = indexSearcher.doc(scoreDoc.doc);
            String val = doc.get("content");
            pages.add(highlighter.getBestFragment(analyser, "content", val));
        }

        return  pages;

    }


}
