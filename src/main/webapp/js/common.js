String.prototype.contains = function (it) {
    return this.indexOf(it) != -1;
};

function postData(url, data, successCallback, failCallback) {
    $.ajax({
        url: '/api/' + url,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        type: 'POST'
    }).done(function (data) {
        if (successCallback != null && successCallback instanceof Function) {
            successCallback(data)
        }
    }.bind(this)).fail(function (data) {
        if (failCallback != null && failCallback instanceof Function) {
            failCallback(data)
        }
    }.bind(this));
}

function homeButtonClicked() {
    window.location.replace("/");
}

function login() {
    window.location.replace("login");
}

function logout() {
    window.location.replace("login?action=logout");
}