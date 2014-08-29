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
package com.feilong.tools.office.excel.loxia;

/**
 * The Interface TrainConstants.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月29日 上午11:03:18
 * @since 1.0.8
 */
public interface TrainConstants{

	/** trainSignUp. */
	String	templateInClassPath			= "\\loxia\\excel\\template\\trainSignUp.html";

	/** The template in class path_report. */
	String	templateInClassPath_report	= "\\loxia\\excel\\template\\trainReport.html";

	/** The template excel folder. */
	String	templateExcelFolder			= "E:\\Workspaces\\feilong\\feilong-platform\\tools\\feilong-office-excel\\src\\test\\resources\\loxia\\excel";

	/** The file name. */
	String	trainSignUpExcel			= templateExcelFolder + "\\java集合框架报名.xlsx";

	/** 文件文件夹. */
	String	templateFolder				= templateExcelFolder + "\\template";

	/** 签到表. */
	String	attendanceExcel				= templateFolder + "\\IT培训签到表.xlsx";

	//***************************************************

	/** The sheet. */
	String	trainSignUpSheet			= "trainSignUpSheet";

	/** The attendance sheet. */
	String	attendanceSheet				= "attendanceSheet";

	//********************************************************

	/** The configuration. */
	String	configuration				= "loxia/feilong-sheets.xml";

	/** The data name. */
	String	dataName					= "trainSignUplist";

	/** The sheet no. */
	int		sheetNo						= 0;

}
