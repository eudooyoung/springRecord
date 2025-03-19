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
	
			<form action="${ pageContext.servletContext.contextPath }/board/insert" method="post">
			
			<br>
				<table id="listArea" class="table table-hover table-striped table-bordered">
			    <tr>
						<td>분야</td>
						<td style="text-align: left;">
						    <select name="category">
						        <option value="10">공통</option>
						        <option value="20">운동</option>
						        <option value="30">요리</option>
						        <option value="70">기타</option>
						    </select>
						</td>
					</tr>
	  			  <tbody>
					<tr>
						<td>제목 </td>
						<td colspan="3"><input type="text" name="title" style="width:500px;"></td>
					</tr>
					<tr>
						<td>내용 </td>
						<td colspan="3">
							<textarea name="content" cols="60" rows="15" style="resize:none;width:500px;"></textarea>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<button class = "btns" type="reset">취소하기</button>
					<button class = "btns" type="submit">등록하기</button>
				</div>
				
			</form>
			<br><br>
	</div>
</body>
</html>