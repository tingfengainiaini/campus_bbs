<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- OK !! -->
<html:html>
<head>
	<title>
		发表文章（新话题）
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">
<body>
	<%@ include file="/common/header.jsp"%>

	<html:errors />
	<html:form action="submitarticle.do">
		<table width="73%" border="0" align="center" bgcolor="#87ceff">
			<tr align="center">
				<td>
				<!-- 应该是用缓存来实现的  -->
					<A href="javascript:history.go(-1);">
						返回主题
					</A>
				</td>
			</tr>
		</table>
		<table width="50%" border="1" align="center" bgcolor="#87ceff">
			<tr align="center">
				<td colspan="2">
					发表新话题
				</td>
			</tr>
			<tr>
				<td width="10%" align="center">
					标&nbsp题：
				</td>
				<td width="100%">
					<INPUT class=input2 maxLength=100 size=80 name=title>
				</td>
			</tr>
			<tr>
				<td height="285" align="center">
					内  容：
				</td>
				<td>
					<textarea class=input2 name=content rows=20 cols=80></textarea>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<html:submit property="submit">
						发表
					</html:submit>
					<html:reset>                     
						重写
	      			</html:reset>
				</td>
			</tr>
		</table>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
