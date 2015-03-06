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
package com.feilong.webservice.cxf;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class JaxWsDynamicClientUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.5 2014年5月5日 下午11:48:14
 * @since 1.0.5
 */
public class JaxWsDynamicClientUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(JaxWsDynamicClientUtil.class);

    /**
     * 调用webservice. <br>
     * CXF provides two factory classes for dynamic classes.
     * <ul>
     * <li>If your service is defined in terms of JAX-WS concepts, you should use the JaxWsDynamicClientFactory.</li>
     * <li>If you do not want or need JAX-WS semantics, use the DynamicClientFactory.</li>
     * </ul>
     * 
     * @param wsdlUrl
     *            the wsdl url
     * @param operationName
     *            the operation name
     * @param params
     *            the params
     * @return The return values that matche the parts of the output message of the operation
     * @throws Exception
     *             the exception
     */
    public static String call(String wsdlUrl,String operationName,Object...params) throws Exception{
        if (log.isInfoEnabled()){
            Map<String, Object> traceMap = getTraceMapForLog(wsdlUrl, operationName, params);
            log.info(JsonUtil.format(traceMap));
        }
        DynamicClientFactory dynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = dynamicClientFactory.createClient(wsdlUrl);
        Object[] obj = client.invoke(operationName, params);
        String result = "" + obj[0];
        return result;
    }

    /**
     * 获得 map for log.
     *
     * @param wsdlUrl
     *            the wsdl url
     * @param operationName
     *            the operation name
     * @param params
     *            the params
     * @return the map for log
     * @since 1.0.9
     */
    public static Map<String, Object> getTraceMapForLog(String wsdlUrl,String operationName,Object...params){
        Map<String, Object> object = new HashMap<String, Object>();
        object.put("wsdlUrl", wsdlUrl);
        object.put("operationName", operationName);
        object.put("params", params);
        return object;
    }
}