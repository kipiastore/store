var container;
var isShowCreateForm = false;
var isShowUpdateForm = false;
var updateForm;
var createForm;
var pageMenuButt;
var currentItem;
$(window).on("load", function () {
    // global init
    createForm = $("#createForm");
    updateForm = $("#updateForm");
    pageMenuButt = $(".pageMenuButt");
    companyOnLoad();

    // Last updated
    container = $(".container");
    container.show();
    container.animate({opacity: 1}, 200);

    // action - show createForm
    pageMenuButt.on("click", function () {
        // hide unused
        container.animate({opacity: 0}, 200);
        setTimeout(function() { container.hide(); }, 190);
        $(".error").hide();
        $(".success").hide();
        if (isShowUpdateForm) {
            updateForm.animate({opacity: 0}, 200);
            setTimeout(function() { updateForm.hide(); }, 200);
            $("div#" + currentItem).css("border-left", "0");
            isShowUpdateForm = false;
        }
        // load form
        pageMenuButt.css("background", "#16a085");
        setTimeout(function() {
            var createForm = $("#createForm");
            createForm.show();
            createForm.animate({opacity: 1}, 200);
            isShowCreateForm = true;
        }, 200);
        // call a extensions
        cleanAddressBlocks();
    });
});