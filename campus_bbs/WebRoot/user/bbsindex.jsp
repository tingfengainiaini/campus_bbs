<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<!-- 用户登陆成功之后到此 -->
<head>
	<title>
		论坛主页
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body>
   <%@ include file="/common/header.jsp"%>

	<html:errors />

	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<!-- 封装论坛信息 -->
	<% String username = (String) session.getAttribute("username");%>
	<table width="100%" border="1" align="center">
		<tr align="left">
			<td colspan="5">
				<table width="80%" border="0" align="left">
					<tr>
						<td><b><i>
						<font color="#22aa88">
							欢迎你访问论坛：
							</font></i> </b>
						<%=username%>
						</td>
						<td>
						<form method="post" action="usertopicsearch.do">
					版面搜索：<input type="text" name="title" size="25" maxlength="80">
					<input type="submit" name="Submit" value="Go">
		       	<!-- <input type="reset" name="Submit2" value="重置"> -->	
				</form>
						
						</td>
						
						<td align="right">
							<A href="logoff.do">
								注销登录
							</A>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr align="center" bgcolor="#32bbcc">
			<td colspan="5">
			<font color="#006688"><b>
				Neo_w BBS</b></font>
			</td>
		</tr>
		<tr align="center" bgcolor="#88dd99">
			<td width="19%">
				论坛/分类
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
		<tr bgcolor="#87ceff">
		   <td colspan="5">
			 
			<html:link styleClass="link" page="/study.do"> 
				<h4>学习交流区</h4> 
			</html:link> 
	          (提问题，解答问题） 
		  
		  </td>
		</tr>
		<%
			Vector forumVector = (Vector) session
				.getAttribute(Constants.FORUM_LIST_KEY);//总容器
				
		    Vector forumvector1 = (Vector)session.getAttribute(Constants.FORUM_LIST_KEY1);//study容器
		    Vector forumvector2 = (Vector)session.getAttribute(Constants.FORUM_LIST_KEY2);//体育容器
		    Vector forumvector3 = (Vector)session.getAttribute(Constants.FORUM_LIST_KEY3);//娱乐容器
			
			Forum forum = null;
			
			if (forumvector1 != null) {
				String manager;
	%>
		<%for (int i = 0; i < forumvector1.size(); i++) {
					forum = (Forum) forumvector1.get(i);
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
	    <tr bgcolor="#87ceff">
		   <td colspan="5">
		 <html:link styleClass="link" page="/sport.do">
				<h4>体育专区</h4>
			</html:link>（球类，田径，游泳，体操……）
		  </td>
		</tr>	
		
			<% 
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
				<%=manager%></A>
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


	<tr bgcolor="#87ceff">
		   <td colspan="5">
		      <html:link styleClass="link" page="/entertainment.do">
				<h4>娱乐专区</h4>
			</html:link>(放松心情，轻装上阵！)
		  </td>
		</tr>	
	<% 	
				if (forumvector3 != null) {
				String manager;
	%>
		<%for (int i = 0; i < forumvector3.size(); i++) {
					forum = (Forum) forumvector3.get(i);
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
				<%=manager%></A>
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
