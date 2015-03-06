/*
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
package com.feilong.taglib.display.sitemap;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.util.Validator;

/**
 * robots.txt 里面定义的 sitemap.xml url list部分 <br>
 * 示例参考:
 * 
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8"?>
 * 
 * <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
 *    <url>
 *       <loc>http://www.example.com/</loc>
 *       <lastmod>2005-01-01</lastmod>
 *       <changefreq>monthly</changefreq>
 *       <priority>0.8</priority>
 *    </url>
 * 
 *    <url>
 *       <loc>http://www.example.com/catalog?item=12&amp;desc=vacation_hawaii</loc>
 *       <changefreq>weekly</changefreq>
 *    </url>
 * 
 *    <url>
 *       <loc>http://www.example.com/catalog?item=73&amp;desc=vacation_new_zealand</loc>
 *       <lastmod>2004-12-23</lastmod>
 *       <changefreq>weekly</changefreq>
 *    </url>
 * 
 * </urlset>
 * }
 * </pre>
 * 
 * 
 * <h3>Entity escaping</h3>
 * 
 * Your Sitemap file must be UTF-8 {@link CharsetType#UTF8} encoded
 * 
 * <blockquote>
 * <p>
 * 
 * <blockquote>
 * <table border="1" cellspacing="0" cellpadding="4">
 * <tr style="background-color:#ccccff">
 * <th align=left>Character</th>
 * <th align=left>Escape Code</th>
 * </tr>
 * <tr valign=top>
 * <td>Ampersand &</td>
 * <td>&amp;</td>
 * </tr>
 * <tr valign=top style="background-color:#eeeeff">
 * <td>Single Quote '</td>
 * <td>&apos;</td>
 * </tr>
 * <tr valign=top>
 * <td>Double Quote "</td>
 * <td>&quot;</td>
 * </tr>
 * <tr valign=top style="background-color:#eeeeff">
 * <td>Greater Than {@code >}</td>
 * <td>&gt;</td>
 * </tr>
 * <tr valign=top>
 * <td>Less Than {@code <}</td>
 * <td>&lt;</td>
 * </tr>
 * </table>
 * </blockquote>
 * 
 * </p>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年6月19日 下午5:56:46
 * @see <a href="http://www.sitemaps.org/protocol.html#xmlTagDefinitions">xmlTagDefinitions</a>
 * @see StringEscapeUtils#ESCAPE_XML
 * @see StringEscapeUtils#escapeXml(String)
 * @see StringEscapeUtils#unescapeXml(String)
 * @see org.apache.commons.lang3.text.translate.EntityArrays
 * @since 1.0.7
 */
