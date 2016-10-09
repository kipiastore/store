<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<a name="top" id="top"></a>
<form method="get" action="search" id="mainForm">
    <%@include file="/WEB-INF/views/portal/components/topbar.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/header.jspf"%>
    <div style="margin-top: -30px;">
        <div style="padding: 1px 0 30px 0;">
            <!--
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
            -->
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb">Поиск по рубрикам</h2>
                <div class="rptShort">
                    <k:forEach var="partitionItem" items="${model.partitionItems}">
                        <p>
                            <span class="subsection" data-id="${partitionItem.partitionId}" id="item-${partitionItem.partitionId}">
                                <span class="subsection-list" data-id="${partitionItem.partitionId}">
                                    <k:if test="${partitionItem.subPartitionItems == null}" >
                                        Список пуст!
                                    </k:if>
                                    <k:forEach var="subPartitionItem" items="${partitionItem.subPartitionItems}">
                                        <a data-id="${partitionItem.partitionId}"
                                           href="subPartition/${subPartitionItem.subPartitionId}">${subPartitionItem.subPartitionName}</a>
                                        <br/>
                                    </k:forEach>
                                </span>
                            </span>
                            <a data-id="${partitionItem.partitionId}" href="partition/${partitionItem.partitionId}">
                                ${partitionItem.partitionName}
                            </a>
                            <span class="rua-p-c-red">
                                ${partitionItem.companyCount}
                            </span>
                            <span class="show-subsection" data-id="${partitionItem.partitionId}"></span>
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
                    <k:forEach var="key" items="${model.bestCompanyGroupByColumn.keySet()}">
                        <tr>
                            <k:forEach var="bestCompanyItem" items="${model.bestCompanyGroupByColumn.get(key)}">
                                <td>
                                    <a href="company/${bestCompanyItem.companyId}" title="${bestCompanyItem.companyName}"
                                       style="background-image: url(<c:url value="/resources/images/${bestCompanyItem.companyLogoFileName}"/>);"></a>
                                </td>
                            </k:forEach>
                        </tr>
                    </k:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%@include file="/WEB-INF/views/portal/components/invitation.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<div class="mail-container">
    <div class="left-container">
        <div class="top-container"></div>
        <div class="email-cancel"></div>
    </div>
    <div class="right-container">
        <div class="top-container"></div>
        <div class="email-submit"></div>
    </div>
    <div class="top-container"></div>
    <div class="center-container">
        <div class="mail-body">
            <div class="mail-body-container">
                <form:form action="sendmail" method="post" id="mailForm">
                    <label>Имя<span class="required">*</span></label>
                    <input title="Введите имя." type="text" maxlength="40" name="name" id="nameField" required />
                    <label>Email<span class="required">*</span></label>
                    <input title="Введите email." type="text" maxlength="80" name="email" id="emailField" required />
                    <label>Сообщение<span class="required">*</span></label>
                    <textarea rows="4" name="body" maxlength="1000" id="messageField" required ></textarea>
                    <div class="g-recaptcha" data-sitekey="6Ldf0AgUAAAAACdVi3u5AiWVjPXsRlQazQZUQss4"></div>
                    <input type="hidden" name="type" id="emailType" />
                </form:form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>