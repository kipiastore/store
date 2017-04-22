<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<head>
    <title>Заметки</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/manager.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/managerDatepicker/jquery-ui.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/managerDatepicker/jquery-ui.structure.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/managerDatepicker/jquery-ui.theme.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/managerDatepicker/datepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/managerDatepicker/jquery-ui.js" />"></script>
</head>
<body>
<%@include file="/WEB-INF/views/manager/components/logout.jspf"%>
<%@include file="/WEB-INF/views/manager/components/header.jspf"%>
<div class="body">
    <div class="pageMenu">
        <div class="pr">
            <form:form action="searchnotesbysecondpage" method="post" id="searchForm">
                <input type="text" maxlength="120" name="name" placeholder="Название"/>
                <input type="text" maxlength="120" name="phone" placeholder="Тел."/>
                <input type="text" maxlength="120" name="contractNum" placeholder="№ Дог.(где его взять?)"/>
                <input type="text" maxlength="120" name="email" placeholder="e-mail"/>
                <input type="text" maxlength="120" name="position" placeholder="position"/>
                <span class="searchButt"><img class="searchIcon" src="<c:url value="/resources/images/search.png" />"/></span>
            </form:form>
        </div>

    </div>
    <div class="menuBody">
        <div class="generalContent">
            <div class="container">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th id="thCompanyNameNoteBySearchMenu">Название</th>
                        <th id="thCompanyHistoryNoteBySearchMenu">История</th>
                        <th id="thCompanyPeriodNoteBySearchMenu">Срок</th>
                        <th id="thCompanyAmountNoteBySearchMenu">Сумма</th>
                        <th id="thCompanyNoteMainBySearchMenu">Примечание</th>
                        <th id="thCompanyLastNoteBySearchMenu">Последняя заметка</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.companyList}">
                        <tr>
                            <td class="tableName" id="ID-${item.id}">${item.nameForNotes}</td>
                            <td>${item.historyOfNote}</td>
                            <td>${item.periodOfContract}</td>
                            <td>${item.debt}</td>
                            <td>${item.noteMain}</td>
                            <td>${item.note}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div id="lineBetweenTables">
                </div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td id="tdCompanyDateNoteBySearchMenu">Дата</td>
                        <td id="tdCompanyHourNoteBySearchMenu">Время</td>
                        <td id="tdCompanyNameNoteBySearchMenu">Название</td>
                        <td id="tdCompanyLastNoteBySearchMenu">Тип заметки</td>
                        <td id="tdCompanyCommentNoteBySearchMenu">Комментарий</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.reminderList}">
                        <tr>
                            <td  class="tableName" id="ID-${item.id}">${item.dateOfNote}</td>
                            <td>${item.hourOfNote}</td>
                            <td>${item.name}</td>
                            <td>${item.typeOfNote}</td>
                            <td>${item.commentOfNote}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <form:form action="updatecompany" modelAttribute="company" method="post" id="updateForm">
                <div class="left-body">
                    <label>Название</label>
                    <input disabled type="text" name="name" id="name" />
                    <label>Менеджер</label>
                    <input disabled type="text" name="manager" id="manager" />
                    <label>Дата заключения договора</label>
                    <input disabled type="text" name="dateOfContract" id="dateOfContract"  />
                    <label>Срок договора от</label>
                    <input disabled type="text" name="dateOfStartContract" id="dateOfStartContract" />
                    <label>Срок договора до</label>
                    <input disabled type="text" name="dateOfEndContract" id="dateOfEndContract" />
                    <label>ФИО Директора</label>
                    <input title="Введите ФИО директора" type="text" maxlength="120" name="directorFullName" id="directorFullName" />
                    <label>Контактоне лицо</label>
                    <input title="Введите контактное лицо" type="text" maxlength="120" name="contactPerson" id="contactPerson" />
                    <label>E-mail</label>
                    <input title="Введите e-mail." type="text" maxlength="120" name="email" id="email" />
                    <label>Сайт</label>
                    <input title="Введите название сайта" type="text" maxlength="90" name="site" id="site" />
                    <div class="openRequisites">Реквизиты</div>
                    <div class="requisites">
                        <label>ИНН</label>
                        <input disabled type="text" name="inn" id="inn"/>
                        <label>Юридическое название</label>
                        <input title="Введите юридическое название" type="text" maxlength="120" name="legalName" id="legalName" />
                        <label>Юридический адрес</label>
                        <input title="Введите юридический адрес фирмы." maxlength="120" type="text" name="legalAddress" id="legalAddress"/>
                        <label>Телефон</label>
                        <input title="Введите номер телефона." maxlength="15" type="text" name="phone" id="phone" />
                        <label>Факс</label>
                        <input title="Введите номер факса." maxlength="15" type="text" name="fax" id="fax"/>
                    </div>
                    <div class="openAddress">Адресса</div>
                    <div class="address">
                        <%@include file="/WEB-INF/views/manager/components/updateAddress.jspf"%>
                    </div>
                    <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />
                    <input type="hidden" name="hiddenId" id="hiddenId" />
                    <input type="submit" value="Обновить" id="submitManagerFormUpdate" />
                </div>
            </form:form>

            <div class="right-body">
                <div class="reminder">
                    <form:form id="addReminderForm" name="addReminderForm">
                        <table id="tableReminder">
                            <tr>
                                <td colspan="2"align="center" id="trReminder">Напоминание</td>
                            </tr>
                            <tr>
                                <td colspan="2"align="center">назначить на:</td>
                            </tr>
                            <tr>
                                <br>
                            <tr>
                                <td>
                                    <select title="Введите часы" name="hourReminder" id="selectReminderHours">
                                        <option value="1:00">1:00</option>
                                        <option value="2:00">2:00</option>
                                        <option value="3:00">3:00</option>
                                        <option value="4:00">4:00</option>
                                        <option value="5:00">5:00</option>
                                        <option value="6:00">6:00</option>
                                        <option value="7:00">7:00</option>
                                        <option value="8:00">8:00</option>
                                        <option value="9:00">9:00</option>
                                        <option value="10:00">10:00</option>
                                        <option value="11:00">11:00</option>
                                        <option value="12:00">12:00</option>
                                        <option value="13:00">13:00</option>
                                        <option value="14:00">14:00</option>
                                        <option value="15:00">15:00</option>
                                        <option value="16:00">16:00</option>
                                        <option value="17:00">17:00</option>
                                        <option value="18:00">18:00</option>
                                        <option value="19:00">19:00</option>
                                        <option value="20:00">20:00</option>
                                        <option value="21:00">21:00</option>
                                        <option value="22:00">22:00</option>
                                        <option value="23:00">23:00</option>
                                        <option value="00:00">00:00</option>
                                    </select>
                                </td>
                                <td>
                                    <input title="Введите дату." pattern="\d{4}-\d{1,2}-\d{1,2}"type="text" name="dateReminder" id="reminderDate" placeholder="Дата" required  />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <select title="Введите тип напоминания" name="typeReminder" id="selectReminderType">
                                        <option selected value="Перезвонить">Перезвонить</option>
                                        <option value="Отказ">Отказ</option>
                                        <option value="Встреча">Встреча</option>
                                        <option value="Закрылись">Закрылись</option>
                                        <option value="Другое">Другое</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <textarea name="commentReminder" id="reminderComment"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit"  value="Создать" id="submitManagerFormReminder"/>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </div>
                <div class="notes">
                    <form:form id ="deleteReminderForm">
                        <table class="tableNotes">
                            <thead class="theadShowReminders">
                            <tr>
                                <td colspan="5"align="center" id="trNotes">Заметки</td>
                            </tr>
                            <tr>
                                <td style="width: 65px;">Дата </td>
                                <td style="width: 72px;">Время</td>
                                <td style="width: 80px;">Статус</td>
                                <td style="width: 280px;">Заметки</td>
                                <td>Редактировать</td>
                            </tr>
                            </thead>
                            <tbody  class="tbodyShowReminders">
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" name="hiddenIdCompanyReminder" id="hiddenIdCompanyReminder" />
<input type="hidden" name="hiddenNameCompanyReminder" id="hiddenNameCompanyReminder" />
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
</div>
<%@include file="/WEB-INF/views/manager/components/menu.jspf"%>
<!--<span class="dataJson">${model.companiesJson}</span>-->
<span class="addingUserJson">${model.addingCompanyJson}</span>
<span class="companyAddressJson">${model.companyAddressJson}</span>
<span class="companyReminderJson">${model.companyReminderJson}</span>
<script type="text/javascript" src="<c:url value="/resources/js/manager.js" />"></script>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
</body>
</html>
