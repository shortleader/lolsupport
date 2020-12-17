<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table>
        <tr>
            <th>summonerName</th>
            <th>profileIconId</th>
            <th>revisionDate</th>
            <th>name</th>
            <th>summonerLevel</th>
            <th>puuid</th>
            <th>id</th>
        </tr>
 
 
        <c:forEach var="list" items="${list}">
            <tr>
                <td><p>${list.summonerName}</p></td>
                <td><p>${list.profileIconId}</p></td>
                <td><p>${list.revisionDate}</p></td>
                <td><p>${list.accountId}</p></td>
                <td><p>${list.summonerLevel}</p></td>
                <td><p>${list.puuid}</p></td>
                <td><p>${list.id}</p></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
