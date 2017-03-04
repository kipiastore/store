$("#headerSearchButt").on("click", function () {
    var search = $(".search");
    search.show();
    search.animate({opacity: 1}, 500);
    $('#searchInKey').focus();
});
$("#headerSearchCancelButt").on("click", function () {
    var search = $(".search");
    search.animate({opacity: 0}, 300);
    setTimeout(function() { search.hide(); }, 300);
});
$(".search").on("click", function (event) {
    if (event.target.getAttribute("class") != "search")
        return;
    var search = $(".search");
    search.animate({opacity: 0}, 300);
    setTimeout(function() { search.hide(); }, 300);
});

$(document).ready(function(){
    $(window).on('keypress', function(e) {
        var key = e.keyCode ? e.keyCode : e.which;
        console.log(e.keyCode);
        if (e.keyCode == 119) {
            $("#headerSearchButt").click();
        }
        if (e.keyCode == 27) {
            $("#headerSearchCancelButt").click();
        }
        if (e.keyCode == 13) {
            $(".searchForm").submit();
        }
    });
});