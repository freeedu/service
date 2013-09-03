<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-responsive.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-theme.css"/>" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-sm-12">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
		<hr>
	</div>
</body>
</html>