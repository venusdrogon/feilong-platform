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
package yicheng;

import java.math.BigDecimal;
import java.util.Date;

import com.feilong.commons.core.Constants;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.security.oneway.MD5Util;
import com.feilong.commons.core.util.NumberUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 飞龙"新华一城网"支付.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 26, 2010 4:28:58 PM
 */
public class YiChengPay{

	/** 测试地址. */
	public final static String	post_url_test	= "https://dev.001town.com/selfhelp/payOrderInput.action";

	/** 正式地址. */
	public final static String	post_url_formal	= "https://pay.001town.com/selfhelp/payOrderInput.action";

	/**
	 * 写内容.
	 * 
	 * @param action
	 *            提交地址
	 * @param feiLongYiChengPayEntity
	 *            feiLongYiChengPayEntity
	 * @return 写内容
	 * @author 金鑫
	 * @version 1.0 2010-11-4 上午11:29:41
	 */
	public static String getWriteContent(String action,YiChengPayEntity feiLongYiChengPayEntity){
		// ***********************************************************************
		StringBuilder stringBuilder = new StringBuilder();
		if (Validator.isNullOrEmpty(action)){
			action = post_url_test;
		}

		// TODO 转成vm写法

		// stringBuilder.append("<form action=\"" + action + "\" method=\"post\" target=\"_blank\">");
		// stringBuilder.append(Constants.lineSeparator);
		// // <!--商户号-->
		// stringBuilder.append(HTMLInput.createInputTag("hidden", "merchantNo", feiLongYiChengPayEntity.getMerchantNo()));
		// stringBuilder.append(Constants.lineSeparator);
		// // <!--订单号(生成)-->
		// stringBuilder.append(HTMLInput.createInputTag("hidden", "orderId", feiLongYiChengPayEntity.getOrderId()));
		// stringBuilder.append(Constants.lineSeparator);
		// // <!--支付流水号(HHmmss)-->
		// stringBuilder.append(HTMLInput.createInputTag("hidden", "serialNo", feiLongYiChengPayEntity.getSerialNo()));
		// stringBuilder.append(Constants.lineSeparator);
		// // <!--订单未付金额（必须为两位小数,最低金额为0.1元）-->
		// stringBuilder.append(HTMLInput.createInputTag("hidden", "amount", feiLongYiChengPayEntity.getAmount()));
		// stringBuilder.append(Constants.lineSeparator);
		// // <!--回调地址,支付成功将向该地址发送支付结果等信息-->
		// stringBuilder.append(HTMLInput.createInputTag("hidden", "callBackUrl", feiLongYiChengPayEntity.getCallBackUrl()));
		// stringBuilder.append(Constants.lineSeparator);
		// // <!--终端号(一城卡提供)-->
		// stringBuilder.append(HTMLInput.createInputTag("hidden", "terminalNo", feiLongYiChengPayEntity.getTerminalNo()));
		// stringBuilder.append(Constants.lineSeparator);
		// // <!--是否全额支付（0为部分支付，1为全额支付）-->
		// stringBuilder.append(HTMLInput.createInputTag("hidden", "isFull", feiLongYiChengPayEntity.getIsFull()));
		// stringBuilder.append(Constants.lineSeparator);
		// // 密文 MD5序列
		// stringBuilder.append(HTMLInput.createInputTag("hidden", "merchantDecodedData", YiChengPay
		// .getMerchantDecodedDataString(feiLongYiChengPayEntity)));
		stringBuilder.append(Constants.LINE_SEPARATOR);
		stringBuilder.append("<input type=\"image\" src=\"/images/netpay/button_001town.gif\" alt=\"点击此处进入新华一城卡付款\">");
		stringBuilder.append(Constants.LINE_SEPARATOR);
		stringBuilder.append("</form>");
		return stringBuilder.toString();
	}

	/**
	 * 将金额转成2位小数.
	 * 
	 * @param amount
	 *            金额
	 * @return 将金额转成2位小数
	 * @author 金鑫
	 * @version 1.0 2010-11-4 上午10:03:30
	 */
	public static String convertAmountTo2XiaoShu(String amount){
		return NumberUtil.toString(new BigDecimal(amount), "#0.00");
	}

