
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   

    
    <c:choose>
    <c:when test="${not empty sessionScope.loginMember}">
		
        <c:set var="list" value="${sessionScope.basket}" scope="page" />
        <c:set var="sessionScope.basket" value="${list}" />
        
    </c:when>
    <c:otherwise>

        
        <!-- 세션의 basket에 무언가 있을 경우 list 변수에 할당 -->
        <c:set var="list" value="${sessionScope.basket}" scope="page" />
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
    <jsp:include page="../common/menubar.jsp" />

<%--    스크립틀릿 차이점 확인 --%>
<%--   <div class="container mt-5">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-12">--%>
<%--            <div class="text-center">--%>
<%--            <jsp:include page="../common/top2.jsp" />--%>
<%--        </div>--%>
<%--        <div class="mt-4">--%>
<%--            <br>--%>
<%--            <% if (session.getAttribute("loginMember") != null) { %>--%>
<%--                <%= ((MemberDTO)session.getAttribute("loginMember")).getName() %>님 장바구니 정보입니다.--%>
<%--                <hr>--%>
<%--            <% } %>--%>
<%--            <form action="processorder" method="post">--%>
<%--                <table class="table table-hover">--%>
<%--                   <thead class="thead-light">--%>
<%--                    <tr>--%>
<%--                        <th></th>--%>
<%--                        <th>아이디</th>--%>
<%--                        <th>물건이름</th>--%>
<%--                        <th>가격</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <%--%>
<%--						ArrayList<ProductDTO> list = (ArrayList<ProductDTO>) session.getAttribute("basket");--%>
<%--						if (list != null ) {--%>
<%--                        for (ProductDTO dto : list) { %>--%>
<%--                            <tr>--%>
<%--                                <td><input type="checkbox" name="selectedItems" value="<%= dto.getId() %>" /></td>--%>
<%--                                <td><%= dto.getId() %></td>--%>
<%--                                <td><%= dto.getName() %></td>--%>
<%--                                <td><%= dto.getPrice() %></td>--%>
<%--                            </tr>--%>
<%--                        <% }--%>
<%--                    } else { %>--%>
<%--                        <tr>--%>
<%--                            <td colspan="4" style="text-align: center;">장바구니에 아무것도 들어있지 않음.</td>--%>
<%--                        </tr>--%>
<%--                    <% } %>--%>
<%--                     </tbody>--%>
<%--                </table>--%>
<%--                <% if (list != null && !list.isEmpty()) { %>--%>
<%--                    <input type="submit" value="선택한 항목 주문하기" class="btn btn-primary" />--%>
<%--                <% } %>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
    
	 <div class="container mt-5">
	    <div class="row">
	        <div class="col-md-12">
	            <div class="text-center">
	                <jsp:include page="../common/top2.jsp" />
	            </div>
	            <div class="mt-4">
	                <br>
	                <c:if test="${not empty sessionScope.loginMember}">
	                    ${sessionScope.loginMember.name}님 장바구니 정보입니다.
	                    <hr>
	                </c:if>
	                <form action="processorder" method="post">
	                    <table class="table table-hover">
	                        <thead class="thead-light">
	                            <tr>
	                                <th></th>
	                                <th>아이디</th>
	                                <th>물건이름</th>
	                                <th>가격</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <c:choose>
	                                <c:when test="${not empty list}">
	                                    <c:forEach var="dto" items="${list}">
	                                        <tr>
	                                            <td><input type="checkbox" name="selectedItems" value="${dto.id}" /></td>
	                                            <td>${dto.id}</td>
	                                            <td>${dto.name}</td>
	                                            <td>${dto.price}</td>
	                                        </tr>
	                                    </c:forEach>
	                                </c:when>
	                                <c:otherwise>
	                                    <tr>
	                                        <td colspan="4" style="text-align: center;">장바구니에 아무것도 들어있지 않음.</td>
	                                    </tr>
	                                </c:otherwise>
	                            </c:choose>
	                        </tbody>
	                    </table>
	                    <c:if test="${not empty list and fn:length(list) > 0}">
	                        <input type="submit" value="선택한 항목 주문하기" class="btn btn-primary" />
	                    </c:if>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>

</body>
</html>

