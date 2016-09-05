<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<html lang="ru">
<head>
    <title>Справочная</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
</head>
<body>
<a name="top"></a>
<form method="get" action="search" id="mainForm">
    <%@include file="/WEB-INF/views/components/header.jspf"%>
    <div class="header_img_variant_1" id="header_img">
        <h2 class="headline centered rua-p-c-white mtmb" style="margin-top: 0">Заголовок 1<small>Текст 2</small></h2>
        <div>
            <div class="searchform form-inline">
                <div class="rua-l-wrapper">
                    <div>
                        <div class="rua-g-right">
                            <a class="btn btn-primary btn-block submit_button" onclick="submit()">Поиск</a>
                        </div>
                        <div class="rua-g-clearfix">
                            <div>
                                <input name="value" type="text" class="keyword input-block-level ui-autocomplete-input"
                                       placeholder="Введите ключевые слова" autocomplete="off"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
                        <k:forEach var="item" items="${homeModel.menuItemList}">
                            <a data-id="${item.id}" href="company/${item.id}">${item.title}
                                <span class="rua-p-c-red">${item.counter}</span>
                            </a>
                        </k:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/views/components/additionalMenu.jspf"%>
    <div style="background: #f7f7f7; overflow: hidden;">
        <h2 class="headline centered mtmb">Лучшие ресурсы</h2>
        <div class="rua-l-wrapper text-center mtmb">
            <table class="logos">
                <tbody>
                    <k:forEach var="key" items="${homeModel.bestResourcesItemMap.keySet()}">
                        <tr>
                            <k:forEach var="item" items="${homeModel.bestResourcesItemMap.get(key)}">
                                <td>
                                    <a href="" title="${item.title}" style="background-image: url(<c:url value="/resources/images/testLogo.jpg"/>);"></a>
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
                        <li></li>
                        <li></li>
                        <li></li>
                    </ol>
                </div>
                <p class="mtmb">
                    <a class="btn btn-danger" href="">Оставить заявку</a>
                </p>
            </div>
            <div class="col d-1of2 m-1of1">
                <img src="<c:url value="/resources/images/mac.png"/>"/>
            </div>
        </div>
    </div>
    <div class="text-center" id="secondImg">
        <div>
            <div class="secondImgText">
                <h2 class="headline centered mtmb rua-p-c-white" id="imgH3text">Текст 1<br>Текст 2</h2>
            </div>
            <div class="secondImgButt">
                <p class="mtmb">
                    <a class="btn btn-primary" id="imgButt" href="">Кнопка</a>
                </p>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/views/components/footer.jspf"%>
</form>
</body>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</html>