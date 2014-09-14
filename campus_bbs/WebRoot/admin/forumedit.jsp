<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<head>
	<title>
		编辑论坛
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<%@ include file="/common/header2.jsp"%>

<html:errors />
<body>
	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<%
		Vector managerUserVector = (Vector) session
			.getAttribute(Constants.MANAGER_CANDIDATE_KEY);
		Vector forumcategoryVectory=(Vector) session.getAttribute(Constants.CATEGOTY_NEW_KEY);
		String strForumid = (String) session
			.getAttribute(Constants.EDIT_FORUMID_KEY);
		String forumname2 = (String) session
			.getAttribute(Constants.EDIT_FORUMNAME_KEY);
		
	    String forumname=new String(forumname2.getBytes("ISO-8859-1"),"utf-8");
	    
		String forumcategory2=(String)session.getAttribute(Constants.CATEGORY_key);
		String forumcategory=new String(forumcategory2.getBytes("ISO-8859-1"),"utf-8");
		 
		String manager2=(String)session.getAttribute(Constants.MANAGER_KEY);
		 String manager=new String(manager2.getBytes("ISO-8859-1"),"utf-8");
	 //	String forumcategory
		User user = null;
		ForumCategory categorylist = null;
		if (managerUserVector != null) {
	%>
	<form name=form1 method="post" action="forumeditsubmit.do">
		<INPUT type=hidden name=forumid value="<%=strForumid%>">
		<table width="80%" border="1" align="center">
			<tr bgcolor="#ffdddd">
				<td align="right">
					<A href="<%=request.getContextPath()%>/backtoadminindex.do">
						首页
					</A>
					&nbsp;
					<A href="adminlogoff.do">
						注销登录
					</A>
					&nbsp;
				</td>
			</tr>
			<tr bgcolor="#ffdddd">
				<td align="center">
				<b>
					修改论坛信息</b>
				</td>
			</tr >
			
			
			
			<tr bgcolor="#ffdddd">
				<td align="left">
				<b>论坛名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</b>	
					<INPUT size=15 name=forumname value="<%=forumname%>">
					</td>
			</tr>
			<tr bgcolor="#ffdddd">
			<td>
<b>
论坛当前类别：
</b> 
<INPUT size=15 name=forumcategory1 value="<%=forumcategory%>">
</td>
</tr>
<tr bgcolor="#ffdddd">
<td>
<b>
新类别：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</b>

<select name=forumcategory size=1>
<option value=""></option>
<% 
  for(int i = 0; i< forumcategoryVectory.size(); i++)
  {
     categorylist=(ForumCategory) forumcategoryVectory.get(i);
  
 %>
  <option value="<%=categorylist.getCategoryname()%>">
  <%=categorylist.getCategoryname()%>
  </option>
  <%
   }
  %>
 
 
     </select>  
      </td>
  </tr>    
          <tr bgcolor="#ffdddd">
          <td>
           <b>
                                 当前版主：&nbsp;&nbsp;&nbsp;
          </b>  
        <INPUT size= 20 name=manager1 value="<%= manager%>">
				
				</td>
				</tr>
				<tr bgcolor="#ffdddd">
				<td>
					<b>	
					新增版主：&nbsp;&nbsp;&nbsp;&nbsp;
					</b>
						
						<select name=manager size=1>
							<option value=""></option>
							<%
								for (int i = 0; i < managerUserVector.size(); i++) {
									user = (User) managerUserVector.get(i);
							%>
							<option value="<%=user.getUsername()%>">
								<%=user.getUsername()%>
							</option>
							<%
								}
							%>
						</select>
						<%
								}
							%>
							</td>
							</tr>
							<tr>
							<td>
						<INPUT class=buttonface type=submit value=" 确 定 ">
						<INPUT class=buttonface type=reset value=" 清 除 ">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
