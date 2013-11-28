<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-md-8 col-md-offset-2">
    <div class="row">
        <div>
            <h3>
                Inviting Codes
                <small><a href='<c:url value="/admin/invite/generate"/>' class="btn btn-primary btn-sm pull-right">Generate
                    10 more
                    Codes</a></small>
            </h3>
        </div>
        <hr>
    </div>
    <div class=""></div>
    <c:forEach items="${invites}" var="invite">
        <div class="panel panel-info">
            <div class="panel-heading">
                <b><c:out value="${invite.inviteCode }"/></b> <a
                    href='<c:url value="/admin/invite/delete?id=${invite.id }"/>'
                    class="btn btn-link btn-sm pull-right">Delete</a>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-3">Invide Code:</div>
                    <div class="col-md-9">
                        <c:out value="${invite.inviteCode }"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">Used:</div>
                    <div class="col-md-9">
                        <c:out value="${invite.used }"/>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>