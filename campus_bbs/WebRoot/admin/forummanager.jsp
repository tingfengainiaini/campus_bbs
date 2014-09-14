<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<head>
	<title>
		论坛列表
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<%@ include file="/common/header2.jsp"%>

<html:errors />
<body>
	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<%
		Vector forumVector = (Vector) session
			.getAttribute(Constants.FORUM_LIST_KEY);
		Forum forum = null;
		if (forumVector != null) {
	%>

	<table width="80%" border="1" align="center">
		<tr align="center" bgcolor="#bbffaa">
			<td width="33%">
				<a href="forumprecreate.do">
					新增论坛
				</a>
			</td>
			<td width="34%">
				<A href="<%=request.getContextPath()%>/backtoadminindex.do">
					首页&nbsp;
				</A>
			</td>
			<td width="33%">
				<A href="adminlogoff.do">
					注销登录&nbsp;
				</A>
			</td>
		</tr>
		<tr align="center">
			<td colspan="3" bgcolor="#bbffaa">
			<b>
				论坛管理</b>
			</td>
		</tr>
		<tr align="center" bgcolor="#bbffaa">
			<td>
				论坛名
			</td>
			<td>
				编辑论坛
			</td>
			<td>
				删除论坛
			</td>
		</tr>
		<%
			for (int i = 0; i < forumVector.size(); i++) {
				forum = (Forum) forumVector.get(i);
		%>
		<tr align="center">
			<td>
			<A href="topiclist.do?forumid=<%=forum.getId()%>&forumname=<%=forum.getForumname()%>&forumcategory=<%=forum.getForumcategory()%></A>" target=_self>
				<%=forum.getForumname()%>
				</A>
			</td>
			<td>
				<a href="forumedit.do?forumid=<%=forum.getId()%>
				        &manager=<%=forum.getManager()%>
				        &forumcategory=<%=forum.getForumcategory()%>
						&forumname=<%=forum.getForumname()%>">
					编辑
				</a>
			</td>
			<td>
				<a href="forumdelete.do?forumid=<%=forum.getId()%>">
					删除
				</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>

	<%
		} else {
	%>
	无论坛分类信息!
	<%
		}
	%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
