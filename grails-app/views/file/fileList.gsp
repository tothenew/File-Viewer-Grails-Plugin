<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="layout" content="none"/>
	<title><g:message code="default.page.title.label" default="File List" /></title>
</head>
<body>
<br/><strong>
	<g:message code="default.page.body.detail" default="Please click on the links below to view detailed reports:" />
</strong><br/><br/>
<g:if test="${showBackLink}">
	<div id="backLink">
		<a class="showReportLink" href="${createLink(action: 'index', params: [filePath: prevLocation])}">
			<g:message code="default.link.back.label" default="Back" />	
		</a>
	</div>
</g:if>
<div id="mainContainer">
	<div id="left-container" style="width:30%; float:left;">
		<g:render template="fileList" model="[locations:locations]" plugin='fileViewer'/>
	</div>
	<div id="right-container" style="width:69%; float:right;border:1px black solid;padding:5px;">
		<g:render template="/file/fileDetails" model="[fileContents: fileContents, filePath: filePath]" plugin='fileViewer'/>
	</div>
</div>
</body>
</html>