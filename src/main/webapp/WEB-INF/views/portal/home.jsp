<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
</head>
<body>
<a name="top" id="top"></a>
<form method="get" action="search" id="mainForm">
    <%@include file="/WEB-INF/views/components/topBar.jspf"%>
    <%@include file="/WEB-INF/views/components/header.jspf"%>
    <div style="margin-top: -30px;">
        <div style="padding: 40px 0;">
            <div class="rua-l-wrapper" style="margin-top: 20px; margin-bottom: 40px;">
                <div class="row text-center">
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border"></div>
                            <div class="front"><strong>100%</strong><span>Проверенные<br>данные</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">Наша команда профессиональных модераторов внимательно изучает новые ресурсы.</div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border"></div>
                            <div class="front"><strong>53 765</strong><span>Наибольшее количество<br>предложений</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">Ежемесячно на нашем сайте размещают более 500 новых ресурсов.</div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border"></div>
                            <div class="front"><strong>24/7</strong><span>Актуальные<br>данные</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">Вы видите только актуальные предложения.</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr style="width: 300px; margin: 110px auto 35px auto;"/>
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb">Поиск по рубрикам</h2>
                <div class="rptShort">
                    <k:forEach var="item" items="${homeModel.partitionItemList}">
                        <p>
                            <a data-id="${item.id}" href="partition/${item.id}">${item.title}Вы видите только актуальные предложения.</a>
                            <span class="rua-p-c-red">${item.counter}</span>
                            <span class="show-subsection" data-id="${item.id}"></span>
                            <span class="subsection" data-id="${item.id}" id="item-${item.id}">
                                <span class="subsection-list" data-id="${item.id}">
                                    <a data-id="${item.id}" href="partition/${item.id}">Фото</a>
                                    <br/>
                                    <a data-id="${item.id}" href="partition/${item.id}">Графика</a>
                                    <br/>
                                    <a data-id="${item.id}" href="partition/${item.id}">Фотоаппараты</a>
                                    <br/>
                                    <a data-id="${item.id}" href="partition/${item.id}">Линзы</a>
                                    <br/>
                                    <a data-id="${item.id}" href="partition/${item.id}">Видео</a>
                                </span>
                            </span>
                        </p>
                    </k:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="bestResources">
        <h2 class="headline centered mtmb">Лучшие ресурсы</h2>
        <div class="rua-l-wrapper text-center mtmb">
            <table class="logos">
                <tbody>
                    <k:forEach var="key" items="${homeModel.bestResourcesItemMap.keySet()}">
                        <tr>
                            <k:forEach var="item" items="${homeModel.bestResourcesItemMap.get(key)}">
                                <td>
                                    <a href="company/${item.id}" title="${item.title}" style="background-image: url(<c:url value="/resources/images/${item.imageUrl}"/>);"></a>
                                </td>
                            </k:forEach>
                        </tr>
                    </k:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%@include file="/WEB-INF/views/components/invitation.jspf"%>
    <%@include file="/WEB-INF/views/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/components/additionalMenu.jspf"%>
    <%@include file="/WEB-INF/views/components/footer.jspf"%>
</form>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>