var isShowingUpButton = false;
var isOpenAdditionalBlock = false;
var additionalLeftInner = $('#additionalLeftInner');
var additionalLeftInnerClass = $('.additionalLeftInner');
var additionalBlock = $('.additionalBlock');
var topHideButt = $('.topHideButt');
var itemId;

function calculateAdditionalBlockPosition() {
    if (window.pageYOffset < 333)
        additionalBlock.css('top', 435 -  Math.round(window.pageYOffset));
    else if (additionalBlock.css('top') != '100px')
        additionalBlock.css('top', 100);
}
var header = $('#Header_header');
header.show();
header.animate({opacity: 1}, 500);

$(window).on('load', function() {
    var preloader = $('.preloader');

    preloader.animate({opacity: 0}, 300);
    setTimeout(function() { preloader.hide(); }, 300);

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
        topHideButt.animate({opacity: 1}, 200);
        isShowingUpButton = true;
    } else {
        topHideButt.animate({opacity: 0}, 100);
        setTimeout(function() { topHideButt.hide(); }, 100);
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

$(document).on('click', function (event) {
    if (additionalBlock.has(event.target).length === 0){
        additionalLeftInnerClass.animate({width: 0}, 200);
        additionalLeftInner.hide();
        isOpenAdditionalBlock = false;
    }
    if (itemId != undefined && itemId != event.target.getAttribute('data-id')) {
        $('#item-'+itemId).hide();
        itemId = undefined;
    }
});

$('.show-subsection').on('click', function(event) {
    if (itemId != undefined && itemId != event.target.getAttribute('data-id'))
        $('#item-'+itemId).hide();
    if (itemId != undefined && itemId == event.target.getAttribute('data-id')) {
        $('#item-'+itemId).hide();
        itemId = undefined;
        return;
    }
    itemId = event.target.getAttribute('data-id');
    $('#item-'+itemId).show();
});

$('.left-container').on("click", function() {
    var container = $('.mail-container');
    container.animate({opacity: 0}, 200);
    setTimeout(function() { container.hide(); }, 190);
});
$('.right-container').on("click", function() {
    var nameField = $('#nameField');
    var emailField = $('#emailField');
    var messageField = $('#messageField');
    var regEx = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
    if (nameField.val() == "" || emailField.val() == "" || messageField.val() == "" || !regEx.test(emailField.val())) {
        if (nameField.val() == "") {
            nameField.css("border-left", "2px solid #e8b3bd");
        } else {
            nameField.css("border-left", "");
        }

        if (emailField.val() == "") {
            emailField.css("border-left", "2px solid #e8b3bd");
        } else {
            emailField.css("border-left", "");
        }

        if (!regEx.test(emailField.val())) {
            emailField.css("border-left", "2px solid #e8b3bd");
        } else {
            emailField.css("border-left", "");
        }

        if (messageField.val() == "") {
            messageField.css("border-left", "2px solid #e8b3bd");
        } else {
            messageField.css("border-left", "");
        }

        return;
    }
    var container = $('.mail-container');
    container.animate({opacity: 0}, 200);
    setTimeout(function() {
        container.hide();
        $('#mailForm').submit();
    }, 190);
});
$('#requestButt').on("click", function() {
    var container = $('.mail-container');
    container.show();
    container.animate({opacity: 1}, 200);
    $('#emailType').val(1);
});
$('#imgButt').on("click", function() {
    var container = $('.mail-container');
    container.show();
    container.animate({opacity: 1}, 200);
    $('#emailType').val(2);
});
$('.b').on("click", function() {
    var container = $('.mail-container');
    container.show();
    container.animate({opacity: 1}, 200);
    $('#emailType').val(3);
});
$('.btn.btn-primary').on("click", function(event) {
    var currentItem = event.target.getAttribute("data-id");
    var hiddenBlock = $('.address.hiddenAdr.address-' + currentItem);
    if (!hiddenBlock.is(':visible')) {
        hiddenBlock.show();
        hiddenBlock.animate({opacity: 1}, 200);
    } else {
        setTimeout(function() {
            hiddenBlock.hide();
        }, 190);
        hiddenBlock.animate({opacity: 0}, 200);
    }
});
