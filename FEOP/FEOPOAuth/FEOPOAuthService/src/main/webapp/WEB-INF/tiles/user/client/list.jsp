<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="col-md-8 col-md-offset-2">
    <div class="row">
        <div>
            <h3>User Applications</h3>
        </div>

        <hr>
    </div>
    <c:choose>
        <c:when test="${not empty applications  }">
            <c:forEach items="${applications}" var="app">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <c:url value="/client/view/${app.clientId }" var="viewTarget"/>
                        <a href="${viewTarget }" class="btn btn-link btn-normal"><c:out value="${app.clientId }"/></a>
                        <a
                                href='<c:url value="/client/delete/${app.clientId }"/>'
                                class="pull-right btn btn-link btn-sm">Delete</a>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-3">Application Id:</div>
                            <div class="col-md-9">
                                <c:out value="${app.clientId }"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Client Secret:</div>
                            <div class="col-md-9">
                                <c:out value="${app.clientSecret }"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Authorized Grant Types:</div>
                            <div class="col-md-9">
                                <c:out value="${app.authorizedGrantTypes }"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Authorities:</div>
                            <div class="col-md-9">
                                <c:out value="${app.authorities }"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Resource Ids:</div>
                            <div class="col-md-9">
                                <c:out value="${app.resourceIds }"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Scope:</div>
                            <div class="col-md-9">
                                <c:out value="${app.scope }"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Redirect Uri:</div>
                            <div class="col-md-9">
                                <c:out value="${app.webServerRedirectUri }"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Additional Info:</div>
                            <div class="col-md-9">
                                <c:out value="${app.additionalInfo }"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning">
                You have no applications. <a href="<c:url value="/client/new"/>">Create</a> now.
            </div>
        </c:otherwise>
    </c:choose>
</div>