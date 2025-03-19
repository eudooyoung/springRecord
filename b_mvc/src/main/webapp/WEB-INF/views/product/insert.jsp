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
				
						$.ajax({
							url: "${pageContext.servletContext.contextPath}/product/companylist",
						
						    success: function(data) {
				                var companySelect = $('select[name="company"]');
				                console.log(data)
				                // 받아온 데이터로 <select> 내용채우기
				                data.forEach(function(company) {
				                    companySelect.append($('<option></option>').attr('value', company.id).text(company.name));
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
	
<%--첨부파일 있을 경우 확인--%>
			<form action="${ pageContext.servletContext.contextPath }/product/insert" method="post" encType="multipart/form-data">
			<div class="thumbnail-insert-area">
			<table align="center">
				
					<tr>
						<td>대표 이미지</td>
						<td colspan="3">
							<div class="title-img-area" id="titleImgArea">
								<img id="titleImg" width="350" height="200">
								<input type="file" id="thumbnailImg1" name="thumbnailImg1" onchange="loadImg(this,1)">
							</div>
						</td>
					</tr>

					
				</table>
			</div>
			<table class="table table-striped table-bordered">

				<tbody>

				<tr>
					<th>상품제목</th>
					<td><input type="text" name="name" class="form-control"></td>
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
					<td><textarea name="content" class="form-control"></textarea></td>
				</tr>

				<tr>
					<th>상품가격</th>
					<td><input type="number" name="price" min="0" class="form-control"></td>
				</tr>

				</tbody>
				
			</table>
		
					<div align="center">
							<button class = "btns" type="reset">취소하기</button>
							<button class = "btns" type="submit">등록하기</button>
					</div>

		
			</form>
			
			</div>

	
		<script>
			 
			const $titleImgArea = document.getElementById("titleImgArea");
			/* 이미지클릭 확인
			$titleImgArea.onclick = function() { 
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