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
package com.feilong.controller.chart.mp2.person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.chart.index.ChartIndex;
import com.feilong.tools.office.excel.ExcelParseUtil;

/**
 * The Class MP2PersonController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 12, 2014 4:01:33 AM
 */
@Controller
public class MP2PersonController{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(MP2PersonController.class);

	/** The file name. */
	private String				fileName		= "mp2 人员名单统计 2014-03-27.xlsx";

	/** The file full path. */
	private String				fileFullPath	= "E:\\DataCommon\\Files\\Mindmap\\xmind\\技术\\mp2\\" + fileName;

	/**
	 * M p2 person.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = { "/jsp/amchartsTest/MP2Person20140212" })
	public String MP2Person(Model model) throws IOException{

		// 获得用户信息列表
		List<Mp2PersonInfo> mp2PersonInfos = getMp2PersonInfoList();

		// *************************************************************************************

		// 基于title分组出来的数据
		Map<String, MP2PersonCountIndex> groupByTitleMap = new HashMap<String, MP2PersonCountIndex>();

		// 基于状态 把用户 撸出来
		// 在jsp页面中${numberMap[1]}将取不到值，因为el表达式中数字1是Long类型（好BT啊，都没加L啊），无法匹配到numberMap中的Integer 1 。
		Map<Long, List<Mp2PersonInfo>> groupByStatusMap = new HashMap<Long, List<Mp2PersonInfo>>();

		// ***********************循环*********************************************************
		for (Mp2PersonInfo mp2PersonInfo : mp2PersonInfos){

			String title = mp2PersonInfo.getTitle();
			Integer status = mp2PersonInfo.getPassportStatus();

			// ********************************************************
			MP2PersonCountIndex mp2PersonCountIndex = null;

			if (groupByTitleMap.containsKey(title)){
				mp2PersonCountIndex = groupByTitleMap.get(title);
			}else{
				mp2PersonCountIndex = new MP2PersonCountIndex();
			}

			// ********************************************************
			List<Mp2PersonInfo> statusList = null;
			if (groupByStatusMap.containsKey(Long.parseLong(status + ""))){
				statusList = groupByStatusMap.get(Long.parseLong(status + ""));
			}else{
				statusList = new ArrayList<Mp2PersonInfo>();
			}

			// ********************************************************

			switch (status) {
				case 1:
					mp2PersonCountIndex.setHasPassportCount(mp2PersonCountIndex.getHasPassportCount() + 1);
					break;
				case 2:
					mp2PersonCountIndex.setChunjieCount(mp2PersonCountIndex.getChunjieCount() + 1);
					break;
				case 3:
					mp2PersonCountIndex.setNoTongjiCount(mp2PersonCountIndex.getNoTongjiCount() + 1);
					break;
				case 4:
					mp2PersonCountIndex.setBanbuliaoCount(mp2PersonCountIndex.getBanbuliaoCount() + 1);
					break;
				case 5:
					mp2PersonCountIndex.setNoFeedBack(mp2PersonCountIndex.getNoFeedBack() + 1);
					break;
				case 6:
					mp2PersonCountIndex.setShanghaiban(mp2PersonCountIndex.getShanghaiban() + 1);
					break;
				case 7:
					mp2PersonCountIndex.setLizhiCount(mp2PersonCountIndex.getLizhiCount() + 1);
					break;
				default:
					break;
			}

			groupByTitleMap.put(title, mp2PersonCountIndex);

			statusList.add(mp2PersonInfo);
			groupByStatusMap.put(Long.parseLong(status + ""), statusList);

		}

		// *****************************************************************

		addModelAttribute(model, groupByStatusMap, groupByTitleMap);

		return "feilong.chart.amchartsTest.mp2.MP2Person20140212";
	}

	/**
	 * 设置属性.
	 * 
	 * @param model
	 *            the model
	 * @param groupByStatusMap
	 *            the group by status map
	 * @param groupByTitleMap
	 *            the group by title map
	 */
	private void addModelAttribute(
			Model model,
			Map<Long, List<Mp2PersonInfo>> groupByStatusMap,
			Map<String, MP2PersonCountIndex> groupByTitleMap){

		// ******************************按组-title***********************************************
		List<MP2PersonCountIndex> personCountIndexGroupByTitleList = new ArrayList<MP2PersonCountIndex>();
		for (Map.Entry<String, MP2PersonCountIndex> entry : groupByTitleMap.entrySet()){
			String key = entry.getKey();
			MP2PersonCountIndex value = entry.getValue();

			value.setGroupType(key);
			personCountIndexGroupByTitleList.add(value);
		}

		// /*********************************************************************************

		List<ChartIndex> passportStatusList = new ArrayList<ChartIndex>();

		for (PassportStatusEnum passportStatusEnum : PassportStatusEnum.values()){
			Integer value = passportStatusEnum.getValue();
			String name = passportStatusEnum.getName();

			List<Mp2PersonInfo> list = groupByStatusMap.get(Long.parseLong(value + ""));
			int size = 0;
			if (Validator.isNotNullOrEmpty(list)){
				size = list.size();
			}
			String color = passportStatusEnum.getColor();
			passportStatusList.add(new ChartIndex(name, name, size, color));
		}
		// *****************************************************************
		model.addAttribute("passportStatusList", JsonUtil.format(passportStatusList));
		model.addAttribute("groupByStatusMap", groupByStatusMap);
		model.addAttribute("personCountIndexGroupByTitleList", JsonUtil.format(personCountIndexGroupByTitleList));
	}

	/**
	 * Gets the mp2 person info list.
	 * 
	 * @return the mp2 person info list
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Mp2PersonInfo> getMp2PersonInfoList() throws FileNotFoundException,IOException{
		// *************************************

		InputStream inputStream = new FileInputStream(fileFullPath);

		// 2013
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Map<String, Object> map = ExcelParseUtil.getSheetMapForLog(sheet);
		log.info(JsonUtil.format(map));

		List<Mp2PersonInfo> list = new ArrayList<Mp2PersonInfo>();

		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < physicalNumberOfRows; ++i){
			Row row = sheet.getRow(i);

			Map<String, Object> rowMapLog = ExcelParseUtil.getRowMapForLog(row);
			log.info(JsonUtil.format(rowMapLog));

			Mp2PersonInfo personInfo = new Mp2PersonInfo();
			for (int j = 0; j < 9; ++j){
				Cell cell = row.getCell(j);

				Map<String, Object> cellMapLog = ExcelParseUtil.getCellMapForLog(cell);
				//log.info(JsonUtil.format(cellMapLog));
			}
			personInfo.setName(ExcelParseUtil.getCellValue(row.getCell(0)));
			personInfo.setTitle(ExcelParseUtil.getCellValue(row.getCell(1)));
			personInfo.setLevel(ExcelParseUtil.getCellValue(row.getCell(2)));
			personInfo.setEntryTime(ExcelParseUtil.getCellValue(row.getCell(3)));
			personInfo.setJoinTime(ExcelParseUtil.getCellValue(row.getCell(4)));
			personInfo.setMemo(ExcelParseUtil.getCellValue(row.getCell(5)));
			personInfo.setPassportCase(ExcelParseUtil.getCellValue(row.getCell(6)));

			int status = Integer.parseInt(ExcelParseUtil.getCellValue(row.getCell(7)));
			personInfo.setPassportStatus(status);
			personInfo.setMark(ExcelParseUtil.getCellValue(row.getCell(8)));

			list.add(personInfo);
		}
		String personInfoList = JsonUtil.format(list);
		log.info(personInfoList);

		return list;
	}
}
