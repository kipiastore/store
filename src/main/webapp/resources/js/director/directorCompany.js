
function companyOnLoad() {

}

function cleanAddressBlocks() {
    addressArray = [];
    var item;
    for (var i = 1; i < 13; i++) {
        item = {};
        item.id = i;
        item.isOpen = false;
        addressArray[i] = item;
        $("#add" + i).hide();
        $("#adAd" + i).removeAttr('required');
        $("#adAd" + i).val("");
        $("#regAd" + i).val("-1");
        $("#phAd" + i).val("");
        $("#infAd" + i).val("");
        $("#UpAdd" + i).hide();
        $("#UpAdAd" + i).removeAttr('required');
        $("#UpAdAd" + i).val("");
        $("#UpRegAd" + i).val("-1");
        $("#UpPhAd" + i).val("");
        $("#UpInfAd" + i).val("");
        $("#UpAddId" + i).val("");
    }
}