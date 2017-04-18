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
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<a name="top" id="top"></a>
<form method="get" action="<c:url value="/search"/>" id="mainForm">
    <%@include file="/WEB-INF/views/portal/components/topBar.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/header.jspf"%>
    <div style="margin-top: -30px;">
        <div style="padding: 1px 0 30px 0;">

            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb">Разделы</h2>
                <div class="rptShort">
                    <div style="float: left;width: 465px;">
                        <k:forEach var="partitionItem" items="${model.partitionItems2}">
                            <p>
                            <span class="subsection" data-id="${partitionItem.partitionId}" id="item-${partitionItem.partitionId}">
                                <span class="subsection-list" data-id="${partitionItem.partitionId}">
                                    <k:if test="${partitionItem.subPartitionItems == null}" >
                                        Список пуст!
                                    </k:if>
                                    <k:forEach var="subPartitionItem" items="${partitionItem.subPartitionItems}">
                                        <span class="rua-p-c-red2">${subPartitionItem.companyCount}</span>
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
                    <div style="float: right;width: 455px;">
                        <k:forEach var="partitionItem" items="${model.partitionItems}">
                            <p>
                            <span class="subsection" data-id="${partitionItem.partitionId}" id="item-${partitionItem.partitionId}">
                                <span class="subsection-list" data-id="${partitionItem.partitionId}">
                                    <k:if test="${partitionItem.subPartitionItems == null}" >
                                        Список пуст!
                                    </k:if>
                                    <k:forEach var="subPartitionItem" items="${partitionItem.subPartitionItems}">
                                        <span class="rua-p-c-red2">${subPartitionItem.companyCount}</span>
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
    </div>
    <%@include file="/WEB-INF/views/portal/components/invitation.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>