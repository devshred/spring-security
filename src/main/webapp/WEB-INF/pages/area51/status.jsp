<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h1>jvm status</h1>
<ul>
<c:forEach var="parameter" items="${status}">
    <li>${parameter}</li>
</c:forEach>
</ul>
[<a href="${pageContext.request.contextPath}/j_spring_security_logout">log out</a>|
<a href="/area51">area51</a>|
<a href="/">home</a>]
</body>
</html>