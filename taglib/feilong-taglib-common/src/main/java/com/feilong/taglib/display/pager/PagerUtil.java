package com.feilong.taglib.display.pager;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.Pager;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.ParamUtil;
import com.feilong.tools.json.JsonUtil;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * 分页工具类<br>
 * 该类主要是将url相关数据转成vm需要的数据,解析成字符串返回.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-5-26 下午11:50:10
 */
public final class PagerUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(PagerUtil.class);

	/**
	 * 解析vm模板 生成分页html代码.
	 * 
	 * @param pagerParams
	 *            带有一系列分页需要的参数
	 * @return 生成分页html代码
	 */
	public static String getPagerContent(PagerParams pagerParams){

		int totalCount = pagerParams.getTotalCount();

		// 有数据,不是空
		// 如果=0 应该显示其他内容
		if (totalCount > 0){

			int currentPageNo = pagerParams.getCurrentPageNo();

			// 解决可能出现界面上 负数的情况
			if (currentPageNo < 1){
				currentPageNo = 1;
			}
			int pageSize = pagerParams.getPageSize();
			int maxIndexPages = pagerParams.getMaxIndexPages();
			String skin = pagerParams.getSkin();
			String pageUrl = pagerParams.getPageUrl();
			String pageParamName = pagerParams.getPageParamName();
			String vmPath = pagerParams.getVmPath();
			String charsetType = pagerParams.getCharsetType();
			boolean debugIsNotParseVM = pagerParams.getDebugIsNotParseVM();

			Pager pager = new Pager(currentPageNo, pageSize, totalCount);
			int allPageNo = pager.getAllPageNo();

			/** ******************************************************** */
			// 获得开始和结束的索引
			int[] startIteratorIndexAndEndIteratorIndexs = getStartIteratorIndexAndEndIteratorIndex(allPageNo, currentPageNo, maxIndexPages);
			// 开始迭代索引编号
			int startIteratorIndex = startIteratorIndexAndEndIteratorIndexs[0];
			// 结束迭代索引编号
			int endIteratorIndex = startIteratorIndexAndEndIteratorIndexs[1];

			int prePageNo = pager.getPrePageNo();
			int nextPageNo = pager.getNextPageNo();
			int firstPageNo = 1;
			int lastPageNo = allPageNo;

			// 所有需要生成url 的 index值
			Set<Integer> indexSet = new HashSet<Integer>();
			indexSet.add(prePageNo);
			indexSet.add(nextPageNo);
			indexSet.add(firstPageNo);
			indexSet.add(lastPageNo);
			for (int i = startIteratorIndex; i <= endIteratorIndex; ++i){
				indexSet.add(i);
			}
			// ****************************************************************************************
			// Map<Integer, String> map = new HashMap<Integer, String>();
			Map<Integer, String> map = getAllPageIndexHrefMap(pageUrl, pageParamName, indexSet, charsetType);
			// ****************************************************************************************
			PagerVMParam pagerVMParam = new PagerVMParam();
			pagerVMParam.setSkin(skin);// 皮肤

			pagerVMParam.setTotalCount(totalCount);// 总行数，总结果数
			pagerVMParam.setCurrentPageNo(currentPageNo);// 当前页
			pagerVMParam.setAllPageNo(allPageNo);// 当前页
			pagerVMParam.setStartIteratorIndex(startIteratorIndex);// 导航页码 开始号码
			pagerVMParam.setEndIteratorIndex(endIteratorIndex);// 导航页码 结束号码
			// ****************************************************************************************
			// 上一页链接
			pagerVMParam.setPreUrl(map.get(prePageNo));
			// 下一页链接
			pagerVMParam.setNextUrl(map.get(nextPageNo));
			// 第一页的链接
			pagerVMParam.setFirstUrl(map.get(firstPageNo));
			// 最后一页的链接
			pagerVMParam.setLastUrl(map.get(lastPageNo));

			LinkedHashMap<Integer, String> iteratorIndexMap = new LinkedHashMap<Integer, String>();
			for (int i = startIteratorIndex; i <= endIteratorIndex; ++i){
				iteratorIndexMap.put(i, map.get(i));
			}
			pagerVMParam.setIteratorIndexMap(iteratorIndexMap);

			Map<String, Object> vmParamMap = new HashMap<String, Object>();
			vmParamMap.put("pagerVMParam", pagerVMParam);
			if (log.isDebugEnabled()){
				log.debug("vmParamMap:{}", JsonUtil.format(vmParamMap));
				log.debug("debugIsNotParseVM:{}", debugIsNotParseVM);
			}

			if (!debugIsNotParseVM){
				String content = VelocityUtil.parseTemplateWithClasspathResourceLoader(vmPath, vmParamMap);
				return content;
			}
		}
		return "";
	}

	/**
	 * 获得所有页码的连接.
	 * 
	 * @param pageUrl
	 *            页面url
	 * @param pageParamName
	 *            分页参数名称
	 * @param indexSet
	 *            索引set
	 * @param charsetType
	 *            编码
	 * @return key是分页页码，value是解析之后的链接
	 */
	private static Map<Integer, String> getAllPageIndexHrefMap(String pageUrl,String pageParamName,Set<Integer> indexSet,String charsetType){
		URI uri = URIUtil.create(pageUrl, charsetType);

		if (null == uri){
			log.error("pageUrl:{} can not create to uri,charsetType:{}", pageUrl, charsetType);
			return null;
		}else{
			String url = uri.toString();
			String before = URIUtil.getBeforePath(url);

			// ***********************************************************************
			// getQuery() 返回此 URI 的已解码的查询组成部分。
			// getRawQuery() 返回此 URI 的原始查询组成部分。 URI 的查询组成部分（如果定义了）只包含合法的 URI 字符。
			String query = uri.getRawQuery();

			// ***********************************************************************
			Map<String, String[]> map = new LinkedHashMap<String, String[]>();
			// 传入的url不带参数的情况
			if (Validator.isNullOrEmpty(query)){
				// nothing to do
			}else{

				// 先返回 安全的 参数 ,避免循环里面 浪费性能
				Map<String, String[]> originalMap = URIUtil.parseQueryToArrayMap(query, charsetType);
				map.putAll(originalMap);
			}

			// ***********************************************************************F
			Map<Integer, String> returnMap = new HashMap<Integer, String>();
			for (Integer index : indexSet){

				// 构建一个数组，完全覆盖pageParamName
				map.put(pageParamName, new String[] { index + "" });

				// 循环里面不再加码,避免 浪费性能
				String encodedUrl = URIUtil.getEncodedUrlByArrayMap(before, map, null);
				returnMap.put(index, encodedUrl);
			}
			return returnMap;
		}
	}

	/**
	 * 获得当前分页数字,不带这个参数 或者转换异常 返回1.
	 * 
	 * @param request
	 *            当前请求
	 * @param pageParamName
	 *            the param name
	 * @return <ul>
	 *         <li>请求参数中,分页参数值 Integer 类型</li>
	 *         <li>如果参数中不带这个分页参数,或者转换异常 返回1</li>
	 *         </ul>
	 */
	public static Integer getCurrentPageNo(HttpServletRequest request,String pageParamName){
		// /s/s-t-b-f-a-cBlack-s-f-p-gHeat+Gear-e-i-o.htm?keyword=&pageNo=%uFF1B
		Integer currentPageNo = null;
		try{
			currentPageNo = ParamUtil.getParameterToInteger(request, pageParamName);
		}catch (Exception e){
			// 抛出异常, 但是不给 currentPageNo 赋值
			e.printStackTrace();
		}

		// 不是空 直接返回
		if (null != currentPageNo){
			return currentPageNo;
		}

		// 不带这个参数 或者转换异常 返回1
		return 1;
	}

	/**
	 * 获得开始和结束的索引.
	 * 
	 * @param allPageNo
	 *            总页数
	 * @param currentPageNo
	 *            当前页数
	 * @param maxIndexPages
	 *            the max index pages
	 * @return 获得开始和结束的索引
	 * @author 金鑫
	 * @version 1.0 2010-5-31 上午06:03:53
	 */
	private static int[] getStartIteratorIndexAndEndIteratorIndex(int allPageNo,int currentPageNo,int maxIndexPages){
		// 最多显示多少个导航页码
		maxIndexPages = getAutoMaxIndexPages(allPageNo, maxIndexPages);
		/**
		 * 开始迭代索引编号
		 */
		int startIteratorIndex = 1;
		/**
		 * 结束迭代索引编号
		 */
		int endIteratorIndex = allPageNo;
		// 总页数大于最大导航页数
		if (allPageNo > maxIndexPages){
			// 当前页导航两边总数和
			int fenTwo = (maxIndexPages - 1);
			// 当前页左侧导航数
			int leftCount = fenTwo / 2;
			// 当前页右侧导航数
			int rightCount = (fenTwo % 2 == 0) ? leftCount : (leftCount + 1);
			// 当前页<=(左边页数+1)
			if (currentPageNo <= (leftCount + 1)){
				startIteratorIndex = 1;
				// 此时迭代结束为maxIndexPages
				endIteratorIndex = maxIndexPages;
			}else{
				// 如果当前页+右边页>=总页数
				if (currentPageNo + rightCount >= allPageNo){
					startIteratorIndex = allPageNo - maxIndexPages + 1;
					// 此时迭代结束为allPageNo
					endIteratorIndex = allPageNo;
				}else{
					startIteratorIndex = currentPageNo - leftCount;
					endIteratorIndex = currentPageNo + rightCount;
				}
			}
		}
		return new int[] { startIteratorIndex, endIteratorIndex };
	}

	/**
	 * 获得分页最大显示的分页码<br>
	 * 如果maxIndexPages 是0或者null,那么根据allpageNo,采用自动调节长度功能<br>
	 * 因为,如果页码大于1000的时候, 如果还是10页码的显示(1001,1002,1003,1004,1005,1006,1007,1008,1009,1010) 显示上面会很长 ,会打乱页面布局 <br>
	 * <ul>
	 * <li>当大于1000的页码 显示6个 即 1001,1002,1003,1004,1005,1006 类似于这样的</li>
	 * <li>当大于100的页码 显示8个 即 101,102,103,104,105,106,107,108 类似于这样的</li>
	 * <li>其余,默认显示10条</li>
	 * </ul>
	 * .
	 * 
	 * @param allPageNo
	 *            分页总总页数,不解释地球人都知道
	 * @param maxIndexPages
	 *            表示手动指定一个固定的显示码<br>
	 *            如果不指定,或者是0 那么就采用自动调节的显示码
	 * @return 最大显示码
	 */
	private static int getAutoMaxIndexPages(int allPageNo,Integer maxIndexPages){
		if (Validator.isNullOrEmpty(maxIndexPages) || 0 == maxIndexPages){
			// 总页数超过1000的时候,自动调节导航数量的作用
			if (allPageNo > 1000){
				return 6;
			}else if (allPageNo > 100){
				return 8;
			}else{
				return 10;// 默认为10
			}
		}
		// 不是 0 或者null,直接返回指定的
		return maxIndexPages;
	}
}
