<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<!-- OK! -->
<head>
	<title>
		新增论坛
	</title>
		<%@ include file="/common/header2.jsp"%>

</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body>
    <%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<%  
     Vector forumcategoryVectory=(Vector) session.getAttribute(Constants.CATEGOTY_NEW_KEY);

     ForumCategory categorylist = null;
    %>

	<html:errors />
	<html:form method="post" action="/forumcreate.do">
		<table width="80%" border="1" align="center">
			<tr>
				<td align="center" bgcolor="#00CCFF" colspan="2">
					新增论坛
				</td>
			</tr>
			<tr>
				<td align="center">
					论坛名：
					<input type="text" name="forumname" size="20" maxlength="50">
					
				</td>
				
				<td>
				     <b>论坛类别：</b>
				
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
      <input type="submit" name="Submit" value="提交">
				</td>
				
			</tr>
		</table>

	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
