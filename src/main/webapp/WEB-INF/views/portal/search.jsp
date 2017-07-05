<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная Одессы | Поиск в Одессе</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<a name="top" id="top"></a>
<span class="style-container"></span>
<form method="get" action="<c:url value="/search"/>" id="mainForm">
    <%@include file="/WEB-INF/views/portal/components/topBar.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/header.jspf"%>
    <div style="background: rgba(126, 126, 126, 0.04);">
        <div style="padding: 1px 0 1px 0;">
            <div class="rua-l-wrapper">
                <div class="searchform form-inline">
                    <div class="rua-l-wrapper">
                        <div>
                            <div class="rua-g-right">
                                <a class="btn btn-primary btn-block submit_button" onclick="$('#mainForm').submit()">Поиск</a>
                            </div>
                            <div class="rua-g-clearfix">
                                <div>
                                    <input name="value" type="text" maxlength="255" class="keyword input-block-level ui-autocomplete-input"
                                           placeholder="Введите ключевые слова" autocomplete="off" id="search-param"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <h2 class="headline centered mtmb title as">Найдено в рубриках</h2>
            </div>
        </div>
    </div>
    <div style="background: rgba(126, 126, 126, 0.04);">
        <div style="padding: 1px 0 1px 0;">
            <div class="rua-l-wrapper">

                <div class="rptShort hide-bt">
                    <k:if test="${model.partitionItems2.size() > 0}">
                        <div style="float: left;width: 450px;">
                            <k:forEach var="partitionItem" items="${model.partitionItems2}">
                                <p>
                                    <span class="subsection" data-id="${partitionItem.partitionId}" id="item-${partitionItem.partitionId}">
                                        <span class="subsection-list" data-id="${partitionItem.partitionId}">
                                            <k:if test="${partitionItem.subPartitionItems == null}">Список пуст!</k:if>
                                            <k:forEach var="subPartitionItem" items="${partitionItem.subPartitionItems}">
                                                <span class="rua-p-c-red2"><b>${subPartitionItem.companyCount}</b></span>
                                                <a data-id="${partitionItem.partitionId}" href="subPartition/${subPartitionItem.subPartitionId}">${subPartitionItem.subPartitionName}</a>
                                                <br/>
                                            </k:forEach>
                                        </span>
                                    </span>
                                    <a data-id="${partitionItem.partitionId}" href="partition/${partitionItem.partitionId}">${partitionItem.partitionName}</a>
                                    <span class="rua-p-c-red"><b>${partitionItem.companyCount}</b></span>
                                    <span class="show-subsection" data-id="${partitionItem.partitionId}"></span>
                                </p>
                            </k:forEach>
                        </div>
                        <div style="float: right;width: 450px;">
                            <k:forEach var="partitionItem" items="${model.partitionItems}">
                                <p>
                                    <span class="subsection" data-id="${partitionItem.partitionId}" id="item-${partitionItem.partitionId}">
                                        <span class="subsection-list" data-id="${partitionItem.partitionId}">
                                            <k:if test="${partitionItem.subPartitionItems == null}">Список пуст!</k:if>
                                            <k:forEach var="subPartitionItem" items="${partitionItem.subPartitionItems}">
                                                <span class="rua-p-c-red2"><b>${subPartitionItem.companyCount}</b></span>
                                                <a data-id="${partitionItem.partitionId}" href="subPartition/${subPartitionItem.subPartitionId}">${subPartitionItem.subPartitionName}</a>
                                                <br/>
                                            </k:forEach>
                                        </span>
                                    </span>
                                    <a data-id="${partitionItem.partitionId}" href="partition/${partitionItem.partitionId}">${partitionItem.partitionName}</a>
                                    <span class="rua-p-c-red"><b>${partitionItem.companyCount}</b></span>
                                    <span class="show-subsection" data-id="${partitionItem.partitionId}"></span>
                                </p>
                            </k:forEach>
                        </div>
                    </k:if>
                    <k:if test="${model.partitionItems2.size() == 0}">
                        <h2 class="headline centered mtmb" style="color: #6d7983;">К сожалению, по Вашему запросу ничего не найдено.</h2>
                    </k:if>
                </div>
            </div>
        </div>
    </div>
    <div style="background: rgba(126, 126, 126, 0.04);">
        <div style="padding: 1px 0 1px 0;">
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb title as">Результаты поиска в Одессе</h2>
            </div>
        </div>
    </div>
    <div style="background: rgba(126, 126, 126, 0.04);">
        <div style="padding: 1px 0 15px 0;">
            <div class="rua-l-wrapper">
                <div class="rptShort">
                    <k:if test="${subPartitions2.size() > 0}">
                        <div style="float: left;width: 450px;">
                            <k:forEach var="item" items="${subPartitions2}">
                                <p>
                                    <a data-id="${item.subPartitionId}" href="subPartition/${item.subPartitionId}">${item.subPartitionName}</a>
                                    <span class="rua-p-c-red"><b>${item.companyCount}</b></span>
                                </p>
                            </k:forEach>
                        </div>
                        <div style="float: right;width: 450px;">
                            <k:forEach var="item" items="${subPartitions1}">
                                <p>
                                    <a data-id="${item.subPartitionId}" href="subPartition/${item.subPartitionId}">${item.subPartitionName}</a>
                                    <span class="rua-p-c-red"><b>${item.companyCount}</b></span>
                                </p>
                            </k:forEach>
                        </div>
                    </k:if>
                    <k:if test="${subPartitions2.size() == 0}">
                        <h2 class="headline centered mtmb" style="color: #6d7983;">К сожалению, по Вашему запросу ничего не найдено.</h2>
                    </k:if>
                </div>
            </div>
        </div>
    </div>
    <k:forEach var="item" items="${companies}">
        <div style="margin-top: 0px;background: rgba(126, 126, 126, 0.04);">
            <div style="padding: 1px 0 1px 0;">
                <div class="rua-l-wrapper2" style="border-color: hsla(0,${ packageToColor.get(item.companyPackageId) }%,66%,1)">
                    <div class="companyMainInfo">
                        <k:if test="${not empty item.imageId && item.isPaid == true}">
                            <img class="position-image" src="download?id=${item.imageId}" title="">
                        </k:if>
                        <div class="company-text-block">
                            <a data-id="${item.id}" href="company/${item.id}">
                                <h3>${item.name} → <span class="visitors">Просмотров: ${item.countCompany}</span></h3>
                            </a>
                            <span>${item.description}</span>
                            <p>
                                <a title="${item.name} - Каталог товаров Одесса" href="company/${item.id}">Показать весь список товаров/услуг фирмы "${item.name}"</a>
                                <!--
                                <k:if test="${not empty item.costOf}">
                                    <span class="companyAmount">Стоимость: <b>${item.costOf}</b></span>
                                </k:if>
                                <k:if test="${empty item.costOf}">
                                    <span class="companyAmount">Цену уточняйте</span>
                                </k:if>-->
                            </p>
                        </div>
                    </div>
                    <div class="container-end"></div>
                    <k:if test="${companyToCompanyAddress.get(item.id).size() > 0}">
                        <div class="AddressList">
                            <k:set var="count" value="0" scope="page" />
                            <k:forEach var="addresItem" items="${companyToCompanyAddress.get(item.id)}">
                                <k:if test="${count == 0}">
                                    <div class="address">
                                        <span class="addressInfo">${addresItem.address}&nbsp;</span>
                                        <span>${addresItem.phones}&nbsp;</span>
                                        <span>${addresItem.information}</span>
                                    </div>
                                </k:if>
                                <k:if test="${count > 0}">
                                    <div class="hiddenAdr-btn">
                                        <p><a class="btn btn-primary" data-id="${item.id}">Филиалы</a></p>
                                    </div>
                                    <div class="address hiddenAdr address-${item.id}">
                                        <span class="addressInfo">${addresItem.address}&nbsp;</span>
                                        <span>${addresItem.phones}&nbsp;</span>
                                        <span>${addresItem.information}</span>
                                    </div>
                                </k:if>
                                <k:set var="count" value="${count + 1}" scope="page"/>
                            </k:forEach>
                        </div>
                    </k:if>
                </div>
            </div>
        </div>
    </k:forEach>
    <div class="line-separator"></div>
    <%@include file="/WEB-INF/views/portal/components/invitation.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
<script>
    setAutocomplete('<c:url value="/api/portal/resource/v1/search/"/>');
</script>
<script>
    $(document).ready(function(){
        $('.paulund_modal').paulund_modal_box();
    });
</script>
</body>
</html>