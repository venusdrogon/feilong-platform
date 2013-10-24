///**
// * Copyright (c) 2010 Jumbomart All Rights Reserved.
//
// *
// * This software is the confidential and proprietary information of Jumbomart.
// * You shall not disclose such Confidential Information and shall use it only in
// * accordance with the terms of the license agreement you entered into
// * with Jumbo.
// *
// * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
// * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
// * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
// * THIS SOFTWARE OR ITS DERIVATIVES.
// *
// */
//package com.jumbo.pepsitmall.manager.fashion;
//
//import java.util.Random;
//
//import loxia.support.cache.annotation.CacheEvict;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//@Transactional
//@Service("fashionInfoManager")
//public class FashionInfoManagerImpl implements FashionInfoManager{
//
//	protected static final Logger	log	= LoggerFactory.getLogger(ProductController.class);
//
//	@Override
//	@CacheEvict({
//			"FashionInfoManagerImpl.findFashionInfoList",
//			"FashionInfoManagerImpl.findFashionInfoListByType",
//			"FashionInfoManagerImpl.finFashInfoByID",
//			"FashionInfoManagerImpl.findFahionInfoListOne",
//			"FashionInfoManagerImpl.findFashionInfoListOutById" })
//	public void createFashionInfo(MultipartFile sImage,FashionInfo fashionInfo){
//		String fileName = System.currentTimeMillis() + new Random().nextInt(99) + "_fashion";
//		uploadImage(sImage, fileName, "_300x246");
//		fashionInfoDao.createFashionInfo(
//				fashionInfo.getTitle(),
//				fashionInfo.getContent(),
//				fashionInfo.getType(),
//				SiteManagerConstants.DOMAIN_IMAGE + getPath(sImage, fileName, "_300x246"),
//				fashionInfo.getLifeCycleStatus(),
//				fashionInfo.getSource(),
//				fashionInfo.getIntroduction());
//	}
//}
