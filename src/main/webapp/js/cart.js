/**
 * Created by studamit on 13/05/2016.
 */


function checkOutItems() {
    window.location.replace("buy");
}

function deleteCartItem(deletingItemType) {
    debugger;
    var deletingDiv = "cartItemType" + deletingItemType;
    var deletingDiv = document.getElementById(deletingDiv);
    deletingDiv.innerHTML = "";
    window.location = "cart?deleteItem," + deletingItemType;
}

//function to delete the temperature item from cart
function deleteTemperature() {
    document.getElementById("temperatureDiv").innerHTML = "";
    window.location = "cart?deleteItem," + 1;
}

// function to delete salinity from the cart
function deleteSalinity() {
    document.getElementById("salinityDiv").innerHTML = "";
    window.location = "cart?deleteItem," + 2;
}

