tinymce.PluginManager.add('fupload', function(editor, url) {
    // Add a button that opens a window
    editor.addButton('fupload', {
        text: 'Upload File',
        icon: false,
        onclick: function() {
            // Open window
            editor.windowManager.open({
                title: 'Fupload plugin',
                body: [
                    {type: 'file', name: 'title', label: 'Title'}
                ],
                onsubmit: function(e) {
                    // Insert content when the window form is submitted
                    editor.insertContent('Title: ' + e.data.title);
                }
            });
        }
    });

    // Adds a menu item to the tools menu
    editor.addMenuItem('fupload', {
        text: 'Fupload plugin',
        context: 'tools',
        onclick: function() {
            // Open window with a specific url
            editor.windowManager.open({
                title: 'TinyMCE site',
                url: 'http://www.tinymce.com',
                width: 800,
                height: 600,
                buttons: [{
                    text: 'Close',
                    onclick: 'close'
                }]
            });
        }
    });
});