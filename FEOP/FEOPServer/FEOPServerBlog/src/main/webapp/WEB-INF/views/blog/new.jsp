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

    function changeCategory() {
        $('#category-value').val($('#category').val());
        retrieveSeries('${catSeriesUrl}');
    }

    function changeSery() {
        $('#sery-value').val($('#sery').val());
    }
</script>

<div class="panel panel-default">
    <div class="panel-body">
        <div class="page-header">
            <h3>New Blog</h3>
        </div>
        <form role="form" class="form-horizontal" action='<c:url value="/my/blog/create"/>' method="post">
            <div class="form-group">
                <label for="blogTitle" class="col-lg-2 control-label">Title</label>

                <div class="col-lg-10">
                    <input class="form-control" name="blogTitle" type="text" placeholder="Title" autofocus required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="blogSubtitle">Subtitle</label>

                <div class="col-lg-10">
                    <input class="form-control" type="text" name="blogSubtitle" placeholder="Subtitle">
                </div>
            </div>
            <div class="form-group">
                <label for="category.id" class="col-lg-2 control-label">Category</label>

                <div class="col-lg-10">
                    <input name="category.id" id="category-value" type="hidden"> <select class="form-control"
                                                                                         id="category"
                                                                                         onchange="changeCategory()">
                </select>
                </div>
            </div>

            <div class="form-group">
                <label for="sery.id" class="col-lg-2 control-label">Sery</label>

                <div class="col-lg-10">
                    <input name="sery.id" id="sery-value" type="hidden"> <select class="form-control" id="sery"
                                                                                 onchange="changeSery();">
                </select>
                </div>
            </div>

            <div class="form-group">
                <label for="tagNames" class="col-lg-2 control-label">Labels</label>

                <div class="col-lg-10">
                    <input id="cat-labels" class="form-control" name="tagNames" placeholder="Create or Search tag"
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
                              rows="6"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" class="btn btn-primary" value="Create" onclick="onPostForm();">
                    <input type="submit" class="btn btn-default" value="Create & New Section">
                </div>
            </div>
        </form>
    </div>
</div>
