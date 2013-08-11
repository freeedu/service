<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
		<div id="content">
			<tiles:insertAttribute name="content" />	
		</div>
		
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>