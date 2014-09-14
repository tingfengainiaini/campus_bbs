<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<!--  主题列表 OK!!! -->
<head>
	<title>
		主题列表
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body>
      <%@ include file="/common/header.jsp"%>

	<html:errors />
	
	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<!-- 下面有一步为：设置讨论类型为：“topic” -->
	<%
		session.setAttribute(Constants.TALK_TYPE_KEY, "topic");
	
		int pageid = ((Integer) session
			.getAttribute(Constants.CUR_PAGEID_KEY)).intValue();
		int forumid = ((Integer) session
			.getAttribute(Constants.CUR_FORUMID_KEY)).intValue();
		String forumname = (String) session
			.getAttribute(Constants.CUR_FORUMNAME_KEY);
		String forumcategory=(String) session.getAttribute(Constants.CATEGORY_key);
		String username = (String) session.getAttribute("username");
		TopicDisp topicDisp = null;
		Vector topicVector = (Vector) session.getAttribute(Constants.TOPIC_LIST_KEY);
		if (topicVector != null) {
	%>
	<table width="94%" border="1" align="center">
		<tr align="left">
			<td colspan="5">
			<table width="100%" border="0" align="center" bgcolor="999900">
					<tr>
						<td>
						<font color="001188"><b>
							欢迎你访问论坛：</b></font>
							<%=username%>
						</td>
						<td align="right">
							<A href="<%=request.getContextPath()%>/backtoindex.do">
								首页
							</A>&nbsp;
							<A href="logoff.do">
								注销登录
							</A>&nbsp;
							<!-- 
							     表示对下面的地址不解不解的是和  responselist.jsp 里面的：
							<A href="newtopic.do">
								回复主题
							</A> 的区别
							-->
							<A href="<%=request.getContextPath()%>/newtopic.do">
								发新帖
							</A>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr align="center" bgcolor="#87ceff">
			<td colspan="5">
			 <A href="javascript:history.go(-1);">
				<%= forumcategory%>
		     </A>&nbsp;>>>
				<%=forumname%> 版
				<A href="leveltopiclist.do?forumid=<%=forumid %>&forumname=<%=forumname %>&forumcategory=<%=forumcategory %> ">本版精华帖</A>
			</td>
		</tr>
		<tr align="center">
			<td width="40%">
				帖子
			</td>
			<td width="12%">
				作者
			</td>
			<td width="15%">
				发表时间
			</td>
			<td width="12%">
				回帖数
			</td>
			<td width="15%">
				最后回复时间
			</td>
		</tr>
		<%for (int i = 0; i < topicVector.size(); i++) {
			topicDisp = (TopicDisp) topicVector.get(i);
		%>
		<tr align="center">
			<td>
				&nbsp;
				<A href="responselist.do?topicid=<%=topicDisp.getId()%>">
					<%=topicDisp.getTitle()%> 
				</A>&nbsp;<font color=green>
				<%=topicDisp.getLevel()%>
			</td>
			<td>
				&nbsp;
					<A href="userlist.do?username=<%=topicDisp.getAuthor()%>">
				<%=topicDisp.getAuthor()%>
				</A>
			</td>
			<td>
				&nbsp;
				<%=topicDisp.getSubmittime()%>
			</td>
			<td>
				&nbsp;
				<%=topicDisp.getReCount()%> 
			</td>
			<td>
				&nbsp;
				<%=topicDisp.getLastTalk()%>
			</td>
		</tr>
		<%}
		%>
		<tr align="right" bgcolor="#999900">
			<td colspan="5">
			 <A href="javascript:history.go(-1);">
				返回！
		     </A>
				<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=0&forumcategory=<%=forumcategory %>">
					首页
				</A>
				<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=pageid-1%>&forumcategory=<%=forumcategory %>">
					前页
				</A>
				<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=pageid+1%>&forumcategory=<%=forumcategory %>">
					后页
				</A>
			</td>
		</tr>
	</table>
	<%} else {
	%>
	<!-- 对不起，您无权访问本页面! -->暂时没有相关的帖子！！
	 <A href="javascript:history.go(-1);">
				返回列表！
		     </A>
	<%}
	%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
