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
package com.feilong.framework.netpay.advance.adaptor.alipay;

import com.feilong.commons.core.lang.EnumUtil;

/**
 * 支付宝错误枚举.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年5月8日 下午4:36:25
 * @see "交易关闭接口(close_trade) v1.2"
 * @since 1.1.2
 */
public enum AlipayErrorCode{

    /*--------------------------------------------
     * 
     * 交易关闭业务错误码
     * --------------------------------------------
     */
    /** "TRADE_STATUS_NOT_AVAILD","交易状态不符合","business". */
    TRADE_STATUS_NOT_AVAILD("TRADE_STATUS_NOT_AVAILD","交易状态不符合","business"),

    /** "ILLEGAL_PARTNER","合作伙伴ID不正确","business". */
    ILLEGAL_PARTNER("ILLEGAL_PARTNER","合作伙伴ID不正确","business"),

    /** "ILLEGAL_SIGN","签名不正确","business". */
    ILLEGAL_SIGN("ILLEGAL_SIGN","签名不正确","business"),

    /** "ILLEGAL_ARGUMENT","参数不正确：本接口里一般用于partnerId或者tradeNo、outOrderNo为空，表单验证失败的情况","business". */
    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT","参数不正确：本接口里一般用于partnerId或者tradeNo、outOrderNo为空，表单验证失败的情况","business"),

    /** "ILLEGAL_SERVICE","Service参数不正确","business". */
    ILLEGAL_SERVICE("ILLEGAL_SERVICE","Service参数不正确","business"),

    /** "TRADE_NOT_EXIST","交易不存在","business". */
    TRADE_NOT_EXIST("TRADE_NOT_EXIST","交易不存在","business"),

    /** "ILLEGAL_REQUEST","无效请求","business". */
    ILLEGAL_REQUEST("ILLEGAL_REQUEST","无效请求","business"),

    /** "ILLEGAL_DYN_MD5_KEY","动态密钥信息错误","business". */
    ILLEGAL_DYN_MD5_KEY("ILLEGAL_DYN_MD5_KEY","动态密钥信息错误","business"),

    /** "ILLEGAL_ENCRYPT","加密不正确","business". */
    ILLEGAL_ENCRYPT("ILLEGAL_ENCRYPT","加密不正确","business"),

    /** "ILLEGAL_USER","客户ID不正确","business". */
    ILLEGAL_USER("ILLEGAL_USER","客户ID不正确","business"),

    /** "ILLEGAL_EXTERFACE","接口配置不正确","business". */
    ILLEGAL_EXTERFACE("ILLEGAL_EXTERFACE","接口配置不正确","business"),

    /** "ILLEGAL_PARTNER_EXTERFACE","商户接口信息不正确","business". */
    ILLEGAL_PARTNER_EXTERFACE("ILLEGAL_PARTNER_EXTERFACE","商户接口信息不正确","business"),

    /** "ILLEGAL_SECURITY_PROFILE","未找到匹配的密钥配置","business". */
    ILLEGAL_SECURITY_PROFILE("ILLEGAL_SECURITY_PROFILE","未找到匹配的密钥配置","business"),

    /** "ILLEGAL_AGENT","代理ID不正确","business". */
    ILLEGAL_AGENT("ILLEGAL_AGENT","代理ID不正确","business"),

    /** "ILLEGAL_SIGN_TYPE","签名类型不正确","business". */
    ILLEGAL_SIGN_TYPE("ILLEGAL_SIGN_TYPE","签名类型不正确","business"),

    /** "ILLEGAL_CHARSET","字符集不合法","business". */
    ILLEGAL_CHARSET("ILLEGAL_CHARSET","字符集不合法","business"),

    /** "ILLEGAL_CLIENT_IP","客户端IP地址无权访问服务","business". */
    ILLEGAL_CLIENT_IP("ILLEGAL_CLIENT_IP","客户端IP地址无权访问服务","business"),

    /** "HAS_NO_PRIVILEGE","无权访问","business". */
    HAS_NO_PRIVILEGE("HAS_NO_PRIVILEGE","无权访问","business"),

    /** "ILLEGAL_DIGEST_TYPE","摘要类型不正确","business". */
    ILLEGAL_DIGEST_TYPE("ILLEGAL_DIGEST_TYPE","摘要类型不正确","business"),

    /** "ILLEGAL_DIGEST","文件摘要不正确","business". */
    ILLEGAL_DIGEST("ILLEGAL_DIGEST","文件摘要不正确","business"),

