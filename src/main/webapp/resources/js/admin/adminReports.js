var isShowCreateForm = false;
var isShowUpdateForm = false;
var currentItem;
var pageMenuButtTarget;
var containerBySubpartition;
var tSubBody;
var messageSubpartition;

var data;
var pageInformation;
var hiddenId;

$(window).on("load", function () {
    var container = $(".container");
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
    updateForm = $("#updateForm");
    containerBySubpartition = $(".containerBySubpartition");
    tSubBody = $(".tSubBody");
    messageSubpartition = $(".messageSubpartition");
});


$(".menuTitleText").on("click", function () {
    if (document.URL.indexOf("searchcompany") != -1) // временное решение
        window.location.replace(document.URL.replace("searchcompany", "s"));
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

});

$(".tableName").on("click", function (event) {
    $(".error").hide();
    $(".success").hide();
    var container = $(".container");
    container.animate({opacity: 0}, 200);
    setTimeout(function() { container.hide(); }, 190);
    updateForm = $("#updateForm");
    setTimeout(function() {
        updateForm.show();
        updateForm.animate({opacity: 1}, 200);
    }, 200);
    isShowUpdateForm = true;

    currentItem = event.target.getAttribute("id");
    $("div#" + currentItem).css("border-left", "2px solid #d87f7f");
    $(".menuBodyItemButtDel").css("border-left", "0");
    var id = currentItem.replace("ID-", "");
    if (pageInformation == undefined)
        pageInformation = $("#pageInformation").val();

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


