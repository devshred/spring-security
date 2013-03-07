<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<h1>jvm status</h1>
<ul>
<c:forEach var="message" items="${messages}">
    <li><a href="/message/${message.id}">message #${message.id}</a></li>
</c:forEach>
</ul>
</body>
</html>