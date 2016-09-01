/**
 * Created by Asura on 31.08.2016.
 */
window.onscroll = function() {
    if (window.pageYOffset > 100)
        document.getElementById('topHideButt').style.display = 'block';
    else
        document.getElementById('topHideButt').style.display = 'none';
}
//http://easywebscripts.net/javascript/opacity.php