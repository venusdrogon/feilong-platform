<%@ page pageEncoding="UTF-8" contentType="image/jpeg; charset=UTF-8"%>

<jsp:directive.page import="java.awt.image.BufferedImage" />
<jsp:directive.page import="java.util.Random" />
<jsp:directive.page import="java.awt.Color" />
<jsp:directive.page import="java.awt.Font" />
<jsp:directive.page import="javax.imageio.ImageIO" />
<jsp:directive.page import="java.awt.Graphics2D" />
<jsp:directive.page import="java.awt.BasicStroke" />

<%@page import="com.feilong.webservice.cxf.FeiLongHTTP"%>
<%@page import="com.feilong.webservice.cxf.ValidateCodeUtilodeUtil"%>
<%@page icom.feilong.commons.core.FeiLongConstantsnstants"%>
<%@page icom.feilong.commons.core.awt.FeiLongFontUtilontUtil"%>
<%@page icom.feilong.commons.core.awt.FeiLongColorUtillorUtil"%>
<%@page ijava.io.IOExceptionception"%>
	//设置页面不缓存	
	FeiLongHTTP.setNoCache(response);
	//保存生成的汉字字符串
	String sRand = ValidateCodeUtil.generateValidateCode(4);
	//将认证码存入session
	session.setAttribute(FeiLongConstants.Session.validateCode, sRand);
	BufferedImage bufferedImage = ValidateCodeUtil.getBufferedImageAfterGraphics(sRand, 88, 25);
	writeImage(response, bufferedImage, out, pageContext);
 out, pageCpublic void writeImage(HttpServletResponse response,BufferedImage bufferedImage,JspWriter out,PageContext pageContext){
		//----------------------------------------------------------------------------------------
		//输出图象到页面
		try{
			ServletOutputStream servletOutputStream = response.getOutputStream();
			ImageIO.write(bufferedImage, "PNG", servletOutputStream);
			//----------------------------------------------------------------------------------------
			servletOutputStream.flush();
			servletOutputStream.close();
			servletOutputStream = null;
			response.flushBuffer();
			//----------------------------------------------------------------------------------------
			out.clear();
			out = pageContext.pushBody();
		}catch (IOException e){
			e.printStackTrace();
		}
	}tackTrace();
		}
	}%>


