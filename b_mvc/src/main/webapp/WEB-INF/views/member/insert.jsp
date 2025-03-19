<%--
  Created by IntelliJ IDEA.
  User: vuiet
  Date: 2025-03-10
  Time: 오후 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 공통 화면 --%>
<jsp:include page="../common/menubar.jsp"/>

<div class="outer outer-member-insert container mt-4">
    <h2 class="text-center mb-4">회원 가입</h2>

    <!-- 회원 가입 폼 -->
    <form id="joinForm" action="${pageContext.servletContext.contextPath}/member/insert" method="post"
          class="needs-validation" novalidate>
        <div class="row mb-3">
            <label for="memberId" class="col-sm-2 col-form-label">* 아이디</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" maxlength="10" name="id" id="memberId" required>
            </div>
            <div class="col-sm-3">
                <button type="button" class="btn btn-outline-primary" id="duplicationCheck">중복확인</button>
                <div id="text"></div>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">* 비밀번호</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" maxlength="10" name="pw" required>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">* 비밀번호확인</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" maxlength="10" name="pw2">
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">* 닉네임</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" maxlength="10" name="name" required>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">연락처</label>
            <div class="col-sm-10">
                <input type="tel" class="form-control" name="tel">
            </div>
        </div>

        <div class="text-center">
            <button type="button" class="btn btn-secondary" id="goMain">메인으로</button>
            <button type="submit" class="btn btn-primary">가입하기</button>
        </div>
    </form>
</div>

<script>
    $(function () {
        $('#goMain').click(function () {
            location.href = "${pageContext.servletContext.contextPath}/"; // 메인 페이지로 이동
        })

        $('#duplicationCheck').click(function () {
            let memberId = $('#memberId').val()
            $.ajax({
                type: 'GET',
                url: '${pageContext.servletContext.contextPath}/member/dupCheck?id=' + memberId,
                success: function (response) {
                    $('#text').text("")
                    if (memberId !== "") {
                        $('#text').append(response)
                    } else {
                        alert("아이디를 입력하세요")
                    }
                },
                error: function (xhr) {
                    alert('아이디 중복 조회 실패')
                }

            })

        })
    })

</script>
</body>
</html>