public class SiteMapURL implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    /**
     * (required)页面的url<br>
     * 这个url必须以协议 protocol (比如 http) 开头,并且 以 斜杆 a trailing slash 结尾<br>
     * 这个值必须< 2,048 个字符. <br>
     */
    private String            loc;

    /**
     * (optional)The date of last modification of the file,文件的最后修改时间<br>
     * 应该使用 W3C Datetime 格式<br>
     * 使用YYYY-MM-DD格式<br>
     * 注意,这个tag和服务器端返回的header里面的If-Modified-Since (304)参数是不同的, 并且搜索引擎可能使用不同的信息.
     */
    private Date              lastmod;

    /**
     * (optional)这个页面变更的频率情况 <br>
     * 这个值提供基本信息给搜索引擎 ,并且搜索引擎可能不准确的按照这个参数走.<br>
     * 有效值:
     * <ul>
     * <li>always(每次访问都不一样,使用这个参数 describe documents that change each time they are accessed)</li>
     * <li>hourly</li>
     * <li>daily</li>
     * <li>weekly</li>
     * <li>monthly</li>
     * <li>yearly</li>
     * <li>never (用来形容 archived URLs 归档url/历史url)</li>
     * </ul>
     * 
     * 注意, 这个tag的值,只是个提示,不是命令<br>
     * 
     * 搜索引擎爬取页面信息的时候,这个标签的值可能仅做参考:<br>
     * 他们可能爬取标识 为hourly 的页面,低于这个频率;<br>
     * 也可能爬取标识为 yearly的页面 高于这个频率 <br>
     * 爬虫可能会定期爬取标识为 never的页面,so that they can handle unexpected changes to those pages.
     * 
     * @see ChangeFreq
     */
    private ChangeFreq        changefreq;

    /**
     * (optional)相对于你其他的页面,这个页面的权重<br>
     * 
     * 有效值范围 0.0 to 1.0 <br>
     * 页面默认的权重是 0.5<br>
     * 
     * <p>
     * 这个值不会影响你的页面和其他站点的比较-它只是让搜索引擎哪些页面的抓取你认为是最重要的.<br>
     * <b>请注意</b>,你指定的这个权重是不大可能影响你的url在搜索引擎结果页面的位置的<br>
     * 搜索引擎在同一个站点选择的时候可能会使用这个信息,因此,你可以使用这个tag在增加 你最重要的一些网页存在在搜索索引的可能.<br>
     * 另外，请注意，对你的所有网站的URL分配高优先级是没用的 <br>
     * 由于权重是相对的,它只作用于你站点内部url的选择 .
     * </p>
     */
    private Float             priority;

    //*********************************************************************************************
    /**
     * 页面的url(只读属性),经过escape和长度判断.
     * 
     * @return the formatLoc
     * @throws IllegalArgumentException
     *             if Validator.isNullOrEmpty(loc) or formatLoc length >=2048
     * @see #loc
     * @see StringEscapeUtils#ESCAPE_XML
     * @see StringEscapeUtils#escapeXml(String)
     * @see StringEscapeUtils#unescapeXml(String)
     */
    public String getFormatLoc() throws IllegalArgumentException{
        if (Validator.isNullOrEmpty(loc)){
            throw new IllegalArgumentException("loc can't be null/empty!");
        }

        String formatLoc = StringEscapeUtils.escapeXml(loc);
        int length = formatLoc.length();
        if (length >= 2048){
            throw new IllegalArgumentException("formatLoc's length:" + length + " can't >= 2048!");
        }
        return formatLoc;
    }

    /**
     * 使用YYYY-MM-DD格式 (只读字段).
     * 
     * @return if Validator.isNullOrEmpty(lastmod),return null; else return yyyy-MM-dd format
     * @see com.feilong.commons.core.date.DateUtil#date2String(Date, String)
     * @see com.feilong.commons.core.date.DatePattern#COMMON_DATE
     * @see #lastmod
     */
    public String getFormatLastmod(){
        if (Validator.isNullOrEmpty(lastmod)){
            return null;
        }
        String formatLastmod = DateUtil.date2String(lastmod, DatePattern.COMMON_DATE);
        return formatLastmod;
    }

    //*******************************************************************************************************

    /**
     * Gets the uRL of the page.
     * 
     * @return the loc
     */
    public String getLoc(){
        return loc;
    }

    /**
     * Sets the uRL of the page.
     * 
     * @param loc
     *            the loc to set
     */
    public void setLoc(String loc){
        this.loc = loc;
    }

    /**
     * Gets the priority of this URL relative to other URLs on your site.
     * 
     * @return the priority
     */
    public Float getPriority(){
        return priority;
    }

    /**
     * Sets the priority of this URL relative to other URLs on your site.
     * 
     * @param priority
     *            the priority to set
     */
    public void setPriority(Float priority){
        this.priority = priority;
    }

    /**
     * Gets the (optional)这个页面变更的频率情况 <br>
     * 这个值提供基本信息给搜索引擎 ,并且搜索引擎可能不准确的按照这个参数走.
     * 
     * @return the changefreq
     */
    public ChangeFreq getChangefreq(){
        return changefreq;
    }

    /**
     * Sets the (optional)这个页面变更的频率情况 <br>
     * 这个值提供基本信息给搜索引擎 ,并且搜索引擎可能不准确的按照这个参数走.
     * 
     * @param changefreq
     *            the changefreq to set
     */
    public void setChangefreq(ChangeFreq changefreq){
        this.changefreq = changefreq;
    }

    /**
     * Gets the (optional)The date of last modification of the file,文件的最后修改时间<br>
     * 应该使用 W3C Datetime 格式<br>
     * 使用YYYY-MM-DD格式<br>
     * 注意,这个tag和服务器端返回的header里面的If-Modified-Since (304)参数是不同的, 并且搜索引擎可能使用不同的信息.
     * 
     * @return the lastmod
     */
    public Date getLastmod(){
        return lastmod;
    }

    /**
     * Sets the (optional)The date of last modification of the file,文件的最后修改时间<br>
     * 应该使用 W3C Datetime 格式<br>
     * 使用YYYY-MM-DD格式<br>
     * 注意,这个tag和服务器端返回的header里面的If-Modified-Since (304)参数是不同的, 并且搜索引擎可能使用不同的信息.
     * 
     * @param lastmod
     *            the lastmod to set
     */
    public void setLastmod(Date lastmod){
        this.lastmod = lastmod;
    }

}
