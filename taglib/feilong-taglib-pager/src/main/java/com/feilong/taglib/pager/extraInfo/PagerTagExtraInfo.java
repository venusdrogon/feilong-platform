package com.feilong.taglib.pager.extraInfo;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

import com.feilong.taglib.pager.parser.PagerTagExport;
import com.feilong.taglib.pager.parser.ParseException;
import com.feilong.taglib.pager.parser.TagExportParser;

public final class PagerTagExtraInfo extends TagExtraInfo{

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData){
		String export = tagData.getAttributeString("export");
		if (export != null){
			try{
				PagerTagExport pagerTagExport = TagExportParser.parsePagerTagExport(export);
				int len = 0;
				if (pagerTagExport.getPageOffset() != null){
					len++;
				}
				if (pagerTagExport.getPageNumber() != null){
					len++;
				}
				VariableInfo[] varinfo = new VariableInfo[len];
				int i = 0;
				String name;
				if ((name = pagerTagExport.getPageOffset()) != null){
					varinfo[i++] = new VariableInfo(name, java.lang.Integer.class.getName(), true, VariableInfo.NESTED);
				}
				if ((name = pagerTagExport.getPageNumber()) != null){
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
				TagExportParser.parsePagerTagExport(export);
			}catch (ParseException ex){
				return false;
			}
		}
		return true;
	}
}
/* vim:set ts=4 sw=4: */
