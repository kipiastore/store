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
                    <div class="rptShort-inner">
                        <ul>
                            <k:forEach var="item" items="${homeModel.menuItemList}">
                                <li class="header-item header-menu">
                                    <a data-id="${item.id}" href="partition/${item.id}">${item.title}
                                        <span data-id="${item.id}" class="rua-p-c-red">${item.counter}</span>
                                    </a>
                                    <ul class="menu-inner">
                                        <li class="header-menu-item">
                                            <a id="item-i" data-id="${item.id}" href="partition/${item.id}">${item.title}
                                                <span data-id="${item.id}" class="rua-p-c-red">${item.counter}</span>
                                            </a>
                                        </li>
                                        <li class="header-menu-item">
                                            <a id="item-i" data-id="${item.id}" href="partition/${item.id}">${item.title}
                                                <span data-id="${item.id}" class="rua-p-c-red">${item.counter}</span>
                                            </a>
                                        </li>
                                        <li class="header-menu-item">
                                            <a id="item-i" data-id="${item.id}" href="partition/${item.id}">${item.title}
                                                <span data-id="${item.id}" class="rua-p-c-red">${item.counter}</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </k:forEach>
                            test
                        </ul>
                    </div>
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
    <div class="rua-l-wrapper mtmb">
        <div class="row middle m-text-center">
            <div class="col d-1of2 m-1of1 m-pl-1of1">
                <h2 class="headline mtmb" id="headlinemtmb">Присоединяйтесь к нам!</h2>
                <div class="rua-p-c-mid">
                    <ol>
                        <li>Мы дружный и молодой коллектив, всегда готовый поддержать друг друга.</li>
                        <li>Предлагаем интересную и стабильную работу, где карьерный рост именно в ваших руках!</li>
                        <li>У нас есть чай, кофе и печеньки в неограниченном количестве!</li>
                    </ol>
                </div>
                <p class="mtmb">
                    <a class="header-link header-link-button" id="requestButt" href="">Оставить заявку</a>
                </p>
            </div>
            <div class="col d-1of2 m-1of1">
                <div class="macImg">
                    <img src="<c:url value="/resources/images/mac.png"/>"/>
                </div>
            </div>
        </div>
    </div>
    <div class="ourBrand">
        <div class="ourBrandHead">
            <div class="ourBrandBody">
                <div class="ourBrandGraphImg">
                    <img src="<c:url value="/resources/images/graph.png"/>" class="graphImg">
                </div>
                <div class="ourBrandGraphText">
                    <h3>
                        Мы предоставляем легкий и быстрый способ рекламы ваших услуг в лучшем виде.
                        <br>
                        Результат не заставит вас ждать!
                    </h3>
                </div>
                <div class="ourBrandButt">
                    <p class="mtmb">
                        <a class="btn btn-primary" id="imgButt" href="">Заказать прощадку</a>
                    </p>
                </div>
            </div>
        </div>
        <div class="ourBrandFooter"></div>
    </div>
    <%@include file="/WEB-INF/views/components/additionalMenu.jspf"%>
    <%@include file="/WEB-INF/views/components/footer.jspf"%>
</form>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>