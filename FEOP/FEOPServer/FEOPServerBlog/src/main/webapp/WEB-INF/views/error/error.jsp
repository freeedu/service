<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="margin: auto; width: 50%; padding-top: 100px;">
    <c:if test="${error != null}">
        <div>
            <h2>Error:</h2>
            <hr/>
        </div>
        <div>
            <label><c:out value="${error }"/></label>
        </div>
    </c:if>
</div>