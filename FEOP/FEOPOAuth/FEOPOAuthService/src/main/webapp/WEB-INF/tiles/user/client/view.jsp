<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="col-md-8 col-md-offset-2">

    <div class="row">
        <div>
            <h3>Your Application Information</h3>
        </div>

        <hr>
    </div>
    <div class="row">
        <div class="alert alert-info">Please remember the following information for using.</div>

        <div class="col-md-3">Client Id:</div>
        <div class="col-md-9">
            <c:out value="${client.clientId }"/>
        </div>
        <div class="col-md-3">Secret:</div>
        <div class="col-md-9">
            <c:out value="${client.clientSecret }"/>
        </div>
    </div>
</div>