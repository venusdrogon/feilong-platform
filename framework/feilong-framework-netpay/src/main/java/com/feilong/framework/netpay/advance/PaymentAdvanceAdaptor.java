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

package com.feilong.framework.netpay.advance;

import com.feilong.framework.netpay.advance.command.QueryRequest;
import com.feilong.framework.netpay.advance.command.QueryResult;
import com.feilong.framework.netpay.advance.command.TradeRole;
import com.feilong.framework.netpay.advance.exception.TradeCloseException;
import com.feilong.framework.netpay.advance.exception.TradeQueryException;
import com.feilong.framework.netpay.payment.PaymentAdaptor;

/**
 * 支付高级文档(此接口 包括 关闭,查询,退款等等功能).<br>
 * <p>
 * focus on 关闭 {@link #closeTrade(String, TradeRole)},查询 {@link #getQueryResult(QueryRequest)},退款(to be continue)等等功能.
 * </p>
 * <p>
 * 如果需要实现 <code>去支付</code>, <code>validate notify</code>, <code>validate redirect</code>等 功能,请参考使用 {@link PaymentAdaptor}
 * </p>
 * 
 * <h3>Need PaymentAdvanceAdaptor reason:</h3>
 * 
 * <blockquote>
 * <ol>
 * <li>不含HttpServletRequest等信息,可适用于 main方法启动以及不含jsp/servlet功能的启动.</li>
 * <li>并且同一个支付网关的不同支付类型,可能去支付的参数不一样(比如支付宝 扫码支付,支付宝信用卡支付,支付宝国际卡支付等等等),但是 关闭交易一样.</li>
 * </ol>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月9日 上午1:09:41
 * @since 1.0.6
 */
public interface PaymentAdvanceAdaptor{

    /**
     * Gets the query result.
     *
     * @param queryRequest
     *            the query request
     * @return the query result
     * @throws TradeQueryException
     *             查询出现异常
     */
    QueryResult getQueryResult(QueryRequest queryRequest) throws TradeQueryException;

    // *******************************************************************************

    /**
     * 是否支持关闭接口.
     * 
     * @return true, if support close trade,otherwise return false
     */
    boolean isSupportCloseTrade();

    /**
     * 关闭交易.
     *
     * @param orderNo
     *            交易号(订单号) 官方商城唯一订单号
     * @param tradeRole
     *            (关闭角色) 一般有 商家 或者 买家 取消交易方：B-买家取消；S-卖家取消
     * @return 成功返回true
     * @throws TradeCloseException
     *             the close trade exception
     */
    boolean closeTrade(String orderNo,TradeRole tradeRole) throws TradeCloseException;

}
