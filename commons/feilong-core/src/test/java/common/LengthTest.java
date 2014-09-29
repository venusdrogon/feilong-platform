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
package common;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月28日 下午6:51:17
 * @since 1.0.8
 */
public class LengthTest{

	private static final Logger	log	= LoggerFactory.getLogger(LengthTest.class);

	/**
	 * Length.
	 */
	@Test
	public void length(){
		log.info("http://203.128.73.211/p/klikpayback/010000140002?s=30cbbe7a9bfcfc1131b018426e8560854bf507b3".length() + "");
		log.info("http://s.mp2.com/??static/public/js/jquery.jqzoom-core.js,static/public/js/jquery.json-2.4.js,static/marketplace/js/item/memberFavorite.js,static/marketplace/js/buyNow/addShoppingCart.js,static/marketplace/messages/messageDetail_en_US.js,static/marketplace/js/item/itemPDPDetail.js,static/marketplace/js/item/itemPDPDetail_subData.js,static/marketplace/js/item/itemPDPDetail_paging.js,static/public/js/cascading/jquery.cascading.data.js,static/public/js/cascading/jquery.cascading.js,static/marketplace/js/item/youMayLikeItemForPDP.js,static/livechat/js/livechat.js,static/trade/js/tradeFeedbacks/rating.js?16b3adc77c0d3e9df617d3178fe43445"
						.length() + "");
		log.info("Rm.109-118, Building H, No.1188 Wanrong Road, Shanghai 200436".length() + "");
		log.info("2F9AD12C10A4E1DCB1DAFA4177CFB7D71119E62033430ED5D90EEA70097E0F6B4FC61D15F6C150B110F328197B8828B7B485CCCED13EB58F1B445DB54FA033CDB9DF6AC21F1D8507"
						.length() + "");
		log.info("9F4FB63737EAFD60EEA0B8FBD546C9752A0D1621A348341F0B6D3AC2E6672EF3".length() + "");
		log.info("http://trade.mp2.com/payment/tcash/success/010005160007/673256aaed2e3f68fb3490b48f526a152143ede5".length() + "");
	}
}
