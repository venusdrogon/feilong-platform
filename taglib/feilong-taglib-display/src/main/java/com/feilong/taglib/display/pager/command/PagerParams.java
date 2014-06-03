/**
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.taglib.display.pager.command;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.feilong.commons.core.entity.Pager;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.taglib.display.pager.PagerUtil;

/**
 * 方法参数.<br>
 * 用于{@link PagerUtil#getPagerContent(PagerParams)}参数封装
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 9, 2013 1:14:35 AM
 */
public class PagerParams implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 7310948528499709685L;

	/** 分页的 基础 url. */
	private String				pageUrl;

	/** 总数据条数. */
	private Integer				totalCount;

	/** 当前第几页. */
	private Integer				currentPageNo;

	/**
	 * 最大 分页码数量.
	 * 
	 * @deprecated 参数名字取得不好,在将来的版本会更改替换,不建议使用这个参数
	 */
	@Deprecated
	private Integer				maxIndexPages;

	/** 每页显示多少条. */
	private Integer				pageSize			= PagerConstants.DEFAULT_PAGESIZE;

	/** 皮肤:可选. */
	private String				skin				= PagerConstants.DEFAULT_SKIN;

	/** 分页参数名称. */
	private String				pageParamName		= PagerConstants.DEFAULT_PAGE_PARAM_NAME;

	/** vm的路径. */
	private String				vmPath				= PagerConstants.DEFAULT_TEMPLATE_IN_CLASSPATH;

	/**
	 * 最多显示页数,(-1或者不设置,默认显示所有页数)<br>
	 * 类似于淘宝不管搜索东西多少,最多显示100页<br>
	 * 这是一种折中的处理方式，空间换时间。 数据查询越往后翻，对服务器的压力越大，速度越低，而且从业务上来讲商品质量也越差，所以就没有必要给太多了。<br>
	 * 新浪微博的时间轴也只给出了10页，同样的折中处理。.
	 * 
	 * @since 1.0.5
	 */
	private Integer				maxShowPageNo		= Pager.DEFAULT_LIMITED_MAX_PAGENO;

	/**
	 * 编码集.
	 * 
	 * @since 1.0.5
	 */
	private String				charsetType			= CharsetType.UTF8;

	/**
	 * 获得此 Java 虚拟机实例的当前默认语言环境值。 <br>
	 * Java 虚拟机根据主机的环境在启动期间设置默认语言环境。如果没有明确地指定语言环境，则很多语言环境敏感的方法都使用该方法。可使用 setDefault 方法更改该值。.
	 * 
	 * @since 1.0.5
	 */
	private Locale				locale				= Locale.getDefault();

	/** debug 模式. */
	private boolean				debugIsNotParseVM;

	/**
	 * The Constructor.
	 * 
	 * @param totalCount
	 *            总数据条数 the total count
	 * @param pageUrl
	 *            分页的 基础 url the page url
	 */
	public PagerParams(Integer totalCount, String pageUrl){
		this.totalCount = totalCount;
		this.pageUrl = pageUrl;
	}

	/**
	 * 获得 分页的 基础 url.
	 * 
	 * @return the pageUrl
	 */
	public String getPageUrl(){
		return pageUrl;
	}

	/**
	 * 设置 分页的 基础 url.
	 * 
	 * @param pageUrl
	 *            the pageUrl to set
	 */
	public void setPageUrl(String pageUrl){
		this.pageUrl = pageUrl;
	}

	/**
	 * 获得 总数据条数.
	 * 
	 * @return the totalCount
	 */
	public Integer getTotalCount(){
		return totalCount;
	}

	/**
	 * 设置 总数据条数.
	 * 
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(Integer totalCount){
		this.totalCount = totalCount;
	}

	/**
	 * 获得 当前第几页.
	 * 
	 * @return the currentPageNo
	 */
	public Integer getCurrentPageNo(){
		return currentPageNo;
	}

	/**
	 * 设置 当前第几页.
	 * 
	 * @param currentPageNo
	 *            the currentPageNo to set
	 */
	public void setCurrentPageNo(Integer currentPageNo){
		this.currentPageNo = currentPageNo;
	}

	/**
	 * 获得 最大 分页码数量.
	 * 
	 * @return the maxIndexPages
	 */
	public Integer getMaxIndexPages(){
		return maxIndexPages;
	}

	/**
	 * 设置 最大 分页码数量.
	 * 
	 * @param maxIndexPages
	 *            the maxIndexPages to set
	 */
	public void setMaxIndexPages(Integer maxIndexPages){
		this.maxIndexPages = maxIndexPages;
	}

	/**
	 * 获得 每页显示多少条.
	 * 
	 * @return the pageSize
	 */
	public Integer getPageSize(){
		return pageSize;
	}

	/**
	 * 设置 每页显示多少条.
	 * 
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize){
		this.pageSize = pageSize;
	}

	/**
	 * 获得 皮肤:可选.
	 * 
	 * @return the skin
	 */
	public String getSkin(){
		return skin;
	}

	/**
	 * 设置 皮肤:可选.
	 * 
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(String skin){
		this.skin = skin;
	}

	/**
	 * 获得 分页参数名称.
	 * 
	 * @return the pageParamName
	 */
	public String getPageParamName(){
		return pageParamName;
	}

	/**
	 * 设置 分页参数名称.
	 * 
	 * @param pageParamName
	 *            the pageParamName to set
	 */
	public void setPageParamName(String pageParamName){
		this.pageParamName = pageParamName;
	}

	/**
	 * 获得 vm的路径.
	 * 
	 * @return the vmPath
	 */
	public String getVmPath(){
		return vmPath;
	}

	/**
	 * 设置 vm的路径.
	 * 
	 * @param vmPath
	 *            the vmPath to set
	 */
	public void setVmPath(String vmPath){
		this.vmPath = vmPath;
	}

	/**
	 * 获得 最多显示页数,(-1或者不设置,默认显示所有页数)<br>
	 * 类似于淘宝不管搜索东西多少,最多显示100页<br>
	 * 这是一种折中的处理方式，空间换时间。 数据查询越往后翻，对服务器的压力越大，速度越低，而且从业务上来讲商品质量也越差，所以就没有必要给太多了。<br>
	 * 新浪微博的时间轴也只给出了10页，同样的折中处理。.
	 * 
	 * @return the maxShowPageNo
	 */
	public Integer getMaxShowPageNo(){
		return maxShowPageNo;
	}

	/**
	 * 设置 最多显示页数,(-1或者不设置,默认显示所有页数)<br>
	 * 类似于淘宝不管搜索东西多少,最多显示100页<br>
	 * 这是一种折中的处理方式，空间换时间。 数据查询越往后翻，对服务器的压力越大，速度越低，而且从业务上来讲商品质量也越差，所以就没有必要给太多了。<br>
	 * 新浪微博的时间轴也只给出了10页，同样的折中处理。.
	 * 
	 * @param maxShowPageNo
	 *            the maxShowPageNo to set
	 */
	public void setMaxShowPageNo(Integer maxShowPageNo){
		this.maxShowPageNo = maxShowPageNo;
	}

	/**
	 * 获得 编码集.
	 * 
	 * @return the charsetType
	 */
	public String getCharsetType(){
		return charsetType;
	}

	/**
	 * 设置 编码集.
	 * 
	 * @param charsetType
	 *            the charsetType to set
	 */
	public void setCharsetType(String charsetType){
		this.charsetType = charsetType;
	}

	/**
	 * 获得 获得此 Java 虚拟机实例的当前默认语言环境值。 <br>
	 * Java 虚拟机根据主机的环境在启动期间设置默认语言环境。如果没有明确地指定语言环境，则很多语言环境敏感的方法都使用该方法。可使用 setDefault 方法更改该值。.
	 * 
	 * @return the locale
	 */
	public Locale getLocale(){
		return locale;
	}

	/**
	 * 设置 获得此 Java 虚拟机实例的当前默认语言环境值。 <br>
	 * Java 虚拟机根据主机的环境在启动期间设置默认语言环境。如果没有明确地指定语言环境，则很多语言环境敏感的方法都使用该方法。可使用 setDefault 方法更改该值。.
	 * 
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
	}

	/**
	 * 获得 debug 模式.
	 * 
	 * @return the debugIsNotParseVM
	 */
	public boolean getDebugIsNotParseVM(){
		return debugIsNotParseVM;
	}

	/**
	 * 设置 debug 模式.
	 * 
	 * @param debugIsNotParseVM
	 *            the debugIsNotParseVM to set
	 */
	public void setDebugIsNotParseVM(boolean debugIsNotParseVM){
		this.debugIsNotParseVM = debugIsNotParseVM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		//返回该对象的哈希码值  支持此方法是为了提高哈希表（例如 java.util.Hashtable 提供的哈希表）的性能。 

		//当我们向一个集合中添加某个元素，集合会首先调用hashCode方法，这样就可以直接定位它所存储的位置，
		//若该处没有其他元素，则直接保存。

		//若该处已经有元素存在，
		//就调用equals方法来匹配这两个元素是否相同，相同则不存，
		//不同则散列到其他位置（具体情况请参考（Java提高篇（）-----HashMap））。

		//这样处理，当我们存入大量元素时就可以大大减少调用equals()方法的次数，极大地提高了效率。

		//hashCode在上面扮演的角色为寻域（寻找某个对象在集合中区域位置）

		//************************************************************************************
		//可替代地，存在使用反射来确定测试中的字段的方法。
		//因为这些字段通常是私有的，该方法中，reflectionHashCode，使用AccessibleObject.setAccessible改变字段的可见性。
		//这点会在一个安全管理器失败，除非相应的权限设置是否正确。
		//它也比明确地测试速度较慢。 
		//HashCodeBuilder.reflectionHashCode(this);
		//************************************************************************************
		//你选择一个硬编码，随机选择，不为零，奇数 
		//理想情况下 每个类不同
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(3, 5);
		return hashCodeBuilder//
				.append(debugIsNotParseVM)//
				.append(charsetType) //
				.append(locale)//
				.append(pageParamName)//
				.append(pageUrl)//
				.append(skin)//
				.append(vmPath)//

				.append(debugIsNotParseVM)//

				.append(currentPageNo)//
				.append(maxIndexPages)//
				.append(maxShowPageNo)//
				.append(pageSize)//
				.append(totalCount)//
				.toHashCode();

		//		final int prime = 37;
		//		int hashResult = 1;
		//		hashResult += (hashResult + getHashCode(charsetType) + getHashCode(locale)) * prime;
		//		hashResult += getHashCode(pageParamName);
		//		hashResult += getHashCode(pageUrl);
		//		hashResult += getHashCode(skin);
		//		hashResult += getHashCode(vmPath);
		//
		//		hashResult += getHashCode(debugIsNotParseVM);
		//		hashResult += getHashCode(currentPageNo);
		//		hashResult += getHashCode(maxIndexPages);
		//		hashResult += getHashCode(maxShowPageNo);
		//		hashResult += getHashCode(pageSize);
		//		hashResult += getHashCode(totalCount);
		//
		//		//		log.info("" + getHashCode(charsetType) + " " + getHashCode(locale) + " " + getHashCode(pageParamName) + " " + " "
		//		//				+ getHashCode(pageUrl) + " " + getHashCode(skin) + " " + getHashCode(vmPath) + " " + getHashCode(debugIsNotParseVM) + " "
		//		//				+ getHashCode(currentPageNo) + " " + getHashCode(maxIndexPages) + " " + getHashCode(maxShowPageNo) + " "
		//		//				+ getHashCode(pageSize) + " " + getHashCode(totalCount));
		//
		//		return hashResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if (obj == null){
			return false;
		}
		if (obj == this){
			return true;
		}
		if (obj.getClass() != getClass()){
			return false;
		}

		//存在使用反射来确定测试中的字段的方法。因为这些字段通常是私有的，该方法中，reflectionEquals，使用AccessibleObject.setAccessible改变字段的可见性。
		//这点会在一个安全管理器失败，除非相应的权限设置是否正确。
		//它也比明确地测试速度较慢。 
		//EqualsBuilder.reflectionEquals(this, obj);

		PagerParams pagerParams = (PagerParams) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();

		return equalsBuilder //
				//.appendSuper(super.equals(obj))//
				.append(this.charsetType, pagerParams.getCharsetType())//
				.append(this.locale, pagerParams.getLocale())//
				.append(this.pageParamName, pagerParams.getPageParamName())//
				.append(this.pageUrl, pagerParams.getPageUrl())//
				.append(this.skin, pagerParams.getSkin())//
				.append(this.vmPath, pagerParams.getVmPath())//

				.append(this.debugIsNotParseVM, pagerParams.getDebugIsNotParseVM())//
				.append(this.currentPageNo, pagerParams.getCurrentPageNo())//
				.append(this.maxIndexPages, pagerParams.getMaxIndexPages())//
				.append(this.maxShowPageNo, pagerParams.getMaxShowPageNo())//
				.append(this.pageSize, pagerParams.getPageSize())//
				.append(this.totalCount, pagerParams.getTotalCount())//
				.isEquals();

		//		//先校验null ,否则obj.getClass 会抛出java.lang.NullPointerException
		//		if (null == obj){
		//			return false;
		//		}

		//see String equals
		//		if (this == obj){
		//			return true;
		//		}

		//		if (obj instanceof PagerParams){
		//			PagerParams pagerParams = (PagerParams) obj;
		//			if (equals(this.charsetType, pagerParams.getCharsetType())//
		//					&& equals(this.locale, pagerParams.getLocale())//
		//					&& equals(this.pageParamName, pagerParams.getPageParamName())//
		//					&& equals(this.pageUrl, pagerParams.getPageUrl())//
		//					&& equals(this.skin, pagerParams.getSkin())//
		//					&& equals(this.vmPath, pagerParams.getVmPath())//
		//
		//					&& this.debugIsNotParseVM == pagerParams.getDebugIsNotParseVM()//
		//
		//					//注意 Integer ==   ( -128~127) 是可以的,其余需要用equals
		//					&& equals(this.currentPageNo, pagerParams.getCurrentPageNo())//
		//					&& equals(this.maxIndexPages, pagerParams.getMaxIndexPages())//
		//					&& equals(this.maxShowPageNo, pagerParams.getMaxShowPageNo())//
		//					&& equals(this.pageSize, pagerParams.getPageSize())//
		//					&& equals(this.totalCount, pagerParams.getTotalCount())//
		//			){
		//				return true;
		//			}
		//		}
		//		return false;

	}

	/**
	 * 如果null==obj1,返回 null==obj2,否则返回 obj1.equals(obj2)
	 * 
	 * @param obj1
	 *            the obj1
	 * @param obj2
	 *            the obj2
	 * @return true, if successful
	 * @since 1.0.7
	 */
	private boolean equals(Object obj1,Object obj2){
		if (null == obj1){
			return null == obj2;
		}
		return obj1.equals(obj2);
	}

	/**
	 * 如果obj是null 返回0 否则 返回obj.hashCode()
	 * 
	 * @param obj
	 *            the obj
	 * @return 如果obj是null 返回0 否则 返回obj.hashCode()
	 * @since 1.0.7
	 */
	private int getHashCode(Object obj){
		return (null == obj) ? 0 : obj.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return super.toString();
	}
}
