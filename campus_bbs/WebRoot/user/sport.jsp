<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<!-- 用户登陆成功之后到此 -->
<head>
	<title>
		论坛--体育专区
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

  
  <body>
 <%@ include file="/common/header.jsp"%>

	<html:errors />

	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<% String username = (String) session.getAttribute("username");%>
	<table width="100%" border="1" align="center">
		<tr align="left">
			<td colspan="5">
				<table width="80%" border="0" align="left">
					<tr>
						<td><b><i>
						<font color="#22aa88">
							欢迎你访问论坛--体育专区：
							</font></i> </b>
						<%=username%>
						</td>
						<td align="right">
						<A href="<%=request.getContextPath()%>/backtoindex.do">
								首页
							</A>&nbsp;
							 >>>&nbsp;
							<A href="<%=request.getContextPath()%>/backtosport.do">
								体育专区
							</A>&nbsp;
							<A href="logoff.do">
								注销登录
							</A>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr align="center" bgcolor="#88dd99">
			<td colspan="5">
			<font color="#006688"><b>
				Neo_w BBS</b></font>
			</td>
		</tr>
		<tr align="center" bgcolor="#88dd99">
			<td width="19%">
			  分类:体育专区
			</td>
			<td width="10%">
				主题数
			</td>
			<td width="39%">
				论坛最新帖子
			</td>
			<td width="12%">
				版主
			</td>
			<td width="20%">
			       最后发表
			</td>
		</tr>
		<!-- 取出所有的论坛的版主  -->
		
		<%
			Vector forumVector = (Vector) session
				.getAttribute(Constants.FORUM_LIST_KEY);//总容器
				
		  //  Vector forumvector1 = (Vector)session.getAttribute(Constants.FORUM_LIST_KEY1);//study容器
		    Vector forumvector2 = (Vector)session.getAttribute(Constants.FORUM_LIST_KEY2);//体育容器
		 //   Vector forumvector3 = (Vector)session.getAttribute(Constants.FORUM_LIST_KEY3);//娱乐容器
			
			Forum forum = null;
			
			if (forumvector2 != null) {
				String manager;
	%>
		<%for (int i = 0; i < forumvector2.size(); i++) {
					forum = (Forum) forumvector2.get(i);
					manager = forum.getManager();
					if (manager == null)
						manager = "";
		%>
		<tr align="center">
			
			<td width="19%">
				&nbsp;
				<A href="topiclist.do?forumid=<%=forum.getId()%>&forumname=<%=forum.getForumname()%>&forumcategory=<%=forum.getForumcategory()%>" target=_self>
					<%=forum.getForumname()%>
				</A>
			</td>
		
			<td width="10%">
				&nbsp;
				<%=forum.getTopicNum()%>
			</td>
		
			<td width="39%">
				&nbsp;
				<%if (forum.getTopicNum() != 0) {%>
				<A href="responselist.do?topicid=<%=forum.getLastTopicId()%>">
					<%=forum.getLastTopicTitle()%>
				</A>
				
				
				<%
					}
				%>
			</td>
		
			<td width="12%">
				&nbsp;
					<A href="userlist.do?username=<%=manager%>">
				<%=manager%>
				</A>
			</td>
			
			<td width="20%">
			&nbsp;
			<%if(forum.getTopicNum() !=0) {%>
			   (
				<font color="#006688">
				<%=forum.getLastTopicAuthor()%>
				</font>
				)
				<FONT color="#006688">
					<A href="userlist.do?username=<%=forum.getLastTopicTime()%>">
					<%=forum.getLastTopicTime()%>
					</A>
				</FONT>
			</td>
			<%
			 }
			%>
		</tr>
		<%		}
			}
		%>
		</table>
			<%@ include file="/common/footer.jsp"%>
  </body>
</html:html>
