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
package com.feilong.tools.mail.util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.mail.Folder;
import javax.mail.MessagingException;

/**
 * The Class FolderUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年2月2日 下午6:05:06
 * @since 1.0.9
 */
public final class FolderUtil{

    /** Don't let anyone instantiate this class. */
    private FolderUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 获得 map for log.
     *
     * @param folder
     *            the folder
     * @return the map for log
     * @throws MessagingException
     *             the messaging exception
     */
    public static final Map<String, Object> getMapForLog(Folder folder) throws MessagingException{
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("getName", folder.getName());
        map.put("getFullName", folder.getFullName());
        map.put("getMode", folder.getMode());

        map.put("getMessageCount", folder.getMessageCount());
        map.put("getDeletedMessageCount", folder.getDeletedMessageCount());
        map.put("getNewMessageCount", folder.getNewMessageCount());
        map.put("getUnreadMessageCount", folder.getUnreadMessageCount());

        map.put("getPermanentFlags", folder.getPermanentFlags());
        map.put("getSeparator", "" + folder.getSeparator());
        map.put("getType", folder.getType());
        map.put("getURLName", "" + folder.getURLName());

        return map;
    }
}
