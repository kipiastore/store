var isShowing = false;
var isOpen = false;
var additionalLeftInner = $('#additionalLeftInner');
var additionalLeftInnerClass = $('.additionalLeftInner');
var additionalBlock = $('.additionalBlock');
var topHideButt = $('.topHideButt');

function calculateAdditionalBlockPosition() {
    if (window.pageYOffset < 333)
        additionalBlock.css('top', 435 -  Math.round(window.pageYOffset));
    else if (additionalBlock.css('top') != '100px')
        additionalBlock.css('top', 100);
}

$(window).on('load', function() {
    var preloader = $('.preloader');
    var header = $('#Header_header');
    preloader.animate({opacity: 0}, 300);
    setTimeout(function() { preloader.hide(); }, 300);
    header.show();
    header.animate({opacity: 1}, 500);
    calculateAdditionalBlockPosition();
    additionalBlock.animate({left: 0}, 500);
});

topHideButt.on("click", function () {
    var top = $('#top').offset().top;
    $('body,html').animate({scrollTop: 0}, 1000);
});

$(window).on('scroll', function() {
    calculateAdditionalBlockPosition();

    if (window.pageYOffset > 250 && isShowing)
        return;
    if (window.pageYOffset < 250 && !isShowing)
        return;
    if (window.pageYOffset > 250) {
        topHideButt.show();
        topHideButt.animate({opacity: 1}, 500);
        isShowing = true;
    } else {
        topHideButt.animate({opacity: 0}, 300);
        setTimeout(function() { topHideButt.hide(); }, 300);
        isShowing = false;
    }
});

$('.additionalRight').on("click", function() {
    if (!isOpen) {
        additionalLeftInnerClass.animate({width: 250}, 500);
        additionalLeftInner.show();
        additionalLeftInner.animate({opacity: 1}, 500);
        isOpen = true;
    } else {
        setTimeout(function() { additionalLeftInner.hide(); }, 200);
        additionalLeftInner.animate({opacity: 0}, 200);
        additionalLeftInnerClass.animate({width: 0}, 200);
        additionalLeftInner.css('opacity', 0);
        isOpen = false;
    }
});

$(document).on('mouseup', function (e) {
    if (additionalBlock.has(e.target).length === 0){
        additionalLeftInnerClass.animate({width: 0}, 200);
        additionalLeftInner.hide();
        isOpen = false;
    }
});
