
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${blogs }" var="blog">
<c:if test="${blog != null }">
	<c:url value="/blog/view?id=${blog.id }" var="viewblog" />

	<div class="blog">
		<div class="blog-title">
			<h1>
				<a href="${viewblog }"><c:out value="${blog.blogTitle }" /></a>
			</h1>
			<h2>
				<a href="${viewblog }">Sub title of the blog and can be empty if there is no sub title</a>
			</h2>
		</div>
		<div class="blog-details">
			<ul>
				<li><span><c:out value="${blog.createDate }" /></span></li>
				<li>By <a href="#"><span><c:out value="${blog.authorName }" /></span></a>
				</li>
				<c:if test="${blog.category != null}">
					<c:url value="/blog/list?c=${blog.category.id }" var="viewCategoryBlog" />
					<li><a href="${viewCategoryBlog }"><c:out value="${blog.category.categoryName }" /></a></li>
				</c:if>
				<li><a href="#"> 100 Commons </a></li>
			</ul>

			<div class="blog-tags">
				<label>Tags:</label>
				<ul>
					<li><a href="#" class="blog-label">Label1</a></li>
				</ul>
			</div>
		</div>
		<div class="blog-content">
			<c:out value="${blog.blogDesc }"/>
			<a href="" class="blog-more">Read More</a>
		</div>

	</div>
</c:if>
</c:forEach>



<div class="blog">
	<div class="blog-title">
		<h1>
			<a href="">Blog Title for the template using</a>
		</h1>
		<h2>
			<a href="">Sub title of the blog and can be empty if there is no sub title</a>
		</h2>
	</div>
	<div class="blog-details">
		<ul>
			<li><span>Jun 1st, 2013</span></li>
			<li>By <a href="#"><span>Mason Mei</span></a>
			</li>
			<li><a href="#">Category1</a></li>
			<li><a href="#"> 100 Commons </a></li>
		</ul>

		<div class="blog-tags">
			<label>Tags:</label>
			<ul>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
			</ul>
		</div>
	</div>
	<div class="blog-content">
		<p>A year ago, we wrote a short tutorial to show you how to send email using MessageUI framework. Some asked how to attach a photo, PDF
			document or Powerpoint in the email. Instead of replying every email individually, we think it’s better to write another short how-to
			tutorial.</p>
		<p>The MessageUI framework has made it really easy to send email in your apps. If you’ve read the official document of MessageUI
			framework, you know the MFMailComposeViewController class already provides a method called “addAttachmentData:” to add any types of files as
			an attachment. In this short tutorial, like other articles of our iOS Programming 101 series, we’ll write a simple app and demonstrate the
			usage of the method.</p>
		<p>
			<img src="http://www.appcoda.com/wp-content/uploads/2013/07/Email-Attachment-Featured.jpg" alt="Email Attachment Featured"
				style="width: NaN%;">
		</p>
		<a href="" class="blog-more">Read More</a>
	</div>

</div>


<div class="blog">
	<div class="blog-title">
		<h1>
			<a href="">Blog Title for the template using</a>
		</h1>
		<h2>
			<a href="">Sub title of the blog and can be empty if there is no sub title</a>
		</h2>
	</div>
	<div class="blog-details">
		<ul>
			<li><span>Jun 1st, 2013</span></li>
			<li>By <a href="#"><span>Mason Mei</span></a>
			</li>
			<li><a href="#">Category1</a></li>
			<li><a href="#"> 100 Commons </a></li>
		</ul>

		<div class="blog-tags">
			<label>Tags:</label>
			<ul>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
				<li><a href="#" class="blog-label">Label1</a></li>
				<li><a href="#" class="blog-label">Label2</a></li>
				<li><a href="#" class="blog-label">Label3</a></li>
			</ul>
		</div>
	</div>
	<div class="blog-content">
		<p>A year ago, we wrote a short tutorial to show you how to send email using MessageUI framework. Some asked how to attach a photo, PDF
			document or Powerpoint in the email. Instead of replying every email individually, we think it’s better to write another short how-to
			tutorial.</p>
		<p>The MessageUI framework has made it really easy to send email in your apps. If you’ve read the official document of MessageUI
			framework, you know the MFMailComposeViewController class already provides a method called “addAttachmentData:” to add any types of files as
			an attachment. In this short tutorial, like other articles of our iOS Programming 101 series, we’ll write a simple app and demonstrate the
			usage of the method.</p>
		<p>
			<img src="http://www.appcoda.com/wp-content/uploads/2013/07/Email-Attachment-Featured.jpg" alt="Email Attachment Featured"
				style="width: NaN%;">
		</p>
		<a href="" class="blog-more">Read More</a>
	</div>
</div>

