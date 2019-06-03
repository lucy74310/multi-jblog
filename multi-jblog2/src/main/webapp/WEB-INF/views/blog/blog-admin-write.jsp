<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog-admin-header.jsp"/>
			<c:import url="/WEB-INF/views/includes/blog-admin-navigation.jsp"/>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog-admin-menu.jsp"/>
				<form  id="wirte-from" method="post" >
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="categoryNo">
				      				<c:forEach items="${category }" var="cate" varStatus="status">
				      					<option value="${cate.no }">${cate.title }</option>
				      				</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="contents"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="button" id="write-button" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"/>
	</div>
	<script>
		$(function(){
			var form = $('#wirte-from');
			$('#write-button').click(function(){
				console.log('write button click');
				$.ajax({
					url : "${pageContext.request.contextPath}/${authUser.id}/admin/write",
					type : "POST",
					dataType : "text",
					data : form.serialize(),
					success : function(res) {
						$('input[name=title]').val("");
						$('textarea[name=contents]').val("");
						if(res=="success") {
							alert("글이 등록되었습니다.");
						}
					},
					error : function(xhr, error) {
						console.error("error : " + error);
					}
					
				})
			});
			
			
		})
		
	</script>
</body>
</html>