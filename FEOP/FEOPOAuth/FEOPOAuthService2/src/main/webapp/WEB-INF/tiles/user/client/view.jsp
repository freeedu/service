<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="viewable widget setting">
	<h1>Your Application Information</h1>
	<div class="widget-content">
		<div class="info-box alert small-box">
			<div class="msg">Please remember the following information for using.</div>
		</div>

		<div class="line">
			<label class="left">Client Id: </label> <label class="right"><c:out value="${client.clientId }" /></label>
		</div>
		<div class="line">
			<label class="left">Secret: </label> <label class="right"><c:out value="${client.clientSecret }" /></label>
		</div>
	</div>
</div>