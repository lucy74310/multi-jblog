<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blog.title}</h1>
			<c:import url="/WEB-INF/views/includes/blog-navigation.jsp"/>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${fn:replace(postVo.contents, newline, "<br>") }
					<p>
				</div>
				<hr>
				<ul class="blog-list">
					<c:forEach items="${postList}" var="post" >
						<c:choose>
							<c:when test="${postVo.no == post.no }">
								<li class="selected"><a href="">${post.title }</a> <span>${post.regDate }</span></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/${blog.blogId}/${post.categoryNo}/${post.no}">${post.title }</a> <span>${post.regDate }</span>	</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blog.logo }">
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/blog-category.jsp"/>
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"/>
		
	</div>
</body>
</html>