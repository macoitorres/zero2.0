<%@page contentType="text/html;charset=ISO-8859-1" language="java"%>
<%@taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>

<title>Zero 2.0 - Upload Page</title>

</head>
<body>

	<s:form beanclass="com.zero.action.UploadActionBean" enctype="multipart-form/data">
	    <s:file name="file"/><s:submit name="uploadFile" value="Submit"></s:submit>
	    
	    <p>${actionBean.actionMsg}</p>
	</s:form>
	
	

</body>
</html>