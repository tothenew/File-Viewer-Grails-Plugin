<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="layout" content="none"/>
	<g:javascript library="jquery" plugin="jquery" />
	<title>File List</title>
</head>
<body>
<br/><strong>Please click on the links below to view detailed reports:</strong><br/><br/>
<div id="mainContainer">
	<div id="left-container" style="width:30%; float:left;">
		<ol>
			<g:each in="${locations}" var="location">
				<li>
					<a class="showReportLink" href="${createLink(action: 'showDetails', params: [filePath: location])}">${location}</a>
				</li>
			</g:each>
		</ol>
	</div>
	<div id="right-container" style="width:69%; float:right;border:1px black solid;padding:5px;">
		Clicking on the file in left will show the contents here

	</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".showReportLink").click(function() {
			var url = jQuery(this).attr('href');
			jQuery.get(url, {}, function(data) {
				jQuery("#right-container").html(data);
			});
			return false;
		});
	});
</script>
</body>
</html>