<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>城市天气信息</title>
	</head>
	<body>
		<jsp:include page="weather"></jsp:include>
		<form action="" method="post">
			<input type="text" id="city" name="city" />
			<input type="submit" value="查询" />
		</form>
		<%
			String info = (String) request.getAttribute("info");
			if (info.equals("#")){
		%>
		<h1>
			该城市暂不被支持天气预报服务
		</h1>
		<%
			}else{
				String[] infos = info.split("#");
		%>
		<%=info%>
		<table border="1">
			<tr>
				<td colspan="3">
					<strong><font color="blue">72小时天气预报</font>--- <font color="red"> <!-- 城市名称 --> <%=infos[0]%>&nbsp;&nbsp;&nbsp;<%=infos[1]%> </font> <font size="-1"> <%=infos[4]%>发布
					</font> </strong>
				</td>
			</tr>
			<tr align="center">
				<td>
					<table>
						<tr align="center">
							<!-- 日期 -->
							<td>
								<font size="-1"> <%=infos[6]%> </font>
							</td>
						</tr>
						<tr align="center">
							<!-- 天气图标 -->
							<td>
								<img src="tqimgs/a_<%=infos[8]%>" />
								<img src="tqimgs/a_<%=infos[9]%>" />
							</td>
						</tr>
						<tr align="center">
							<!-- 天气细则 -->
							<td>
								<font size="-1"> <%=infos[5]%><br> <%=infos[7]%><br> </font>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<table align="center">
						<tr align="center">
							<td>
								<!-- 第二天的时间 -->
								<font size="-1"> <%=infos[13]%> </font>
							</td>
						</tr>
						<tr align="center">
							<td>
								<!-- 第二天的天气图标 -->
								<img src="tqimgs/a_<%=infos[15]%>">
								<img src="tqimgs/a_<%=infos[16]%>">
							</td>
						</tr>
						<tr align="center">
							<td>
								<!-- 第二天的天气细则 -->
								<font size="-1"> <%=infos[12]%><br> <%=infos[14]%><br> </font>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<table align="center">
						<tr align="center">
							<td>
								<!-- 第三天的天气时间 -->
								<font size="-1"> <%=infos[18]%> </font>
							</td>
						</tr>
						<tr align="center">
							<td>
								<!-- 第三天的天气图标 -->
								<img src="tqimgs/a_<%=infos[20]%>" />
								<img src="tqimgs/a_<%=infos[21]%>" />
							</td>
						</tr>
						<tr align="center">
							<td>
								<!-- 第三天的天气细则 -->
								<font size="-1"> <%=infos[17]%><br> <%=infos[19]%><br> </font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<!-- 居家指数 -->
					<strong><font color="blue">温馨提醒</font> </strong>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table>
						<tr>
							<td>
								<img src="tqimgs/sun.gif" />
							</td>
							<td>
								<font size="-1"> <%=infos[10]%> </font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<%
						try{
								//这里开始进行具体详细测试
								String str = infos[11];
								//穿衣指数
								String s1 = str.substring(str.indexOf("穿衣指数："), str.indexOf("穿衣指数：") + 4);
								String s1Content = str.substring(str.indexOf("穿衣指数：") + 5, str.indexOf("感冒指数："));
								//感冒指数
								String s2 = str.substring(str.indexOf("感冒指数："), str.indexOf("感冒指数：") + 4);
								String s2Content = str.substring(str.indexOf("感冒指数：") + 5, str.indexOf("晨练指数："));
								//晨练指数
								String s3 = str.substring(str.indexOf("晨练指数："), str.indexOf("晨练指数：") + 4);
								String s3Content = str.substring(str.indexOf("晨练指数：") + 5, str.indexOf("交通指数："));
								//交通指数
								String s7 = str.substring(str.indexOf("交通指数："), str.indexOf("交通指数：") + 4);
								String s7Content = str.substring(str.indexOf("交通指数：") + 5, str.indexOf("中暑指数："));
								//中暑指数
								String s4 = str.substring(str.indexOf("中暑指数："), str.indexOf("中暑指数：") + 4);
								String s4Content = str.substring(str.indexOf("中暑指数：") + 5, str.indexOf("防晒指数："));
								//防晒指数
								String s5 = str.substring(str.indexOf("防晒指数："), str.indexOf("防晒指数：") + 4);
								String s5Content = str.substring(str.indexOf("防晒指数：") + 5, str.indexOf("旅行指数："));
								//旅行指数
								String s6 = str.substring(str.indexOf("旅行指数："), str.indexOf("旅行指数：") + 4);
								String s6Content = str.substring(str.indexOf("旅行指数：") + 5);
					%>
					<table>
						<tr>
							<td width="50">
								<img src="tqimgs/zhishu_01.gif" />
							</td>
							<td>
								<strong><%=infos[11]%> <font size="-1"> <%=s1%>&nbsp; </font> </strong>
							</td>
							<td>
								<font size="-1"> <%=s1Content%> </font>
							</td>
						</tr>
						<tr>
							<td width="50">
								<img src="tqimgs/zhishu_20.gif" />
							</td>
							<td>
								<strong> <font size="-1"> <%=s2%>&nbsp; </font> </strong>
							</td>
							<td>
								<font size="-1"> <%=s2Content%> </font>
							</td>
						</tr>
						<tr>
							<td width="50">
								<img src="tqimgs/zhishu_03.gif" />
							</td>
							<td>
								<strong> <font size="-1"> <%=s3%>&nbsp; </font> </strong>
							</td>
							<td>
								<font size="-1"> <%=s3Content%> </font>
							</td>
						</tr>
						<tr>
							<td width="50">
								<img src="tqimgs/zhishu_22.gif" />
							</td>
							<td>
								<strong> <font size="-1"> <%=s4%>&nbsp; </font> </strong>
							</td>
							<td>
								<font size="-1"> <%=s4Content%> </font>
							</td>
						</tr>
						<tr>
							<td width="50">
								<img src="tqimgs/zhishu_07.gif" />
							</td>
							<td>
								<strong> <font size="-1"> <%=s5%>&nbsp; </font> </strong>
							</td>
							<td>
								<font size="-1"> <%=s5Content%> </font>
							</td>
						</tr>
						<tr>
							<td width="50">
								<img src="tqimgs/zhishu_31.gif" />
							</td>
							<td>
								<strong> <font size="-1"> <%=s6%>&nbsp; </font> </strong>
							</td>
							<td>
								<font size="-1"> <%=s6Content%> </font>
							</td>
						</tr>
						<tr>
							<td width="50">
								<img src="tqimgs/zhishu_11.gif" />
							</td>
							<td>
								<strong> <font size="-1"> <%=s7%>&nbsp; </font> </strong>
							</td>
							<td>
								<font size="-1"> <%=s7Content%> </font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<%
			}catch (Exception e){
					e.printStackTrace();
				}
		%>
		<%
			}
		%>
	</body>
</html>
