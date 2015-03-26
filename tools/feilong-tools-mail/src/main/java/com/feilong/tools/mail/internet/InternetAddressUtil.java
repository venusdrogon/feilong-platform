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
package com.feilong.tools.mail.internet;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import com.feilong.commons.core.util.Validator;

/**
 * The Class InternetAddressUtil.<br>
 * 
 * 
 * <pre>
 * name&lt;xxx@xxx.com.cn&gt;
 * 
 * $to: The e-mail address or addresses where the message will be sent to. 
 * The formatting of this string will be validated with the PHP e-mail validation filter. 
 * 
 * Some examples are:
 * 	user@example.com
 * 	user@example.com, anotheruser@example.com
 * 	User &lt;user@example.com&gt;
 * 	User &lt;user@example.com&gt;, Another User &lt;anotheruser@example.com&gt;
 * </pre>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月23日 下午11:53:42
 * @since 1.0.8
 */
public final class InternetAddressUtil{

    /** Don't let anyone instantiate this class. */
    private InternetAddressUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 将nameAndEmail map转成 InternetAddress数组.
     *
     * @param nameAndEmailMap
     *            the name and email map
     * @param charset
     *            the charset
     * @return the internet address[]
     * @throws UnsupportedEncodingException
     *             the unsupported encoding exception
     * @see javax.mail.internet.MimeUtility#encodeWord(String, String, String)
     * @see javax.mail.internet.MimeUtility#getDefaultMIMECharset()
     */
    public static final InternetAddress[] toInternetAddressArray(Map<String, String> nameAndEmailMap,String charset)
                    throws UnsupportedEncodingException{

        if (Validator.isNullOrEmpty(nameAndEmailMap)){
            throw new NullPointerException("nameAndEmailMap can't be null/empty!");
        }

        InternetAddress[] internetAddresses = new InternetAddress[nameAndEmailMap.size()];

        int i = 0;
        for (Map.Entry<String, String> entry : nameAndEmailMap.entrySet()){
            String name = entry.getKey();
            String email = entry.getValue();

            internetAddresses[i] = new InternetAddress(email, name, charset);
            i++;
        }
        return internetAddresses;
    }

    /**
     * 将 internetAddresses 数组 转成 toUnicodeString方法 list
     *
     * @param internetAddresses
     *            the internet addresses
     * @return the list< string>
     */
    public static final List<String> toUnicodeStringList(InternetAddress[] internetAddresses){

        if (Validator.isNullOrEmpty(internetAddresses)){
            throw new NullPointerException("internetAddresses can't be null/empty!");
        }

        List<String> list = new ArrayList<String>();

        for (InternetAddress internetAddress : internetAddresses){
            list.add(internetAddress.toUnicodeString());
        }

        return list;
    }
}
