<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/jquery-ui-1.9.2.custom.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/summernote.css"/> " rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-ui-1.9.2.custom.js" />"></script>
    <script src="<c:url value="/resources/js/default.js" />"></script>
    <script src="<c:url value="/resources/js/summernote.js"/>"></script>

    <script type="text/javascript">
         $(document).ready(function(){
             $('.rich-text-editor').summernote({
                 height: 150
             });
         }) ;

         var onPostForm = function () {
             $('textarea.rich-text-editor').val($('.note-editor .note-editable').code());
         }
    </script>


    <%--<script type="text/javascript" src="<c:url value="/resources/plugins/tinymce/tinymce.min.js"/>"></script>--%>
    <%--<script type="text/javascript">--%>
        <%--tinymce--%>
                <%--.init({--%>
                    <%--selector: 'textarea.richeditor',--%>
                    <%--theme: 'modern',--%>
                    <%--plugins: [--%>
                        <%--"advlist autolink lists link image charmap print preview hr anchor pagebreak",--%>
                        <%--"searchreplace wordcount visualblocks visualchars code fullscreen",--%>
                        <%--"insertdatetime media nonbreaking save table contextmenu directionality",--%>
                        <%--"emoticons template paste textcolor autosave fupload" ],--%>
                    <%--toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",--%>
                    <%--toolbar2: "preview media | forecolor backcolor emoticons | fupload"--%>
                <%--});--%>
    <%--</script>--%>
</head>
<body>
<tiles:insertAttribute name="head"/>
<div class="container">
    <div class="col-sm-9">
        <tiles:insertAttribute name="content-left"/>
    </div>
    <div class="col-sm-3">
        <tiles:insertAttribute name="content-right"/>
    </div>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>