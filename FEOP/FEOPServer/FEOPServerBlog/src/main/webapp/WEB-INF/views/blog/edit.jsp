<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="categoriesUrl" value="/api/cat/top"/>
<c:url var="autocompleteUrl" value="/api/tag/list"/>
<c:url var="catSeriesUrl" value="/api/sery/list"/>
<script type="text/javascript">
    function split(val) {
        return val.split(/,\s*/);
    }

    function extractLast(term) {
        return split(term).pop();
    }
    $(document).ready(
            function () {
                $("#cat-labels").autocomplete({
                    delay: 500,
                    autoFocus: false,
                    source: function (request, response) {
                        $.getJSON("${autocompleteUrl}", {
                            q: extractLast(request.term),
                        }, response);
                    },
                    search: function () {
                        // custom minLength
                        var term = extractLast(this.value);
                        if (term.length < 2) {
                            return false;
                        }
                    },
                    select: function (event, ui) {
                        var terms = split(this.value);
                        // remove the current input
                        terms.pop();
                        // add the selected item
                        terms.push(ui.item.tagName);
                        // add placeholder to get the comma-and-space at the end
                        terms.push("");
                        this.value = terms.join(", ");
                        return false;
                    }
                }).data('ui-autocomplete')._renderItem = function (ul, item) {
                    return $('<li>').append('<a>' + item.tagName + "</a>")
                            .appendTo(ul);
                };
            });
    $(document).ready(function () {
        retrieveCategories("${categoriesUrl}");
    })
    $(document).ready(function () {
        retrieveSeries("${catSeriesUrl}");
    })
</script>

<div class="page-header"
">
<h3>
    Update Blog<span> <a class="btn btn-link btn-sm pull-right"
                         href="<c:url value="/blog/view?id=${blog.id }"/>">View</a></span>
</h3>
</div>

<c:if test="${blog != null }">
    <c:url value="/blog/view?id=${blog.id }" var="viewblog"/>

    <form role="form" class="form-horizontal well" action='<c:url value="/my/blog/create"/>' method="post">
        <div class="form-group">
            <input class="form-control" name="id" type="hidden" value="${blog.id }">
        </div>
        <div class="form-group">
            <label for="blogTitle" class="col-lg-2 control-label">Title</label>

            <div class="col-lg-10">
                <input class="form-control" name="blogTitle" type="text" placeholder="Title" value="${blog.blogTitle }"
                       required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 control-label" for="blogSubtitle">Subtitle</label>

            <div class="col-lg-10">
                <input class="form-control" type="text" name="blogSubtitle" placeholder="Subtitle"
                       value="${blog.blogSubtitle }">
            </div>
        </div>
        <fieldset disabled>
            <div class="form-group">
                <label for="createDate" class="col-lg-2 control-label">Create Date</label>

                <div class="col-lg-10">
                    <input class="form-control" type="datetime" name="createDate" value="${blog.createDate  }">
                </div>
            </div>
        </fieldset>
        <div class="form-group">
            <label for="category.id" class="col-lg-2 control-label">Category</label>

            <div class="col-lg-10">
                <input name="category.id" id="category-value" type="hidden" value="${blog.category.id }"> <select
                    class="form-control" id="category"
                    onclick="retrieveCategories('${categoriesUrl}')" onchange="changeCategory()">
            </select>
            </div>
        </div>

        <div class="form-group">
            <label for="sery.id" class="col-lg-2 control-label">Sery</label>

            <div class="col-lg-10">
                <input name="sery.id" id="sery-value" type="hidden" value="${blog.sery.id }"> <select
                    class="form-control" id="sery"
                    onclick="retrieveSeries('${catSeriesUrl}');" onchange="changeSery();">
            </select>
            </div>
        </div>

        <div class="form-group">
            <label for="tagNames" class="col-lg-2 control-label">Labels</label>

            <div class="col-lg-10">
                <input id="cat-labels" class="form-control" value="${blog.tagNames }" name="tagNames"
                       placeholder="Create or Search tag"
                       onclick="labelautocomplete('${autocompleteUrl}');">

                <div class="dropdown">
                    <ul class="dropdown-menu" id="ui-autocomplete" role="menu"></ul>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="blogDesc" class="col-lg-2 control-label">Description</label>

            <div class="col-lg-10">
                <textarea class="form-control richeditor" name="blogDesc" placeholder="Description"
                          rows="6">${blog.blogDesc }</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <input type="submit" class="btn btn-primary" value="Update"> <a class="btn btn-link btn-sm"
                                                                                href="#new-section">Create Section</a>
            </div>
        </div>
    </form>


    <c:if test="${not empty blog.sections }">
        <div class="panel-group" id="accordion">
            <c:forEach var="section" items="${blog.sections }">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapse${section.sequence }" class="accordion-toggle" data-toggle="collapse"
                               data-parent="#accordion"> <c:out
                                    value="${section.sectionTitle }"/>
                            </a> <a href='<c:url value="/section/delete?id=${section.id }"></c:url>'
                                    class="btn btn-link btn-sm pull-right">Delete</a>
                        </h4>
                    </div>
                    <div id="collapse${section.sequence }" class="panel-collapse collapse" style="height: 0px;">
                        <div class="panel-body">${section.sectionContent}</div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <br>

    <div id="new-section">
        <form role="form" class="form-horizontal well" method="post" action="<c:url value="/section/save"/>">
            <div class="form-group">
                <input class="form-control" name="blogId" type="hidden" value="${blog.id }">
            </div>
            <div class="form-group">
                <label for="sectionTitle" class="col-lg-2 control-label">Section Title</label>

                <div class="col-lg-10">
                    <input class="form-control" name="sectionTitle" type="text" placeholder="Section Title" autofocus
                           required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="sectionContent">Content</label>

                <div class="col-lg-10">
                    <textarea class="form-control richeditor" name="sectionContent" placeholder="Subtitle"
                              rows="18"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" class="btn btn-primary" value="Add Section"> <input type="submit"
                                                                                             class="btn btn-default"
                                                                                             value="Add & New">
                </div>
            </div>
        </form>
    </div>
</c:if>