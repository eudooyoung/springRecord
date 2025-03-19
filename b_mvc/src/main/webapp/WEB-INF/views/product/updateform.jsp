<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.servletContext.contextPath }/resources/css/project.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	
	<script type="text/javascript">
				$(function() {
					  var selectedCompanyId = $('#productCompanyId').val();
					  
						$.ajax({
							url: "${pageContext.servletContext.contextPath}/product/companylist",
						
						    success: function(data) {
						    	var companySelect = $('select[name="company"]');
					            // Hidden input에 저장된 회사 ID

					            // 받아온 데이터로 <select> 내용 채우기
					            data.forEach(function(company) {
					                var option = $('<option></option>')
					                    .attr('value', company.id)
					                    .text(company.name);

					                // 회사 ID가 제품의 회사 ID와 같으면 selected 속성 추가
					                if (company.id == selectedCompanyId) {
					                    option.attr('selected', 'selected');
					                }

					                companySelect.append(option);
					            });
				            },
				            error: function() {
				                alert('회사 목록을 불러오는 데 실패했습니다.');
				            }
						})
					
				})
</script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	
	<div class="container my-4">
	    <form action="${pageContext.servletContext.contextPath}/product/update" method="post" encType="multipart/form-data">
	        <input type="hidden" name="productId" value="${requestScope.productList[0].id}" />
			<input type="hidden" id="productCompanyId" value="${requestScope.productList[0].company.id}">

	        <table class="table table-striped table-bordered">
	            <tbody>
	                <tr>
	                    <th width="150px">상품아이디</th>
	                    <td colspan="5">${requestScope.productList[0].id}</td>
	                </tr>
	                <tr>
	                    <th>상품제목</th>
	                    <td><input type="text" name="name" value="${requestScope.productList[0].name}" class="form-control"></td>
	                </tr>
	                <tr>
	                    <th>상품가격</th>
	                    <td><input type="number" name="price" value="${requestScope.productList[0].price}" class="form-control"></td>
	                </tr>
	                   <tr >
	                   <th>상품회사</th>
                        <td><span >
                            <select name="company" class="form-control">
                                <!-- AJAX로 데이터가 채워집니다. -->
                            </select>
                        </span></td>
                    </tr>

	                <tr>
	                    <th>상세내용</th>
	                    <td><textarea name="content" class="form-control">${requestScope.productList[0].content}</textarea></td>
	                </tr>


	                <tr>
	                    <th>대표사진</th>
	                    <td class="text-center">
	                        <img src="${pageContext.servletContext.contextPath}${requestScope.productList[0].attachments[0].thumbnailPath}" id="titleImg" alt="Product Image" width="200" >
	                        <input type="file" name="thumbnailImg1" class="form-control"  onchange="loadImg(this,1)">
	                    <!-- 기존 대표 사진의 경로를 hidden 필드로 전달 -->
				        <input type="hidden" name="existingThumbnailImg1" value="${requestScope.productList[0].attachments[0].thumbnailPath}">
	                    </td>
	                </tr>

	            </tbody>
	        </table>

	        <div class="text-center">
	            <button class="btn btn-danger" type="reset">취소하기</button>
	            <button class="btn btn-primary" type="submit">수정하기</button>
	            
	        </div>
	    </form>
	    <div align="right">
	    <button class="btn btn-primary" onclick="location.href='${pageContext.servletContext.contextPath}/product/list'">목록으로</button>
	    </div>
	</div>
	
		<script>
			
			const $titleImgArea = document.getElementById("titleImgArea");
			const $contentImgArea1 = document.getElementById("contentImgArea1");
			const $contentImgArea2 = document.getElementById("contentImgArea2");
			const $contentImgArea3 = document.getElementById("contentImgArea3");
			
			/* $titleImgArea.onclick = function() { 
				document.getElementById("thumbnailImg1").click(); 
			}
			
			$contentImgArea1.onclick = function() {
				document.getElementById("thumbnailImg2").click();
			}
			
			$contentImgArea2.onclick = function() {
				document.getElementById("thumbnailImg3").click();
			}
			
			$contentImgArea3.onclick = function() {
				document.getElementById("thumbnailImg4").click();
			} */
			
			function loadImg(value, num) {
				if (value.files && value.files[0]) {
					const reader = new FileReader();
					reader.onload = function(e) {
						switch(num){
						case 1:
							document.getElementById("titleImg").src = e.target.result;

							break;
						case 2:
							document.getElementById("contentImg1").src = e.target.result;

							break;
						case 3:
							document.getElementById("contentImg2").src = e.target.result;

							break;
						case 4:
							document.getElementById("contentImg3").src = e.target.result;

							break;
						}
					}
					reader.readAsDataURL(value.files[0]);
				}
			}
			
		</script>
</body>
</html>