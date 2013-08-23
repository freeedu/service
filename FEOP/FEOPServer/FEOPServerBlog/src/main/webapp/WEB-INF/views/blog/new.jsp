<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="new-blog" class="editable" action='<c:url value="/blog/create"/>' method="post">
    <h1>New Blog</h1>
    <fieldset class="inputs">
    	<label class="input-title" for="blogTitle">Title</label>
      	<input id="blogtitle"  name="blogTitle" type="text" placeholder="Title" autofocus required>   
        <label class="input-title" for="blogSubtitle">Subtitle</label>
       <input id="subtitle" type="text" name="blogSubtitle" placeholder="Subtitle">
       <label class="input-title" for="categoryId">Category</label>
       	<select id="category" name="categoryId" >
        	<option value="" selected="selected">Choose...</option>
       		<option value="apple">Apple</option>
			<option value="orange">Orange</option>
       </select>
       
       <label class="input-title" for="tagIds">Labels</label>
       <div id="labels">
       		<input name="tagIds" hidden="true">
       		<label>Label1</label>
       </div>
       <input type="search">
       <label class="input-title" for="blogDesc">Description</label>
       <textarea id="description" name="blogDesc" placeholder="Description" rows="6"></textarea>
       
    </fieldset>
    <fieldset class="actions">
        <input type="submit" id="submit" value="Create">
    </fieldset>
</form>