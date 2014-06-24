package com.feilong.taglib.pager.extraInfo;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

import com.feilong.taglib.pager.parser.IndexTagExport;
import com.feilong.taglib.pager.parser.ParseException;
import com.feilong.taglib.pager.parser.TagExportParser;

@SuppressWarnings("all")public final class IndexTagExtraInfo extends TagExtraInfo{

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData){
		String export = tagData.getAttributeString("export");
		if (export != null){
			try{
				IndexTagExport indexTagExport = TagExportParser.parseIndexTagExport(export);
				int len = 0;
				if (indexTagExport.getItemCount() != null){
					len++;
				}
				if (indexTagExport.getPageCount() != null){
					len++;
				}
				VariableInfo[] varinfo = new VariableInfo[len];
				int i = 0;
				String name;
				if ((name = indexTagExport.getItemCount()) != null){
					varinfo[i++] = new VariableInfo(name, java.lang.Integer.class.getName(), true, VariableInfo.NESTED);
				}
				if ((name = indexTagExport.getPageCount()) != null){
					varinfo[i++] = new VariableInfo(name, java.lang.Integer.class.getName(), true, VariableInfo.NESTED);
				}
				return varinfo;
			}catch (ParseException ex){
				return new VariableInfo[0];
			}
		}
		return new VariableInfo[0];
	}

	@Override
	public boolean isValid(TagData tagData){
		String export = tagData.getAttributeString("export");
		if (export != null){
			try{
				TagExportParser.parseIndexTagExport(export);
			}catch (ParseException ex){
				return false;
			}
		}
		return true;
	}
}
/* vim:set ts=4 sw=4: */
