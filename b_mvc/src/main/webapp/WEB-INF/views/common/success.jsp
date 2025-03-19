<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		(function(){
			const successCode = "${ requestScope.successCode }";
			
			let successMessage = "";
			let movePath = "";
			
			switch(successCode){
				case "insertMember" : 
					successMessage = "회원 가입에 성공하셨습니다!";
					movePath = "${ pageContext.servletContext.contextPath }";
					break;
				case "updateMember" : 
					successMessage = "회원 정보 변경에 성공하셨습니다!";
					movePath = "${ pageContext.servletContext.contextPath }/member/update";
					break;
				case "deleteMember" : 
					successMessage = "회원 탈퇴에 성공하셨습니다!";
					movePath = "${ pageContext.servletContext.contextPath }/index.jsp";
					break;
				case "insertNotice" : 
					successMessage = "공지사항 등록에 성공하셨습니다!";
					movePath = "${ pageContext.servletContext.contextPath }/notice/list";
					break;
				case "insertBoard" : 
					successMessage = "게시판 등록에 성공하셨습니다!";
					movePath = "${ pageContext.servletContext.contextPath }/board/list";
					break;
				case "updateBoard" :
					successMessage = "게시글 수정에 성공하셨습니다!";
					movePath = "${ pageContext.servletContext.contextPath }/board/list";
					break;
				case "insertProduct" : 
					successMessage = "제품등록에 성공하셨습니다!";
					movePath = "${ pageContext.servletContext.contextPath }/product/list";
					break;
				case "deleteBoard" : 
					successMessage = "게시글삭제에 성공하셨습니다!";
					movePath = "${ pageContext.servletContext.contextPath }/board/list";
					break;
			}

			alert(successMessage);
			
			location.replace(movePath);
			
/* 			location.replace()를 사용할 경우, 브라우저의 주소 표시줄이 변경된 URL로 갱신함
			리다이렉트는 이루어지지만 브라우저의 히스토리에서 현재 페이지를 새 URL로 대체!
			Back 버튼을 사용했을 때 이전 페이지로 돌아갈수없다*/
		})();
	
	</script>
</body>
</html>
