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
            if (entry.length < 10)
                isEnd = true;
            var tmpHtml = '';
            position += 10;
            var addressList = '';
            entry.forEach(function(company) {
                addressList += company.id + ',';
            });
            addressList = addressList.replace(/,$/, '');
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
            });
        });
    }
});