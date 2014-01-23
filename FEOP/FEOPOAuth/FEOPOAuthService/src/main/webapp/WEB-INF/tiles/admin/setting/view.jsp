<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-sm-8 col-sm-offset-2">

    <h4>View Setting</h4>
    <hr>

    <div class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-12">
                <b class="btn-link">${setting.key }</b>
                <c:url value="/admin/settings/edit?id=${setting.id }" var="update"/>
                <a href="${update }" class="btn btn-link btn-sm pull-right">Edit</a>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Setting Key:</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" disabled value="${setting.key }">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Setting Value:</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" disabled value="${setting.value }">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Effect Start Date:</label>
            <div class="col-sm-9">
                <input type="datetime" class="form-control" disabled value="${setting.startDate }">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Effect End Date:</label>
            <div class="col-sm-9">
                <input type="datetime" class="form-control" disabled value="${setting.endDate }">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Diabled:</label>
            <div class="col-sm-9">
                <input type="checkbox" value="${setting.disabled }"/>
            </div>
        </div>
    </div>
</div>
</div>