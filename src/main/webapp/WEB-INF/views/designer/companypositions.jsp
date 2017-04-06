<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Позиции</title>
<link rel="stylesheet" href="<c:url value="/resources/css/designer.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/multi-select.css" />" >
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.structure.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.theme.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datapicker/datepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datapicker/jquery-ui.js" />"></script>
<body>
<%@include file="/WEB-INF/views/designer/components/logout.jspf"%>
<%@include file="/WEB-INF/views/designer/components/header.jspf"%>

<div class="body">
    <div class="pageMenu">
        <div class="pr">
            <form:form action="../../positionsearchcompany" method="post" id="searchForm">
                <span class="searchButt"><img class="searchIcon" src="<c:url value="/resources/images/search.png" />"/></span>
                <input type="text" maxlength="120" name="name" placeholder="Фирма"/>
                <input type="text" maxlength="120" name="legalName" placeholder="Юр. Назв."/>
                <input type="text" maxlength="120" name="phone" placeholder="Тел."/>
                <input type="text" maxlength="120" name="contractNum" placeholder="№ Дог."/>
                <input type="text" maxlength="120" name="email" placeholder="e-mail"/>
            </form:form>
        </div>
        <span class="pageMenuButt" style="opacity: 0; cursor: default; width: 0px">С</span>
    </div>
    <div class="menuBody">
        <div class="pre-loading">
            <div class="loading">

            </div>
        </div>
        <div class="generalContent">
            <span class="error" id="addError">${addError}</span>
            <span class="error">${updateError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>
            <span class="localMessage"></span>
            <span class="button create" >Добавить</span>
            <div style="height: 5px;"></div>
            <c:forEach var="item" items="${model.companySubpartitionContentList}">
                <div class="position-main-container def-block">
                    <div class="position-block">
                        <div class="position-addintional-block">
                            <span data-id="${item.companySubpartitionId}" id="subNameId-${item.id}">${item.subPartitionName}</span>
                        </div>
                        <div class="position-image-block">
                            <img class="position-image" id="imageId-${item.id}" data-id="${item.imageId}" src="../../download?id=${item.imageId}" title="">
                        </div>
                        <div class="position-text-block">
                            <span id="infoId-${item.id}">${item.info}</span>
                        </div>
                        <div class="position-addintional-block-a">
                            <span class="button delete" data-id="${item.id}">Удалить</span>
                            <span class="button edit" data-id="${item.id}">Редактировать</span>
                        </div>
                        <hr/>
                    </div>
                </div>
            </c:forEach>

            <form:form action="../../addsubpartitioninfos" method="post" id="createForm" modelAttribute="companySubpartitionContent" enctype="multipart/form-data">
                <div class="def-block">
                    <div class="position-addintional-block-b">
                        <label>Позиция<span class="required">*</span></label>

                        <select name="companySubpartitionId" title="" required id="createCompanySubpartitionId">
                            <c:forEach var="item" items="${model.subPartitionItemList}">
                                <option value="${item.companySubpartitionId}">${item.subpartitionName}</option>
                            </c:forEach>
                        </select>

                        <label>Файл<span class="required">*</span></label>
                        <input type="file" required name="file" title="gif jpeg jpg png bmp" id="createFile"/>
                        <label>Информация<span class="required">*</span></label>
                        <textarea rows="10" required name="info" maxlength="1600" id="createDescription"></textarea>
                        <input type="hidden" name="companyId" value="${model.companyId}" />
                        <span class="button create-cancel" >Отменить</span>
                        <span class="button create-submit" >Сохранить</span>
                    </div>
                </div>
            </form:form>
            <form:form action="../../editsubpartitioninfos" modelAttribute="companySubpartitionContent" enctype="multipart/form-data" method="post" id="updateForm">
                <div class="def-block">
                    <div class="position-addintional-block-a">
                        <div class="position-addintional-block">
                            <span class="subPartitionNameSpan"></span>
                        </div>
                        <label>Файл</label>
                        <input type="file" name="file" title="gif jpeg jpg png bmp" id="updateFile"/>
                        <textarea rows="10" name="info" maxlength="1600" id="updateDescription"></textarea>
                        <input type="hidden" name="companyId" value="${model.companyId}" />
                        <input type="hidden" name="contentId" id="updateCompanySubpartitionContentId"  />
                        <input type="hidden" name="companySubpartitionId" id="updateCompanySubpartitionId" />
                        <input type="hidden" name="imageId" id="updateImageId" />
                        <span class="button edit-cancel" >Отменить</span>
                        <span class="button edit-submit" >Сохранить</span>
                    </div>
                </div>
            </form:form>

            <form:form action="../../deletesubpartitioninfos" method="post" id="deleteForm">
                <input type="hidden" name="companyId" value="${model.companyId}" />
                <input type="hidden" name="companySubpartitionContentId" id="companySubpartitionContentId"  />
            </form:form>

        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Позиции</span>
    </div>
    <div class="menuBody">

    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.multi-select.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/designerPosition.js" />"></script>
<style>
    .pr {
        //float: left;
        //height: 40px;
    }
    .ms-container {
        background: transparent url(<c:url value="/resources/images/switch.png" />) no-repeat 50% 50%;
        width: auto;
    }
    @media screen and (max-width : 1136px) {
        .body .pageMenu { height: 76px; }
    }
</style>
<style>
    .def-block {
        //max-width: 900px;
        margin: auto;
        height: 370px;
    }
    .position-block {

    }
    .position-image-block {
        float: left;
    }
    .position-image {
        height: 250px;
        padding: 10px;
    }
    .position-text-block {
        padding: 10px;
        text-align: justify;
    }
    .position-addintional-block {
        float: left;
        width: 100%;
        text-align: center;
        padding-bottom: 20px;
        padding-top: 10px;
    }
    .position-addintional-block-a {
        float: left;
        width: 100%;
    }
    .button {
        display: block;
        border: 1px solid #a8cb98;
        width: 130px;
        text-align: center;
        padding: 6px;
        background-color: #16a085;
        color: #fff;
        cursor: pointer;
        border-radius: 4px;
        margin-left: 20px;
        float: right;
    }
    hr {
        float: left;
        width: 100%;
        border: 0;
        height: 1px;
        background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
    }
    .button a {
        color: #fff;
    }
    .button a:hover {
        color: #fff;
    }
    #deleteForm {
        display: none;
    }
    #updateForm {
        display: none;
    }
    #createForm {
        display: none;
    }
</style>
</body>
</html>
