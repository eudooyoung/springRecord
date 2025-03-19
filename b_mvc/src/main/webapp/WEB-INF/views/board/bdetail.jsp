<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
		<div class="container my-4">
    	<table class="table table-striped table-bordered">
				<tbody>
                    <tr>
                        <th scope="row" width="100">글번호</th>
                        <td><strong>${ b.no }</strong></td>
                    </tr>
                    <th>분야</th>
					<td style="text-align: left;">
				    <c:choose>
				        <c:when test="${b.categoryCode == 10}">공통</c:when>
				        <c:when test="${b.categoryCode == 20}">운동</c:when>
				        <c:when test="${b.categoryCode == 30}">요리</c:when>
				        <c:when test="${b.categoryCode == 70}">기타</c:when>
				        <c:otherwise>알 수 없음</c:otherwise>
				    </c:choose>
					</td>
				                    <tr>
                        <th scope="row">제목</th>
                        <td><strong>${ b.title }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">작성자</th>
                        <td><strong>${ b.writer }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">조회수</th>
                        <td><strong>${ b.count }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">작성일</th>
                        <td><strong>${ b.createdDate }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">내용</th>
                        <td  class="scrollable-cell" height=300px><strong>${ b.content }</strong></td>
                    </tr>
                </tbody>
            </table>
            	  <div class="text-right mt-4">
	  	  	  	<button class="btn btn-primary" onclick="location.href='${pageContext.servletContext.contextPath}/board/list'">목록으로</button>
	   		  </div>    
        </div>
	<div class="text-center mt-4">

		<c:if test="${ sessionScope.loginMember.id.equals(b.writer) }">
			<!-- 체크! 로그인된 유저의 name을 session에서 불러와 b.writer와 같으면 버튼 보이기 -->
			<button class="btns"
				onclick="location.href='${pageContext.servletContext.contextPath}/board/update?no=${ b.no }'">수정하기</button>
			<!-- 
					@WebServlet("/board/update") >> get 방식 >> BoardUpdateServlet
				
					form 태그를 이용할 때 method를 생략하면 get 방식으로 전송된다.	-->

			<button class="btns"
				onclick="location.href='${pageContext.servletContext.contextPath}/board/delete?no=${ b.no }'">삭제하기</button>

		</c:if>

	</div>

	<br>





</body>
</html>