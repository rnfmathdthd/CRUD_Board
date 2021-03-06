<%@page import="com.study.common.vo.CoronaVO"%>
<%@page import="com.study.common.service.CoronaServiceimpl"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>COVID-19 tracker</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<%@ include file="/WEB-INF/inc/header.jsp" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<div class = "container">

    <h1 class="display-4">Coronavirus Tracker Application(Korea)</h1>
    <p class="lead">한국의 코로나19 현황테이블</p>

    <div class="jumbotron">
        <h1 class="display-4" th:text="${koreaStats[0].total}"></h1>
        <p class="lead">Total cases reported</p>
        <hr class="my-4">
        <p>
            <span>New cases reported since previous day</span>
            <span th:text="${koreaStats[0].diffFromPrevDay}">0</span>
        </p>
    </div>

    <table class= "table">
        <tr>
            <th>지역</th>
            <th>확진환자수(전일 대비 증감량)</th>
            <th>사망자</th>
            <th>일일 검사건수</th>
            <th title = "인구 10만 명당">발생률(*)</th>
        </tr>
        <tr th:each = "stat : ${koreaStats} ">
            <td th:text = "${stat.country}"> </td>
            <td th:text = "|${stat.total}(${stat.diffFromPrevDay})|">0</td>
            <td th:text = "${stat.death}">0</td>
            <td th:text = "${stat.inspection}">0</td>
            <td th:text = "${stat.incidence}">0</td>
        </tr>
    </table>
</div>
</body>
</html>
