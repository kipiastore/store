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


$(".menuTitleText").on("click", function () {
    $(".pageMenuButt").animate({opacity: 0}, 200);
    if (document.URL.indexOf("searchcompany") != -1) // временное решение
        window.location.replace(document.URL.replace("searchcompany", ""));
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

    var hiddenCreateForm = $("#hiddenCreateForm");
    hiddenCreateForm.animate({opacity: 0}, 200);
    setTimeout(function() { hiddenCreateForm.hide(); }, 200);
    $("#companyIdAdd").val('');
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
    $.get('../api/admin/resource/v1/report/company/'+id, function(entry1) {
        var htmlCode = '';
        entry1.forEach(function(entry2) {
            htmlCode += '<tr id="' + entry2.id + '">';
            htmlCode += '<td>' + entry2.name + '</td>';
            htmlCode += '<td>' + new Date(entry2.createdDate).customFormat("#YYYY#-#MM#-#DD#") + '</td>';
            htmlCode += '<td>' + entry2.owner + '</td>';
            htmlCode += '<td>' + entry2.description + '</td>';
            htmlCode += '<td class="tdButton"><a href="../admin/download?id=' + entry2.fileId + '">Скачать</a></td>';
            htmlCode += '<td><span data-id="' + entry2.id + '" id="deleteRep" class="tdButton">Удалить</span></td>';
            htmlCode += '</tr>';
        });
        $("#companyIdAdd").val(id);
        $(".pageMenuButt").animate({opacity: 1}, 50);
        $("#bodyReports").html(htmlCode);

        $("#deleteRep").on("click", function (event) {
            var recordId = event.target.getAttribute("data-id");
            $.get('../api/admin/resource/v1/report/erase/' + recordId, function(entry0) {
                console.log(entry0);
            });
            $("#"+recordId).remove();
        });
    });
});

$(".pageMenuButt").on("click", function () {
    if ($("#companyIdAdd").val() == '')
        return;
    var hiddenCreateForm = $("#hiddenCreateForm");
    hiddenCreateForm.show();
    hiddenCreateForm.animate({opacity: 1}, 200);
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


