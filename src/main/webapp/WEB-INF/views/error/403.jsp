<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>403</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href='http://fonts.googleapis.com/css?family=Capriola' rel='stylesheet' type='text/css'>
    <style type="text/css">
        body {
            font-family: 'Capriola', sans-serif;
        }
        body {
            background: #b9babb;
        }
        .wrap {
            margin: 0 auto;
        }
        .logo h1 {
            font-size: 200px;
            color: #16a085;
            text-align: center;
            margin-bottom: 1px;
            text-shadow: 4px 4px 1px white;
        }
        .logo p {
            color: #54514d;;
            font-size: 20px;
            margin-top: 1px;
            text-align: center;
        }
        .logo p span {
            color: lightgreen;
        }
        .sub p, .sub a {
            color: #fffefc;
            font-size: 13px;
            text-decoration: none;
            display: block;
            width: 200px;
            margin: auto;
            border-radius: 3px;
            background-color: rgba(22, 160, 133, 0.1);
            padding: 1px;
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="logo">
        <h1>403</h1>
        <p>Доступ запрещен!<br/>${msg}</p>
        <div class="sub">
            <p><a href="<c:url value="/login"/>">Вход</a></p>
        </div>
    </div>
</div>
</body>