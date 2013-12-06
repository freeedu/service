<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-md-8 col-md-offset-2">
    <div class="row">
        <div>
            <h3>New Template</h3>
        </div>
        <hr>
    </div>
    <c:url value="/admin/et/new" var="newAction"/>
    <form action="${newAction }" method="post" name="emailTemplateForm" class="editable client2">

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
            <label for="name">Template Name</label> <input id="name" name="name" class="form-control" required/>
        </div>
        <div class="form-group">
            <label for="subject">Subject</label> <input id="subject" name="subject" class="form-control" required/>
        </div>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea id="content" name="content" class="form-control" required rows="6"></textarea>
        </div>
        <div class="form-group">
            <input type="submit" value="Create" class="btn btn-primary btn-sm"/>
        </div>
    </form>
</div>