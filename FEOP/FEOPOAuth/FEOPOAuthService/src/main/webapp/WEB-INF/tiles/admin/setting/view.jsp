<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-md-8 col-md-offset-2">
    <div class="row">
        <div>
            <h3>View Setting</h3>
        </div>
        <hr>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <strong>${setting.key }</strong>
            <c:url value="/admin/settings/edit?id=${setting.id }" var="update"/>
            <a href="${update }" class="btn btn-link btn-sm pull-right">Edit</a>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3">Setting Key:</div>
                <div class="col-md-9">
                    <c:out value="${setting.key }"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">Setting Value:</div>
                <div class="col-md-9">
                    <c:out value="${setting.value }"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">Effect Start Date:</div>
                <div class="col-md-9">
                    <c:out value="${setting.startDate }"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">Effect End Date:</div>
                <div class="col-md-9">
                    <c:out value="${setting.endDate }"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">Diabled:</div>
                <div class="col-md-9">
                    <c:out value="${setting.disabled }"/>
                </div>
            </div>
        </div>
    </div>
</div>