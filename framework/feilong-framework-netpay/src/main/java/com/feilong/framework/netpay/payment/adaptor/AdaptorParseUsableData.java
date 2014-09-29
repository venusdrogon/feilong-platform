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
package com.feilong.framework.netpay.payment.adaptor;

import java.io.Serializable;

import com.feilong.framework.netpay.payment.adaptor.tcash.PublicTokenResponse;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;

/**
 * 标识一个对象是一个在adaptor里面解析,并且可以用于controller的对象,数据来源可以是支付网关的数据,也可以是自己parse之后或者加工之后的数据.
 * <p>
 * 标识接口,类似于 {@link Serializable} , {@link Cloneable}
 * </p>
 * 
 * <p>
 * <b>注:</b> 该接口的数据可能已经在adaptor内直接用于 {@link PaymentFormEntity#hiddenParamMap},也可能对象和
 * {@link PaymentFormEntity#setHiddenParamMap(java.util.Map)} 完全没有关系,该对象返回出来只是纯粹用于方便做业务处理
 * </p>
 * 
 * <p>
 * <b>典型例子:</b>t-cash这个支付方式 在支付渲染支付请求之前,需要先获取token信息,同时拿到refNum数据,这个字段需要存到database用于后续的支付查询流程,因此,增加 {@link PublicTokenResponse}对象设置到
 * {@link PaymentFormEntity#setAdaptorParseUsableData(AdaptorParseUsableData)}属性中返回给后续业务逻辑处理
 * </p>
 * 
 * <p>
 * 一般用于特殊的流程处理,一般用不到
 * </p>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月23日 下午4:57:49
 * @since 1.0.8
 * 
 * @see java.io.Serializable
 * @see PaymentFormEntity
 */
public interface AdaptorParseUsableData extends Serializable{

}