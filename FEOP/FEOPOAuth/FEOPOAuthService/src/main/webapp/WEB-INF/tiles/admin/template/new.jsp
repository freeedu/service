<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-md-8 col-md-offset-2">

    <h4>New Template</h4>
    <hr>
    <c:url value="/admin/et/save" var="newAction"/>
    <form action="${newAction }" method="post" name="emailTemplateForm" class="form-horizontal">

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
            <label for="name" class="col-sm-3">Template Name</label>

            <div class="col-sm-9">
                <input id="name" name="name" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <label for="subject" class="col-sm-3">Subject</label>

            <div class="col-sm-9">
                <input id="subject" name="subject" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <label for="content" class="col-sm-3">Content</label>

            <div class="col-sm-9">
                <textarea id="content" name="content" class="form-control" required rows="6"></textarea>
            </div>
        </div>
        <div class="form-group" class="col-sm-3">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </div>
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <input type="submit" value="Create" class="btn btn-primary btn-sm"/>
                <input type="reset" value="Reset" class="btn btn-default btn-sm"/>
            </div>
        </div>
    </form>
</div>