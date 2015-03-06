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
package com.feilong.tools.scm;

import java.io.Serializable;

/**
 * The Class ScmAntCopyConfig.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月11日 下午5:57:26
 * @since 1.0.8
 */
public final class ScmAntCopyConfig implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID                   = 566966317818490406L;

    /** 过滤不想传的文件 采用 endWith 来 匹配. */
    private String[]          excludeFileNames;

    /** 是否忽视不规则的文件,maven的话,如果不不遵循maven目录结构,那么文件就被忽略,默认 <code>true</code>. */
    private boolean           ignoreNotRuleFile                  = true;

    /** java文件名字后缀改成class,默认 <code>true</code>. */
    private boolean           changeJavaFileExtensionNameToClass = true;

    /**
     * 获得 过滤不想传的文件 采用 endWith 来 匹配.
     *
     * @return the excludeFileNames
     */
    public String[] getExcludeFileNames(){
        return excludeFileNames;
    }

    /**
     * 设置 过滤不想传的文件 采用 endWith 来 匹配.
     *
     * @param excludeFileNames
     *            the excludeFileNames to set
     */
    public void setExcludeFileNames(String[] excludeFileNames){
        this.excludeFileNames = excludeFileNames;
    }

    /**
     * 获得 java文件名字后缀改成class,默认 <code>true</code>.
     *
     * @return the changeJavaFileExtensionNameToClass
     */
    public boolean getChangeJavaFileExtensionNameToClass(){
        return changeJavaFileExtensionNameToClass;
    }

    /**
     * 设置 java文件名字后缀改成class,默认 <code>true</code>.
     *
     * @param changeJavaFileExtensionNameToClass
     *            the changeJavaFileExtensionNameToClass to set
     */
    public void setChangeJavaFileExtensionNameToClass(boolean changeJavaFileExtensionNameToClass){
        this.changeJavaFileExtensionNameToClass = changeJavaFileExtensionNameToClass;
    }

    /**
     * 获得 是否忽视不规则的文件,maven的话,如果不不遵循maven目录结构,那么文件就被忽略,默认 <code>true</code>.
     *
     * @return the ignoreNotRuleFile
     */
    public boolean getIgnoreNotRuleFile(){
        return ignoreNotRuleFile;
    }

    /**
     * 设置 是否忽视不规则的文件,maven的话,如果不不遵循maven目录结构,那么文件就被忽略,默认 <code>true</code>.
     *
     * @param ignoreNotRuleFile
     *            the ignoreNotRuleFile to set
     */
    public void setIgnoreNotRuleFile(boolean ignoreNotRuleFile){
        this.ignoreNotRuleFile = ignoreNotRuleFile;
    }
}
