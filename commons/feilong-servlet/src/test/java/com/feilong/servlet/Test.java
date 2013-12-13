package com.feilong.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yeeku.H.lee kongyeeku@163.com
 * @version 1.0 <br>
 *          Copyright (C), 2005-2008, yeeku.H.Lee <br>
 *          This program is protected by copyright laws. <br>
 *          Program Name: <br>
 *          Date:
 */
public class Test extends HttpServlet{

	private static final long	serialVersionUID	= 248447040735730839L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setAttribute("memberType", "文艺青年");
		request.getSession().setAttribute("memberType", "普通青年");
		request.getSession().getServletContext().setAttribute("memberType", "二货青年");
		response.sendRedirect(request.getContextPath() + "/member.jsp");
	}
}
