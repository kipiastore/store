var isShowCreateForm = false;
var isShowUpdateForm = false;
var currentItem;
var pageMenuButtTarget;

var data;
var pageInformation;
// user fileds
var updateForm;
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
var companyPackage;
var costOf;
var legalName;
var inn;
var legalAddress;
var phone;
var fax;
var directorFullName;
var contactPerson;
var hiddenId;

$(window).on("load", function () {
    if ($("#addError")[0].innerHTML != "") {
        try {
            var user = $.parseJSON($(".addingUserJson")[0].innerHTML);
            $("#newFullName").val(user.fullName);
            $("#newUsername").val(user.username);
            $("#newStatus").val(user.status);
        } catch(e) {
            console.log(e);
        }
        var createForm = $("#createForm");
        createForm.show();
        createForm.animate({opacity: 1}, 200);
        isShowCreateForm = true;
    }
});

$(".pageMenuButt").on("click", function (event) {
    $(".error").hide();
    $(".success").hide();
    if (isShowUpdateForm) {
        var updateForm = $("#updateForm");
        updateForm.animate({opacity: 0}, 200);
        setTimeout(function() { updateForm.hide(); }, 200);
        $("#" + currentItem).css("border-left", "0");
        isShowUpdateForm = false;
    }
    pageMenuButtTarget = event.target;
    pageMenuButtTarget.setAttribute("style", "background : #16a085;");
    setTimeout(function() {
        var createForm = $("#createForm");
        createForm.show();
        createForm.animate({opacity: 1}, 200);
        isShowCreateForm = true;
    }, 200);
});

$(".menuBodyItemInfo").on("click", function (event) {
    if (pageInformation == undefined)
        pageInformation = $("#pageInformation").val();
    if (!(pageInformation == 4 || pageInformation == 5 || pageInformation == 1))
        return;
    $(".error").hide();
    $(".success").hide();
    updateForm = $("#updateForm");
    if (isShowCreateForm) {
        var createForm = $("#createForm");
        if (pageMenuButtTarget != undefined)
            pageMenuButtTarget.setAttribute("style", "background : #738dae;");
        createForm.animate({opacity: 0}, 200);
        setTimeout(function() { createForm.hide(); }, 200);
    }
    if (isShowCreateForm && !isShowUpdateForm) {
        setTimeout(function() {
            updateForm.show();
            updateForm.animate({opacity: 1}, 200);
        }, 200);
    } else if (isShowUpdateForm) {
        updateForm.animate({opacity: 0}, 200);
        setTimeout(function() {
            updateForm.hide();
            updateForm.show();
            updateForm.animate({opacity: 1}, 200);
        }, 200);
    } else {
        updateForm.show();
        updateForm.animate({opacity: 1}, 200);
    }
    isShowUpdateForm = true;
    isShowCreateForm = false;

    if (currentItem != undefined) {
        $("#" + currentItem).css("border-left", "0");
    }
    currentItem = event.target.getAttribute("id");
    $("#" + currentItem).css("border-left", "2px solid #d87f7f");
    var id = currentItem.replace("ID-", "");

    if (pageInformation == 4 || pageInformation == 5) {
        if (data == undefined) {
            data = $.parseJSON($(".dataJson")[0].innerHTML);
            fullName = $("#fullName");
            username = $("#username");
            password = $("#password");
            password2 = $("#password2");
            accessStatus = $("#accessStatus");
            usernameHidden = $("#usernameHidden");
        }
        data.forEach(function(entry) {
            if (entry.username == id) {
                fullName.val(entry.fullName);
                username.val(entry.username);
                usernameHidden.val(entry.username);
                password.val("");
                password2.val("");
                accessStatus.val(entry.status);
            }
        });
    }
    if (pageInformation == 1) {
        if (data == undefined) {
            data = $.parseJSON($(".dataJson")[0].innerHTML);
            companyName = $("#name");
            keywords = $("#keywords");
            dateOfContract = $("#dateOfContract");
            dateOfStartContract = $("#dateOfStartContract");
            dateOfEndContract = $("#dateOfEndContract");
            manager = $("#manager");
            companyPackage = $("#companyPackage");
            costOf = $("#costOf");
            legalName = $("#legalName");
            inn = $("#inn");
            legalAddress = $("#legalAddress");
            phone = $("#phone");
            fax = $("#fax");
            directorFullName = $("#directorFullName");
            contactPerson = $("#contactPerson");
            hiddenId = $("#hiddenId");
        }
        data.forEach(function(entry) {
            if (entry.id == id) {
                companyName.val(entry.name);
                keywords.val(entry.keywords);
                dateOfContract.val(entry.dateOfContract.substring(0, 11));
                dateOfStartContract.val(entry.dateOfStartContract.substring(0, 11));
                dateOfEndContract.val(entry.dateOfEndContract.substring(0, 11));
                manager.val(entry.manager);
                companyPackage.val(entry.companyPackage);
                costOf.val(entry.costOf);
                legalName.val(entry.legalName);
                inn.val(entry.inn);
                legalAddress.val(entry.legalAddress);
                phone.val(entry.phone);
                fax.val(entry.fax);
                directorFullName.val(entry.directorFullName);
                contactPerson.val(entry.contactPerson);
                hiddenId.val(entry.id);
            }
        });
    }
});

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