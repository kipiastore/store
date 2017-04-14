var isShowCreateForm = false;
var isShowUpdateForm = false;
var currentItem;
var pageMenuButtTarget;

var data;
var pageInformation;

var packageName;
var priority;
var cost;
var numOfPositions;
var hiddenId;

$(window).on("load", function () {
    var container = $(".container");
    container.show();
    container.animate({opacity: 1}, 200);

    var updateForm = $("#updateForm");
    updateForm.show();
    updateForm.animate({opacity: 1}, 200);
    // $(".pageMenuButt").click();
});
/*
 $(".pageMenuButt").on("click", function (event) {
 var container = $(".container");
 container.animate({opacity: 0}, 200);
 setTimeout(function() { container.hide(); }, 190);
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
 */
$(".menuBodyItemInfo").on("click", function (event) {
    /*
     var container = $(".container");
     container.animate({opacity: 0}, 200);
     setTimeout(function() { container.hide(); }, 190);
     isShowUpdateForm = true;

     if (pageInformation == undefined)
     pageInformation = $("#pageInformation").val();
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
     */
    if (currentItem != undefined) {
        $("div#" + currentItem).css("border-left", "0");
    }
    currentItem = event.target.getAttribute("id");
    $("div#" + currentItem).css("border-left", "2px solid #d87f7f");
    $(".menuBodyItemButtDel").css("border-left", "0");
    var id = currentItem.replace("ID-", "");

    if (packageName == undefined) {
        packageName = $("#name");
        priority = $("#priority");
        cost = $("#cost");
        numOfPositions = $("#numOfPositions");
        hiddenId = $("#hiddenId");
    }
    $.get('../api/admin/resource/v1/package/'+id, function(entry2) {
        packageName.val(entry2.name);
        priority.val(entry2.priority);
        cost.val(entry2.cost);
        numOfPositions.val(entry2.numOfPositions);
        hiddenId.val(id);
    });
});

/*
 $(".menuBodyItemButtDel").on("click", function (event) {
 var tmp = event.target.getAttribute('id').replace("ID-", "");
 $("#deleteKey").val(tmp);
 if (confirm("Удалить?")) {
 $("#deleteForm").submit();
 }
 });
 */
