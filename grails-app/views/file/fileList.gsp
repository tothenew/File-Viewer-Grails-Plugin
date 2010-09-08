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
		<g:render template="fileList" model="[locations:locations]" plugin='fileViewer'/>
	</div>
	<div id="right-container" style="width:69%; float:right;border:1px black solid;padding:5px;">
		Clicking on the file in left will show the contents here

	</div>
</div>
<script type="text/javascript">
	function bindOnClick() {
		jQuery(".showReportLink").click(function() {
			var url = jQuery(this).attr('href');
			var element = jQuery(this);
			jQuery.get(url, {}, function(data) {
				if (data.message) {
					if (data.isFile) {
						jQuery("#right-container").html(data.message);
					} else {
						jQuery(element).parent().find('div.children').html(data.message);
					}
				}
			});
			return false;
		});
	}
	jQuery(document).ready(function() {
		return bindOnClick();
	});
</script>
</body>
</html>