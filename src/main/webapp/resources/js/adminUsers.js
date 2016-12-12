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
var site;

var addressArray;
var BreakException = {};

var dataCompanyAddressJson;

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
});

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
    cleanAddressBlocks();
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

$(".menuTitleText").on("click", function () {
    if (document.URL.indexOf("searchcompany") != -1) // временное решение
        window.location.replace(document.URL.replace("searchcompany", "companies"));
    cleanAddressBlocks();
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
    if (pageInformation == 1) {
        loadCompany(id);
    }
    if (pageInformation == 2) {
        $("#hiddenId").val(id);
    }
});

$(".menuBodyItemInfo").on("click", function (event) {
    var container = $(".container");
    container.animate({opacity: 0}, 200);
    setTimeout(function() { container.hide(); }, 190);
    isShowUpdateForm = true;

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
        $("div#" + currentItem).css("border-left", "0");
    }
    currentItem = event.target.getAttribute("id");
    $("div#" + currentItem).css("border-left", "2px solid #d87f7f");
    $(".menuBodyItemButtDel").css("border-left", "0");
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
        loadCompany(id);
    }
});

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
    }
    if (dataCompanyAddressJson == undefined) {
        dataCompanyAddressJson = $.parseJSON($(".companyAddressJson")[0].innerHTML);
    }
    var item;
    addressArray = [];
    cleanAddressBlocks();

    $.get('../api/admin/resource/v1/company/'+id, function(entry) {
        console.log('sd');
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

        isShowForOperator.prop('checked', entry.isShowForOperator);
        isShowForSite.prop('checked', entry.isShowForSite);
        isPaid.prop('checked', entry.isPaid);
        isRedirect.prop('checked', entry.isRedirect);
        isOffPosition.prop('checked', entry.isOffPosition);
        isClosed.prop('checked', entry.isClosed);
        isPriority.prop('checked', entry.isPriority);

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
if (pageInformation == 2)
    $('#optgroup').multiSelect({ selectableOptgroup: true });

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