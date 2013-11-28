<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-header"
">
<h3>New Comment</h3>
</div>

<c:if test="${newcomment != null }">
    <form method="post" role="form" class="form-horizontal" action="<c:url value="/comment/save"/>">
        <div class="form-group">
            <input class="form-control" name="blogId" type="hidden" value="${newcomment.blogId }"> <input
                class="form-control"
                name="blogSectionId" type="hidden" value="${newcomment.blogSectionId}"> <input class="form-control"
                                                                                               name="commentId"
                                                                                               type="hidden"
                                                                                               value="${newcomment.commentId}">
        </div>
        <div class="form-group">
            <label for="author" class="col-lg-2 control-label"><span>Name</span>(*)</label>

            <div class="col-lg-10">
                <input class="form-control" name="author" type="text" placeholder="Author" autofocus required>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-lg-2 control-label"><span>Email</span>(*)</label>

            <div class="col-lg-10">
                <input class="form-control" name="email" type="text" placeholder="Email" required>
            </div>
        </div>
        <div class="form-group">
            <label for="site" class="col-lg-2 control-label"><span>Name</span>(*)</label>

            <div class="col-lg-10">
                <input class="form-control" name="site" type="text" placeholder="Site (Optional)">
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-2 control-label" for="sectionContent">Content</label>

            <div class="col-lg-10">
                <textarea class="form-control" name="commentContent" placeholder="Comment Content" rows="10"></textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <input type="submit" class="btn btn-primary" value="Comment">
            </div>
        </div>
    </form>
</c:if>