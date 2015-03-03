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
package com.feilong.taglib.display.breadcrumb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * 飞龙面包屑标签.
 * 
 * @author 金鑫 2010-6-8 上午05:50:38
 */
public class BreadCrumbTag extends AbstractCommonTag{

	private static final long				serialVersionUID	= -8596553099620845748L;

	/** The Constant log. */
	private static final Logger				log					= LoggerFactory.getLogger(BreadCrumbTag.class);

	/** 连接符,默认>. */
	private String							connector			= ">";

	/** siteMapEntityList,用户所有可以访问的菜单url List. */
	private List<BreadCrumbEntity<Object>>	siteMapEntityList;

	/**
	 * 实现自定义站点地图数据提供程序的途径.
	 * 
	 * @return the object
	 */
	@Override
	protected Object writeContent(){
		if (Validator.isNotNullOrEmpty(siteMapEntityList)){
			// ConfigurationManager configurationManager = new ConfigurationManager(this.pageContext.getServletContext());
			// String xmlString = configurationManager.getSiteMapXmlPath(siteMapProvider);
			// SiteMapConfigure siteMapConfigure = new SiteMapConfigure(xmlString);
			// return siteMapConfigure.getSiteMapContent("index.jsp");
			HttpServletRequest request = getHttpServletRequest();
			log.info("request.getRequestURI():{}", request.getRequestURI());
			if (log.isDebugEnabled()){
				// UrlPathHelperUtil.showProperties(request);
			}
			// if (Validator.isNull(currentPath)){
			String currentPath = RequestUtil.getOriginatingServletPath(request);
			// }
			log.info("urlPathHelper.getLookupPathForRequest(request):{}", currentPath);

			Object allParentSiteMapEntityList = getAllParentSiteMapEntityList(currentPath, siteMapEntityList);

			Map<String, Object> contextKeyValues = new HashMap<String, Object>();
			contextKeyValues.put("siteMapEntityList", allParentSiteMapEntityList);
			contextKeyValues.put("connector", connector);
			contextKeyValues.put("request", request);
			String siteMapString = VelocityUtil.parseTemplateWithClasspathResourceLoader("velocity/sitemap.vm", contextKeyValues);

			log.debug("siteMapString is:{}", siteMapString);
			if (Validator.isNullOrEmpty(siteMapString)){
				return "";
			}
			return siteMapString;
		}
		log.warn("siteMapEntityList is null");
		return "";
	}

	/**
	 * 按照父子关系排序好的 list.
	 * 
	 * @param currentPath
	 *            the current path
	 * @param siteMapEntities
	 *            the site map entities
	 * @return the all parent site map entity list
	 */
	public <T> List<BreadCrumbEntity<T>> getAllParentSiteMapEntityList(String currentPath,List<BreadCrumbEntity<T>> siteMapEntities){
		log.info("currentPath:{}", currentPath);
		BreadCrumbEntity<T> siteMapEntity_in = getSiteMapEntityByPath(currentPath, siteMapEntities);
		return getAllParentSiteMapEntityList(siteMapEntity_in, siteMapEntities);
	}

	/**
	 * 按照父子关系排序好的 list.
	 * 
	 * @param siteMapEntity_in
	 *            the site map entity_in
	 * @param siteMapEntities
	 *            the site map entities
	 * @return the all parent site map entity list
	 */
	public <T> List<BreadCrumbEntity<T>> getAllParentSiteMapEntityList(
					BreadCrumbEntity<T> siteMapEntity_in,
					List<BreadCrumbEntity<T>> siteMapEntities){
		if (null == siteMapEntity_in){
			return null;
		}
		// 每次成一个新的
		List<BreadCrumbEntity<T>> allParentSiteMapEntityList = new ArrayList<BreadCrumbEntity<T>>();
		constructParentSiteMapEntityList(siteMapEntity_in, siteMapEntities, allParentSiteMapEntityList);
		log.info("before Collections.reverse,allParentSiteMapEntityList size:{}", allParentSiteMapEntityList.size());
		// for (SiteMapEntity sme : allParentSiteMapEntityList){
		// log.info(sme.getName());
		// }
		// 反转
		Collections.reverse(allParentSiteMapEntityList);
		// log.info("after Collections.reverse");
		// for (SiteMapEntity sme : allParentSiteMapEntityList){
		// log.info(sme.getName());
		// }
		return allParentSiteMapEntityList;
	}

	/**
	 * 通过当前的SiteMapEntity,查找到所有的父节点<br>
	 * 递归生成.
	 * 
	 * @param siteMapEntity_in
	 *            the site map entity_in
	 * @param siteMapEntities
	 *            the site map entities
	 * @param allParentSiteMapEntityList
	 *            the all parent site map entity list
	 */
	private <T> void constructParentSiteMapEntityList(
					BreadCrumbEntity<T> siteMapEntity_in,
					List<BreadCrumbEntity<T>> siteMapEntities,
					List<BreadCrumbEntity<T>> allParentSiteMapEntityList){
		// 加入到链式表
		allParentSiteMapEntityList.add(siteMapEntity_in);
		Object parentId = siteMapEntity_in.getParentId();

		// 标识 是否找到parent node
		BreadCrumbEntity<T> siteMapEntity_parent = null;
		// ****************************************************
		for (BreadCrumbEntity<T> loopSiteMapEntity : siteMapEntities){
			// 当前的id和传入的siteMapEntity equals
			if (loopSiteMapEntity.getId().equals(parentId)){
				log.info("loopSiteMapEntity.getId():{},siteMapEntity_in.getParentId():{}", loopSiteMapEntity.getId(), parentId);
				siteMapEntity_parent = loopSiteMapEntity;
				break;
			}
		}
		if (null != siteMapEntity_parent){
			// 递归
			constructParentSiteMapEntityList(siteMapEntity_parent, siteMapEntities, allParentSiteMapEntityList);
		}
	}

	/**
	 * 匹配路径.
	 * 
	 * @param currentPath
	 *            the current path
	 * @param siteMapEntities
	 *            the site map entities
	 * @return the site map entity by path
	 */
	public <T> BreadCrumbEntity<T> getSiteMapEntityByPath(String currentPath,List<BreadCrumbEntity<T>> siteMapEntities){
		boolean flag = false;
		BreadCrumbEntity<T> siteMapEntity_return = null;
		for (BreadCrumbEntity<T> siteMapEntity : siteMapEntities){
			if (siteMapEntity.getRequestMapping().equals(currentPath)){
				flag = true;
				siteMapEntity_return = siteMapEntity;
				break;
			}
		}
		if (!flag){
			log.warn("currentPath is :{},can't find match BreadCrumbEntity", currentPath);
		}
		return siteMapEntity_return;
	}

	/**
	 * Sets the 连接符.
	 * 
	 * @param connector
	 *            the new 连接符
	 */
	public void setConnector(String connector){
		this.connector = connector;
	}

	/**
	 * Sets the siteMapEntityList,用户所有可以访问的菜单url List.
	 * 
	 * @param siteMapEntityList
	 *            the new siteMapEntityList,用户所有可以访问的菜单url List
	 */
	public void setSiteMapEntityList(List<BreadCrumbEntity<Object>> siteMapEntityList){
		this.siteMapEntityList = siteMapEntityList;
	}

}
