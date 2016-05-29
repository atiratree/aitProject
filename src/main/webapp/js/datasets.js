/**
 * Created by studamit on 02/05/2016.
 */

function login(){
    window.location.replace("login");
}

function logout(){
    window.location.replace("login?action=logout");
}

function addItemToCart(cartType)
{
    debugger;
    window.location = "addToCart?visualisation=" + cartType;
}

function buyItems() {
        window.location = "addToCart?/buyItems";
    }

function boughtItems()
{
    window.location ="boughtItems";
}



