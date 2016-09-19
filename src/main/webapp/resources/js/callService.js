$("#headerSearchButt").on("click", function () {
    var search = $(".search");
    search.show();
    search.animate({opacity: 1}, 500);
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