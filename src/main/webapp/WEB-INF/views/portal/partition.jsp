<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная Одессы | ${model.partitionItem.partitionName} в Одессе</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home-min2.css" />"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<a name="top" id="top"></a>
<span class="style-container"></span>
<form method="get" action="<c:url value="/search"/>" id="mainForm">
    <%@include file="/WEB-INF/views/portal/components/topBar.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/header.jspf"%>
    <div style="background: rgba(126, 126, 126, 0.04);">
        <div style="padding: 1px 0 15px 0;">
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb title"><a class="main-ref" href="../index">Главная страница</a> → ${model.partitionItem.partitionName} в Одессе</h2>
                <div class="rptShort hide-bt">
                    <k:if test="${model.partitionItem.subPartitionItems2.size() > 0}">
                        <div style="float: left;width: 450px;">
                            <k:forEach var="item" items="${model.partitionItem.subPartitionItems2}">
                                <p>
                                    <a data-id="${item.subPartitionId}" href="../subPartition/${item.subPartitionId}">${item.subPartitionName}</a>
                                    <span class="rua-p-c-red"><b>${item.companyCount}</b></span>
                                </p>
                            </k:forEach>
                        </div>
                        <div style="float: right;width: 450px;">
                            <k:forEach var="item" items="${model.partitionItem.subPartitionItems1}">
                                <p>
                                    <a data-id="${item.subPartitionId}" href="../subPartition/${item.subPartitionId}">${item.subPartitionName}</a>
                                    <span class="rua-p-c-red"><b>${item.companyCount}</b></span>
                                </p>
                            </k:forEach>
                        </div>
                    </k:if>
                    <k:if test="${model.partitionItem.subPartitionItems2.size() == 0}">
                        <h2 class="headline centered mtmb" style="color: #6d7983;">Извините, раздел еще не заполнен!</h2>
                    </k:if>
                </div>
                <div class="open-list-btn">Показать все!</div>
                <script>
                    var rptShort = $('.rptShort');
                    $('.style-container').html('<style>.rptShort-open { max-height: ' + rptShort.height() + 'px; }</style>');
                    if (rptShort.height() < 550)
                        $('.open-list-btn').hide();
                    else
                        rptShort.addClass('rptShort-closed');
                </script>
            </div>
        </div>
    </div>
    <k:if test="${not empty model.companyHiPrior}">
        <div style="margin-top: 0px;background: rgba(126, 126, 126, 0.04);">
            <div style="padding: 1px 0 1px 0">
                <div class="rua-l-wrapper">
                    <h2 class="headline centered mtmb title" style="margin-top: 0px;">Редакция рекомендует</h2>
                </div>
            </div>
        </div>
        <div style="margin-top: 0px;background: rgba(126, 126, 126, 0.04);">
            <div style="padding: 1px 0 1px 0;border-bottom: 20px solid #3d7677;">
                <k:forEach var="item" items="${model.companyHiPrior}">
                    <div class="rua-l-wrapper2" style="border-color: hsla(0,${item.colorPoint}%,66%,1)">
                        <div class="companyMainInfo">
                            <a data-id="${item.companyId}" href="../company/${item.companyId}">
                                <h3>${item.companyName}</h3>
                            </a>
                            <span>${item.companyInformation}</span>
                            <span class="companyAmount"><k:if test="${not empty item.costOf}">Стоимость: <b>${item.costOf}</b></k:if></span>
                        </div>
                        <k:if test="${model.companyToCompanyAddress.get(item.companyId).size() > 0}">
                            <div class="AddressList">
                                <k:set var="count" value="0" scope="page" />
                                <k:forEach var="addresItem" items="${model.companyToCompanyAddress.get(item.companyId)}">
                                    <k:if test="${count == 0}">
                                        <div class="address">
                                            <span class="addressInfo">${addresItem.address}&nbsp;</span>
                                            <span>${addresItem.phones}&nbsp;</span>
                                            <span>${addresItem.information}</span>
                                        </div>
                                    </k:if>
                                    <k:if test="${count > 0}">
                                        <div class="hiddenAdr-btn">
                                            <p><a class="btn btn-primary" data-id="${item.companyId}">Филиалы</a></p>
                                        </div>
                                        <div class="address hiddenAdr address-${item.companyId}">
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
                </k:forEach>
            </div>
        </div>
    </k:if>
    <k:if test="${empty model.companyHiPrior}">
        <div class="line-separator"></div>
    </k:if>
    <%@include file="/WEB-INF/views/portal/components/invitation.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
<script>
    var previousDateInMilliseconds = new Date().getTime();
    var searchInput = $('#search-param');
    searchInput.on("input", function(event) {
        var currentDateInMilliseconds = new Date().getTime();
        console.log(currentDateInMilliseconds);
        if (currentDateInMilliseconds - previousDateInMilliseconds < 300) {
            return;
        } else {
            previousDateInMilliseconds = currentDateInMilliseconds;
        }
        var value = searchInput.val();
        console.log(value);
        if (value != '') {
            $.get('../api/portal/resource/v1/search/' + value, function (entry) {
                console.log(entry);
                searchInput.autocomplete({
                    source: entry,
                    minLength: 0,
                    autoFocus: true
                });
                searchInput.autocomplete('search', value);
            });
        }
    });
</script>
<style>
    .ui-widget.ui-widget-content {
        box-shadow: 0px 0px 0px 0 rgba(0,0,0,0.16),0 0px 5px 0 rgba(0,0,0,0.12)!important;
        border: none;
    }
    .ui-menu .ui-state-focus, .ui-menu .ui-state-active {
        margin: 0px;
    }
    .ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active, a.ui-button:active, .ui-button:active, .ui-button.ui-state-active:hover {
        background: #eef2f7;
        font-weight: normal;
        color: #3d7677;
        box-shadow: 0px 0px 0px 0 rgba(0,0,0,0.16),0 0px 1px 0 rgba(0,0,0,0.12)!important;
        border: none;
    }
</style>
</body>
</html>