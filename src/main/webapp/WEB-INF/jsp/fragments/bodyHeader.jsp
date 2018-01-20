<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="meals" class="navbar-brand"><spring:message code="app.title"/></a>

        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-info" href="users"><spring:message code="user.title"/></a>
                    </sec:authorize>
                    <a class="btn btn-primary" href="logout">
                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                    </a>
                </sec:authorize>
            </form>
        </div>
    </div>
</div>