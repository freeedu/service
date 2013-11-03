<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-responsive.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-theme.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery-ui-1.9.2.custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery-1.8.2.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 60px;
}
</style>

</head>
<body>
	<tiles:insertAttribute name="head" />
	<div class="container-wrapper">
		<div class="container">
			<div class="content-wrapper">
				<div class="content">
					<div class="row row-offcanvas row-offcanvas-right">
						<tiles:insertAttribute name="content" />
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