    /** "ILLEGAL_FILE_FORMAT","文件格式不正确","business". */
    ILLEGAL_FILE_FORMAT("ILLEGAL_FILE_FORMAT","文件格式不正确","business"),

    /** "ILLEGAL_ENCODING","不支持该编码类型","business". */
    ILLEGAL_ENCODING("ILLEGAL_ENCODING","不支持该编码类型","business"),

    /** "ILLEGAL_REQUEST_REFERER","防钓鱼检查不支持该请求来源","business". */
    ILLEGAL_REQUEST_REFERER("ILLEGAL_REQUEST_REFERER","防钓鱼检查不支持该请求来源","business"),

    /** "ILLEGAL_ANTI_PHISHING_KEY","防钓鱼检查非法时间戳参数","business". */
    ILLEGAL_ANTI_PHISHING_KEY("ILLEGAL_ANTI_PHISHING_KEY","防钓鱼检查非法时间戳参数","business"),

    /** "ANTI_PHISHING_KEY_TIMEOUT","防钓鱼检查时间戳超时","business". */
    ANTI_PHISHING_KEY_TIMEOUT("ANTI_PHISHING_KEY_TIMEOUT","防钓鱼检查时间戳超时","business"),

    /** "ILLEGAL_EXTER_INVOKE_IP","防钓鱼检查非法调用IP","business". */
    ILLEGAL_EXTER_INVOKE_IP("ILLEGAL_EXTER_INVOKE_IP","防钓鱼检查非法调用IP","business"),

    /*--------------------------------------------
     * 
     * 系统错误 ,当出现系统错误提示时，请联系支付宝技术支持协助处理
     * --------------------------------------------
     */
    /** "SYSTEM_ERROR","支付宝系统错误","system". */
    SYSTEM_ERROR("SYSTEM_ERROR","支付宝系统错误","system"),

    /** "SESSION_TIMEOUT","session超时","system". */
    SESSION_TIMEOUT("SESSION_TIMEOUT","session超时","system"),

    /** "ILLEGAL_TARGET_SERVICE","错误的target_service","system". */
    ILLEGAL_TARGET_SERVICE("ILLEGAL_TARGET_SERVICE","错误的target_service","system"),

    /** "ILLEGAL_ACCESS_SWITCH_SYSTEM","partner不允许访问该类型的系统","system". */
    ILLEGAL_ACCESS_SWITCH_SYSTEM("ILLEGAL_ACCESS_SWITCH_SYSTEM","partner不允许访问该类型的系统","system"),

    /** "ILLEGAL_SWITCH_SYSTEM","切换系统异常","system". */
    ILLEGAL_SWITCH_SYSTEM("ILLEGAL_SWITCH_SYSTEM","切换系统异常","system"),

    /** "EXTERFACE_IS_CLOSED","接口已关闭","system". */
    EXTERFACE_IS_CLOSED("EXTERFACE_IS_CLOSED","接口已关闭","system");

    //*************************************************************************************
    /**
     * 获得 by code value.
     *
     * @param codeValue
     *            the code value
     * @return the by code value
     * @see EnumUtil#getEnumByPropertyValue(Class, String, Object)
     */
    public static AlipayErrorCode getByCodeValue(String codeValue){
        //对应字段名称
        String propertyName = "code";
        try{
            return EnumUtil.getEnumByPropertyValue(AlipayErrorCode.class, propertyName, codeValue);
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    /** The code. */
    private String code;

    /** The description. */
    private String description;

    /** The type. */
    private String type;

    /**
     * The Constructor.
     *
     * @param code
     *            the code
     * @param description
     *            the description
     * @param type
     *            the type
     */
    private AlipayErrorCode(String code, String description, String type){
        this.code = code;
        this.description = description;
        this.type = type;
    }

    /**
     * 获得 code.
     *
     * @return the code
     */
    public String getCode(){
        return code;
    }

    /**
     * 设置 code.
     *
     * @param code
     *            the code to set
     */
    public void setCode(String code){
        this.code = code;
    }

    /**
     * 获得 description.
     *
     * @return the description
     */
    public String getDescription(){
        return description;
    }

    /**
     * 设置 description.
     *
     * @param description
     *            the description to set
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * 获得 type.
     *
     * @return the type
     */
    public String getType(){
        return type;
    }

    /**
     * 设置 type.
     *
     * @param type
     *            the type to set
     */
    public void setType(String type){
        this.type = type;
    }
}
