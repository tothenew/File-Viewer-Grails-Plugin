<g:if test="${filePath}">
	<div class="fileContents">${fileContents}</div>
	<div class="downloadLink">
		<a href="${createLink(controller:'file', action:'downloadFile', params:[filePath:filePath])}">
			<g:message code="default.link.download.label" default="Download complete file" />
		</a>
	</div>
</g:if>