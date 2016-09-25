var isShowCreateUserForm = false;
var isShowUpdateUserForm = false;
var currentItem;
$(window).on("load", function () {
    if ($(".error")[0].innerHTML != "") {
        var createUserForm = $("#createUserForm");
        createUserForm.show();
        createUserForm.animate({opacity: 1}, 200);
        isShowCreateUserForm = true;
    }
});
$(".pageMenuButt").on("click", function () {
    if (isShowUpdateUserForm) {
        var updateUserForm = $("#updateUserForm");
        updateUserForm.animate({opacity: 0}, 200);
        setTimeout(function() { updateUserForm.hide(); }, 200);
        $("#" + currentItem).css("border-left", "0");
        isShowUpdateUserForm = false;
    }
    setTimeout(function() {
        var createUserForm = $("#createUserForm");
        createUserForm.show();
        createUserForm.animate({opacity: 1}, 200);
        isShowCreateUserForm = true;
    }, 200);
});
var data;
var updateUserForm;
var fullName;
var username;
var password;
var password2;
var role;
var accessStatus;
$(".menuBodyItemInfo").on("click", function (event) {
    if (currentItem != undefined) {
        $("#" + currentItem).css("border-left", "0");
    }
    updateUserForm = $("#updateUserForm");
    if (isShowCreateUserForm) {
        var createUserForm = $("#createUserForm");
        createUserForm.animate({opacity: 0}, 200);
        setTimeout(function() { createUserForm.hide(); }, 200);
    }
    if (isShowCreateUserForm && !isShowUpdateUserForm) {
        setTimeout(function() {
            updateUserForm.show();
            updateUserForm.animate({opacity: 1}, 200);
        }, 200);
    } else {
        updateUserForm.show();
        updateUserForm.animate({opacity: 1}, 200);
    }
    isShowUpdateUserForm = true;
    isShowCreateUserForm = false;

    if (data == undefined) {
        data = $.parseJSON($(".dataJson")[0].innerHTML);
        fullName = $("#fullName");
        username = $("#username");
        password = $("#password");
        password2 = $("#password2");
        role = $("#role");
        accessStatus = $("#accessStatus");
    }
    currentItem = event.target.getAttribute("id");
    $("#" + currentItem).css("border-left", "2px solid #d87f7f");
    var id = currentItem.replace("ID-", "");
    data.forEach(function(entry) {
        console.log(entry);
        if (entry.username == id) {
            fullName.val("");
            username.val(entry.username);
            password.val("");
            password2.val("");
            if (entry.userRole.length != 0)
                role.val(entry.userRole[0].role);
            accessStatus.val("closed");
        }
    });
});

$(".menuBodyItemButtDel").on("click", function (event) {
    var id = event.target.getAttribute('id').replace("ID-", "");
    $("#deleteUser").val(id);
    $("#deleteUserForm").submit();
});

//border-left: 2px solid #d87f7f;