	/**
	 * 根据feiLongYiChengPayEntity 生成MerchantDecodedData值
	 * 
	 * <pre>
	 * 按如下规则拼凑字符串:商户号，终端号，订单号，流水号，金额，全额支付标识，回调地址，密钥。
	 * 注意:不需要任何连接符号，直接附加。然后对获得的字符串进行MD5加密，最后将结果转换为小写。
	 * 
	 * </pre>
	 * 
	 * .
	 * 
	 * @param feiLongYiChengPayEntity
	 *            the fei long yi cheng pay entity
	 * @return 根据feiLongYiChengPayEntity 生成MerchantDecodedData值
	 * @author 金鑫
	 * @version 1.0 Oct 26, 2010 5:02:58 PM
	 */
	public static String getMerchantDecodedDataString(YiChengPayEntity feiLongYiChengPayEntity){
		StringBuilder md5_stringBuilder = new StringBuilder("");
		// 商户号
		md5_stringBuilder.append(feiLongYiChengPayEntity.getMerchantNo());
		// 终端号
		md5_stringBuilder.append(feiLongYiChengPayEntity.getTerminalNo());
		// 订单号
		md5_stringBuilder.append(feiLongYiChengPayEntity.getOrderId());
		// 流水号
		md5_stringBuilder.append(feiLongYiChengPayEntity.getSerialNo());
		// 金额
		md5_stringBuilder.append(feiLongYiChengPayEntity.getAmount());
		// 全额支付标识
		md5_stringBuilder.append(feiLongYiChengPayEntity.getIsFull());
		// 回调地址
		md5_stringBuilder.append(feiLongYiChengPayEntity.getCallBackUrl());
		// 密钥
		md5_stringBuilder.append(feiLongYiChengPayEntity.getKey());
		return MD5Util.encode(md5_stringBuilder.toString()).toLowerCase();
	}

	/**
	 * 根据feiLongYiChengPayEntity 生成ResultDecodedData值
	 * 
	 * <pre>
	 * 按如下规则拼凑字符串:商户号，终端号，订单号，流水号，金额，全额支付标识，支付状态，密钥。
	 * 注意:不需要任何连接符号，直接附加。然后对获得的字符串进行MD5加密，最后将结果转换为小写。
	 * MD5(merchantNo + terminalNo + orderId + serialNo + amount + isFull +  payStatus + KEY).toLowerCase()。
	 * </pre>
	 * 
	 * @param feiLongYiChengPayEntity
	 *            the fei long yi cheng pay entity
	 * @return 根据feiLongYiChengPayEntity 生成ResultDecodedData值
	 * @author 金鑫
	 * @version 1.0 2010-11-4 下午04:26:27
	 */
	public static String getResultDecodedDataString(YiChengPayEntity feiLongYiChengPayEntity){
		StringBuilder md5_stringBuilder = new StringBuilder("");
		// 商户号
		md5_stringBuilder.append(feiLongYiChengPayEntity.getMerchantNo());
		// 终端号
		md5_stringBuilder.append(feiLongYiChengPayEntity.getTerminalNo());
		// 订单号
		md5_stringBuilder.append(feiLongYiChengPayEntity.getOrderId());
		// 流水号
		md5_stringBuilder.append(feiLongYiChengPayEntity.getSerialNo());
		// 金额
		md5_stringBuilder.append(feiLongYiChengPayEntity.getAmount());
		// 全额支付标识
		md5_stringBuilder.append(feiLongYiChengPayEntity.getIsFull());
		// 支付状态 payStatus
		md5_stringBuilder.append(feiLongYiChengPayEntity.getPayStatus());
		// 密钥
		md5_stringBuilder.append(feiLongYiChengPayEntity.getKey());
		return MD5Util.encode(md5_stringBuilder.toString()).toLowerCase();
	}

	/**
	 * 通过订单创建时间生成流水号.
	 * 
	 * @param createTime
	 *            创建时间
	 * @return 通过订单创建时间生成流水号
	 * @author 金鑫
	 * @version 1.0 2010-11-22 上午09:46:42
	 */
	public static String getSerialNo(Date createTime){
		return DateUtil.date2String(createTime, "HHmmss");
	}
}
