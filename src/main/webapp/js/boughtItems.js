/**
 * Created by studamit on 28/05/2016.
 */

function homeButtonClicked() {
    var homeButtonId = document.getElementById("homeButton");
    window.location.replace("/");
}

function logOut() {
    window.location.replace("login?action=logout");
}