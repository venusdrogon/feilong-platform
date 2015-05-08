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
package com.feilong.tools.middleware.email;

/**
 * 邮箱 枚举.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-3-22 上午11:54:50
 * @since 1.0
 * @deprecated 这玩意该做成配置形式的
 */
@Deprecated
public enum EmailType{
    // -----------------------------------网易--------------------------------------------
    /**
     * "网易163邮箱","http://mail.163.com/","163.com"
     */
    EMAIL163("网易163邮箱","http://mail.163.com/","163.com"),

    /**
     * "网易126邮箱","http://www.126.com/","126.com"
     */
    EMAIL126("网易126邮箱","http://www.126.com/","126.com"),

    /**
     * "网易vip邮箱","http://vip.163.com/","vip.163.com"
     */
    EMAIL163VIP("网易vip邮箱","http://vip.163.com/","vip.163.com"),

    /**
     * "网易yeah邮箱","http://www.yeah.net/","yeah.net
     */
    YEAH("网易yeah邮箱","http://www.yeah.net/","yeah.net"),

    /**
     * "188财富邮","http://www.188.com/","188.com"
     */
    EMAIL188("188财富邮","http://www.188.com/","188.com"),
    // ---------------------------------yahoo----------------------------------------
    /**
     * "雅虎邮箱","http://mail.cn.yahoo.com/","yahoo.cn"
     */
    YAHOO("雅虎邮箱","http://mail.cn.yahoo.com/","yahoo.cn"),
    /**
     * "雅虎邮箱","http://mail.cn.yahoo.com/","yahoo.com.cn"
     */
    YAHOOCOMCN("雅虎邮箱","http://mail.cn.yahoo.com/","yahoo.com.cn"),
    // -----------------------------sina----------------------------------------
    /**
     * "新浪免费邮箱","http://mail.sina.com.cn/","sina.com"
     */
    SINA("新浪免费邮箱","http://mail.sina.com.cn/","sina.com"),
    /**
     * "新浪vip邮箱","http://mail.sina.com.cn/","vip.sina.com"
     */
    SINAVIP("新浪vip邮箱","http://mail.sina.com.cn/","vip.sina.com"),
    // -----------------------------sohu----------------------------------------
    /**
     * "搜狐免费邮箱","http://mail.sohu.com/","sohu.com"
     */
    SOHU("搜狐免费邮箱","http://mail.sohu.com/","sohu.com"),
    /**
     * "搜狐vip邮箱","http://mail.sohu.com/","vip.sohu.com"
     */
    SOHUVIP("搜狐vip邮箱","http://mail.sohu.com/","vip.sohu.com"),
    // ---------------------------------QQ---------------------------------
    /**
     * "QQ邮箱","http://mail.qq.com/","qq.com"
     */
    QQ("QQ邮箱","http://mail.qq.com/","qq.com"),
    /**
     * "QQ vip 邮箱","http://mail.qq.com/","vip.qq.com"
     */
    QQVIP("QQ vip 邮箱","http://mail.qq.com/","vip.qq.com"),
    // ---------------------------------Tom---------------------------------
    /**
     * "TOM免费邮箱","http://mail.tom.com/","tom.com"
     */
    TOM("TOM免费邮箱","http://mail.tom.com/","tom.com"),
    /**
     * "Tom vip邮箱","http://vip.tom.com/","163.net"
     */
    EMAIL163NET("Tom vip邮箱","http://vip.tom.com/","163.net"),
    // ---------------------------------else----------------------------------
    /**
     * "电信189邮箱","http://www.189.cn/webmail/","189.com"
     */
    EMAIL189("电信189邮箱","http://www.189.cn/webmail/","189.com"),
    /**
     * "谷歌邮箱","http://mail.google.com","gmail.com"
     */
    GAMIL("谷歌邮箱","http://mail.google.com","gmail.com"),
    /**
     * "hotmail 邮箱","http://www.hotmail.com","hotmail.com"
     */
    HOTMAIL("hotmail 邮箱","http://www.hotmail.com","hotmail.com"),
    /**
     * "21cn免费邮箱","http://mail.21cn.com/","21cn.com"
     */
    EMAIL21CN("21cn免费邮箱","http://mail.21cn.com/","21cn.com"),
    /**
     * "foxmail邮箱","http://mail.qq.com/","foxmail.com"
     */
    FOXMAIL("foxmail邮箱","http://mail.qq.com/","foxmail.com");

    // -------------------------------------------------------------------------
    /**
     * 通过 postfix 获得EmailType.
     *
     * @param postfix
     *            the postfix
     * @return EmailType
     */
    public static EmailType getEmailTypeByPostfix(String postfix){
        if (null != postfix){
            for (EmailType _emailType : EmailType.values()){
                if (postfix.equals(_emailType.getPostfix())){
                    return _emailType;
                }
            }
        }
        return null;
    }

    // ***********************************************************************
    // 邮箱中文名
    /** The chinese name. */
    private String chineseName;

    // 网址
    /** The website. */
    private String website;

    // 后缀
    /** The postfix. */
    private String postfix;

    /**
     * The Constructor.
     *
     * @param chineseName
     *            the chinese name
     * @param website
     *            the website
     * @param postfix
     *            the postfix
     */
    private EmailType(String chineseName, String website, String postfix){
        this.chineseName = chineseName;
        this.website = website;
        this.postfix = postfix;
    }

    /**
     * 邮箱中文名.
     *
     * @return the chineseName
     */
    public String getChineseName(){
        return chineseName;
    }

    /**
     * 邮箱中文名.
     *
     * @param chineseName
     *            the chineseName to set
     */
    public void setChineseName(String chineseName){
        this.chineseName = chineseName;
    }

    /**
     * 网址.
     *
     * @return the website
     */
    public String getWebsite(){
        return website;
    }

    /**
     * 网址.
     *
     * @param website
     *            the website to set
     */
    public void setWebsite(String website){
        this.website = website;
    }

    /**
     * 后缀.
     *
     * @return the postfix
     */
    public String getPostfix(){
        return postfix;
    }

    /**
     * 后缀.
     *
     * @param postfix
     *            the postfix to set
     */
    public void setPostfix(String postfix){
        this.postfix = postfix;
    }
}
