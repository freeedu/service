<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                            q: extractLast(request.term)
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

<div class="panel panel-default">
    <select class="form-control" style="padding-left: 20px;">
        <option value="Internet Explorer" style="padding-left: 20px;">De</option>
        <option value="Firefox" style="padding-left: 20px;">De</option>
        <option value="Chrome" style="padding-left: 20px;">De</option>
        <option value="Opera" style="padding-left: 20px;">De</option>
        <option value="Safari" style="padding-left: 20px;">De</option>
        <optgroup label="test" class="close" style="padding-left: 20px;">
            <option value="Internet Explorer" style="padding-left: 20px;">De</option>
            <option value="Firefox">De</option>
            <option value="Chrome">De</option>
            <option value="Opera">De</option>
            <option value="Safari">De</option>
            <optgroup label="subtext" class="close">
                <option value="Internet Explorer">De</option>
                <option value="Firefox">De</option>
                <option value="Chrome">De</option>
                <option value="Opera">De</option>
                <option value="Safari">De</option>
            </optgroup>
        </optgroup>
        <optgroup label="test2" class="close">
            <option value="Internet Explorer">De</option>
            <option value="Firefox">De</option>
            <option value="Chrome">De</option>
            <option value="Opera">De</option>
            <option value="Safari">De</option>
        </optgroup>
    </select>
</div>

<div class="panel panel-default">
    <div class="panel-body">
        <div class="page-header">
            <h3>
                Update Blog<span> <a class="btn btn-link btn-sm pull-right"
                                     href="<c:url value="/blog/view?id=${blog.id }"/>">View</a></span>
            </h3>
        </div>

        <c:if test="${blog != null }">
        <c:url value="/blog/view?id=${blog.id }" var="viewblog"/>

        <form role="form" class="form-horizontal" action='<c:url value="/my/blog/update"/>' method="post">
            <div class="form-group">
                <input class="form-control" name="id" type="hidden" value="${blog.id }">
            </div>
            <div class="form-group">
                <label for="blogTitle" class="col-lg-2 control-label">Title</label>

                <div class="col-lg-10">
                    <input id="blogTitle" class="form-control" name="blogTitle" type="text" placeholder="Title"
                           value="${blog.blogTitle }"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="blogSubtitle">Subtitle</label>

                <div class="col-lg-10">
                    <input id="blogSubtitle" class="form-control" type="text" name="blogSubtitle" placeholder="Subtitle"
                           value="${blog.blogSubtitle }">
                </div>
            </div>
            <fieldset disabled>
                <div class="form-group">
                    <label for="createDate" class="col-lg-2 control-label">Create Date</label>

                    <div class="col-lg-10">
                        <input id="createDate" class="form-control" type="datetime" name="createDate"
                               value="${blog.createDate  }">
                    </div>
                </div>
            </fieldset>
            <div class="form-group">
                <label for="category-value" class="col-lg-2 control-label">Category</label>

                <div class="col-lg-10">
                    <input name="category.id" id="category-value" type="hidden" value="${blog.category.id }">
                    <datalist
                            class="form-control" id="category"
                            onclick="retrieveCategories('${categoriesUrl}')" onchange="changeCategory()">
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label for="sery-value" class="col-lg-2 control-label">Sery</label>

                <div class="col-lg-10">
                    <input name="sery.id" id="sery-value" type="hidden" value="${blog.sery.id }"> <select
                        class="form-control" id="sery"
                        onclick="retrieveSeries('${catSeriesUrl}');" onchange="changeSery();">
                </select>
                </div>
            </div>

            <div class="form-group">
                <label for="cat-labels" class="col-lg-2 control-label">Labels</label>

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
                    <textarea id="blogDesc" class="form-control rich-text-editor" name="blogDesc" placeholder="Description"
                              rows="6">${blog.blogDesc }</textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" class="btn btn-primary" value="Update" onclick="onPostForm();">
                    <a class="btn btn-link btn-sm" href="#new-section" data-toggle="modal">Create
                    Section</a>
                </div>
            </div>
        </form>


        <c:if test="${not empty blog.sections }">
            <div class="panel-group" id="accordion">
                <c:forEach var="section" items="${blog.sections }">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#collapse${section.sequence }" class="accordion-toggle"
                                   data-toggle="collapse"
                                   data-parent="#accordion">${section.sectionTitle }</a>
                                <a href='<c:url value="/section/delete?id=${section.id }"></c:url>'
                                   class="btn btn-link btn-sm pull-right">Delete</a>
                            </h4>
                        </div>
                        <div id="collapse${section.sequence }" class="panel-collapse collapse"
                             style="height: 0px;">
                            <div class="panel-body">${section.sectionContent}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <div class="modal fade" id="new-section" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h3 class="modal-title">
                            Create Section
                        </h3>
                    </div>

                    <form role="form" class="form-horizontal" method="post"
                          action="<c:url value="/section/save"/>">
                        <div class="modal-body">
                            <div class="form-group">
                                <input class="form-control" name="blogId" type="hidden" value="${blog.id }">
                            </div>
                            <div class="form-group">
                                <label for="sectionTitle" class="col-lg-2 control-label">Section Title</label>

                                <div class="col-lg-10">
                                    <input id="sectionTitle" class="form-control" name="sectionTitle" type="text"
                                           placeholder="Section Title"
                                           autofocus
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label" for="sectionContent">Content</label>

                                <div class="col-lg-10">
                                    <textarea id="sectionContent" class="form-control rich-text-editor"
                                              name="sectionContent"
                                              placeholder="Subtitle"
                                              rows="18"></textarea>
                                </div>
                            </div>
                        </div>


                        <div class="modal-footer">
                            <input type="submit" class="btn btn-primary" value="Add Section"> <input
                                type="submit"
                                class="btn btn-default"
                                value="Save">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
    </c:if>
</div>