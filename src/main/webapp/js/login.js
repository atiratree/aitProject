$(document).ready(function () {
    $(".toggleLogin").click(toggleLogin);
});

function toggleLogin() {
    if ($("#signUpDiv").is(":visible")) {
        $("#signUpDiv").hide();
        $("#signInDiv").show();
    } else {
        $("#signUpDiv").show();
        $("#signInDiv").hide();
    }

    return false;
}