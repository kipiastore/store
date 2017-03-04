var isShowCreateForm = false;
var isShowUpdateForm = false;
var isShowAddCompanyReminderForm = false;
var currentItem;
var pageMenuButtTarget;

var data;
var pageInformation;
// user fileds
var updateForm;
var addCompanyReminderForm;
var fullName;
var username;
var password;
var password2;
var accessStatus;
var usernameHidden;
// company fields
var companyName;
var keywords;
var dateOfContract;
var dateOfStartContract;
var dateOfEndContract;
var manager;
var companyPackageId;
var costOf;
var legalName;
var inn;
var legalAddress;
var phone;
var fax;
var directorFullName;
var contactPerson;
var hiddenId;
var description;
var isShowForOperator;
var isShowForSite;
var isPaid;
var isRedirect;
var isOffPosition;
var isClosed;
var isPriority;
var email;
var site;
var addressArray;
var BreakException = {};
var dataCompanyAddressJson;
var dataCompanyReminderJson;
var dataCompanyReminderJsonAnswer;

var hourReminder;
var dateReminder;
var typeReminder;
var commentReminder;
var hiddenIdCompanyReminder;
var hiddenNameCompanyReminder;
var idReminder;
$(window).on("load", function () {
    var container = $(".container");
    container.show();
});
function cleanAddressBlocks() {
    addressArray = [];
    var item;
    for (var i = 1; i < 13; i++) {
        item = {};
        item.id = i;
        item.isOpen = false;
        addressArray[i] = item;
        $("#add" + i).hide();
        $("#adAd" + i).removeAttr('required');
        $("#adAd" + i).val("");
        $("#regAd" + i).val("-1");
        $("#phAd" + i).val("");
        $("#infAd" + i).val("");
        $("#UpAdd" + i).hide();
        $("#UpAdAd" + i).removeAttr('required');
        $("#UpAdAd" + i).val("");
        $("#UpRegAd" + i).val("-1");
        $("#UpPhAd" + i).val("");
        $("#UpInfAd" + i).val("");
        $("#UpAddId" + i).val("");
    }
}


$(".tableName").on("click", function (event) {
    var container = $(".container");
    container.animate({opacity: 0}, 200);
    setTimeout(function() { container.hide(); }, 190);
    updateForm = $("#updateForm");
    addCompanyReminderForm=$(".right-body");
    setTimeout(function() {
        updateForm.show();
        updateForm.animate({opacity: 1}, 200);
        addCompanyReminderForm.show();
        addCompanyReminderForm.animate({opacity: 1}, 200);
    }, 200);
    isShowUpdateForm = true;
    isShowAddCompanyReminderForm=true;
    currentItem = event.target.getAttribute("id");
    $("div#" + currentItem).css("border-left", "2px solid #d87f7f");
    $(".menuBodyItemButtDel").css("border-left", "0");
    var id = currentItem.replace("ID-", "");
    loadCompany(id);
});

