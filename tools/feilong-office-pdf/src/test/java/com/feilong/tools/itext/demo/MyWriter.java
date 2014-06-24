/* 
 * Created on 2005-7-1 
 */
package com.feilong.tools.itext.demo;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Table;

/**
 * @author jcoder 
 */
public class MyWriter extends PDFWriter{

	public MyWriter(String path){
		super(path);
		try{
			header = new PDFParagragh("仪器设备调拨单", 1, 15);
			document.add(header);
			table = new Table(14);
			table.setBorderWidth(0);
			table.addCell(new PDFCell("（单价：500元以上含500元）", 1, 5));
			table.addCell(new PDFCell("2005年7月1号", 1, 9));
			document.add(table);
			table = new Table(14);
			table.setBorderWidth(1);
			table.addCell(new PDFCell("设备编号", 1, 2));
			table.addCell(new PDFCell("设备名称", 1, 3));
			table.addCell(new PDFCell("型号规格", 1, 2));
			table.addCell(new PDFCell("数量", 1, 1));
			table.addCell(new PDFCell("单价", 1, 1));
			table.addCell(new PDFCell("总价", 1, 1));
			table.addCell(new PDFCell("附件", 1, 2));
			table.addCell(new PDFCell("备注", 1, 2));
			table.endHeaders();//换行 
			table.addCell(new PDFCell("0126242245", 1, 2));
			table.addCell(new PDFCell("IBM大型机", 1, 3));
			table.addCell(new PDFCell("5465-445GH", 1, 2));
			table.addCell(new PDFCell("3", 1, 1));
			table.addCell(new PDFCell("299,000", 1, 1));
			table.addCell(new PDFCell("2,230,200", 1, 1));
			table.addCell(new PDFCell("无", 1, 2));
			table.addCell(new PDFCell("软件学院买入", 1, 2));
			table.endHeaders();
			table.addCell(new PDFCell("调出单位意见：", 1, 11));
			table.addCell(new PDFCell("院（系）签章", 1, 3));
			table.endHeaders();
			table.addCell(new PDFCell("申请调入单位意见：", 1, 11));
			table.addCell(new PDFCell("院（系）签章", 1, 3));
			table.endHeaders();
			table.addCell(new PDFCell("设备管理科审批：", 1, 5));
			table.addCell(new PDFCell("实验室与设备管理处审批", 1, 4));
			table.addCell(new PDFCell("校部审批：", 1, 5));
			table.endHeaders();
			document.add(table);
			close();//别忘记关闭 
		}catch (BadElementException e){
			e.printStackTrace();
		}catch (DocumentException e){
			e.printStackTrace();
		}
	}
}
