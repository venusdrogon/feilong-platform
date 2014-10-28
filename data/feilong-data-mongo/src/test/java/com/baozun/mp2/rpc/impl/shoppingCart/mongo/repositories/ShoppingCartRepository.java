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
package com.baozun.mp2.rpc.impl.shoppingCart.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.baozun.mp2.rpc.impl.shoppingCart.mongo.documents.ShoppingCart;

/**
 * The Interface ShoppingCartRepository.
 */
@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String>{

	/**
	 * 获取未登录购物车信息.
	 *
	 * @return the list< shopping cart>
	 */
	List<ShoppingCart> findByMemberIdNull();

	/**
	 * 获取某个用户购物车信息.
	 *
	 * @param memberId
	 *            the member id
	 * @return the list< shopping cart>
	 */
	List<ShoppingCart> findByMemberId(Long memberId);

	/**
	 * 获取某个购物车行信息.
	 *
	 * @param id
	 *            the id
	 * @return the shopping cart
	 */
	ShoppingCart findById(String id);

	/**
	 * Find by sku id and uid.
	 *
	 * @param skuId
	 *            the sku id
	 * @param uid
	 *            the uid
	 * @return the shopping cart
	 */
	ShoppingCart findBySkuIdAndUid(Long skuId,String uid);

	/**
	 * Find by sku id and member id.
	 *
	 * @param skuId
	 *            the sku id
	 * @param memberId
	 *            the member id
	 * @return the shopping cart
	 */
	ShoppingCart findBySkuIdAndMemberId(Long skuId,Long memberId);
}
