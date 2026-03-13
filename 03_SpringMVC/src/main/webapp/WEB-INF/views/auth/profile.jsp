<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="jakarta.tags.core" prefix="c" %>

<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>회원정보</title>
    </head>
    <body>
        <h2>회원정보</h2>

        <c:if test="${ not empty user }">
            사용자 번호 : ${ user.no }
            <br>
            사용자 아이디 : ${ user.username }
            <br>
            사용자 주소 : ${ user.address }

        </c:if>
        <c:if test="${ empty user }">
            회원 정보가 없습니다.
        </c:if>

    </body>
</html>
