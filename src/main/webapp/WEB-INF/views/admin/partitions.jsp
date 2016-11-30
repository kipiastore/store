<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Разделы</title>
<link rel="stylesheet" href="<c:url value="/resources/css/admin.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<body>
<%@include file="/WEB-INF/views/admin/components/logout.jspf"%>
<%@include file="/WEB-INF/views/admin/components/header.jspf"%>
<div class="body">
    <div class="pageMenu">
        <div class="pr"></div>
        <span class="pageMenuButt">Добавить</span>
    </div>
    <div class="menuBody">
        <div class="generalContent">
            <span class="error" id="addError">${addError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>
            <form:form action="addpartition" modelAttribute="partition"  method="post" id="createForm">
                <label>Название раздела/подраздела<span class="required">*</span></label>
                <input title="Введите название раздела." maxlength="120" autofocus type="text" name="name" required />
                <label>Тип раздел/подраздел</label>
                <select name="partitionLevel" id="partitionLevel" title="">
                    <option value="1">Раздел</option>
                    <option value="2">Подраздел</option>
                </select>
                    <div id="show" style="display:none">
                        <label>Выбирите название раздела к которому будет пренадлижать создаваемый подрездел</label>
                        <select name="namePartition" title="">
                            <c:forEach var="item" items="${model.partitionItems}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                <input type="submit" value="Добавить" />
            </form:form>
        </div>
    </div>
</div>
<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Разделы</span>
    </div>
    <div class="menuBody">
        <form:form action="deletepartition" method="post" id="deleteForm">
            <c:forEach var="key" items="${model.subPartitionsGroupedByPartition.keySet()}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemHeadInfo" data-id="${key.id}">
                        <span class="soloTest" data-id="${key.id}">${key.name}</span>
                    </div>
                </div>
                <div style="display: none" id="itemsID-${key.id}">
                    <c:forEach var="item" items="${model.subPartitionsGroupedByPartition.get(key)}">
                        <div class="menuBodyItem" >
                            <div class="menuBodyItemInfo" style="cursor: auto;" id="ID-${item.id}">
                                <span class="soloTest" id="ID-${item.id}">${item.name}</span>
                            </div>
                            <div class="menuBodyItemButt">
                                <div class="menuBodyItemButtDel" id="ID-${item.id}"></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
            <input type="hidden" name="partitionId" id="deleteKey" />
        </form:form>
    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/admin/adminPartition.js" />"></script>
</body>
</html>
