<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

    [ <a href="/">home</a>
    | <a href="/area51">area51</a>
    | <a href="/messages">messages</a>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        | <a href="/area51/status">status</a>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        | <a href="${pageContext.request.contextPath}/j_spring_security_logout">log out</a>
    </security:authorize>
]