var subPartitionId = $('.subPartitionId').html();
var container = $('.container');
var position = 0;
var priority;
var isEnd;

$(window).on('load', function() {
    isEnd = false;
    $('.pre-loading').show();
    $.get('../api/portal/resource/v1/company/priority/', function(entryP) {
        priority = entryP;
        $.get('../api/portal/resource/v1/company/SubPartition/' + subPartitionId + '/' + position, function(entry) {
            var tmpHtml = '';
            if (entry.length == 0) {
                container.html(tmpHtml);
                $('.pre-loading').hide();
                return;
            }
            if (entry.length < 10)
                isEnd = true;
            position += 10;
            var addressList = '';
            entry.forEach(function(company) {
                addressList += company.id + ',';
            });
            addressList = addressList.replace(/,$/, '');
            $.get('../api/portal/resource/v1/company/address/' + addressList, function(companyAddressItems) {
                entry.forEach(function(company) {
                    var count = 0;
                    var tmpAddersList;
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
                    var tmpText = '';
                    if (company.costOf != '')
                        tmpText = '<span class="companyAmount">Стоимость: <b>' + company.costOf + '</b>';
                    if (company.costOf == null)
                        tmpText = '';
                    tmpHtml
                        += '<div class="rua-l-wrapper2" style="border-color: hsla(0,' + color + '%,66%,1)">'
                        + '<div class="companyMainInfo">'
                        + '<a data-id="' + company.id + '" href="../company/' + company.id + '">'
                        + '<h3>' + company.name + '</h3>'
                        + '</a>'
                        + '<span>&nbsp;&nbsp;&nbsp;&nbsp;' + company.description + '</span>'
                        + tmpText
                        + '</div>'
                        + '<div class="AddressList">'
                        + tmpAddressTxt
                        + '</div>'
                        + '</div>';
                });
                container.html(tmpHtml);
                $('.pre-loading').hide();
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
    });
});

var isReady = true;
$(window).on('scroll', function() {
    if($(window).scrollTop() + $(window).height() == $(document).height() && position != 0 && isReady && !isEnd) {
        isReady = false;
        $('.pre-loading').show();
        $.get('../api/portal/resource/v1/company/SubPartition/' + subPartitionId + '/' + position, function(entry) {
            if (entry.length < 10)
                isEnd = true;
            var addressList = '';
            entry.forEach(function(company) {
                addressList += company.id + ',';
            });
            addressList = addressList.replace(/,$/, '');
            var tmpHtml = container.html();
            $.get('../api/portal/resource/v1/company/address/' + addressList, function(companyAddressItems) {
                entry.forEach(function(company) {
                    var tmpAddersList;
                    companyAddressItems.forEach(function(companyAddressItem) {
                        if (company.id == companyAddressItem.companyId) {
                            tmpAddersList = companyAddressItem.companyAddresses;
                        }
                    });
                    var tmpAddressTxt = '';
                    if (tmpAddersList != undefined) {
                        tmpAddersList.forEach(function (aItem) {
                            tmpAddressTxt += '<div class="address">'
                                + '<span class="addressInfo">' + aItem.address + '</span>&nbsp;'
                                + '<span>' + aItem.phones + '</span>&nbsp;'
                                + '<span>' + aItem.information + '</span>'
                                + '</div>';
                        });
                    }
                    var color = 0;
                    priority.forEach(function (tmpCol) {
                        if (tmpCol.packageId == company.companyPackageId) {
                            color = tmpCol.priority;
                        }
                    });
                    var tmpText = '';
                    if (company.costOf != '')
                        tmpText = '<span class="companyAmount">Стоимость: <b>' + company.costOf + '</b></k:if>';
                    if (company.costOf == null)
                        tmpText = '';
                    tmpHtml
                        += '<div class="rua-l-wrapper2" style="border-color: hsla(0,' + color + '%,66%,1)">'
                        + '<div class="companyMainInfo">'
                        + '<a data-id="' + company.id + '" href="../company/' + company.id + '">'
                        + '<h3>' + company.name + '</h3>'
                        + '</a>'
                        + '<span>&nbsp;&nbsp;&nbsp;&nbsp;' + company.description + '</span>'
                        + tmpText
                        + '</div>'
                        + '<div class="AddressList">'
                        + tmpAddressTxt
                        + '</div>'
                        + '</div>';
                });
                container.html(tmpHtml);
                position += 10;
                isReady = true;
                $('.pre-loading').hide();
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