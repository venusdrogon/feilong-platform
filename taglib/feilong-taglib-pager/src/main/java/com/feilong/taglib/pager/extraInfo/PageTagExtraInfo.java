package com.feilong.taglib.pager.extraInfo;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

import com.feilong.taglib.pager.parser.PageTagExport;
import com.feilong.taglib.pager.parser.ParseException;
import com.feilong.taglib.pager.parser.TagExportParser;

public class PageTagExtraInfo extends TagExtraInfo{

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData){
		String export = tagData.getAttributeString("export");
		if (export != null){
			try{
				PageTagExport pageTagExport = TagExportParser.parsePageTagExport(export);
				int len = 0;
				if (pageTagExport.getPageUrl() != null){
					len++;
				}
				if (pageTagExport.getPageNumber() != null){
					len++;
				}
				if (pageTagExport.getFirstItem() != null){
					len++;
				}
				if (pageTagExport.getLastItem() != null){
					len++;
				}
				VariableInfo[] varinfo = new VariableInfo[len];
				int i = 0;
				String name;
				if ((name = pageTagExport.getPageUrl()) != null){
					varinfo[i++] = new VariableInfo(name, java.lang.String.class.getName(), true, VariableInfo.NESTED);
				}
				if ((name = pageTagExport.getPageNumber()) != null){
					varinfo[i++] = new VariableInfo(name, java.lang.Integer.class.getName(), true, VariableInfo.NESTED);
				}
				if ((name = pageTagExport.getFirstItem()) != null){
					varinfo[i++] = new VariableInfo(name, java.lang.Integer.class.getName(), true, VariableInfo.NESTED);
				}
				if ((name = pageTagExport.getLastItem()) != null){
					varinfo[i++] = new VariableInfo(name, java.lang.Integer.class.getName(), true, VariableInfo.NESTED);
				}
				return varinfo;
			}catch (ParseException ex){
				return new VariableInfo[0];
			}
		}
		return new VariableInfo[] {
				new VariableInfo(PageTagExport.PAGE_URL, java.lang.String.class.getName(), true, VariableInfo.NESTED),
				new VariableInfo(PageTagExport.PAGE_NUMBER, java.lang.Integer.class.getName(), true, VariableInfo.NESTED) };
	}

	@Override
	public boolean isValid(TagData tagData){
		String export = tagData.getAttributeString("export");
		if (export != null){
			try{
				TagExportParser.parsePageTagExport(export);
			}catch (ParseException ex){
				return false;
			}
		}
		return true;
	}
}
