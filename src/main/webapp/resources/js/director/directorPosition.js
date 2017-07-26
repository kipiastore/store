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
/*
$(".pageMenuButt").on("click", function (event) {
    var container = $(".container");
    container.animate({opacity: 0}, 200);
    setTimeout(function() { container.hide(); }, 190);

    $(".error").hide();
    $(".success").hide();
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
*/

$(".menuTitleText").on("click", function () {
    $(".localMessage").hide();
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
    var prLoader = $(".pre-loading");
    var container = $(".container");
    container.animate({opacity: 0}, 200);
    setTimeout(function() { container.hide(); }, 190);
    updateForm = $("#updateForm");
    setTimeout(function() {
        updateForm.show();
        //updateForm.animate({opacity: 1}, 200);
        prLoader.show();
    }, 200);
    isShowUpdateForm = true;

    currentItem = event.target.getAttribute("id");
    $("div#" + currentItem).css("border-left", "2px solid #d87f7f");
    $(".menuBodyItemButtDel").css("border-left", "0");
    var id = currentItem.replace("ID-", "");
    if (pageInformation == undefined)
        pageInformation = $("#pageInformation").val();
    if (pageInformation == 2) {
        if (hiddenId == undefined) {
            hiddenId = $("#hiddenId");
        }
        $.get('../api/admin/resource/v1/company/'+id, function(entry1) {
            hiddenId.val(id);
            $.get('../api/admin/resource/v1/package/'+entry1.companyPackageId, function(entry2) {
                numOfMaxSelected = entry2.numOfPositions;
                numOfSelectedItems = 0;
                $('#optgroup').multiSelect('deselect_all');
                $.get('../api/admin/resource/v1/companySubpartition/company/'+id, function(entry3) {
                    if (entry3.length > 0) {
                        var tmpArray = [];
                        var tmpCounter = 0;
                        entry3.forEach(function (entry4) {
                            tmpArray[tmpCounter] = entry4.subPartitionId + '';
                            tmpCounter++;
                        });
                        $('#optgroup').multiSelect('select', tmpArray);
                    }
                    $('.pickListItem.ms-elem-selectable').hide();
                    $(".ms-optgroup-label").unbind();
                    $(".ms-optgroup-label").on("click", function (event) {
                        var partitionName = $(this).find('span').html();
                        partitionArray.forEach(function(entry) {
                            if (partitionName == entry.name) {
                                $(".pickListItem." + entry.id + ".ms-elem-selectable").toggle();
                                $(".pickListItem." + entry.id + ".ms-elem-selectable" + ".ms-selected").hide();
                            }
                        });
                    });
                    prLoader.hide();
                    updateForm.animate({opacity: 1}, 200);
                });
            });
        });
    }
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
                localMessage.show();
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

