var subPartitionId = $('.subPartitionId').html();
var container = $('.container');
var position = 0;
var priority;

$(window).on('load', function() {
    $.get('../api/portal/resource/v1/company/priority/', function(entryP) {
        priority = entryP;
        //console.log(priority);
        $.get('../api/portal/resource/v1/company/SubPartition/' + subPartitionId + '/' + position, function(entry) {
            var tmpHtml = '';
            position += 10;
            entry.forEach(function(company) {
                var color = 0;
                priority.forEach(function (tmpCol) {
                    if (tmpCol.packageId == company.companyPackageId) {
                        color = tmpCol.priority;
                    }
                });
                tmpHtml += '<div class="rua-l-wrapper2" style="border-color: hsla(0,' + color + '%,66%,1)">'
                    + '<a data-id="' + company.id + '" href="../company/' + company.id + '">'
                    + '<h3>' + company.name + '</h3>'
                    + '</a>'
                    + '<span>' + company.description + '</span>'
                    + '</div>';
            });
            container.html(tmpHtml);
        });
    });
});

var isReady = true;
$(window).on('scroll', function() {
    if($(window).scrollTop() + $(window).height() == $(document).height() && position != 0 && isReady) {
        isReady = false;
        $.get('../api/portal/resource/v1/company/SubPartition/' + subPartitionId + '/' + position, function(entry) {
            var tmpHtml = container.html();
            entry.forEach(function(company) {
                var color = 0;
                priority.forEach(function (tmpCol) {
                    if (tmpCol.packageId == company.companyPackageId) {
                        color = tmpCol.priority;
                    }
                });
                tmpHtml += '<div class="rua-l-wrapper2" style="border-color: hsla(0,' + color + '%,66%,1)">'
                    + '<a data-id="' + company.id + '" href="../company/' + company.id + '">'
                    + '<h3>' + company.name + '</h3>'
                    + '</a>'
                    + '<span>' + company.description + '</span>'
                    + '</div>';
            });
            container.html(tmpHtml);
            position += 10;
            isReady = true;
        });
    }
});