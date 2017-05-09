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
var numOfMaxSelected = 0;
var numOfSelectedItems = 0;

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
    updateForm = $("#updateForm");
    containerBySubpartition = $(".containerBySubpartition");
    tSubBody = $(".tSubBody");
    messageSubpartition = $(".messageSubpartition");
});

$(".button.delete").on("click", function (event) {
    var itemsID = event.target.getAttribute('data-id');
    console.log(itemsID);
    $("#companySubpartitionContentId").val(itemsID);
    if (confirm("Удалить?")) {
        $("#deleteForm").submit();
    }
});

$(".button.edit").on("click", function (event) {
    var itemsID = event.target.getAttribute('data-id');
    console.log(itemsID);

    var mainContainer = $(".position-main-container");
    var updateForm = $("#updateForm");
    mainContainer.animate({opacity: 0}, 200);
    setTimeout(function() {
        mainContainer.hide();
        $(".button.create").hide();
        updateForm.show();
        updateForm.animate({opacity: 1}, 200);
    }, 200);
    $("#updateCompanySubpartitionContentId").val(itemsID);
    $(".subPartitionNameSpan").html($("#subNameId-"+itemsID).html());
    $("#updateDescription").val($("#infoId-"+itemsID).html());
    $("#updateCompanySubpartitionId").val($("#subNameId-"+itemsID).attr('data-id'));
    $("#updateImageId").val($("#imageId-"+itemsID).attr('data-id'));
});

$(".button.create").on("click", function () {
    var mainContainer = $(".position-main-container");
    var createForm = $("#createForm");
    mainContainer.animate({opacity: 0}, 200);
    setTimeout(function() {
        mainContainer.hide();
        $(".button.create").hide();
        createForm.show();
        createForm.animate({opacity: 1}, 200);
    }, 200);
});


$(".button.edit-cancel").on("click", function () {
    var mainContainer = $(".position-main-container");
    var updateForm = $("#updateForm");
    updateForm.animate({opacity: 0}, 200);
    setTimeout(function() {
        updateForm.hide();
        mainContainer.show();
        $(".button.create").show();
        mainContainer.animate({opacity: 1}, 200);
    }, 200);
    $("#updateDescription").val("");
    $("#updateFile").val("");
    $("#updateCompanySubpartitionContentId").val("");
    $(".subPartitionNameSpan").html();
    $("#updateCompanySubpartitionId").val();
});

$(".button.create-cancel").on("click", function () {
    var mainContainer = $(".position-main-container");
    var createForm = $("#createForm");
    createForm.animate({opacity: 0}, 200);
    setTimeout(function() {
        createForm.hide();
        mainContainer.show();
        $(".button.create").show();
        mainContainer.animate({opacity: 1}, 200);
    }, 200);
    $("#createFile").val();
    $("#createDescription").val();
    $("#createCompanySubpartitionId").val();
});

$(".button.edit-submit").on("click", function () {
    if (confirm("Сохранить?")) {
        $("#updateForm").submit();
    }
});

$(".button.create-submit").on("click", function () {
    if ($("#createCompanySubpartitionId").val() != null) {
        if (confirm("Сохранить?")) {
            $("#createForm").submit();
        }
    } else {
        alert("Свободных позиций нет.");
    }
});

$(".menuTitleText").on("click", function () {
    if (document.URL.indexOf("designer/positions/company/") != -1) {
        // временное решение
        //window.location.replace(document.URL.replace("searchcompany", "s"));
        window.location.replace(document.URL.split('/positions/company/')[0]);
    }
    if (document.URL.indexOf("designer/positionsearchcompany") != -1) {
        // временное решение
        //window.location.replace(document.URL.replace("searchcompany", "s"));
        window.location.replace((document.URL + '').replace('positionsearchcompany','positions'));
    }
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

$(".menuBodyItemInfo").on("click", function (event) {
    $(".error").hide();
    $(".success").hide();
    var container = $(".container");
    container.animate({opacity: 0}, 200);
    setTimeout(function() {
        container.hide();
        updateForm.hide();
        containerBySubpartition.show();
        containerBySubpartition.animate({opacity: 1}, 200);
    }, 190);
    isShowUpdateForm = false;

    tSubBody.html('<tr><td class="tableName" id="ID-2">name</td></tr>');

    if (currentItem != undefined) {
        $("div#" + currentItem).css("border-left", "0");
    }
    currentItem = event.target.getAttribute("id");
    $("div#" + currentItem).css("border-left", "2px solid #d87f7f");
    $(".menuBodyItemButtDel").css("border-left", "0");
    var id = currentItem.replace("ID-", "");
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

$(document).ready(function(){
    $("#partitionLevel").click(function() {
        var val = $("#partitionLevel").val();
        console.log(val);
        if (val == 2)
            $("#show").show();
        else
            $("#show").hide();
    });
});

//http://loudev.com/
if (pageInformation == undefined)
    pageInformation = $("#pageInformation").val();
var localMessage;
if (pageInformation == 2) {
    localMessage = $(".localMessage");
    localMessage.show();
    $('#optgroup').multiSelect({
        selectableOptgroup: true,
        afterSelect: function (values) {
            numOfSelectedItems += values.length;
            if (numOfSelectedItems > numOfMaxSelected) {
                $('#optgroup').multiSelect('deselect', values);
                localMessage.html("Вы можете выбрать только " + numOfMaxSelected + " позиций.");
            }
        },
        afterDeselect: function(values){
            numOfSelectedItems -= values.length;
        }
    });
}
$('#updateForm').submit(function() {
    if (numOfSelectedItems == 0)
        $("#optgroup").val(["-1"]);
});

