<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class=" col-md-8 col-md-offset-2">
    <h4>Edit Template</h4>
    <hr>
    <c:url value="/admin/et/update" var="update"/>
    <form action="${update }" method="post" class="form-horizontal" name="template">

        <s:hasBindErrors htmlEscape="true" name="emailTemplateForm">
            <c:if test="${errors.errorCount gt 0}">
                <div class="form-group">
                    <div class="alert alert-danger">
                        <h5>Errors:</h5>
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
            <input id="id" type="hidden" value="${template.id }" name="id"
                   class="form-control"/>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2">Name:</label>

            <div class="col-sm-10"><input id="name" type="text" value="${template.name }" name="name"
                                          class="form-control"
                                          disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label for="subject" class="col-sm-2">Subject:</label>

            <div class="col-sm-10">
                <input id="subject" type="text" value="${template.subject }" name="subject"
                       disabled="disabled" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="version" class="col-sm-2">Version:</label>

            <div class="col-sm-10"><input id="version" type="text" value="${template.version }" name="version"
                                          class="form-control" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label for="content" class="col-sm-2">Content:</label>

            <div class="col-sm-10"><textarea id="content" name="content" class="form-control"
                                             rows="6">${template.content }</textarea>
            </div>
        </div>
        <div class="form-group">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </div>
        <div class="form-group">
            <div class="col-sm-10 col-sm-offset-2">
                <input type="submit" value="Update Profile" class="btn btn-primary btn-sm"/>
            </div>
        </div>
    </form>
</div>