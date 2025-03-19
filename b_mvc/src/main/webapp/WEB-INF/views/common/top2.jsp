<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<c:if test="${not empty sessionScope.basket}">
    <c:set var="list" value="${sessionScope.basket}" scope="page" />
    장바구니 count: <c:out value="${fn:length(list)}"/>개
</c:if>

