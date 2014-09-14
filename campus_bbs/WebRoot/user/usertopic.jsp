<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<!-- !!OK!! -->
<head>
	<title>
		搜索版块主题结果列表
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<%@ include file="/common/header2.jsp"%>

<html:errors />
<body>
	<br>
	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<%
		Vector topicVector = (Vector) session.getAttribute(Constants.TOPIC_LIST_KEY);
		Topic topic = null;
	%>
	<table width="90%" border="1" align="center">
		<tr bgcolor="#00CCFF">
			<td width= "100%" colspan="3" align="right">
				<A href="<%=request.getContextPath()%>/backtoindex.do">
					返回首页
				</A>
				&nbsp;
				<A href="adminlogoff.do">
					注销登录
				</A>
				&nbsp;
			</td>
		</tr>
		
		
		<tr width= "100%" align="center">
			<td width= "100%" colspan="3" >
				搜索结果版块主题列表
			</td>
		</tr>
		
		
		<tr align="center" bgcolor="#00CCFF"  colspan="3">
			<td width="20%">
				文章标题
			</td>
			<td width="8%">
				作者
			</td>
			<td width="40%">
				内容
			</td>
	<!--  
			<td width="9%">
				加精
			</td>
			<td width="9%">
				删除
			</td>
			-->
		</tr>
		<%
			if (topicVector != null) {
				for (int i = 0; i < topicVector.size(); i++) {
					topic = (Topic) topicVector.get(i);
		%>
		<tr align="center">
			<td>	<A href="responselist.do?topicid=<%=topic.getId()%>">
				<%=topic.getTitle()%></A>&nbsp; <font color=red><%=topic.getLevel()%>
			</td>
			<td>
			<A href="userlist.do?username=<%=topic.getAuthor()%>">
				<%=topic.getAuthor()%>
			</td>
				<td>
				<%=topic.getContent()%>
			</td>
			<!-- <td>
				<a href="topicmanagelevel.do?topicid=<%=topic.getId()%>">
					*加精*
				</a>
			</td>
			<td>
				<a href="topicdelete.do?topicid=<%=topic.getId()%>">
					删除
				</a>
			</td> -->
			
		</tr>
		<%
				}
			}

		%>
		<!-- <tr align="center">
			<td colspan="3">
				<form method="post" action="topicsearch.do">
					<input type="text" name="title" size="25" maxlength="80">
					<input type="submit" name="Submit" value="查询">
					<input type="reset" name="Submit2" value="重置">
				</form>
			</td>
		</tr> -->
	</table>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>