/*
 function loadCompany(id) {
 if (data == undefined) {
 data = $.parseJSON($(".dataJson")[0].innerHTML);
 companyName = $("#name");
 keywords = $("#keywords");
 dateOfContract = $("#dateOfContract");
 dateOfStartContract = $("#dateOfStartContract");
 dateOfEndContract = $("#dateOfEndContract");
 manager = $("#manager");
 companyPackageId = $("#companyPackageId");
 costOf = $("#costOf");
 legalName = $("#legalName");
 inn = $("#inn");
 legalAddress = $("#legalAddress");
 phone = $("#phone");
 fax = $("#fax");
 directorFullName = $("#directorFullName");
 contactPerson = $("#contactPerson");
 hiddenId = $("#hiddenId");
 description = $("#description");
 isShowForOperator = $("#isShowForOperator");
 isShowForSite = $("#isShowForSite");
 isPaid = $("#isPaid");
 isRedirect = $("#isRedirect");
 isOffPosition = $("#isOffPosition");
 isClosed = $("#isClosed");
 isPriority = $("#isPriority");
 email = $("#email");
 site = $("#site");
 hiddenIdCompanyReminder=$("#hiddenIdCompanyReminder");
 hiddenNameCompanyReminder=$("#hiddenNameCompanyReminder");
 }
 if (dataCompanyAddressJson == undefined) {
 dataCompanyAddressJson = $.parseJSON($(".companyAddressJson")[0].innerHTML);
 }
 if (dataCompanyReminderJson == undefined) {
 dataCompanyReminderJson = $.parseJSON($(".companyReminderJson")[0].innerHTML);
 }
 var item;
 addressArray = [];
 cleanAddressBlocks();
 data.forEach(function(entry) {
 if (entry.id == id) {
 companyName.val(entry.name);
 keywords.val(entry.keywords);
 dateOfContract.val(entry.dateOfContract.substring(0, 11));
 dateOfStartContract.val(entry.dateOfStartContract.substring(0, 11));
 dateOfEndContract.val(entry.dateOfEndContract.substring(0, 11));
 manager.val(entry.manager);
 companyPackageId.val(entry.companyPackageId);
 costOf.val(entry.costOf);
 legalName.val(entry.legalName);
 if (entry.inn != "null")
 inn.val(entry.inn);
 else
 inn.val("");
 legalAddress.val(entry.legalAddress);
 phone.val(entry.phone);
 fax.val(entry.fax);
 directorFullName.val(entry.directorFullName);
 contactPerson.val(entry.contactPerson);
 description.val(entry.description);
 email.val(entry.email);
 site.val(entry.site);
 isShowForOperator.prop('checked', newBoolean(entry.isShowForOperator));
 isShowForSite.prop('checked', newBoolean(entry.isShowForSite));
 isPaid.prop('checked', newBoolean(entry.isPaid));
 isRedirect.prop('checked', newBoolean(entry.isRedirect));
 isOffPosition.prop('checked', newBoolean(entry.isOffPosition));
 isClosed.prop('checked', newBoolean(entry.isClosed));
 isPriority.prop('checked', newBoolean(entry.isPriority));
 hiddenIdCompanyReminder.val(entry.id);
 hiddenNameCompanyReminder.val(entry.name);

 var countReminder=0;
 dataCompanyReminderJson.forEach(function(entry4) {     //создание дива для отображения  напоминаний
 if (entry4.companyId == entry.id) {
 for(var i=0;i<entry4.companyReminders.length;i++){   $("#tbodyShowReminders").html(
 $("#tbodyShowReminders").html()+' <tr id="trColorFill-'+i+'">'+
 '<td ><span  id="dateReminder-'+i+'"></span> </td>'+
 '<td ><span id="hourReminder-'+i+'"></span></td>'+
 '<td ><span id="typeReminder-'+i+'"></span></td>'+
 '<td ><span id="commentReminder-'+i+'"></span></td>'+
 '<td hidden="true" ><span id="hiddenReminder-'+i+'"></span></td>'+
 '<td ><div class="reminderDeleteClass" id="buttonReminder-'+i+'">Удалить</div></td>'+
 '</tr>');
 }
 $(".reminderDeleteClass").on("click", function (event) {
 var reminderId = event.target.getAttribute('id');
 var reminderHiddenId = $("#hiddenReminder-" + reminderId.replace("buttonReminder-", "")).html();
 var data = {};
 data["id"] = reminderHiddenId;
 data["companyId"] = $("#hiddenIdCompanyReminder").val();
 var token = $('#csrfToken').val();
 var header = $('#csrfHeader').val();
 $.ajax({
 type: "POST",
 url: "deletereminder",
 data: JSON.stringify(data),
 dataType: 'json',
 timeout: 600000,
 async : false,
 success: function (data) {        //получение ответа с rest сервера
 display(data);               //вызов йункции для отображения полученных с сервера данных
 },
 beforeSend: function(xhr) {
 xhr.setRequestHeader("Accept", "application/json");
 xhr.setRequestHeader("Content-Type", "application/json");
 xhr.setRequestHeader(header, token);
 },
 });
 return false;
 });
 entry4.companyReminders.forEach(function(entry5) {
 $( "#hourReminder-"+countReminder).html(entry5.hourReminder);
 $ ("#dateReminder-"+countReminder).html(entry5.dateReminder);
 $ ("#typeReminder-"+countReminder).html(entry5.typeReminder);
 $ ("#commentReminder-"+countReminder).html(entry5.commentReminder);
 $( "#hiddenReminder-"+countReminder).html(entry5.id);
 var typeReminder= entry5.typeReminder;
 if(typeReminder=="Перезвонить"){
 $ ("#trColorFill-"+countReminder).css("background-color", "#7dc573");
 }
 if(typeReminder=="Отказ"){
 $ ("#trColorFill-"+countReminder).css("background-color", "#f16c4d");
 }
 if(typeReminder=="Встреча"){
 $ ("#trColorFill-"+countReminder).css("background-color", "#4c89c1");
 }
 if(typeReminder=="Другое"){
 $ ("#trColorFill-"+countReminder).css("background-color", "#fff79a");
 }
 countReminder++;
 });
 }
 });
 jQuery(document).ready(    //отпрвка джисоном данных с формы в фоновом режиме
 function($) {
 $("#addReminderForm").submit(function(event) {
 var data = {};
 data["dateReminder"] =$("#reminderDate").val().toString();
 data["typeReminder"] = $("#selectReminderType").val();
 data["commentReminder"] = $("#reminderComment").val();
 data["companyId"] = $("#hiddenIdCompanyReminder").val();
 data["companyName"] = $("#hiddenNameCompanyReminder").val();
 data["hourReminder"] = $("#selectReminderHours").val();
 var token = $('#csrfToken').val();
 var header = $('#csrfHeader').val();
 console.log(JSON.stringify(data));
 $.ajax({
 type: "POST",
 url: "addreminder",
 data: JSON.stringify(data),
 dataType: 'json',
 timeout: 600000,
 async : false,
 success: function (data) {      //получение ответа с rest сервера
 display(data);               //вызов йункции для отображения полученных с сервера данных
 },
 beforeSend: function(xhr) {
 xhr.setRequestHeader("Accept", "application/json");
 xhr.setRequestHeader("Content-Type", "application/json");
 xhr.setRequestHeader(header, token);
 },
 });
 return false;
 });
 });

 function display(data) {
 $("#addReminderForm").trigger('reset');//reset  формы
 $("#tbodyShowReminders").html(""); //очистка дива
 countReminder = 0;
 dataCompanyReminderJsonAnswer = data ;
 dataCompanyReminderJsonAnswer.forEach(function (entry6) {     //создание напоминаний из ответа сервера(data)
 $("#tbodyShowReminders").html(
 $("#tbodyShowReminders").html() + ' <tr id="trColorFill-'+countReminder+'">' +
 '<td ><span  id="dateReminder-' + countReminder + '"></span> </td>' +
 '<td ><span id="hourReminder-' + countReminder + '"></span></td>' +
 '<td ><span id="typeReminder-' + countReminder + '"></span></td>' +
 '<td ><span id="commentReminder-' + countReminder + '"></span></td>' +
 '<td hidden="true"><span id="hiddenReminder-' + countReminder + '"></span></td>' +
 '<td ><div class="reminderDeleteClass" id="buttonReminder-' + countReminder + '">Удалить</div></td>' +
 '</tr>');
 countReminder++;
 });
 countReminder=0;
 dataCompanyReminderJsonAnswer.forEach(function (entry6) {     //создание напоминаний из ответа сервера(data)
 data.forEach(function (entry7) {
 $("#hourReminder-" + countReminder).html(entry7.hourReminder);
 $("#dateReminder-" + countReminder).html(entry7.dateReminder);
 $("#typeReminder-" + countReminder).html(entry7.typeReminder);
 $("#commentReminder-" + countReminder).html(entry7.commentReminder);
 $("#hiddenReminder-" + countReminder).html(entry7.id);
 var typeReminder= entry7.typeReminder;
 if(typeReminder=="Перезвонить"){
 $ ("#trColorFill-"+countReminder).css("background-color", "#7dc573");
 }
 if(typeReminder=="Отказ"){
 $ ("#trColorFill-"+countReminder).css("background-color", "#f16c4d");
 }
 if(typeReminder=="Встреча"){
 $ ("#trColorFill-"+countReminder).css("background-color", "#4c89c1");
 }
 if(typeReminder=="Другое"){
 $ ("#trColorFill-"+countReminder).css("background-color", "#fff79a");
 }
 countReminder++;
 });
 });
 $(".reminderDeleteClass").on("click", function (event) {
 var reminderId = event.target.getAttribute('id');
 var reminderHiddenId = $("#hiddenReminder-" + reminderId.replace("buttonReminder-", "")).html();
 var data = {};
 data["id"] = reminderHiddenId;
 data["companyId"] = $("#hiddenIdCompanyReminder").val();
 var token = $('#csrfToken').val();
 var header = $('#csrfHeader').val();
 $.ajax({
 type: "POST",
 url: "deletereminder",
 data: JSON.stringify(data),
 dataType: 'json',
 timeout: 600000,
 async : false,
 success: function (data) {      //получение ответа с rest сервера
 display(data);               //вызов йункции для отображения полученных с сервера данных
 },
 beforeSend: function(xhr) {
 xhr.setRequestHeader("Accept", "application/json");
 xhr.setRequestHeader("Content-Type", "application/json");
 xhr.setRequestHeader(header, token);
 },
 });
 return false;
 });
 }
 hiddenId.val(entry.id);
 for (var i = 1; i < 13; i++) {
 item = {};
 item.id = i;
 item.isOpen = false;
 addressArray[i] = item;
 $("#UpAdd" + i).hide();
 $("#UpAdAd" + i).removeAttr('required');
 $("#UpAdAd" + i).val("");
 $("#UpRegAd" + i).val("-1");
 $("#UpPhAd" + i).val("");
 $("#UpInfAd" + i).val("");
 $("#UpAddId" + i).val("");
 }

 var count = 1;
 dataCompanyAddressJson.forEach(function(entry2) {
 if (entry2.companyId == entry.id) {
 entry2.companyAddresses.forEach(function(entry3) {
 addressArray[count].isOpen = true;
 $("#UpAdd" + count).show();
 $("#UpAdAd" + count).prop("required", true);
 $("#UpAdAd" + count).val(entry3.address);
 $("#UpRegAd" + count).val(entry3.regionId);
 $("#UpPhAd" + count).val(entry3.phones);
 $("#UpInfAd" + count).val(entry3.information);
 $("#UpAddId" + count).val(entry3.id);
 count++;
 });
 calculatePosition(addressArray, "UpAdd");
 }
 });
 }
 });
 }
 */

