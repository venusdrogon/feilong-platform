/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.tools.office.excel;

import java.io.Serializable;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

/**
 * excel个性化配置.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 10, 2014 2:51:13 AM
 */
public class ExcelConfigEntity implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID             = 1L;

    /** 是否隔行变色 默认是true. */
    private boolean           isRowChangeColor             = true;

    /** 当字段带有特殊字符串的时候是否添加样式? 默认是false. */
    private boolean           isHasSpecialStringToAddStyle = false;

    /** 是否冻结窗口 默认是true. */
    private boolean           isFreezePane                 = true;

    /** 特殊字符串标识. */
    private String            specialString;

    /** 高亮. */
    private HSSFCellStyle     cellStyle_hightLight;

    /** 隔行变色. */
    private HSSFCellStyle     cellStyle_changeColorRow;

    /** 隔行且变色. */
    private HSSFCellStyle     cellStyle_changeColorRowAndHightLight;

    /**
     * Gets the 是否隔行变色 默认是true.
     * 
     * @return the isRowChangeColor
     */
    public boolean getIsRowChangeColor(){
        return isRowChangeColor;
    }

    /**
     * Sets the 是否隔行变色 默认是true.
     * 
     * @param isRowChangeColor
     *            the isRowChangeColor to set
     */
    public void setIsRowChangeColor(boolean isRowChangeColor){
        this.isRowChangeColor = isRowChangeColor;
    }

    /**
     * Gets the 当字段带有特殊字符串的时候是否添加样式? 默认是false.
     * 
     * @return the isHasSpecialStringToAddStyle
     */
    public boolean getIsHasSpecialStringToAddStyle(){
        return isHasSpecialStringToAddStyle;
    }

    /**
     * Sets the 当字段带有特殊字符串的时候是否添加样式? 默认是false.
     * 
     * @param isHasSpecialStringToAddStyle
     *            the isHasSpecialStringToAddStyle to set
     */
    public void setIsHasSpecialStringToAddStyle(boolean isHasSpecialStringToAddStyle){
        this.isHasSpecialStringToAddStyle = isHasSpecialStringToAddStyle;
    }

    /**
     * Gets the 是否冻结窗口 默认是true.
     * 
     * @return the isFreezePane
     */
    public boolean getIsFreezePane(){
        return isFreezePane;
    }

    /**
     * Sets the 是否冻结窗口 默认是true.
     * 
     * @param isFreezePane
     *            the isFreezePane to set
     */
    public void setIsFreezePane(boolean isFreezePane){
        this.isFreezePane = isFreezePane;
    }

    /**
     * Gets the 特殊字符串标识.
     * 
     * @return the specialString
     */
    public String getSpecialString(){
        return specialString;
    }

    /**
     * Sets the 特殊字符串标识.
     * 
     * @param specialString
     *            the specialString to set
     */
    public void setSpecialString(String specialString){
        this.specialString = specialString;
    }

    /**
     * Gets the 高亮.
     * 
     * @return the cellStyle_hightLight
     */
    public HSSFCellStyle getCellStyle_hightLight(){
        return cellStyle_hightLight;
    }

    /**
     * Sets the 高亮.
     * 
     * @param cellStyle_hightLight
     *            the cellStyle_hightLight to set
     */
    public void setCellStyle_hightLight(HSSFCellStyle cellStyle_hightLight){
        this.cellStyle_hightLight = cellStyle_hightLight;
    }

    /**
     * Gets the 隔行变色.
     * 
     * @return the cellStyle_changeColorRow
     */
    public HSSFCellStyle getCellStyle_changeColorRow(){
        return cellStyle_changeColorRow;
    }

    /**
     * Sets the 隔行变色.
     * 
     * @param cellStyle_changeColorRow
     *            the cellStyle_changeColorRow to set
     */
    public void setCellStyle_changeColorRow(HSSFCellStyle cellStyle_changeColorRow){
        this.cellStyle_changeColorRow = cellStyle_changeColorRow;
    }

    /**
     * Gets the 隔行且变色.
     * 
     * @return the cellStyle_changeColorRowAndHightLight
     */
    public HSSFCellStyle getCellStyle_changeColorRowAndHightLight(){
        return cellStyle_changeColorRowAndHightLight;
    }

    /**
     * Sets the 隔行且变色.
     * 
     * @param cellStyle_changeColorRowAndHightLight
     *            the cellStyle_changeColorRowAndHightLight to set
     */
    public void setCellStyle_changeColorRowAndHightLight(HSSFCellStyle cellStyle_changeColorRowAndHightLight){
        this.cellStyle_changeColorRowAndHightLight = cellStyle_changeColorRowAndHightLight;
    }

}
