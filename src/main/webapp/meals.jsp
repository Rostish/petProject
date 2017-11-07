<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<style>
    .green {
        background-color: green
    }

    .red {
        background-color: red
    }
</style>
<h3><a href="index.html">Home</a></h3>

<div>
    <h2>Meals</h2>
    <table border="1">
        <thead>
        <td>id:</td>
        <td>Прием пищи:</td>
        <td>Каллории:</td>
        <td>Дата:</td>
        <td>Превышено:</td>
        <td>Удалить:</td>
        <td>Обновить:</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${meals}" var="meal">
        <tr class="${meal.exceed == true ? 'red':'green'}">
            <td><c:out value="${meal.userid}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><javatime:format value="${meal.dateTime}" pattern="YYYY-MM-dd HH:mm" var="parsedDate"/>
                <c:out value="${parsedDate}"/></td>
            <td><c:out value="${meal.exceed}"/></td>
            <td><a href="meals?action=edit&userid=<c:out value="${meal.userid}"/>">Update</a></td>
            <td><a href="meals?action=delete&userid=<c:out value="${meal.userid}"/>">Delete</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
    <th2><b>ADD/Edit meal</b></th2>
    <br>
    <form method="POST" action='meals' name="frmAddUser">
        User ID : <input type="text" readonly="readonly" name="userid"
                         value="<c:out value="${meal.userid}" />" /> <br />
        Прием пищи: <input
            type="text" name="description"
            value="<c:out value="${meal.description}" />"/> <br/>
        Каллории : <input
            type="text" name="calories"
            value="<c:out value="${meal.calories}" />"/> <br/>
        Дата : <input
            type="date" name="dateTime"
            value="<javatime:format pattern="YYYY-MM-dd HH:mm" value="${meal.dateTime}"/>"/> <br/>
        <input
                type="submit" value="ADD"/>
    </form>
</div>

</body>
</html>
