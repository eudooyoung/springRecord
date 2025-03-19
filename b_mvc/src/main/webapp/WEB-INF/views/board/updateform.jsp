<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	th {width: 200px;}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	
	<div class="outer outer-board-insert " style="display: flex; justify-content: center;">
	
			<form action="${ pageContext.servletContext.contextPath }/board/update" method="post">
			
			<br>
				<table id="listArea" class="table table-hover table-striped table-bordered">
			    <tr>
					<th>분야</th>
						<td style="text-align: left;">
						    <select name="category">
						        <option value="10" <c:if test="${b.categoryCode eq 10}">selected</c:if>>공통</option>
								<option value="20" <c:if test="${b.categoryCode eq 20}">selected</c:if>>운동</option>
								<option value="30" <c:if test="${b.categoryCode eq 30}">selected</c:if>>요리</option>
								<option value="70" <c:if test="${b.categoryCode eq 70}">selected</c:if>>기타</option>
						    </select>
						</td>
					</tr>
	  			  <tbody>
					<tr>
						<th>제목 </th>
						<td colspan="3"><input type="text" name="title" value="${ b.title }" style="width:500px;"></td>
					</tr>
					<tr>
						<th>내용 </th>
						<td colspan="3">
							<textarea name="content" cols="60" rows="15" style="resize:none;width:500px;">${ b.content }</textarea>
						</td>
					</tr>
				</table>
				<input type="hidden" name="no" value="${ b.no }">
				<br>
				<div align="center">
					<button class = "btns" type="reset">취소하기</button>
					<button class = "btns" type="submit">수정하기</button>
				</div>
			</form>
			<br><br>
	</div>
</body>
</html>