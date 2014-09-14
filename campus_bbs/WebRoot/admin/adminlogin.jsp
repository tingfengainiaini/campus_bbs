<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 管理员登录界面登陆成功之后跳转到adminindex页面 -->
<html:html>
<head>
	<title>
		管理员登录
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body><br>
	<%@ include file="/common/header2.jsp"%>

	<html:errors />

	<br><html:form action="adminlogin.do" focus="username">
		<center>
			<table border="0" cellspacing="2" cellpadding="2" width="100%" bgcolor="ffddff">
				<tr>
					<td width="40%" align="right">
					<b>	用户名：</b>
					</td>
					<td width="60%">
						<html:text property="username" />
					</td>
				</tr>
				<tr >
					<td align="right">
				<b>		密码：</b>
					</td>
					<td width="60%"><html:password property="password" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<html:submit property="submit">                     
		      	登录
		      </html:submit>
					</td>
					<td align="left">
						<html:reset>
					重置 
						</html:reset>
					</td>
				</tr>
			</table>
		</center>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
