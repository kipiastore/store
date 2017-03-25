<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Разделы</title>
<link rel="stylesheet" href="<c:url value="/resources/css/director.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<body>
<%@include file="/WEB-INF/views/director/components/logout.jspf"%>
<%@include file="/WEB-INF/views/director/components/header.jspf"%>
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
            <form:form action="updatepartition" modelAttribute="partition" method="post" id="updateForm">
                <label>Название раздела/подраздела<span class="required">*</span></label>
                <input title="Введите название раздела." maxlength="120" autofocus type="text" name="name" required id="keyName"/>
                <input type="hidden" name="id" id="updateKey" />
                <input type="hidden" name="type" id="updateType" />
                <input type="submit" value="Обновить" />
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
                    <div class="menuBodyItemHeadInfo" id="ID-${key.id}" data-name="${key.name}" data-id="${key.id}">
                        <span class="soloTest" id="ID-${key.id}" data-name="${key.name}" data-id="${key.id}">${key.name}</span>
                    </div>
                    <div class="menuBodyItemButt">
                        <div class="menuBodyItemButtDel" data-type="partition" id="ID-${key.id}"></div>
                    </div>
                </div>
                <div style="display: none" class="subItemsList" id="itemsID-${key.id}">
                    <c:forEach var="item" items="${model.subPartitionsGroupedByPartition.get(key)}">
                        <div class="menuBodyItem" >
                            <div class="menuBodyItemInfo" id="ID-${item.id}">
                                <span class="soloTest" data-type="subPartition" data-name="${item.name}" id="ID-${item.id}">${item.name}</span>
                            </div>
                            <div class="menuBodyItemButt" id="menuBodyItemButtId">
                                <div class="menuBodyItemButtDel" data-type="subPartition" id="ID-${item.id}"></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
            <input type="hidden" name="deleteId" id="deleteKey" />
            <input type="hidden" name="type" id="deleteType" />
        </form:form>
    </div>
</div>
<style>
    .menuBodyItemHeadInfo {
        //float: right;
        width: 245px;
    }
</style>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/director/directorPartition.js" />"></script>
</body>
</html>
