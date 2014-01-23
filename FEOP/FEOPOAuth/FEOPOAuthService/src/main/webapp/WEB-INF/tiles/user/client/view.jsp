<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="col-md-8 col-md-offset-2">
    <div class="form-horizontal">
        <h4>Your Application Information</h4>
        <hr>

        <div class="alert alert-info">Please remember the following information for using.</div>

        <div class="form-group">
            <label class="col-sm-3">Client Id:</label>

            <div class="col-sm-9">
                ${client.clientId }
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Secret:</label>

            <div class="col-sm-9">
                ${client.clientSecret }
            </div>
        </div>
    </div>
</div>
