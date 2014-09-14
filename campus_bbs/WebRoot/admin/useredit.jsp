<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		论坛主页
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body>
	<%@ include file="/common/header2.jsp"%>

	<html:errors />

	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<!--  还是先从session里面取出对象   --> 
	<%
		Vector allForumsVector = (Vector) session
			.getAttribute(Constants.ALLFORUMS_KEY);
		Forum forum = null;
    %>

	<form name=form1 method="post" action="edituser.do">
		<table width="80%" border="1" align="center">
			<tr align="right">
				<td colspan="3">
					<A href="<%=request.getContextPath()%>/backtoadminindex.do">
						首页&nbsp;
					</A>
					<A href="adminlogoff.do">
						注销登录&nbsp;
					</A>
				</td>
			</tr>
			<tr align="center" bgcolor="33ddff">
				<td colspan="3">
				<b>
					修改用户权限</b>
				</td>
			</tr>
			<tr  bgcolor="33dddd">
				<td width="26%">
					用户名：
					<%=new String(request.getParameter("username").getBytes("ISO-8859-1"),"utf-8")%>
					<INPUT type=hidden size=15 name=username value="<%=new String(request.getParameter("username").getBytes("ISO-8859-1"),"utf-8")%>">
				</td>
				</tr>
				<tr bgcolor="33dddd">
				<td width="37%">
					级别：&nbsp;&nbsp;&nbsp;
					<select size=1 name="grade">
						<option value="0">
							管理员
						</option>
						<option value="1">
							版主
						</option>
						<option value="2">
							普通用户
						</option>
					</select>
				</td>
				</tr>
				<tr bgcolor="33dddd">
				<td width="37%">
					指定论坛：
					<select size=1 name="forum">
						<%
							for (int i = 0; i < allForumsVector.size(); i++) {
								forum = (Forum) allForumsVector.get(i);
						%>
						<option value="<%=forum.getId()%>">
							<%=forum.getForumname()%>
						</option>
						<%
							}
						%>
					</select>
				</td> 
			</tr>
			<tr align="center" bgcolor="33ddff">
				<td colspan="3">
					<INPUT class=buttonface type=submit value=" 确 定 ">
					&nbsp;
					<INPUT class=buttonface type=reset value=" 重置 ">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>