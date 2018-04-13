<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="user">

				<form id="join-form" name="modifyform" method="post" action="${pageContext.servletContext.contextPath }/user/modify">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${name }">
					
					<label class="block-label">  ${check == "fail" ? '이전 패스워드를 확인해주세요.' : '이전 패스워드' }</label>
					<input name="checkPw" type="password" value="">

					<label class="block-label">새로운 패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
					<c:choose>
					<c:when test='${gender == "female" }'>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked='checked'>
						<label>남</label> <input type="radio" name="gender" value="male">
					</c:when>
					<c:when test='${gender == "male" }'>
						<label>여</label> <input type="radio" name="gender" value="female">
						<label>남</label> <input type="radio" name="gender" value="male" checked='checked'>
					</c:when>
					</c:choose>
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>