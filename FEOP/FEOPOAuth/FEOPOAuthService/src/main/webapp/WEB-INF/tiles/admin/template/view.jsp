<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class=" col-sm-8 col-sm-offset-2">

    <h4>View Template</h4>
    <hr>
    <c:if test="${template != null}"></c:if>
    <div class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-12">
                <b class="btn-link">${template.name }</b>

                <div class="pull-right">
                    <c:url value="/admin/et/edit?id=${template.id }" var="update"/>
                    <a href="${update }" class="btn btn-link btn-sm pull-right">Edit</a>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3">Template Id:</label>

            <div class="col-sm-9">
                <div class="form-control">
                    ${template.id } </div>
            </div>

        </div>
        <div class="form-group">
            <label class="col-sm-3">Name:</label>

            <div class="col-sm-9">
                <div class="form-control">
                    ${template.name } </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Subject:</label>

            <div class="col-sm-9">
                <div class="form-control">${template.subject }
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Version:</label>

            <div class="col-sm-9">
                <div class="form-control">
                    ${template.version }
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3">Content:</label>

            <div class="col-sm-9">
                <textarea rows="6" disabled="disabled" class="form-control">${template.content }</textarea>
            </div>

        </div>
    </div>
</div>