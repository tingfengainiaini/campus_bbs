<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		帖子列表
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">
<body>
	<%@ include file="/common/header.jsp"%>

	<html:errors />
	
	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<%
		session.setAttribute(Constants.TALK_TYPE_KEY, "response");
		String username = (String) session.getAttribute("username");
		Vector responseVector = (Vector) session
			.getAttribute(Constants.RESPONSE_LIST_KEY);
		Response resp = null;
		if (responseVector != null) {
			String content;
			String grade;
	%>
	<table width="97%" border="1" align="center">
		<tr>
			<td colspan="2">
				<table width="97%" border="0" align="center">
					<tr>
						<td>
							欢迎你访问论坛：<%=username%>
						</td>
						<td align="right">
							<A href="<%=request.getContextPath()%>/backtoindex.do">
								首页
							</A>&nbsp;
							<A href="logoff.do">
								注销登录
							</A>&nbsp;
							<A href="newtopic.do">
								回复主题
							</A>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<%
			for (int i = 0; i < responseVector.size(); i++) {
				resp = (Response) responseVector.get(i);
				content = resp.getContent();
				if (content == null)
					content = "";
				grade = resp.getGrade();
				if (grade == null)
					grade = "";
		%>
		<tr>
			<td colspan="2">
				<table width="100%" border="0" bordercolor="#00FFCC">
					<tr bgcolor="#ff66CC">
						<td width="22%">
							作者：<A href="userlist.do?username=<%=resp.getAuthor()%>"><%=resp.getAuthor()%></A>
						</td>
						<td width="78%">
							标题：<%=resp.getTitle()%>
					  </td>
					</tr>
					<tr bgcolor="#CCCCCC">
						<td>
							等级：<%=grade%>
							<BR>
							发表时间:<%=resp.getSubmittime()%>
					  </td>
						<td>
							<%=content%>&nbsp;
					  </td>
				  </tr>
			  </table>
			</td>
		</tr>
		<%}
			%>
		<%} 
		%>
		<tr>
		<td>
		 <A href="javascript:history.go(-1);">
				返回上一页！
		     </A>
		</td>
		</tr>
</table>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
