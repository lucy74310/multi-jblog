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
		      	<table class="admin-cat" id="category-table">
		      		<tr id="table-head">
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
		      		<tr class="row">
						<td class="no"></td>
						<td class="title"></td>
						<td class="count"></td>
						<td class="description"></td>
						<td class="delete"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
					</tr> 
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="cat-add-form">
      				<input type="hidden" name="blogId" value="${authUser.id }">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="title"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="button" id="cat-add-button" value="카테고리 추가" ></td>
			      		</tr>      		      		
			      	</table>
		      	</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"/>
	</div>
	
	<script>
		
		var empty = $('.row');
		var tableHead = $('#table-head').clone();
		var table = $('#category-table');
		
		$(function(){
			getList();
			$('#cat-add-button').click(function(){
				insertCategory();
			});
		});
		
		function listRender(data) {
			table.empty();
			tableHead.appendTo(table);
			var row ;
			console.log(data);
			data.forEach( function (ele) {
				row = empty.clone();
				row.children('.no').html(ele.no);
				row.children('.title').html(ele.title);
				row.children('.count').html(ele.postNum);
				row.children('.description').html(ele.description);
				row.children('.delete').on("click", function() {
					deleteCatetory(ele.no);
				});
				
				row.appendTo(table);
			}); 
		}
		
		function getList() {
			$.ajax({
				url: "${pageContext.request.contextPath}/${authUser.id}/admin/category/list", 
				type: "POST",
				dataType: "json",
				data: "",
				success : function(res) {
					listRender(res.data);
				},
				error : function(xhr, error) {
					console.error("error : " + error);
				}
			});
		}
		
		
		function insertCategory() {
			$.ajax({
				url: "${pageContext.request.contextPath}/${authUser.id}/admin/category/add", 
				type: "POST",
				dataType: "json",
				data: $('#cat-add-form').serialize(),
				success : function(res) {
					listRender(res.data);
				},
				error : function(xhr, error) {
					console.error("error : " + error);
				}
			});
		}
		
		function deleteCatetory(no) {
			console.log('deleteCategory');
			$.ajax({
				url: "${pageContext.request.contextPath}/${authUser.id}/admin/category/delete", 
				type: "POST",
				dataType: "json",
				data: {"no" : no ,"blogId" : "${authUser.id}" },
				success : function(res) {
					listRender(res.data);
				},
				error : function(xhr, error) {
					console.error("error : " + error);
				}
			});	
		}
		
	
	</script>
</body>
</html>