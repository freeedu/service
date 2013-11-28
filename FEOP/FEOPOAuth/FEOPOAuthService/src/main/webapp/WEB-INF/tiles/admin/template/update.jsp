<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class=" col-md-8 col-md-offset-2">
    <div class="row">
        <div>
            <h3>Edit Template</h3>
        </div>
        <hr>
    </div>
    <c:url value="/admin/et/update" var="update"/>
    <form action="${update }" method="post" class="form-horizontal" name="emailTemplateForm">

        <s:hasBindErrors htmlEscape="true" name="emailTemplateForm">
            <c:if test="${errors.errorCount gt 0}">
                <div class="form-group">
                    <div class="alert alert-danger">
                        <h4>Errors:</h4>
                        <font color="red"> <c:forEach items="${errors.allErrors}" var="error">
                            <s:message code="${error.code}" arguments="${error.arguments}"
                                       text="${error.defaultMessage}"/>
                            <br/>
                        </c:forEach>
                        </font>
                    </div>
                </div>
            </c:if>
        </s:hasBindErrors>

        <div class="form-group">
            <label for="id">Template Id:</label><input type="text" value="${template.id }" name="id"
                                                       class="form-control" disabled="disabled"/>
        </div>
        <div class="form-group">
            <label for="name">Name:</label><input type="text" value="${template.name }" name="name" class="form-control"
                                                  disabled="disabled"/>
        </div>
        <div class="form-group">
            <label for="subject">Subject:</label><input type="text" value="${template.subject }" name="subject"
                                                        disabled="disabled" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="version">Version:</label><input type="text" value="${template.version }" name="version"
                                                        class="form-control" disabled="disabled"/>
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea name="content" class="form-control" rows="6">${template.content }</textarea>
        </div>
        <div class="form-group">
            <input type="submit" value="Update Profile" class="btn btn-primary btn-sm"/>
        </div>
    </form>
</div>