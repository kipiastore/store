var isShowing = false;
var isOpen = false;
var additionalLeftInner = $('#additionalLeftInner');
var additionalLeftInnerClass = $('.additionalLeftInner');
var additionalBlock = $('.additionalBlock');
var topHideButt = $('.topHideButt');

window.onload = function() {

    $('.preloader').hide();
    $('#Header_header').animate({opacity: 1}, 500);
    if (window.pageYOffset < 333)
        additionalBlock.css('top', 435 -  Math.round(window.pageYOffset));
    else if (additionalBlock.css('top') != '100px')
        additionalBlock.css('top', 100);
    additionalBlock.animate({left: 0}, 500);
};
window.onscroll = function() {
    if (window.pageYOffset < 333)
        additionalBlock.css('top', 435 -  Math.round(window.pageYOffset));
    else if (additionalBlock.css('top') != '100px')
        additionalBlock.css('top', 100);

    if (window.pageYOffset > 250 && isShowing)
        return;
    if (window.pageYOffset < 250 && !isShowing)
        return;
    if (window.pageYOffset > 250) {
        topHideButt.animate({opacity: 1}, 500);
        isShowing = true;
    } else {
        topHideButt.animate({opacity: 0}, 300);
        isShowing = false;
    }
};
$('.additionalRight').on("click", function() {
    if (!isOpen) {
        additionalLeftInnerClass.animate({width: 250}, 500);
        isOpen = true;
        additionalLeftInner.show();
        additionalLeftInner.animate({opacity: 1}, 500);
    } else {
        additionalLeftInnerClass.animate({width: 0}, 200);
        isOpen = false;
        additionalLeftInner.hide();
    }
});
$(document).mouseup(function (e) {
    if (additionalBlock.has(e.target).length === 0){
        additionalLeftInnerClass.animate({width: 0}, 200);
        additionalLeftInner.hide();
        isOpen = false;
    }
});
function submit() {
    $('#mainForm').submit();
}