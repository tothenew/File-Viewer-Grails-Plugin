<g:if test="${filePath}">
	<div class="fileContents">${fileContents}</div>
	<div class="downloadLink">
		<a href="${createLink(controller:'file', action:'downloadFile', params:[filePath:filePath])}">Download complete file</a>
	</div>
</g:if>