function loadCompany(id) {
    if (companyName == undefined) {
        companyName = $("#name");
        keywords = $("#keywords");
        dateOfContract = $("#dateOfContract");
        dateOfStartContract = $("#dateOfStartContract");
        dateOfEndContract = $("#dateOfEndContract");
        manager = $("#manager");
        companyPackageId = $("#companyPackageId");
        costOf = $("#costOf");
        legalName = $("#legalName");
        inn = $("#inn");
        legalAddress = $("#legalAddress");
        phone = $("#phone");
        fax = $("#fax");
        directorFullName = $("#directorFullName");
        contactPerson = $("#contactPerson");
        hiddenId = $("#hiddenId");
        description = $("#description");
        isShowForOperator = $("#isShowForOperator");
        isShowForSite = $("#isShowForSite");
        isPaid = $("#isPaid");
        isRedirect = $("#isRedirect");
        isOffPosition = $("#isOffPosition");
        isClosed = $("#isClosed");
        isPriority = $("#isPriority");
        email = $("#email");
        site = $("#site");
        hiddenIdCompanyReminder=$("#hiddenIdCompanyReminder");
        hiddenNameCompanyReminder=$("#hiddenNameCompanyReminder");
    }
    if (dataCompanyAddressJson == undefined) {
        dataCompanyAddressJson = $.parseJSON($(".companyAddressJson")[0].innerHTML);
    }
    if (dataCompanyReminderJson == undefined) {
        dataCompanyReminderJson = $.parseJSON($(".companyReminderJson")[0].innerHTML);
    }
    var item;
    addressArray = [];
    cleanAddressBlocks();
    $.get('../api/admin/resource/v1/company/'+id, function(entry) {
        companyName.val(entry.name);
        keywords.val(entry.keywords);
        dateOfContract.val(new Date(entry.dateOfContract).customFormat("#YYYY#-#MM#-#DD#"));
        dateOfStartContract.val(new Date(entry.dateOfStartContract).customFormat("#YYYY#-#MM#-#DD#"));
        dateOfEndContract.val(new Date(entry.dateOfEndContract).customFormat("#YYYY#-#MM#-#DD#"));
        manager.val(entry.manager);
        companyPackageId.val(entry.companyPackageId);
        costOf.val(entry.costOf);
        legalName.val(entry.legalName);
        if (entry.inn != "null")
            inn.val(entry.inn);
        else
            inn.val("");
        legalAddress.val(entry.legalAddress);
        phone.val(entry.phone);
        fax.val(entry.fax);
        directorFullName.val(entry.directorFullName);
        contactPerson.val(entry.contactPerson);
        description.val(entry.description);
        email.val(entry.email);
        site.val(entry.site);
        isShowForOperator.prop('checked', newBoolean(entry.isShowForOperator));
        isShowForSite.prop('checked', newBoolean(entry.isShowForSite));
        isPaid.prop('checked', newBoolean(entry.isPaid));
        isRedirect.prop('checked', newBoolean(entry.isRedirect));
        isOffPosition.prop('checked', newBoolean(entry.isOffPosition));
        isClosed.prop('checked', newBoolean(entry.isClosed));
        isPriority.prop('checked', newBoolean(entry.isPriority));
        hiddenIdCompanyReminder.val(entry.id);
        hiddenNameCompanyReminder.val(entry.name);

        var countReminder=0;
        //6.02.2017
        dataCompanyReminderJson.forEach(function(entry4) {     //создание дива для отображения  напоминаний
            if (entry4.companyId == entry.id) {
                for(var i=0;i<entry4.companyReminders.length;i++){   $(".tbodyShowReminders").html(
                    $(".tbodyShowReminders").html()+' <tr id="trColorFill-'+i+'">'+
                    '<td ><span  id="dateReminder-'+i+'"></span> </td>'+
                    '<td ><span id="hourReminder-'+i+'"></span></td>'+
                    '<td ><span id="typeReminder-'+i+'"></span></td>'+
                    '<td ><span id="commentReminder-'+i+'"></span></td>'+
                    '<td hidden="true" ><span id="hiddenReminder-'+i+'"></span></td>'+
                    '<td ><div class="reminderEditClass" id="buttonReminder-'+i+'" >Редактировать</div></td>'+
                    '</tr>');
                }

                $(".reminderEditClass").on("click", function (event) {
                    var reminderId = event.target.getAttribute('id');
                    var reminderHiddenId = $("#hiddenReminder-" + reminderId.replace("buttonReminder-", "")).html();
                    var data = {};
                    data["id"] = reminderHiddenId;
                    data["companyId"] = $("#hiddenIdCompanyReminder").val();
                    var token = $('#csrfToken').val();
                    var header = $('#csrfHeader').val();
                    $.ajax({
                        type: "POST",
                        url: "getreminder",
                        data: JSON.stringify(data),
                        dataType: 'json',
                        timeout: 600000,
                        async : false,
                        success: function (data) {        //получение ответа с rest сервера
                            getReminder(data);               //вызов йункции для отображения полученных с сервера данных
                        },
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                            xhr.setRequestHeader(header, token);
                        },
                    });
                    return false;
                });
                entry4.companyReminders.forEach(function(entry5) {
                    $( "#hourReminder-"+countReminder).html(entry5.hourReminder);
                    $ ("#dateReminder-"+countReminder).html(new Date(entry5.dateReminder).customFormat("#DD#.#MM#.#YYYY#"));
                    $ ("#typeReminder-"+countReminder).html(entry5.typeReminder);
                    $ ("#commentReminder-"+countReminder).html(entry5.commentReminder);
                    $( "#hiddenReminder-"+countReminder).html(entry5.id);
                    var typeReminder= entry5.typeReminder;
                    if(typeReminder=="Перезвонить"){
                        $ ("#trColorFill-"+countReminder).css("background-color", "#7dc573");
                    }
                    if(typeReminder=="Отказ"){
                        $ ("#trColorFill-"+countReminder).css("background-color", "#f16c4d");
                    }
                    if(typeReminder=="Встреча"){
                        $ ("#trColorFill-"+countReminder).css("background-color", "#4c89c1");
                    }
                    if(typeReminder=="Другое"){
                        $ ("#trColorFill-"+countReminder).css("background-color", "#fff79a");
                    }
                    countReminder++;
                });
            }
        });
        function getReminder(data) {
            console.log(data)
            $("#selectReminderHours").val(data.hourReminder);
            $("#reminderDate").val(new Date(data.dateReminder).customFormat("#YYYY#-#MM#-#DD#"));
            $("#selectReminderType").val(data.typeReminder);
            $("#reminderComment").val(data.commentReminder);
            $("#submitManagerFormReminder").val("Обновить");
            idReminder=data.id;
        }
        jQuery(document).ready(    //отпрвка джисоном данных с формы в фоновом режиме
            function($) {
                $("#addReminderForm").submit(function(event) {
                    if($("#submitManagerFormReminder").val()=="Создать") {
                        var data = {};
                        data["dateReminder"] = $("#reminderDate").val();
                        data["typeReminder"] = $("#selectReminderType").val();
                        data["commentReminder"] = $("#reminderComment").val();
                        data["companyId"] = $("#hiddenIdCompanyReminder").val();
                        data["companyName"] = $("#hiddenNameCompanyReminder").val();
                        data["hourReminder"] = $("#selectReminderHours").val();
                        var token = $('#csrfToken').val();
                        var header = $('#csrfHeader').val();
                        console.log(JSON.stringify(data));
                        $.ajax({
                            type: "POST",
                            url: "addreminder",
                            data: JSON.stringify(data),
                            dataType: 'json',
                            timeout: 600000,
                            async: false,
                            success: function (data) {      //получение ответа с rest сервера
                                display(data);               //вызов йункции для отображения полученных с сервера данных
                            },
                            beforeSend: function (xhr) {
                                xhr.setRequestHeader("Accept", "application/json");
                                xhr.setRequestHeader("Content-Type", "application/json");
                                xhr.setRequestHeader(header, token);
                            },
                        });
                        return false;
                    }
                    if($("#submitManagerFormReminder").val()=="Обновить") {
                        var data = {};
                        data["dateReminder"] = $("#reminderDate").val();
                        data["typeReminder"] = $("#selectReminderType").val();
                        data["commentReminder"] = $("#reminderComment").val();
                        data["companyName"] = $("#hiddenNameCompanyReminder").val();
                        data["hourReminder"] = $("#selectReminderHours").val();
                        data["id"] = idReminder;
                        var token = $('#csrfToken').val();
                        var header = $('#csrfHeader').val();
                        console.log(JSON.stringify(data));
                        $.ajax({
                            type: "POST",
                            url: "updatereminder",
                            data: JSON.stringify(data),
                            dataType: 'json',
                            timeout: 600000,
                            async: false,
                            success: function (data) {
                                $("#submitManagerFormReminder").val("Создать");//получение ответа с rest сервера
                                display(data);               //вызов йункции для отображения полученных с сервера данных
                            },
                            beforeSend: function (xhr) {
                                xhr.setRequestHeader("Accept", "application/json");
                                xhr.setRequestHeader("Content-Type", "application/json");
                                xhr.setRequestHeader(header, token);
                            },
                        });
                        return false;
                    }
                });
            });


        function display(data) {
            $("#addReminderForm").trigger('reset');//reset  формы
            $(".tbodyShowReminders").html(""); //очистка дива
            countReminder = 0;
            dataCompanyReminderJsonAnswer = data ;
            dataCompanyReminderJsonAnswer.forEach(function (entry6) {     //создание напоминаний из ответа сервера(data)
                $(".tbodyShowReminders").html(
                    $(".tbodyShowReminders").html() + ' <tr id="trColorFill-'+countReminder+'">' +
                    '<td ><span  id="dateReminder-' + countReminder + '"></span> </td>' +
                    '<td ><span id="hourReminder-' + countReminder + '"></span></td>' +
                    '<td ><span id="typeReminder-' + countReminder + '"></span></td>' +
                    '<td ><span id="commentReminder-' + countReminder + '"></span></td>' +
                    '<td hidden="true"><span id="hiddenReminder-' + countReminder + '"></span></td>' +
                    '<td ><div class="reminderEditClass" id="buttonReminder-' + countReminder + '">Редактировать</div></td>' +
                    '</tr>');
                countReminder++;
            });
            countReminder=0;
            dataCompanyReminderJsonAnswer.forEach(function (entry6) {     //создание напоминаний из ответа сервера(data)
                data.forEach(function (entry7) {
                    $("#hourReminder-" + countReminder).html(entry7.hourReminder);
                    $("#dateReminder-" + countReminder).html(new Date(entry7.dateReminder).customFormat("#DD#.#MM#.#YYYY#"));
                    $("#typeReminder-" + countReminder).html(entry7.typeReminder);
                    $("#commentReminder-" + countReminder).html(entry7.commentReminder);
                    $("#hiddenReminder-" + countReminder).html(entry7.id);
                    var typeReminder= entry7.typeReminder;
                    if(typeReminder=="Перезвонить"){
                        $ ("#trColorFill-"+countReminder).css("background-color", "#7dc573");
                    }
                    if(typeReminder=="Отказ"){
                        $ ("#trColorFill-"+countReminder).css("background-color", "#f16c4d");
                    }
                    if(typeReminder=="Встреча"){
                        $ ("#trColorFill-"+countReminder).css("background-color", "#4c89c1");
                    }
                    if(typeReminder=="Другое"){
                        $ ("#trColorFill-"+countReminder).css("background-color", "#fff79a");
                    }
                    countReminder++;
                });
            });
            $(".reminderEditClass").on("click", function (event) {
                var reminderId = event.target.getAttribute('id');
                var reminderHiddenId = $("#hiddenReminder-" + reminderId.replace("buttonReminder-", "")).html();
                var data = {};
                data["id"] = reminderHiddenId;
                data["companyId"] = $("#hiddenIdCompanyReminder").val();
                var token = $('#csrfToken').val();
                var header = $('#csrfHeader').val();
                $.ajax({
                    type: "POST",
                    url: "getreminder",
                    data: JSON.stringify(data),
                    dataType: 'json',
                    timeout: 600000,
                    async : false,
                    success: function (data) {        //получение ответа с rest сервера
                        getReminder(data);               //вызов йункции для отображения полученных с сервера данных
                    },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader("Accept", "application/json");
                        xhr.setRequestHeader("Content-Type", "application/json");
                        xhr.setRequestHeader(header, token);
                    },
                });
                return false;
            });
            //6.02.2017
            /*
             $(".reminderDeleteClass").on("click", function (event) {
             var reminderId = event.target.getAttribute('id');
             var reminderHiddenId = $("#hiddenReminder-" + reminderId.replace("buttonReminder-", "")).html();
             var data = {};
             data["id"] = reminderHiddenId;
             data["companyId"] = $("#hiddenIdCompanyReminder").val();
             var token = $('#csrfToken').val();
             var header = $('#csrfHeader').val();
             $.ajax({
             type: "POST",
             url: "deletereminder",
             data: JSON.stringify(data),
             dataType: 'json',
             timeout: 600000,
             async : false,
             success: function (data) {      //получение ответа с rest сервера
             display(data);               //вызов йункции для отображения полученных с сервера данных
             },
             beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
             xhr.setRequestHeader(header, token);
             },
             });
             return false;
             });
             */
        }
        hiddenId.val(entry.id);
        for (var i = 1; i < 13; i++) {
            item = {};
            item.id = i;
            item.isOpen = false;
            addressArray[i] = item;
            $("#UpAdd" + i).hide();
            $("#UpAdAd" + i).removeAttr('required');
            $("#UpAdAd" + i).val("");
            $("#UpRegAd" + i).val("-1");
            $("#UpPhAd" + i).val("");
            $("#UpInfAd" + i).val("");
            $("#UpAddId" + i).val("");
        }

        var count = 1;
        dataCompanyAddressJson.forEach(function(entry2) {
            if (entry2.companyId == entry.id) {
                entry2.companyAddresses.forEach(function(entry3) {
                    addressArray[count].isOpen = true;
                    $("#UpAdd" + count).show();
                    $("#UpAdAd" + count).prop("required", true);
                    $("#UpAdAd" + count).val(entry3.address);
                    $("#UpRegAd" + count).val(entry3.regionId);
                    $("#UpPhAd" + count).val(entry3.phones);
                    $("#UpInfAd" + count).val(entry3.information);
                    $("#UpAddId" + count).val(entry3.id);
                    count++;
                });
                calculatePosition(addressArray, "UpAdd");
            }
        });
    });
}


