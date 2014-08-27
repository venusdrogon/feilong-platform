package com.feilong.struts2.action.converter;

import java.util.Map;
import java.util.StringTokenizer;

import ognl.DefaultTypeConverter;

import com.feilong.struts2.bean.User;

public class UserConvert extends DefaultTypeConverter{

	@Override
	public Object convertValue(Map context,Object value,Class toType){
		if (toType == User.class){
			String[] userInfo = (String[]) value;
			String userInfoString = userInfo[0];
			StringTokenizer stringTokenizer = new StringTokenizer(userInfoString, ";");
			String userName = stringTokenizer.nextToken();
			String password = stringTokenizer.nextToken();
			User user = new User();
			user.setPassword(password);
			user.setUserName(userName);
			return user;
		}
		return null;
	}
}
