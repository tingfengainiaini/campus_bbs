<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- ����ҳ��  -->
<html:html>
<head>
	<title>
		��Ϣҳ��
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
		<!-- ��javascriptʵ�ַ���֮ǰҳ�棨��������������ܽ����Ķ�������  -->
			<a href="javascript:history.go(-1)">
				������һҳ
			</a>
		</h2>
	</center>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
