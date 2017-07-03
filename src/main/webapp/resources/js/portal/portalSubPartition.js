var subPartitionId = $('.subPartitionId').html();
var container = $('.container');
var position = 0;
var priority;
var isEnd;

$(window).on('load', function() {
    $.get('../api/portal/resource/v1/company/priority/', function(entryP) {
        priority = entryP;
    });
    if (parseInt($('.companyCounter').html()) < 10) {
        isEnd = true;
    }
    else
        isEnd = false;
    position += 10;
});

function showLoading() {
    var preLoad = $('.pre-loading');
    preLoad.show();
    preLoad.animate({paddingTop: 15, paddingBottom: 30, opacity: 1}, 200);
}

function hideLoading() {
    var preLoad = $('.pre-loading');
    preLoad.animate({paddingTop: 0, paddingBottom: 0, opacity: 0}, 200);
    setTimeout(function() {
        preLoad.hide();
    }, 200);
}

var isReady = true;
$('.load-more-btn').on('click', function() {
    if (parseInt($('.companyCounter').html()) === 0)
        isEnd = true;
    //if($(window).scrollTop() + $(window).height() >= $(document).height() - 200  && position != 0 && isReady && !isEnd) {
    if (position != 0 && isReady && !isEnd) {
        isReady = false;
        showLoading();
        $.get('../api/portal/resource/v1/company/SubPartition/' + subPartitionId + '/' + position, function(entry) {
            if (entry.length == 0) {
                hideLoading();
                return;
            }
            var addressList = '';
            entry.forEach(function(company) {
                addressList += company.id + ',';
            });
            addressList = addressList.replace(/,$/, '');
            var tmpHtml = container.html();
            $.get('../api/portal/resource/v1/company/address/' + addressList, function(companyAddressItems) {
                entry.forEach(function(company) {
                    var tmpAddersList;
                    var count = 0;
                    companyAddressItems.forEach(function(companyAddressItem) {
                        if (company.id == companyAddressItem.companyId) {
                            tmpAddersList = companyAddressItem.companyAddresses;
                        }
                    });
                    var tmpAddressTxt = '';
                    if (tmpAddersList != undefined) {
                        tmpAddersList.forEach(function (aItem) {
                            if (count == 0) {
                                tmpAddressTxt += '<div class="address">'
                                    + '<span class="addressInfo">' + aItem.address + '</span>&nbsp;'
                                    + '<span>' + aItem.phones + '</span>&nbsp;'
                                    + '<span>' + aItem.information + '</span>'
                                    + '</div>';
                            }
                            if (count > 0) {
                                tmpAddressTxt += '<div class="hiddenAdr-btn">'
                                    + '<p><a class="btn btn-primary" data-id="' + company.id + '">Филиалы</a></p>'
                                    + '</div>'
                                    + '<div class="address hiddenAdr address-' + company.id + '">'
                                    + '<span class="addressInfo">' + aItem.address + '</span>&nbsp;'
                                    + '<span>' + aItem.phones + '</span>&nbsp;'
                                    + '<span>' + aItem.information + '</span>'
                                    + '</div>';
                            }
                            count++;
                        });
                    }
                    var color = 0;
                    priority.forEach(function (tmpCol) {
                        if (tmpCol.packageId == company.companyPackageId) {
                            color = tmpCol.priority;
                        }
                    });
                    var costOf = '';
                    var totalArd = '';
                    var imageBlock = '';

                    console.log(company);

                    //if (company.costOf != '' && company.costOf != null)
                        //costOf = '<span class="companyAmount">Стоимость: <b>' + company.costOf + '</b></span>';
                    //else
                        //costOf = '<span class="companyAmount">Цену уточняйте</span>';

                    if (tmpAddressTxt != '')
                        totalArd = '<div class="AddressList">' + tmpAddressTxt + '</div>';
                    if (company.imageId != '' && company.isPaid)
                        imageBlock = '<img class="position-image" src="../download?id=' + company.imageId + '" title="">';

                    tmpHtml +=
                        '<div class="rua-l-wrapper2" style="border-color: hsla(0,' + color + '%,66%,1)">' +
                            '<div class="companyMainInfo">' +
                                imageBlock +
                                '<div class="company-text-block">' +
                                    '<a data-id="' + company.id + '" href="../company/' + company.id + '">' +
                                    '<h3>' + company.name + ' → <span class="visitors">Просмотров: ' + company.countCompany + '</span></h3>' +
                                    '</a>' +
                                    '<span>' + company.description + '</span>' +
                                    '<p>' +
                                    '<a title="' + company.name + ' - Каталог товаров Одесса" href="">Показать весь список товаров/услуг фирмы "' + company.name + '"</a>' +
                                    costOf +
                                    '</p>' +
                                '</div>' +
                            '</div>' +
                            '<div class="container-end"></div>' +
                            totalArd +
                        '</div>';

                });
                container.html(tmpHtml);
                if (entry.length < 10) {
                    isEnd = true;
                    $('.load-more-btn').animate({backgroundColor: "#ffffff"}, 200);
                    $('.load-more-btn').html('В этом разделе больше нет доступных позиций.');
                    $('.load-more-btn').css('cursor','auto');
                }

                position += 10;
                isReady = true;
                hideLoading();
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
            });
        });
    }
});