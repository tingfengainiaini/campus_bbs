<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="GBK"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<!-- �û���¼OK!! -->
<head>
	<title>
		�û���¼
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">
<body><br>
	<%@ include file="/common/header.jsp"%>

	<html:errors />

	<html:form action="userlogin.do" focus="password" method="post">
		<center>
			<table border="0" cellspacing="2" cellpadding="2" width="100%" bgcolor="#87ceff">
				<tr>
					<td width="40%" align="right">
						�û���
					</td>
					<td width="60%">
						<html:text property="username" />
					</td>
				</tr>
				<tr>
					<td align="right">
						����
					</td>
					<td width="60%"><html:password property="password" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<html:submit property="submit">                     
		      				��¼
		      			</html:submit>
					</td>
					<td align="left">
						<A href="userlogin.do?username=guest">
							
						</A>
						<A href="registration.do">
							
						</A>
					</td>
				</tr>
			</table>
		</center>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>