var isShowingUpButton = false;
var isOpenAdditionalBlock = false;
var additionalLeftInner = $('#additionalLeftInner');
var additionalLeftInnerClass = $('.additionalLeftInner');
var additionalBlock = $('.additionalBlock');
var topHideButt = $('.topHideButt');
var itemId;
var rptShort;
var isRptShortClosed;

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
    rptShort = $('.rptShort.hide-bt');
    if (rptShort.length > 0) {
        isRptShortClosed = true;
    }
    var preloader = $('.preloader');
    setTimeout(function() { preloader.animate({opacity: 0}, 300); }, 510);
    setTimeout(function() { preloader.hide(); }, 820);

    calculateAdditionalBlockPosition();
    additionalBlock.animate({left: 0}, 500);
});

$('.open-list-btn').on("click", function () {
    if (rptShort != undefined && rptShort.length > 0) {
        if (isRptShortClosed) {
            rptShort.addClass('rptShort-open');
            isRptShortClosed = false;
            $('.open-list-btn').html('Скрыть!');
        } else {
            rptShort.removeClass("rptShort-open");
            isRptShortClosed = true;
            $('.open-list-btn').html('Показать все!');
        }
    }
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
        $('.show-subsection').removeClass("open-down");
    }
});

$('.show-subsection').on('click', function(event) {
    $('.show-subsection').removeClass("open-down");
    if (itemId != undefined && itemId != event.target.getAttribute('data-id'))
        $('#item-'+itemId).hide();
    if (itemId != undefined && itemId == event.target.getAttribute('data-id')) {
        $('#item-'+itemId).hide();
        itemId = undefined;
        return;
    }
    itemId = event.target.getAttribute('data-id');
    $(event.target).addClass("open-down");
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


function setAutocomplete(source) {
    window.autocompleteState = {
        previousDateInMilliseconds : new Date().getTime(),
        searchInput : $('#search-param'),
        previousValue : ''
    };

    window.autocompleteState.searchInput.on("keyup", function() {
        var value = window.autocompleteState.searchInput.val().trim();
        if (value != '') {
            var currentDateInMilliseconds = new Date().getTime();
            if (currentDateInMilliseconds - window.autocompleteState.previousDateInMilliseconds < 200) {
                return;
            } else {
                window.autocompleteState.previousDateInMilliseconds = currentDateInMilliseconds;
            }
            window.autocompleteState.previousValue = value;
            $.get(source + value, function (entry) {
                if (window.autocompleteState.previousValue != value) {
                    return;
                }
                window.autocompleteState.searchInput.autocomplete({
                    source: entry,
                    minLength: 1,
                    autoFocus: true
                });
                window.autocompleteState.searchInput.autocomplete('search', value);
            });
        } else {
            window.autocompleteState.previousValue = value;
            window.autocompleteState.searchInput.autocomplete({
                source: []
            });
        }
    });
}
(function($){


    // Defining our jQuery plugin

    $.fn.paulund_modal_box = function(prop){
        // Default parameters

        var options = $.extend({
            height : "250",
            width : "500",
            title:"О нас",
            description: "Описание нашей компании",
            top: "20%",
            left: "35%",
        },prop);

        //Click event on element
        return this.click(function(e){
            add_block_page();
            add_popup_box();
            add_styles();

            $('.paulund_modal_box').fadeIn();
        });

        /**
         * Add styles to the html markup
         */
        function add_styles(){
            $('.paulund_modal_box').css({
                'position':'absolute',
                'left':options.left,
                'top':options.top,
                'display':'none',
                'height': options.height + 'px',
                'width': options.width + 'px',
                'border':'1px solid #fff',
                'box-shadow': '0px 2px 7px #292929',
                '-moz-box-shadow': '0px 2px 7px #292929',
                '-webkit-box-shadow': '0px 2px 7px #292929',
                'border-radius':'10px',
                '-moz-border-radius':'10px',
                '-webkit-border-radius':'10px',
                'background': '#f2f2f2',
                'z-index':'50',
            });
            $('.paulund_modal_close').css({
                'position':'relative',
                'top':'-25px',
                'left':'20px',
                'float':'right',
                'display':'block',
                'height':'50px',
                'width':'50px',
                'background': 'url(resources/images/close-paulund-modal-box.png) no-repeat',
            });
            $('.paulund_block_page').css({
                'position':'fixed',
                'top':'0',
                'left':'0',
                'background-color':'rgba(0,0,0,0.6)',
                'height':'100%',
                'width':'100%',
                'z-index':'10'
            });
            $('.paulund_inner_modal_box').css({
                'background-color':'#fff',
                'height':(options.height - 50) + 'px',
                'width':(options.width - 50) + 'px',
                'padding':'10px',
                'margin':'15px',
                'border-radius':'10px',
                '-moz-border-radius':'10px',
                '-webkit-border-radius':'10px'
            });
        }

        /**
         * Create the block page div
         */
        function add_block_page(){
            var block_page = $('<div class="paulund_block_page"></div>');

            $(block_page).appendTo('body');
        }

        /**
         * Creates the modal box
         */
        function add_popup_box(){
            var pop_up = $('<div class="paulund_modal_box"><a href="#" class="paulund_modal_close"></a><div class="paulund_inner_modal_box"><h2>' + options.title + '</h2><p>' + options.description + '</p></div></div>');
            $(pop_up).appendTo('.paulund_block_page');

            $('.paulund_modal_close').click(function(){
                $(this).parent().fadeOut().remove();
                $('.paulund_block_page').fadeOut().remove();
            });
        }

        return this;
    };

})(jQuery);

