<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(vo.content, newLine, "<br>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/reply?no=${vo.no }&page=${page }">답글</a>
					<a href="${pageContext.servletContext.contextPath }/board/list?page=${page }&kwd=${kwd}">글목록</a>
					<c:if test="${vo.userVo.no == sessionScope.authUser.no }">
						<a href="${pageContext.servletContext.contextPath }/board/modify?no=${vo.no }&page=${page }&kwd=${kwd}">글수정</a>
					</c:if>
				</div>
				<form action="${pageContext.servletContext.contextPath }/comment?a=add" method="post">
				<table class="tbl-ex">
					<tr><th colspan=4>댓글</th></tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td class="label">내용</td>
					<td>
						<textarea id="content" name="content"></textarea>
					</td>
					</tr>
					<tr><td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td></tr>
				</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>