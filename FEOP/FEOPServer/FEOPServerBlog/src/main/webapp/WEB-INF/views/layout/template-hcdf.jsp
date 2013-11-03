<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!Doctype html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<%-- <script type="text/javascript" src="<c:url value="/resources/js/default.js" />"></script> --%>
<%-- <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css" media="screen"> --%>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-responsive.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-theme.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery-ui-1.9.2.custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery-1.8.2.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.9.2.custom.js" />"></script>
<script src="<c:url value="/resources/js/default.js" />" type="text/javascript" lang="javascript"></script>

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom:60px;
}
</style>
<script type="text/javascript" src="<c:url value="/resources/plugins/tinymce/tinymce.min.js"/>"></script>
<script type="text/javascript">
	tinymce
			.init({
				selector : 'textarea.richeditor',
				theme : 'modern',
				plugins : [
						"advlist autolink lists link image charmap print preview hr anchor pagebreak",
						"searchreplace wordcount visualblocks visualchars code fullscreen",
						"insertdatetime media nonbreaking save table contextmenu directionality",
						"emoticons template paste textcolor autosave fupload" ],
				toolbar1 : "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
				toolbar2 : "preview media | forecolor backcolor emoticons | fupload"
			});
</script>
</head>
<body>
	<tiles:insertAttribute name="head" />
	<%-- <tiles:insertAttribute name="breadcrumb" /> --%>
	<div class="container">
		<div class="container-wrapper">
			<div class="content-wrapper">
				<div class="content">
					<div class="row row-offcanvas row-offcanvas-right">
						<div class="col-sm-9">
							<tiles:insertAttribute name="content-left" />
						</div>
						<div class="col-sm-3 sidebar-offcanvas">
							<div class="well sidebar-nav">
								<tiles:insertAttribute name="content-right" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="private-footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>

</body>
</html>