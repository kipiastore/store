var additionalLeftInner = $('#additionalLeftInner');
var additionalLeftInnerClass = $('.additionalLeftInner');
var additionalBlock = $('.additionalBlock');
var topHideButt = $('.topHideButt');
var currentItemId;
var isShowingUpButton = false;
var isOpenAdditionalBlock = false;
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
    $('body,html').animate({scrollTop: 0}, 700);
});

$(window).on('scroll', function() {
    calculateAdditionalBlockPosition();

    if (window.pageYOffset > 250 && isShowingUpButton)
        return;
    if (window.pageYOffset < 250 && !isShowingUpButton)
        return;
    if (window.pageYOffset > 250) {
        topHideButt.show();
        topHideButt.animate({opacity: 1}, 500);
        isShowingUpButton = true;
    } else {
        topHideButt.animate({opacity: 0}, 300);
        setTimeout(function() { topHideButt.hide(); }, 300);
        isShowingUpButton = false;
    }
});

$('.additionalRight').on("click", function() {
    if (!isOpenAdditionalBlock) {
        additionalLeftInnerClass.animate({width: 250}, 500);
        additionalLeftInner.show();
        additionalLeftInner.animate({opacity: 1}, 500);
        isOpenAdditionalBlock = true;
    } else {
        setTimeout(function() { additionalLeftInner.hide(); }, 200);
        additionalLeftInner.animate({opacity: 0}, 200);
        additionalLeftInnerClass.animate({width: 0}, 200);
        additionalLeftInner.css('opacity', 0);
        isOpenAdditionalBlock = false;
    }
});

$(document).on('mouseup', function (event) {
    if (additionalBlock.has(event.target).length === 0){
        additionalLeftInnerClass.animate({width: 0}, 200);
        additionalLeftInner.hide();
        isOpen = false;
    }
});

$('.item-i').on('mouseenter', function(event) {
    currentItemId = event.target.getAttribute('data-id');
    $('#item-'+currentItemId).show();
});

$('document').on('mousemove', function(event) {
    if (event.target.getAttribute('data-id') != currentItemId) {
        $('.subsection').hide();
        console.log('ss');
    }
    //http://dimox.name/beautiful-tooltips-with-jquery/
});
