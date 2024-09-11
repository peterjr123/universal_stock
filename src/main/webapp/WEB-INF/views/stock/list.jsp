<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html lang="kr">
<head>
</head>
<body>
주식 리스트
<div>
    <c:forEach items="${stocks}" var="stock">
        <h2>주식 이름: ${stock.name}</h2>
        <div>현재 가격: ${stock.price}</div>
    </c:forEach>
</div>
</body>
</html>