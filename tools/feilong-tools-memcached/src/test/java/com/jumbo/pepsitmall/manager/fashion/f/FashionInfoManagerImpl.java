///**
// * Copyright (c) 2010 Jumbomart All Rights Reserved.
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
//package com.jumbo.pepsitmall.manager.fashion.f;
//
//import java.util.List;
//
//import loxia.support.cache.annotation.CacheParam;
//import loxia.support.cache.annotation.Cacheable;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//@Service("fashionInfoManager")
//public class FashionInfoManagerImpl implements FashionInfoManager{
//
//	@Transactional(readOnly = true)
//	@Cacheable
//	public List<FashionInfo> findFashionInfoList(){
//		return fashionInfoDao.findFashionInfoList();
//	}
//
//	@Transactional(readOnly = true)
//	@Cacheable
//	public Pagination<FashionInfo> findFashionInfoListByType(
//			@CacheParam("s") int start,
//			@CacheParam("z") int pageSize,
//			@CacheParam(value = "fashionType") int type){
//		return fashionInfoDao.findFashionInfoListByType(start, pageSize, type);
//	}
//}
