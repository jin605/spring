<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="jakarta.tags.core" prefix="c" %>

<c:set var="contexthPath" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Hello World</title>
    </head>
    <body>

        <h2> "Hello World!" </h2>

        <form action="${ contexthPath }/auth/login" method="post" >
            <label for="userId">아이디 : </label>
            <input type="text" id="username" name="username" required/>

            <br>

            <label for="password">비밀번호 : </label>
            <input type="password" id="password" name="password" required/>

            <br>

            <label><input type="checkbox" name="hobby" value="운동" checked>운동</label>
            <label><input type="checkbox" name="hobby" value="등산" >등산</label>
            <label><input type="checkbox" name="hobby" value="독서" >독서</label>

            <br>

            <input type="button" value="회원가입">
            <input type="submit" value="로그인">


        </form>

    </body>
</html>