function newBoolean(bool) {
    return bool != "false";
}

$(".menuBodyItemButtDel").on("click", function (event) {
    var tmp = event.target.getAttribute('id').replace("ID-", "");
    $("#deleteKey").val(tmp);
    if (confirm("Удалить?")) {
        $("#deleteForm").submit();
    }
});

var requisitesIsOpen;
$(".openRequisites").on("click", function () {
    if (requisitesIsOpen) {
        $(".requisites").hide();
        requisitesIsOpen = false;
    } else {
        $(".requisites").show();
        requisitesIsOpen = true;
    }
});
var addressIsOpen;
$(".openAddress").on("click", function () {
    if (addressIsOpen) {
        $(".address").hide();
        addressIsOpen = false;
    } else {
        $(".address").show();
        addressIsOpen = true;
    }

});

$(".add-addressUp").on("click", function () {
    var item;
    if (addressArray == undefined) {
        addressArray = [];
        for (var i = 1; i < 13; i++) {
            item = {};
            item.id = i;
            item.isOpen = false;
            addressArray[i] = item;
        }
    }
    try {
        addressArray.forEach(function(entry) {
            if (!entry.isOpen) {
                entry.isOpen = true;
                $("#UpAdd" + entry.id).show();
                $("#UpAdAd" + entry.id).prop("required", true);
                throw BreakException;
            }
        });
    } catch (e) {
        if (e !== BreakException) throw e;
    }
    calculatePosition(addressArray, "UpAdd");
});

