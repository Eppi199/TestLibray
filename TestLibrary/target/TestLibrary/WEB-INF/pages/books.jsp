<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Books</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff7c0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../TestLibrary_war_exploded/index.jsp">Back to main menu</a>


<br/>
<br/>

<h1>Library</h1>

<c:if test="${!empty listBook_Author}">
    <table class="tg">
        <tr>
            <th width="80">ID_all</th>
            <th width="80">ID_book</th>
            <th width="80">ID_Author</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="120">Year</th>
            <th width="70">Edit</th>
            <th width="70">Delete</th>
        </tr>
        <c:forEach items="${listBook_Author}" var="book">
            <tr>
                <td>${book.id_all}</td>
                <td>${book.id_book}</td>
                <td>${book.id_author}</td>
                <td>${book.title_book}</td>
                <td>${book.name_author}</td>
                <td>${book.year_book}</td>
                <td><a href="<c:url value="/edit/${book.id_all}/${book.id_book}/${book.id_author}"/>">Edit</a></td>
                <td><a href="<c:url value="/remove/${book.id_all}/${book.id_book}/${book.id_author}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<br/>
<br/>

<c:url var="addAction" value="/books/add"/>

<form:form action="${addAction}" commandName="book">
    <table>

        <c:if test="${!empty book.title_book}">
            <form:form action="${addAction}" commandName="book_author">
                <td>
                    <form:hidden path="id_all" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id_all"/>
                </td>
            </form:form>
            <tr>
                <td>
                    <form:hidden path="id_book" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id_book"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="title_book">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title_book"/>
            </td>
        </tr>

        <form:form action="${addAction}" commandName="author">
            <td>
                <form:hidden path="id_author" readonly="true" size="8" disabled="true"/>
                <form:hidden path="id_author"/>
            </td>
            <tr>
                <td>
                    <form:label path="name_author">
                        <spring:message text="Author"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name_author"/>
                </td>
            </tr>
        </form:form>
        <tr>
            <td>
                <form:label path="year_book">
                    <spring:message text="Year"/>
                </form:label>
            </td>
            <td>
                <form:input path="year_book"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty book.title_book}">
                    <input type="submit"
                           value="<spring:message text="     EDIT     "/>"/>
                </c:if>
                <c:if test="${empty book.title_book}">
                    <input type="submit"
                           value="<spring:message text="     ADD     "/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>

