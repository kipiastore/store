var isShowCreateForm = false;
var isShowUpdateForm = false;
var currentItem;
var pageMenuButtTarget;
$(window).on("load", function () {
    if ($("#addError")[0].innerHTML != "") {
        try {
            var user = $.parseJSON($(".addingUserJson")[0].innerHTML);
            $("#newFullName").val(user.fullName);
            $("#newUsername").val(user.username);
            $("#newRole").val(user.userRole[0].role);
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
var data;
var updateForm;
var fullName;
var username;
var password;
var password2;
var role;
var accessStatus;
var usernameHidden;
var pageInformation;
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
            role = $("#role");
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
                if (entry.userRole.length != 0)
                    role.val(entry.userRole[0].role);
                accessStatus.val(entry.status);
            }
        });
    }
    if (pageInformation == 1) {
        if (data == undefined) {
            data = $.parseJSON($(".dataJson")[0].innerHTML);

        }
        data.forEach(function(entry) {
            if (entry.company == id) {

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