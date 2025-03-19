<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>

<%@ page import="java.util.Date"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	<!-- 제품 검색 조건추가  -->
	<div class="container mt-4">

		<form
			action="${ pageContext.servletContext.contextPath }/product/search"
			method="get">
			<table class="table table-bordered text-center">
				<tr>
					<td>제품명</td>
					<td><input class="form-control" id="name" name="name"
						placeholder="제품명을 입력하세요"></input></td>
					<td rowspan="3">
						<button type="submit" class="btn btn-primary"
							style="width: 100%; height: 20%;">검색</button>
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>
						<div class="d-flex justify-content-between">
							<input type="number" class="form-control me-2" id="minPrice"
								name="minPrice" placeholder="최소가격" min="0"></input> <span
								class="mx-2">~</span> <input type="number"
								class="form-control ms-2" id="maxPrice" name="maxPrice"
								placeholder="최대가격" min="0"></input>
						</div>
					</td>
				</tr>
				<tr>
					<td>회사명</td>
					<td><input class="form-control" id="company" name="company"
						placeholder="회사명을 입력하세요"></input></td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 제품 리스트 -->
	<div class="container mt-4">
		<div align="right">
		<c:if
			test="${(!empty sessionScope.loginMember) && sessionScope.loginMember.id eq 'admin'}">
			<button class="btn btn-success"
				onclick="location.href='${pageContext.servletContext.contextPath}/product/insert'">등록하기</button>
		</c:if>
		</div>
		<br>
		<table class="table table-hover table-bordered">
			<thead class="thead-light" style="text-align: center;">
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제품명</th>
					<th scope="col">제품가격</th>
					<th scope="col">회사명</th>
					<th scope="col">등록일</th>
					<%-- 카운트 올라가는거 확인 --%>
					<th scope="col" id="cartContents">장바구니 count: <c:out
							value="${not empty sessionScope.basket ? fn:length(sessionScope.basket) : '0'}" />개
					</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}">
					<tr>
						<td colspan="6" class="text-center">조회된 내용이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="p" items="${list}">
					<tr>
						<td class="tdc">${p.id}</td>
						<td class="tdc"
							onclick="location.href='${pageContext.servletContext.contextPath}/product/selectone?no=${p.id}'">${p.name}</td>
						<td class="tdc">${p.price}</td>
						<td class="tdc">${p.company.name}</td>
						<td class="tdc">${p.createDate}</td>
						<td>
							<button type="button" class="btn btn-primary"
								onclick="addToCart(${p.id})">장바구니 추가</button>
						</td>
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
	            location.href = '${pageContext.servletContext.contextPath}/product/selectone?no=' + no;
	        }
	    }
	}
		
	function addToCart(productId) {
	    $.ajax({
	        type: "POST",
	        url: "${pageContext.servletContext.contextPath}/cart/add",
			// 넘겨주는 값 jQuery 문법 확인
	        data: {productId: productId},
	        success: function(response) {

	            $("#cartContents").html("장바구니 count: " + response + "개");

	            alert(productId + "번 제품이 장바구니에 추가되었습니다.")
				choice = confirm('장바구니 페이지로 이동하시겠습니까') 
				if(choice){
					location.href = '${pageContext.servletContext.contextPath}/product/basket'
				}
	        },
	        error: function(xhr) { 
	         	alert('장바구니 추가에 실패했습니다.');
	          
	        }
	    });
	}
	</script>


</body>
</html>