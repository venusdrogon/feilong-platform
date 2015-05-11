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
package com.feilong.framework.netpay.payment.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用来构建支付数据的(独立的entity,不受官方商城版本影响).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 25, 2014 8:27:43 PM
 */
public class PayRequest implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    // **********************required******************************************************
    /** <span style="color:red">(required)</span> 交易号码,必须唯一,可以是 订单code,也可以是自定义的交易code. */
    private String            tradeNo;

    /** <span style="color:red">(required)</span> 需要在线支付的总金额(含运费),<span style="color:red">订单行总价加起来总和+运费transferFee 必须＝totalFee</span>. */
    private BigDecimal        totalFee         = new BigDecimal(0);

    // ***********************optional*****************************************************

    /**
     * (optional)支付网关服务器端同步通知商城的url地址<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如支付宝支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     */
    private String            notifyUrl;

    /**
     * (optional)支付网关客户端跳转到商城的url地址<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如支付宝支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     */
    private String            returnUrl;

    /**
     * (optional)支付网关客户端跳转到商城的url地址(失败的时候地址)<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如T-Cash支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     */
    private String            returnFailUrl;

    // *******买家信息************************************

    /** (optional)买家,定义为 Serializable ,兼容 Long,String等,某些查询需要传递该值,不同商城的实现不同,可能是 id,也可能是 code,视情况而定. */
    private Serializable      buyer;

    /** (optional)买家的姓名,一般的支付网关不需要这个参数,但是个别的支付网关是需要的,需要这个参数的适配器 自行验证. */
    private String            buyerName;

    /** (optional)买家的邮箱,一般的支付网关不需要这个参数,但是个别的支付网关是需要的,需要这个参数的适配器 自行验证. */
    private String            buyerEmail;

    //*********运费信息**************************************

    /** (optional)需要在线支付的运费,一般的支付网关不需要这个参数,但是个别的支付网关是需要的,需要这个参数的适配器 自行验证. */
    private BigDecimal        transferFee      = new BigDecimal(0);

    // ********支付明细行信息**********************************
    /** (optional)支付的订单明细行, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的,需要这个参数的适配器 自行验证. */
    private List<PaySoLine>   paySoLineList    = new ArrayList<PaySoLine>();

    // ***************************************************************************************

    /**
     * (optional) 交易创建时间, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的,需要这个参数的适配器 自行验证.
     */
    private Date              createDate;

    // ***************************************************************************************

    /**
     * Gets the (required)交易号码,必须唯一,可以是 订单code,也可以是自定义的交易code.
     * 
     * @return the tradeNo
     */
    public String getTradeNo(){
        return tradeNo;
    }

    /**
     * Sets the (required)交易号码,必须唯一,可以是 订单code,也可以是自定义的交易code.
     * 
     * @param tradeNo
     *            the tradeNo to set
     */
    public void setTradeNo(String tradeNo){
        this.tradeNo = tradeNo;
    }

    /**
     * Gets the (required)需要在线支付的总金额(含运费),订单行总价加起来总和+运费transferFee 必须＝totalFee.
     * 
     * @return the totalFee
     */
    public BigDecimal getTotalFee(){
        return totalFee;
    }

    /**
     * Sets the (required)需要在线支付的总金额(含运费),订单行总价加起来总和+运费transferFee 必须＝totalFee.
     * 
     * @param totalFee
     *            the totalFee to set
     */
    public void setTotalFee(BigDecimal totalFee){
        this.totalFee = totalFee;
    }

    /**
     * Gets the (optional)支付网关服务器端同步通知商城的url地址<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如支付宝支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     * 
     * @return the notifyUrl
     */
    public String getNotifyUrl(){
        return notifyUrl;
    }

    /**
     * Sets the (optional)支付网关服务器端同步通知商城的url地址<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如支付宝支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     * 
     * @param notifyUrl
     *            the notifyUrl to set
     */
    public void setNotifyUrl(String notifyUrl){
        this.notifyUrl = notifyUrl;
    }

    /**
     * Gets the (optional)支付网关客户端跳转到商城的url地址<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如支付宝支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     * 
     * @return the returnUrl
     */
    public String getReturnUrl(){
        return returnUrl;
    }

    /**
     * Sets the (optional)支付网关客户端跳转到商城的url地址<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如支付宝支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     * 
     * @param returnUrl
     *            the returnUrl to set
     */
    public void setReturnUrl(String returnUrl){
        this.returnUrl = returnUrl;
    }

    /**
     * Gets the (optional)买家的姓名,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
     * 
     * @return the buyerName
     */
    public String getBuyerName(){
        return buyerName;
    }

    /**
     * Sets the (optional)买家的姓名,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
     * 
     * @param buyerName
     *            the buyerName to set
     */
    public void setBuyerName(String buyerName){
        this.buyerName = buyerName;
    }

    /**
     * Gets the (optional)买家的邮箱,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
     * 
     * @return the buyerEmail
     */
    public String getBuyerEmail(){
        return buyerEmail;
    }

    /**
     * Sets the (optional)买家的邮箱,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
     * 
     * @param buyerEmail
     *            the buyerEmail to set
     */
    public void setBuyerEmail(String buyerEmail){
        this.buyerEmail = buyerEmail;
    }

    /**
     * Gets the (optional)需要在线支付的运费,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
     * 
     * @return the transferFee
     */
    public BigDecimal getTransferFee(){
        return transferFee;
    }

    /**
     * Sets the (optional)需要在线支付的运费,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
     * 
     * @param transferFee
     *            the transferFee to set
     */
    public void setTransferFee(BigDecimal transferFee){
        this.transferFee = transferFee;
    }

    /**
     * Gets the (optional)支付的订单明细行, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
     * 
     * @return the paySoLineList
     */
    public List<PaySoLine> getPaySoLineList(){
        return paySoLineList;
    }

    /**
     * Sets the (optional)支付的订单明细行, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
     * 
     * @param paySoLineList
     *            the paySoLineList to set
     */
    public void setPaySoLineList(List<PaySoLine> paySoLineList){
        this.paySoLineList = paySoLineList;
    }

    /**
     * Gets the (optional) 交易创建时间, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的,需要这个参数的适配器 自行验证.
     * 
     * @return the createDate
     */
    public Date getCreateDate(){
        return createDate;
    }

    /**
     * Sets the (optional) 交易创建时间, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的,需要这个参数的适配器 自行验证.
     * 
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    /**
     * 获得 (optional)买家,定义为 Serializable ,兼容 Long,String等,某些查询需要传递该值,不同商城的实现不同,可能是 id,也可能是 code,视情况而定.
     * 
     * @return the buyer
     */
    public Serializable getBuyer(){
        return buyer;
    }

    /**
     * 设置 (optional)买家,定义为 Serializable ,兼容 Long,String等,某些查询需要传递该值,不同商城的实现不同,可能是 id,也可能是 code,视情况而定.
     * 
     * @param buyer
     *            the buyer to set
     */
    public void setBuyer(Serializable buyer){
        this.buyer = buyer;
    }

    /**
     * 获得 (optional)支付网关客户端跳转到商城的url地址(失败的时候地址)<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如T-Cash支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     *
     * @return the returnFailUrl
     */
    public String getReturnFailUrl(){
        return returnFailUrl;
    }

    /**
     * 设置 (optional)支付网关客户端跳转到商城的url地址(失败的时候地址)<br>
     * (不同的支付网关对这个参数的配置需求不一样)<br>
     * 比如T-Cash支持传递这个参数,这样本地开发环境,staging环境,以及正式环境均可灵活更改url信息<br>
     * 而DOKU,BCA等印尼的网站就不一样, 他们这些配置是提前告诉他们gateway的工作人员, 他们来配置,是固定的地址,如果需要更改,需要通知他们来更改,我们自己不能直接更改.
     *
     * @param returnFailUrl
     *            the returnFailUrl to set
     */
    public void setReturnFailUrl(String returnFailUrl){
        this.returnFailUrl = returnFailUrl;
    }
}