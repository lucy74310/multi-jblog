<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/menu.jsp"/>
		<form:form
			modelAttribute="userVo"
			class="join-form" 
			id="join-form" 
			method="post" 
			action="${pageContext.servletContext.contextPath }/user/join">
			
			<label class="block-label" for="name">이름</label>
			<form:input path="name"/>
			<p style="color:red; font-weight:bold;">
				<form:errors path ="name"/>
			</p>
			
			<label class="block-label" for="id">아이디</label>
			<form:input path="id"/>
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img 
				id="img-checkid" 
				style="display: none;" 
				src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="color:red; font-weight:bold;">
				<form:errors path ="id"/>
			</p>
			<form:hidden path="idCheck"/>
			<p style="color:red; font-weight:bold;">
				<form:errors path ="idCheck"/>
			</p>
			

			<label class="block-label" for="password">패스워드</label>
			<form:password path="password"/>
			<p style="color:red; font-weight:bold;">
				<form:errors path="password"/>
			</p>
			<fieldset>
				<legend>약관동의</legend>
				<form:checkbox path="agreeProv"/>
				<label for="agreeProv1" class="l-float" >서비스 약관에 동의합니다.
				</label>
			</fieldset>
			<p style="color:red; font-weight:bold;">
				<form:errors path="agreeProv"/>
			</p>			

			<input type="submit" value="가입하기">
		</form:form>
	</div>
	<script>
	$(function(){
		
		$('#id').change(function(){
			$('#btn-checkid').show();
			$('#img-checkid').hide();
		});
		
		console.log($('#agreeProv').val());
		if ($('#agreeProv').val() == "true") {
			console.log($('#agreeProv').val());
			$('#agreeProv').prop('checked', true)
		}
		
		$('#agreeProv').click(function(){
			$(this).val($(this).is(':checked'));
			console.log($('#agreeProv').val());	
		})
		
		$('#btn-checkid').click(function() {
			var id = $('#id').val();
			
			if( id == '') {
				return;
			}
			
			/* ajax 통신 */
			$.ajax({
				url : "${pageContext.servletContext.contextPath}/user/api/checkid?id="+id,
				type : "get",
				dataType : "json",
				data: "",
				success : function(res) {
					if(res.result != "success") {
						console.error(res);
						
						return;
					}
					
					if(res.data == true) {
						$('#idCheck').val('false');
						alert("이미 존재하는 아이디 입니다. \n다른 아이디를 사용해 주세요.");
						$('#id').focus();
						$('#id').val("");
						
						return;
					}
					
					$('#idCheck').val('true');
					$('#btn-checkid').hide();
					$('#img-checkid').show();
					
					console.log(res);
				},
				error : function(xhr, error) {
					console.error(xhr);
					console.error("error: " + error);
				}
			});
			
		});		
	})
	</script>
</body>
</html>
