<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>${title!''}</title>
<link rel="stylesheet" href="${themePath}/style.css" />
</head>
<body>
	<div id="main">
		<div id="header">
			<div class="left">
				<h1><a href="${ctxPath}/">${blog.title}</a></h1>
				<span id="subtitle">${blog.subtitle}</span>
			</div>
			<div class="right" id="sdiv">
				<form id="sform" action="${ctxPath}/search" method="get">
					<input id="sinput" type="text" name="s">
					<input id="sbtn" type="submit" value="搜索">
				</form>
			</div>
			<div class="clear"></div>
		</div>
		
		<div id="pagenav">
			<a href="${ctxPath}/">首页</a>
			<#list pages as page>
			<a href="${ctxPath}/page/${page.url}">${page.title}</a>
			</#list>
		</div>
		
		
		<div id="content" class="left">