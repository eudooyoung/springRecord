<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	
	<div class="container mt-4">
<%--		로그인이 되어있으면 작성하기 버튼 나타남--%>
	<c:if test="${ !empty sessionScope.loginMember }">
	 <div align="right">
        <button class="btns" id="writeBoard" onclick="location.href='${pageContext.servletContext.contextPath}/board/insert'">작성하기</button>
    </div>
	</c:if>
	<br>
	<table id="listArea" class="table table-hover table-striped table-bordered">
	        <tr>
				<th scope="col" style="text-align: center; width: 100px;">번호</th>
				<th scope="col" style="text-align: center; width: 200px;">제목</th>
				<th scope="col" style="text-align: center; width: 150px;">작성자</th>
				<th scope="col" style="text-align: center; width: 50px;">조회수</th>
				<th scope="col" style="text-align: center; width: 100px;">작성일</th>
				<th scope="col" style="text-align: center; width: 100px;">수정일</th>
	        </tr>
	    <tbody>
	        <c:if test="${empty list}">
	            <tr>
	                <td colspan="5" class="text-center">조회된 내용이 없습니다.</td>
	            </tr>
	        </c:if>

<%--		 서블릿에서 넘겨준 list를 변수 b로 받음--%>
	        <c:forEach var="b" items="${list}">
	            <tr>
	                <td class="text-center tdc"><c:out value="${b.no}" /></td>
	                <td class="text-center tdc">
	                    <a href="${pageContext.servletContext.contextPath}/board/selectone?no=${b.no}" class="text-decoration-none">
	                        <c:out value="${b.title}" />
	                    </a>
	                </td>
	                <td class="text-center tdc"><c:out value="${b.writer}" /></td>
	                <td class="text-center tdc"><c:out value="${b.count}" /></td>
	                <td class="text-center tdc"> <c:out value="${b.createdDate}" /></td>
					<td class="text-center tdc"> <c:out value="${b.modifiedDate}" /></td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	</div>

	<script>
		
	if(document.getElementsByClassName("tdc")) {
	    const $tds = document.getElementsByClassName("tdc");
	    for(let i = 0; i < $tds.length; i++) {
	        $tds[i].onmouseenter = function() {
	            this.parentNode.style.backgroundColor = "skyblue";
	            this.parentNode.style.cursor = "pointer";
	        }
	        
	        $tds[i].onmouseout = function() {
	            this.parentNode.style.backgroundColor = "white";
	        }
	        $tds[i].onclick = function() {
	            var no = this.parentNode.children[0].innerText;

	            // '/selectone' 서블릿으로 리디렉션하면서 쿼리 스트링에 게시물 번호를 포함시킴
	            location.href = '${pageContext.servletContext.contextPath}/board/selectone?no=' + no;
	        }
	    }
	}
		
	</script>
</body>


</html>