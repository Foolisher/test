package org.gver.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;

/**
 * @author wanggen on 14-8-11.
 * @Desc:
 */
public class Searcher {

    private static final String indexDir = "/usr/dev/workspace-luna/test/test.lucene/src/main/resources/indexes/";

    @Test
    public void query() throws Exception {

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(indexDir)));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Analyzer analyser = new StandardAnalyzer(Version.LUCENE_46);

        QueryParser queryParser = new QueryParser(Version.LUCENE_46, "language", analyser);

        Query query = queryParser.parse("PHP");

        TopDocs docs = indexSearcher.search(query, 100);

        System.out.println("总共搜索到 ["+docs.scoreDocs.length+"] 文档");

        for(int i=0;i<docs.scoreDocs.length;i++){
            ScoreDoc scoreDoc = docs.scoreDocs[i];
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.err.println("Result["+scoreDoc.doc+"]: "+doc.getField("language"));
        }

    }

    @Test
    public void testHighlighter() throws  Exception{

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(indexDir)));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Analyzer analyser = new StandardAnalyzer(Version.LUCENE_46);

        QueryParser queryParser = new QueryParser(Version.LUCENE_46, "language", analyser);

        Query query = queryParser.parse("PHP jsp");

        TopDocs docs = indexSearcher.search(query, 100);

        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query, "language");
        Highlighter highlighter = new Highlighter(formatter, scorer);

        System.out.println("总共搜索到 [" + docs.scoreDocs.length + "] 文档");
        for(int i=0;i<docs.scoreDocs.length;i++){
            ScoreDoc scoreDoc = docs.scoreDocs[i];
            Document doc = indexSearcher.doc(scoreDoc.doc);
            String val = doc.get("language");
            System.err.println(highlighter.getBestFragment(analyser,"language", val));
        }

    }

    @Test
    public void fuzzyQuery() throws Exception {

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(indexDir)));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Analyzer analyser = new StandardAnalyzer(Version.LUCENE_46);

        Term term = new Term("language", "PHP");
        FuzzyQuery fuzzyQuery = new FuzzyQuery(term);

        TopDocs docs = indexSearcher.search(fuzzyQuery, 100);

        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(fuzzyQuery, "language");
        Highlighter highlighter = new Highlighter(formatter, scorer);

        System.out.println("总共搜索到 [" + docs.scoreDocs.length + "] 文档");
        for (int i = 0; i < docs.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = docs.scoreDocs[i];
            Document doc = indexSearcher.doc(scoreDoc.doc);
            String val = doc.get("language");
            System.err.println(highlighter.getBestFragment(analyser, "language", val));
        }

    }


    @Test
    public void queryNews() throws  Exception{

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(indexDir+"news/")));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Analyzer analyser = new StandardAnalyzer(Version.LUCENE_46);

        QueryParser queryParser = new QueryParser(Version.LUCENE_46, "content", analyser);

        Query query = queryParser.parse("人民");

        TopDocs docs = indexSearcher.search(query, 100);

        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query, "content");
        Highlighter highlighter = new Highlighter(formatter, scorer);

        System.out.println("总共搜索到 [" + docs.scoreDocs.length + "] 文档");
        for(int i=0;i<docs.scoreDocs.length;i++){
            ScoreDoc scoreDoc = docs.scoreDocs[i];
            Document doc = indexSearcher.doc(scoreDoc.doc);
            String val = doc.get("content");
            System.err.println(highlighter.getBestFragment(analyser,"content", val));
            System.out.println("\n\n\n");
        }

    }

}
