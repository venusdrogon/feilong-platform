/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.tools;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-1-10 下午05:50:51
 */
public class LuceneUtilTest{

	@SuppressWarnings("unused")
	private static final Logger	log			= LoggerFactory.getLogger(LuceneUtilTest.class);

	// 保存索引文件的地方
	String						indexDir	= "F:\\indexDir\\";

	// 创建Directory对象
	File						indexFile	= new File(indexDir);

	Version						version		= Version.LUCENE_30;

	@Test
	public void createIndex() throws IOException{
		Directory directory = new SimpleFSDirectory(indexFile);
		Analyzer analyzer = new StandardAnalyzer(version);
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(version, analyzer);
		indexWriterConfig.setOpenMode(OpenMode.CREATE);
		// 创建IndexWriter对象,
		IndexWriter indexWriter = new IndexWriter(//
				directory,// 第一个参数是Directory,
				indexWriterConfig);
		// **********************************************************************
		// 将要搜索TXT文件的地方
		String dateDir = "F:\\dateDir";
		File[] files = new File(dateDir).listFiles();
		Document document = null;
		for (int i = 0; i < files.length; i++){
			document = new Document();
			// 创建Field对象，并放入doc对象中
			document.add(new Field("contents", new FileReader(files[i])));
			document.add(new Field("filename", files[i].getName(), Field.Store.YES, Field.Index.ANALYZED));
			document.add(new Field("indexDate", DateTools.dateToString(new Date(), DateTools.Resolution.DAY), Field.Store.YES, Field.Index.NOT_ANALYZED));
			// 写入IndexWriter
			indexWriter.addDocument(document);
		}
		// 查看IndexWriter里面有多少个索引
		System.out.println("indexWriter.numDocs:" + indexWriter.numDocs());
		//indexWriter.optimize();
		indexWriter.close();
	}

	/***
	 * 搜索
	 */
	@Test
	public void search() throws Exception{
		Directory directory = new SimpleFSDirectory(indexFile);
		//创建 IndexSearcher对象，相比IndexWriter对象，这个参数就要提供一个索引的目录就行了  
		IndexSearcher indexSearch = new IndexSearcher(directory);
		//创建QueryParser对象 
		String f = "contents";
		f = "filename";
		QueryParser queryParser = new QueryParser(//
				version,//第一个参数表示Lucene的版本,
				f, //第二个表示搜索Field的字段
				new StandardAnalyzer(version)//第三个表示搜索使用分词器
		);
		queryParser.setAllowLeadingWildcard(true);
		//生成Query对象  
		String query2 = "langhua9527";
		query2 = "副本";
		Query query = queryParser.parse(query2);
		//搜索结果 TopDocs里面有scoreDocs[]数组，里面保存着索引值  
		TopDocs topDocs = indexSearch.search(query, 10);
		//topDocs.totalHits表示一共搜到多少个  
		System.out.println("找到了" + topDocs.totalHits + "个");
		//循环topDocs.scoreDocs数据，并使用indexSearch.doc方法把Document还原，再拿出对应的字段的值  
		for (int i = 0; i < topDocs.scoreDocs.length; i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			Document document = indexSearch.doc(scoreDoc.doc);
			System.out.println(document.get("filename"));
			System.out.println(document.get("indexDate"));
			//System.out.println(document.get("contents"));
		}
		indexSearch.close();
	}
}
