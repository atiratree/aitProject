function checkPassword()
{
    var password =document.getElementById("signupPassword").value();
    var hasNumbers = password.match(/\d+/g);

    if(password.length <5)
    {
        document.getElementById("signUpPasswordError").innerHTML="Password must be greather than 5 letters";
        return false;
    }

    if(hasNumbers!=null)
    {
        document.getElementById("signUpPasswordError").innerHTML="Password must contain a number";
        return false;
    }
    
    return false;
}