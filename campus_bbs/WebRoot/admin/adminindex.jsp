<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<!--  管理员登陆成功之后到此  !!! OK !!!-->
<head>
	<title>
		管理员页面
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body>
  <%@ include file="/common/header2.jsp"%>

	<div align="center">
		<h2>
			<html:link styleClass="link" page="/usermanager.do">
				用户管理
			</html:link>
		</h2>
		<h2>
			<html:link styleClass="link" page="/forummanager.do">
				论坛管理
			</html:link>
		</h2>
		<h2>
			<html:link styleClass="link" page="/topicmanager.do">
				主题管理
			</html:link>
		</h2>
		<h2>
			<html:link styleClass="link" page="/system.do">
			    系统帮助
			</html:link>
		</h2>
	</div>

	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
