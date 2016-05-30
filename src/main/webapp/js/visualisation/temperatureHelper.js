function prepareYearTemperatureSelects(visId) {
    $('#temperatureYearSelect').show();
    var fromYear = $('#fromYearPicker');
    var toYear = $('#toYearPicker');
    var maxYear = getResource('maxYear');
    var minYear = getResource('minYear');

    for (var i = maxYear; i >= minYear; i--) {
        fromYear.append($('<option />').val(i).html(i));
        toYear.append($('<option />').val(i).html(i));
    }

    fromYear.val(maxYear - 5);

    function changeVisualisation() {
        var fromYearVal = fromYear.find("option:selected").text();
        var toYearVal = toYear.find("option:selected").text();
        if (fromYearVal > toYearVal) {
            fromYear.val(toYearVal);
        }
        setVisualisation()
    }

    toYear.change(changeVisualisation);
    fromYear.change(changeVisualisation);
}

function prepareMonthTemperatureSelects() {
    $('#temperatureYearSelect').show();
    var monthPicker = $('#monthPicker');

    getAllMonthStrings().map(function (month) {
        monthPicker.append($('<option />').val(month).html(month));
    });
    
    monthPicker.change(setVisualisation);
}



function getFirstOrNull(data) {
    return data.length == 0 ? null : data[0];
}

function getLastOrNull(data) {
    return data.length == 0 ? null : data[data.length - 1];
}

function getMonth(number) {
    if (isNaN(number) || number < 1 || number > 12) {
        return "";
    }
    return getAllMonthStrings()[number - 1];
}

function getAllMonthStrings() {
    return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
}

function getMonthsNumber(month) {
    return getAllMonthStrings().indexOf(month) + 1;
}


function filterByAttribute(data, attribute, value) {
    return data.filter(function (o) {
        return o[attribute] == value
    });
}


function getContext() {
    return document.getElementById("canvas");
}


function sortTemperatureObjects(data) {
    return data.filter(function (obj) {
        return obj != null;
    }).sort(function (a, b) {
        if (b.year != a.year) {
            return a.year - b.year
        }

        return a.month - b.month;
    });
}