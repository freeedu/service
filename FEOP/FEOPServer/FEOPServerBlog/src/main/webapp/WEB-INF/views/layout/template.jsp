<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css" media="screen">
</head>
<body>
	<div id="container">
		<tiles:insertAttribute name="head" />
		<tiles:insertAttribute name="breadcrumb" />
		<div id="content">
			<div id="content-left">
				<tiles:insertAttribute name="content-left" />
			</div>
			<div id="content-right">
				<tiles:insertAttribute name="content-right" />
			</div>
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>