$(".UpCloseAdButt").on("click", function (event) {
    var addId = event.target.getAttribute('data-id');
    try {
        addressArray.forEach(function(entry) {
            if (entry.id == addId &&
                ($("#UpAddId" + entry.id).val() == "" ||
                confirm("Удалить?\nАдрес будет удален после того, как вы нажмете Обновить."))) {
                entry.isOpen = false;
                $("#UpAdd" + entry.id ).hide();
                $("#UpAdAd" + entry.id ).removeAttr('required');
                $("#UpAdAd" + entry.id ).val("");
                $("#UpRegAd" + entry.id ).val("-1");
                $("#UpPhAd" + entry.id ).val("");
                $("#UpInfAd" + entry.id ).val("");
                $("#UpDelete").val($("#UpDelete").val() + "," + $("#UpAddId" + entry.id).val());
                $("#UpAddId" + entry.id).val("");
                throw BreakException;
            }
        });
    } catch (e) {
        if (e !== BreakException) throw e;
    }
    calculatePosition(addressArray, "UpAdd");
});

$(".add-address").on("click", function () {
    var item;
    if (addressArray == undefined) {
        addressArray = [];
        for (var i = 1; i < 13; i++) {
            item = {};
            item.id = i;
            item.isOpen = false;
            addressArray[i] = item;
        }
    }
    try {
        addressArray.forEach(function(entry) {
            if (!entry.isOpen) {
                entry.isOpen = true;
                $("#add" + entry.id).show();
                $("#adAd" + entry.id).prop("required", true);
                throw BreakException;
            }
        });
    } catch (e) {
        if (e !== BreakException) throw e;
    }
    calculatePosition(addressArray, "add");
});
$(".closeAdButt").on("click", function (event) {
    var addId = event.target.getAttribute('data-id');
    try {
        addressArray.forEach(function(entry) {
            if (entry.id == addId) {
                entry.isOpen = false;
                $("#add" + entry.id).hide();
                $("#adAd" + entry.id).removeAttr('required');
                $("#adAd" + entry.id).val("");
                $("#phAd" + entry.id).val("");
                $("#infAd" + entry.id).val("");
                throw BreakException;
            }
        });
    } catch (e) {
        if (e !== BreakException) throw e;
    }
    calculatePosition(addressArray, "add");
});
function calculatePosition(items, type) {
    var counter = 1;
    items.forEach(function(entry) {
        if (entry.isOpen) {
            var tmpBlock = $("#" + type + entry.id);
            if (counter & 1) {
                tmpBlock.removeClass();
                tmpBlock.addClass("left-address-item");
            } else {
                tmpBlock.removeClass();
                tmpBlock.addClass("right-address-item");
            }
            counter++;
        }
    });
}

