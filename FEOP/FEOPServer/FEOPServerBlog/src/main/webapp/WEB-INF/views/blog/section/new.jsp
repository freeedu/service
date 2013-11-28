<div>
    <script type="text/javascript" src="<c:url value="
    /resources/plugins/tinymce/tinymce.min.js"/>"></script>
    <
    script
    type = "text/javascript" >
            tinymce
                    .init({
                        selector: 'textarea#section-content',
                        theme: 'modern',
                        plugins: [
                            "advlist autolink lists link image charmap print preview hr anchor pagebreak",
                            "searchreplace wordcount visualblocks visualchars code fullscreen",
                            "insertdatetime media nonbreaking save table contextmenu directionality",
                            "emoticons template paste textcolor autosave fupload" ],
                        toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
                        toolbar2: "preview media | forecolor backcolor emoticons | fupload"
                    });
    </script>
    <form id="update-section" class="editable" method="put">
        <h1>New Section</h1>
        <fieldset class="inputs">
            <label class="input-title" for="section-name">Section Name</label> <input type="text" name="section-name"
                                                                                      id="section-name"
                                                                                      placeholder="Section Name"> <label
                class="input-title" for="description">Description</label>
            <textarea class="description" name="description" rows="6"></textarea>
            <label class="input-title" for="content">Content</label>
            <textarea id="section-content" name="content" style="visibility: visible;"></textarea>
        </fieldset>
        <fieldset class="actions">
            <input type="submit" id="submit" value="Save">
        </fieldset>
    </form>
</div>