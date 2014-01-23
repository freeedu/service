<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-md-8 col-md-offset-2">

    <h4>
        Inviting Codes
        <small><a href='<c:url value="/admin/invite/generate"/>' class="btn btn-primary btn-sm pull-right">Generate
            10 more
            Codes</a></small>
    </h4>
    <hr>
    <c:choose>
        <c:when test="${not empty invites  }">
            <table class="table table-hover table-responsive">
                <thead>
                <tr class="text-center">
                    <td>#</td>
                    <td>Invide Code</td>
                    <td>Used</td>
                    <td>Action</td>
                </tr>
                </thead>
                <c:forEach items="${invites}" var="invite">
                    <tr class="text-center">
                        <td><b><c:out value="${invite.id }"/></b></td>
                        <td><c:out value="${invite.inviteCode }"/></td>
                        <td><c:out value="${invite.used }"/></td>
                        <td><a href='<c:url value="/admin/invite/delete?id=${invite.id }"/>'
                               class="btn btn-link btn-sm ">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">
                There is no Inviting.
            </div>
        </c:otherwise>
    </c:choose>
</div>