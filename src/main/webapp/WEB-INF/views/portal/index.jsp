<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная Одессы | Что-то ищете? Доверьте это профессионалам!</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
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
        <div style="padding: 1px 0 15px 0;border-bottom: 20px solid #3d7677;">
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb title">Разделы</h2>
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
            $.get('api/portal/resource/v1/search/' + value, function (entry) {
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