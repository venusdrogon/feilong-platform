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
//package com.jumbo.pepsitmall.manager.pgp;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import loxia.support.cache.annotation.CacheParam;
//import loxia.support.cache.annotation.Cacheable;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @author min.zhang
// */
//@Service("shopManager")
//@Transactional
//public class ShopManagerImpl implements ShopManager{
//
//	@Cacheable
//	@Transactional(readOnly = true)
//	public Map<String, Object> findShopByShopCode(@CacheParam(value = "fsbscShopCode",ignore = false) String shopCode,@CacheParam(value = "cc") String cityCode){
//		Map<String, Object> m = new HashMap<String, Object>();
//		// 店铺
//		Shop shop = shopDao.findShopByShopCode(shopCode, cityCode);
//		if (shop == null){
//			return m;
//		}
//		// 店铺优惠 ?
//		List<Coupon> coupons = couponDao.findCouponListByShopCode(shopCode, cityCode);
//		// 店铺评价统计信息
//		ShopCommentStats shopCommentStats = shopCommentStatsRepository.findShopCommentStatsByShopCode(shopCode, cityCode);
//		// 地铁
//		List<Metro> metros = metroDao.findMetroListByShopCode(shopCode, cityCode);
//		// 商圈
//		BusinessArea businessArea = businessAreaDao.findBusinessAreaByShopCode(shopCode, cityCode);
//		// 菜系
//		Cuisine cuisine = cuisineDao.findCuisineByShopCode(shopCode, cityCode);
//		m.put("shop", shop);
//		m.put("coupons", coupons);
//		m.put("shopCommentStats", shopCommentStats);
//		m.put("businessArea", businessArea);
//		m.put("metros", metros);
//		m.put("cuisine", cuisine);
//		m.putAll(putShopImgToMap(shopCode));
//		return m;
//	}
//
//	@Cacheable(expire = 3600 * 24)
//	public List<ShopCommentStats> findShopTop5(@CacheParam(value = "cityCode") String cityCode){
//		return shopCommentStatsRepository.findShopCodeTop5ByCityCode(cityCode);
//	}
//}
