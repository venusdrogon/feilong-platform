///**
// * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
// * <p>
// * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
// * 	You shall not disclose such Confidential Information and shall use it 
// *  only in accordance with the terms of the license agreement you entered into with FeiLong.
// * </p>
// * <p>
// * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
// * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
// * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
// * 	THIS SOFTWARE OR ITS DERIVATIVES.
// * </p>
// */
//package com.feilong.tools.solr;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.List;
//
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrQuery.ORDER;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocumentList;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
// * @version 1.0 2012-2-18 下午8:17:03
// */
//public class QuerySolrTest{
//
//	private static final Logger	log	= LoggerFactory.getLogger(QuerySolrTest.class);
//
//	private String				url	= "http://127.0.0.1:8888/solr-3.5/core0";
//
//	@Test
//	public void readingDatafromSolr() throws SolrServerException,IOException{
//		// Construct a SolrQuery
//		SolrQuery query = new SolrQuery();
//		// query.setQuery("*:*");
//		query.setQuery("code:258746-602");
//		// query.setQuery("code:258746~");
//		// query.setQuery("258746");
//		// ***************************************************
//		query.setHighlight(true);
//		query.addHighlightField("code");
//		query.setHighlightSimplePre("<font color='red'>");// 前缀
//		query.setHighlightSimplePost("</font>");// 后缀
//		query.set("hl.usePhraseHighlighter", true);
//		query.set("hl.highlightMultiTerm", true);
//		query.set("hl.snippets", 3);// 三个片段,默认是1
//		query.set("hl.fragsize", 50);// 每个片段50个字，默认是100
//		// ***************************************************
//		query.addSortField("id", ORDER.desc);
//		// ***************************************************
//		query.setStart(0); // 起始位置 …分页
//		query.setRows(10);// 文档数
//		query(query);
//	}
//
//	private void query(SolrQuery query) throws SolrServerException,MalformedURLException{
//		// Get an instance of server first
//		CommonsHttpSolrServer server = new CommonsHttpSolrServer(url);
//		// Query the server
//		QueryResponse queryResponse = server.query(query);
//		// Get the results
//		SolrDocumentList docs = queryResponse.getResults();
//		log.info("文档个数：" + docs.getNumFound());
//		log.info("查询时间：" + queryResponse.getQTime());
//		// To read Documents as beans, the bean must be annotated as given in the example.
//		List<SkuItem> result = queryResponse.getBeans(SkuItem.class);
//		log.info("result.size():{}", result.size());
//		for (SkuItem solrBean : result){
//			Object[] objects = { solrBean.getId(), solrBean.getCode(), solrBean.getName() };
//			log.info("id:{},code:{},name:{}", objects);
//		}
//	}
//
//	@Test
//	public void advancedUsage() throws SolrServerException,IOException{
//		SolrQuery solrQuery = new SolrQuery().setQuery("id=1");
//		query(solrQuery);
//	}
//
//}
