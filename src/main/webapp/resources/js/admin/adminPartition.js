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

var addressArray;
var BreakException = {};

var dataCompanyAddressJson;

$(window).on("load", function () {
    var container = $(".container");
    pageInformation = $("#pageInformation").val();
    container.show();
    container.animate({opacity: 1}, 200);
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
    $(".pageMenuButt").click();
});

$(".pageMenuButt").on("click", function (event) {
    var container = $(".container");
    container.animate({opacity: 0}, 200);
    setTimeout(function() { container.hide(); }, 190);

    var val = $("#partitionLevel").val();

    if (val == 2)
        $("#show").show();
    else
        $("#show").hide();

    //$(".error").hide();
    //$(".success").hide();
    if (isShowUpdateForm) {
        var updateForm = $("#updateForm");
        updateForm.animate({opacity: 0}, 200);
        setTimeout(function() { updateForm.hide(); }, 200);
        $("div#" + currentItem).css("border-left", "0");
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


$(".menuTitleText").on("click", function () {
    if (document.URL.indexOf("searchcompany") != -1) // временное решение
        window.location.replace(document.URL.replace("searchcompany", "companies"));

    updateForm = $("#updateForm");
    updateForm.animate({opacity: 0}, 200);
    setTimeout(function() { updateForm.hide(); }, 200);
    var createForm = $("#createForm");
    createForm.animate({opacity: 0}, 200);
    setTimeout(function() { createForm.hide(); }, 200);
    var container = $(".container");
    if (pageMenuButtTarget != undefined)
        pageMenuButtTarget.setAttribute("style", "background : #738dae;");
    $(".menuBodyItemInfo").css("border-left", "0");
    setTimeout(function() {
        container.show();
        container.animate({opacity: 1}, 200);
    }, 200);
    $(".pageMenuButt").click();
});

$(".menuBodyItemInfo").on("click", function (event) {
    console.log('sad2');
    var container = $(".container");
    container.animate({opacity: 0}, 200);
    setTimeout(function() { container.hide(); }, 190);
    isShowUpdateForm = true;

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
        $("div#" + currentItem).css("border-left", "0");
    }
    currentItem = event.target.getAttribute("id");
    $("div#" + currentItem).css("border-left", "2px solid #d87f7f");


    var id = currentItem.replace("sub-pr-ID-", "");
    $('#updateKey').val(id);
    $('#keyName').val(event.target.getAttribute("data-name"));
    $('#updateType').val(event.target.getAttribute("data-type"));

    $(".menuBodyItemButtDel").css("border-left", "0");
});
//var itemsStatusList;
$(".menuBodyItemHeadInfo").on("click", function (event) {
    var itemsID = event.target.getAttribute('data-id');
    /*
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
     */

    //var container = $(".container");
    //container.animate({opacity: 0}, 200);
    //setTimeout(function() { container.hide(); }, 190);
    //isShowUpdateForm = true;

    $(".error").hide();
    $(".success").hide();
    //updateForm = $("#updateForm");
    if (isShowCreateForm) {
        //var createForm = $("#createForm");
        if (pageMenuButtTarget != undefined)
            pageMenuButtTarget.setAttribute("style", "background : #738dae;");
        //createForm.animate({opacity: 0}, 200);
        //setTimeout(function() { createForm.hide(); }, 200);
    }
    /*
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
     */
    var prvItem;
    if (currentItem != undefined) {
        $("div#" + currentItem).css("border-left", "0");
        if (currentItem == event.target.getAttribute("id")) {
            prvItem = currentItem;
        }
    }
    currentItem = event.target.getAttribute("id");

    $("div#" + currentItem).css("border-left", "2px solid #d87f7f");



    var id = currentItem.replace("pr-ID-", "");
    //$('#updateKey').val(id);
    //$('#keyName').val(event.target.getAttribute("data-name"));
    //$('#updateType').val(event.target.getAttribute("data-type"));

    $(".menuBodyItemButtDel").css("border-left", "0");

    $(".subItemsList").hide();
    $("#itemsID-"+id).show();

    //
    if (prvItem != undefined && prvItem == currentItem) {
        $("div#" + currentItem).css("border-left", "0");
        $("#itemsID-"+id).hide();
        $(".pageMenuButt").click();
        currentItem = undefined;
    }
});

$(".menuBodyItemButtDel").on("click", function (event) {
    if (event.target.getAttribute('data-type') == 'partition') {
        $("#deleteKey").val(event.target.getAttribute('id').replace("pr-ID-", ""));
        $("#deleteType").val('partition');
    } else {
        $("#deleteKey").val(event.target.getAttribute('id').replace("sub-pr-ID-", ""));
        $("#deleteType").val('subPartition');
    }
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

$(".searchButt").on("click", function () {
    $("#searchForm").submit();
});

$(document).ready(function() {
    $("#partitionLevel").change(function() {
        var val = $("#partitionLevel").val();
        //console.log(val);
        if (val == 2)
            $("#show").show();
        else
            $("#show").hide();
    });
});

////////////////////////////////