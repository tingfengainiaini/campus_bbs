<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		用户管理
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body>
	<%@ include file="/common/header2.jsp"%>

	<html:errors />

	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<!-- 用vector来封装用户信息   --> 
	<%
		Vector userVector = (Vector) session
			.getAttribute(Constants.USER_LIST_KEY);
		User user = null;
		if (userVector != null) {
	%>
	<div align="center">
		<table width="80%" border="1" align="center">
			<tr align="right" bgcolor=white>
				<td colspan="6">
					<A href="<%=request.getContextPath()%>/backtoadminindex.do" target="text">
					<b>
						首页&nbsp;
						</b>
					</A>
					<A href="adminlogoff.do">
					<b>
						注销登录&nbsp;
						</b>
					</A>
				</td>
			</tr>
			<tr align="center" bgcolor="#0099CC">
				<td colspan="6">
				<b>
					用户管理</b>
				</td>
			</tr>
			<tr align="center" bgcolor="#0099CC">
				<td width="25%">
					<b>用户名</b>
				</td>
				<td width="25%">
					<b>级别</b>
				</td>
				<td width="25%">
					<b>编辑</b>
				</td>
				<td width="25%">
				<b>	删除</b>
				</td>
			</tr>
			<!-- 依次取出容器中的user对象 -->
			<%for (int i = 0; i < userVector.size(); i++) {
					user = (User) userVector.get(i);
			%>
			<tr align="center">
				<td>
				<A href="userlist.do?username=<%=user.getUsername()%>">
					<%=user.getUsername()%>
					</A>
				</td>
				<td>
					<%=user.getGrade()%>
				</td>
				<td>
					<a href="useredit.do?username=<%=user.getUsername()%>">
						编辑
					</a>
				</td>
				<td>
					<a href="userdelete.do?username=<%=user.getUsername()%>">
						删除
					</a>
				</td>
			</tr>
			<%}
			%>
			<tr bgcolor="#009980">
				<td colspan="4">
					<form method="post" action="searchuser.do">
						请输入您要搜索的用户名：
						<input type="text" name="username" size="18" maxlength="50">
						<input type="submit" name="Submit" value="提交">
					</form>
				</td>
			</tr>
		</table>
	</div>
		<%} else {
		%>
			无用户信息!
		<%}
		%>
		<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
