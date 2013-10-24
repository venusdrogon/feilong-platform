package com.feilong.commons.office.excel;

import java.io.Serializable;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

/**
 * excel实体配置
 * 
 * @author 金鑫 2010-7-7 上午11:38:26
 */
public class FeiLongExcelEntity implements Serializable{

	private static final long	serialVersionUID				= 1L;

	/**
	 * 是否隔行变色 默认是true
	 */
	private boolean				isRowChangeColor				= true;

	/**
	 * 当字段带有特殊字符串的时候是否添加样式? 默认是false
	 */
	private boolean				isHasSpecialStringToAddStyle	= false;

	/**
	 * 是否冻结窗口 默认是true
	 */
	private boolean				isFreezePane					= true;

	/**
	 * 特殊字符串标识
	 */
	private String				specialString;

	/**
	 * 高亮
	 */
	private HSSFCellStyle		cellStyle_hightLight;

	/**
	 * 隔行变色
	 */
	private HSSFCellStyle		cellStyle_changeColorRow;

	/**
	 * 隔行且变色
	 */
	private HSSFCellStyle		cellStyle_changeColorRowAndHightLight;

	/**
	 * 
	 */
	public FeiLongExcelEntity(){
		super();
	}

	/**
	 * 是否隔行变色 默认是true
	 * 
	 * @return the isRowChangeColor
	 */
	public boolean isRowChangeColor(){
		return isRowChangeColor;
	}

	/**
	 * 是否隔行变色 默认是true
	 * 
	 * @param isRowChangeColor
	 *            the isRowChangeColor to set
	 */
	public void setRowChangeColor(boolean isRowChangeColor){
		this.isRowChangeColor = isRowChangeColor;
	}

	/**
	 * 是否冻结窗口 默认是true
	 * 
	 * @return the isFreezePane
	 */
	public boolean isFreezePane(){
		return isFreezePane;
	}

	/**
	 * 是否冻结窗口
	 * 
	 * @param isFreezePane
	 *            the isFreezePane to set
	 */
	public void setFreezePane(boolean isFreezePane){
		this.isFreezePane = isFreezePane;
	}

	/**
	 * 当字段带有特殊字符串的时候是否添加样式? 默认是false
	 * 
	 * @return the isHasSpecialStringToAddStyle
	 */
	public boolean isHasSpecialStringToAddStyle(){
		return isHasSpecialStringToAddStyle;
	}

	/**
	 * 当字段带有特殊字符串的时候是否添加样式? 默认是false
	 * 
	 * @param isHasSpecialStringToAddStyle
	 *            the isHasSpecialStringToAddStyle to set
	 */
	public void setHasSpecialStringToAddStyle(boolean isHasSpecialStringToAddStyle){
		this.isHasSpecialStringToAddStyle = isHasSpecialStringToAddStyle;
	}

	/**
	 * 特殊字符串标识
	 * 
	 * @return the specialString
	 */
	public String getSpecialString(){
		return specialString;
	}

	/**
	 * 特殊字符串标识
	 * 
	 * @param specialString
	 *            the specialString to set
	 */
	public void setSpecialString(String specialString){
		this.specialString = specialString;
	}

	/**
	 * @return the cellStyle_hightLight
	 */
	public HSSFCellStyle getCellStyle_hightLight(){
		return cellStyle_hightLight;
	}

	/**
	 * @param cellStyle_hightLight
	 *            the cellStyle_hightLight to set
	 */
	public void setCellStyle_hightLight(HSSFCellStyle cellStyle_hightLight){
		this.cellStyle_hightLight = cellStyle_hightLight;
	}

	/**
	 * @return the cellStyle_changeColorRow
	 */
	public HSSFCellStyle getCellStyle_changeColorRow(){
		return cellStyle_changeColorRow;
	}

	/**
	 * @param cellStyle_changeColorRow
	 *            the cellStyle_changeColorRow to set
	 */
	public void setCellStyle_changeColorRow(HSSFCellStyle cellStyle_changeColorRow){
		this.cellStyle_changeColorRow = cellStyle_changeColorRow;
	}

	/**
	 * @return the cellStyle_changeColorRowAndHightLight
	 */
	public HSSFCellStyle getCellStyle_changeColorRowAndHightLight(){
		return cellStyle_changeColorRowAndHightLight;
	}

	/**
	 * @param cellStyle_changeColorRowAndHightLight
	 *            the cellStyle_changeColorRowAndHightLight to set
	 */
	public void setCellStyle_changeColorRowAndHightLight(HSSFCellStyle cellStyle_changeColorRowAndHightLight){
		this.cellStyle_changeColorRowAndHightLight = cellStyle_changeColorRowAndHightLight;
	}
}
