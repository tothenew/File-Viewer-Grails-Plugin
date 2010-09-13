<ol>
	<g:each in="${locations}" var="location">
		<li>
			<a class="showReportLink" href="${createLink(action: 'showDetails', params: [filePath: location])}">${location}</a>
			<div class="children" style="padding:5px;"></div>
		</li>
	</g:each>
</ol>
<script type="text/javascript">
		jQuery(document).ready(function() {
		return bindOnClick();
	});
</script>