$("#createForm").submit(function() {
    var addressArrayJson = [];
    var companyAddress;
    var counter = 0;
    if (addressArray != undefined) {
        addressArray.forEach(function(entry) {
            if (entry.isOpen) {
                companyAddress = {};
                companyAddress.address = $("#adAd" + entry.id).val();
                companyAddress.regionId = Number($("#regAd" + entry.id).val());
                companyAddress.phones = $("#phAd" + entry.id).val();
                companyAddress.information = $("#infAd" + entry.id).val();
                addressArrayJson[counter] = companyAddress;
                counter++;
            }
        });
        $("#addressJson").val(JSON.stringify(addressArrayJson));
    }
});

$("#updateForm").submit(function() {
    var addressArrayJson = [];
    var companyAddress;
    var counter = 0;
    if (addressArray != undefined) {
        addressArray.forEach(function(entry) {
            if (entry.isOpen) {
                companyAddress = {};
                companyAddress.address = $("#UpAdAd" + entry.id).val();
                if ($("#UpAddId" + entry.id).val() != "-1")
                    companyAddress.regionId = Number($("#UpRegAd" + entry.id).val());
                else
                    companyAddress.regionId = null;
                companyAddress.phones = $("#UpPhAd" + entry.id).val();
                companyAddress.information = $("#UpInfAd" + entry.id).val();
                if ($("#UpAddId" + entry.id).val() != "")
                    companyAddress.id = Number($("#UpAddId" + entry.id).val());
                else
                    companyAddress.id = null;
                addressArrayJson[counter] = companyAddress;
                counter++;
            }
        });
        $("#UpAddressJson").val(JSON.stringify(addressArrayJson));
    }
});

