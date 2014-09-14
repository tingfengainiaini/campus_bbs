<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 出错页面  -->
<html:html>
<head>
	<title>
		消息页面
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body>&nbsp;  
  
	<%@ include file="/common/header.jsp"%>

	<center>
	<html:errors />
	
	<html:messages id="message" message="true" >
	<bean:write name="message"/>
	</html:messages>
	
		<h2>
		<!-- 用javascript实现返回之前页面（仅仅是浏览器就能解析的东西；）  -->
			<a href="javascript:history.go(-1)">
				返回上一页
			</a>
		</h2>
	</center>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
