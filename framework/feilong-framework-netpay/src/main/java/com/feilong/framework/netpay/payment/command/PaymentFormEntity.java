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
package com.feilong.framework.netpay.payment.command;

import java.io.Serializable;
import java.util.Map;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor;
import com.feilong.framework.netpay.payment.adaptor.AdaptorParseUsableData;

/**
 * 支付请求数据的封装.
 * 
 * <h3>说明1:使用指南(hello world)</h3>
 * 
 * <blockquote>
 * <p>
 * Example 1: 在Adaptor内通过数据,构建 {@link PaymentFormEntity}
 * </p>
 * 
 * <pre>
 * PaymentFormEntity paymentFormEntity = new PaymentFormEntity();
 * paymentFormEntity.setAction(actionGateway);
 * paymentFormEntity.setMethod(method);
 * paymentFormEntity.setHiddenParamMap(hiddenParamMap);
 * </pre>
 * 
 * 注:Adaptor内,可以直接 调用 {@link AbstractPaymentAdaptor#getPaymentFormEntity(PayRequest, Map)}方法<br>
 * 然后 在vm 或者jsp 中,可以用过下面方式渲染<br>
 * 
 * 
 * <pre>
 *         {@code<form} id="submitForm" name="submitForm" action="${paymentFormEntity.action}" method="${paymentFormEntity.method}"{@code>}
 *             #foreach($entry in ${paymentFormEntity.hiddenParamMap.entrySet()} )
 *                {@code  <input} type="hidden" name="${entry.key}" value="${entry.value}"{@code />}
 *         	#end
 *        {@code </form>}
 * </pre>
 * 
 * </blockquote>
 * 
 * 
 * <h3>说明2:直接获得完整的请求url地址</h3>
 * 
 * <blockquote>
 * <p>
 * 可以通过 直接调用 {@link #getFullEncodedUrl()}来获取完整的请求路径
 * </p>
 * </blockquote>
 * 
 * 
 * <h3>说明3:返回额外的数据信息</h3>
 * 
 * <blockquote>
 * <p>
 * 如果在 adaptor需要返回的信息不仅仅用于表单提交, 还需要用于后续的逻辑处理, 比如存放额外的信息到database ,那么可以使用 {@link #setAdaptorParseUsableData(AdaptorParseUsableData)} ,构建的对象实现
 * {@link AdaptorParseUsableData}接口即可
 * </p>
 * </blockquote>
 * 
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 15, 2013 7:12:48 PM
 * @version 1.0.8 2014-9-23 17:25
 * @see AbstractPaymentAdaptor#getPaymentFormEntity(PayRequest, Map)
 * @since 1.0.0
 */
public class PaymentFormEntity implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long      serialVersionUID = -8414890066925723419L;

    /** 表单 action 提交的url. */
    private String                 action;

    /** form 的method 有get 和post. */
    private String                 method;

    /** 一堆的隐藏域 map key 为 hidden name,value 为 hidden value. */
    private Map<String, String>    hiddenParamMap;

    /**
     * 一个在adaptor里面解析,并且可以用于controller的对象,数据来源可以是支付网关的数据,也可以是自己parse之后或者加工之后的数据.
     * 
     * @since 1.0.8
     */
    private AdaptorParseUsableData adaptorParseUsableData;

    /**
     * 完整的请求路径.
     * 
     * @return the full encoded url
     */
    public String getFullEncodedUrl(){
        return URIUtil.getEncodedUrlByValueMap(action, hiddenParamMap, CharsetType.UTF8);
    }

    // ************************************************************

    /**
     * Gets the action.
     * 
     * @return the action
     */
    public String getAction(){
        return action;
    }

    /**
     * Sets the action.
     * 
     * @param action
     *            the action to set
     */
    public void setAction(String action){
        this.action = action;
    }

    /**
     * Gets the method.
     * 
     * @return the method
     */
    public String getMethod(){
        return method;
    }

    /**
     * Sets the method.
     * 
     * @param method
     *            the method to set
     */
    public void setMethod(String method){
        this.method = method;
    }

    /**
     * Gets the hidden param map.
     * 
     * @return the hiddenParamMap
     */
    public Map<String, String> getHiddenParamMap(){
        return hiddenParamMap;
    }

    /**
     * Sets the hidden param map.
     * 
     * @param hiddenParamMap
     *            the hiddenParamMap to set
     */
    public void setHiddenParamMap(Map<String, String> hiddenParamMap){
        this.hiddenParamMap = hiddenParamMap;
    }

    /**
     * 获得 一个在adaptor里面解析,并且可以用于controller的对象,数据来源可以是支付网关的数据,也可以是自己parse之后或者加工之后的数据.
     *
     * @return the adaptorParseUsableData
     * @since 1.0.8
     */
    public AdaptorParseUsableData getAdaptorParseUsableData(){
        return adaptorParseUsableData;
    }

    /**
     * 设置 一个在adaptor里面解析,并且可以用于controller的对象,数据来源可以是支付网关的数据,也可以是自己parse之后或者加工之后的数据.
     *
     * @param adaptorParseUsableData
     *            the adaptorParseUsableData to set
     * @since 1.0.8
     */
    public void setAdaptorParseUsableData(AdaptorParseUsableData adaptorParseUsableData){
        this.adaptorParseUsableData = adaptorParseUsableData;
    }
}