var itemsStatusList;
$(".menuBodyItemHeadInfo").on("click", function (event) {
    var itemsID = event.target.getAttribute('data-id');
    if (itemsStatusList == undefined) {
        itemsStatusList = [];
        for (var i = 1; i < 17; i++){
            itemsStatusList[i] = {};
            itemsStatusList[i].itemsID = i;
            itemsStatusList[i].isShow = false;
        }
    }
    itemsStatusList.forEach(function(entry) {
        if (entry.itemsID == itemsID) {
            if (entry.isShow) {
                $("#itemsID-"+itemsID).hide();
                entry.isShow = false;
            } else {
                $("#itemsID-"+itemsID).show();
                entry.isShow = true;
            }
        }
    });
});
$(".searchButt").on("click", function () {
    $("#searchForm").submit();
});
/*
 $("#deleteReminder").on("click", function () {
 $("#searchForm").submit();
 });
 */

Date.prototype.customFormat = function(formatString){
    var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
    YY = ((YYYY=this.getFullYear())+"").slice(-2);
    MM = (M=this.getMonth()+1)<10?('0'+M):M;
    MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
    DD = (D=this.getDate())<10?('0'+D):D;
    DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][this.getDay()]).substring(0,3);
    th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
    formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
    h=(hhh=this.getHours());
    if (h==0) h=24;
    if (h>12) h-=12;
    hh = h<10?('0'+h):h;
    hhhh = hhh<10?('0'+hhh):hhh;
    AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
    mm=(m=this.getMinutes())<10?('0'+m):m;
    ss=(s=this.getSeconds())<10?('0'+s):s;
    return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
};

