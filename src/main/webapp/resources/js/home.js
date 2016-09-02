/**
 * Created by Asura on 31.08.2016.
 */
var isShowing = false;
window.onscroll = function() {
    if (window.pageYOffset > 200 && isShowing)
        return;
    if (window.pageYOffset < 200 && !isShowing)
        return;
    if (window.pageYOffset > 200) {
        $('.topHideButt').animate({opacity: 1}, 500);
        isShowing = true;
    } else {
        $('.topHideButt').animate({opacity: 0}, 300);
        isShowing = false;
    }
}
window.onload = function() {
    $('#Header_header').animate({opacity: 1}, 300);
}
var isOpen = false;
$('.additionalRight').on("focus click", function() {
    if (!isOpen) {
        $('.additionalLeftInner').animate({width: 500}, 500);
        isOpen = true;
    } else {
        $('.additionalLeftInner').animate({width: 0}, 500);
        isOpen = false;
    }
});
