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
package com.feilong.taglib.display.httpconcat;

import java.util.ArrayList;
import java.util.List;

import com.feilong.taglib.display.httpconcat.command.HttpConcatParam;

/**
 * The Class BaseHttpConcatTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月24日 下午11:50:17
 * @since 1.0.7
 */
public abstract class BaseHttpConcatTest{

    /**
     * 获得 http concat param.
     *
     * @return the http concat param
     */
    protected HttpConcatParam getHttpConcatParam(){
        return getHttpConcatParamByI(null);
    }

    /**
     * 获得 http concat param by i.
     *
     * @param i
     *            the i
     * @return the http concat param by i
     */
    protected HttpConcatParam getHttpConcatParamByI(Integer i){
        HttpConcatParam httpConcatParam1 = new HttpConcatParam();
        httpConcatParam1.setType("js");
        //httpConcatParam.setDomain("http://www.feilong.com");
        httpConcatParam1.setRoot("/js" + i + "/");
        httpConcatParam1.setHttpConcatSupport(true);
        List<String> itemSrcList = new ArrayList<String>();
        itemSrcList.add("public/js/jquery-1.9.1.js");
        itemSrcList.add("public/js/common.js");

        itemSrcList.add("public/js/jquery.lazyload.min.js");
        itemSrcList.add("marketplace/js/item/searchItem.js");
        itemSrcList.add("public/js/jquery-ui-1.10.3.custom.js");
        itemSrcList.add("public/js/jquery-migrate-1.2.0.js");
        itemSrcList.add("public/js/loxia2/jquery.loxiacore-2.js");
        itemSrcList.add("public/js/loxia2/jquery.loxia.locale_${request.getAttribute('locale')}.js");
        itemSrcList.add("public/components/handlebars/js/handlebars-v1.1.2.js");
        itemSrcList.add("marketplace/js/marketplace.js");
        itemSrcList.add("public/js/ajax.extend.js");

        itemSrcList.add("public/js/jquery.jqzoom-core.js");
        itemSrcList.add("public/js/jquery.json-2.4.js");
        itemSrcList.add("marketplace/js/item/memberFavorite.js");
        itemSrcList.add("marketplace/js/buyNow/addShoppingCart.js");
        itemSrcList.add("marketplace/messages/messageDetail_in_ID.js");
        itemSrcList.add("marketplace/js/item/itemPDPDetail.js");
        itemSrcList.add("marketplace/js/item/itemPDPDetail_subData.js");
        itemSrcList.add("marketplace/js/item/itemPDPDetail_paging.js");
        itemSrcList.add("public/js/cascading/jquery.cascading.data.js");
        itemSrcList.add("public/js/cascading/jquery.cascading.js");
        itemSrcList.add("marketplace/js/item/youMayLikeItemForPDP.js");
        itemSrcList.add("livechat/js/livechat.js");
        itemSrcList.add("trade/js/tradeFeedbacks/rating.js");
        itemSrcList.add("member/js/dialogSignIn.js");
        itemSrcList.add("public/components/faceBook/faceBookInit.js");
        itemSrcList.add("member/messages/message_dialogSignIn_in_ID.js");

        httpConcatParam1.setItemSrcList(itemSrcList);
        httpConcatParam1.setVersion("20140517");
        return httpConcatParam1;
    }
}