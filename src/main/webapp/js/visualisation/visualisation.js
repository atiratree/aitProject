var chart;

$(document).ready(function () {
    prepareVisualisation();
    setVisualisation();
});

function prepareVisualisation() {
    var visId = getResource('visId');
    if (!visId) {
        return
    }

    if (visId.startsWith("TEMPERATURE")) {
        prepareYearTemperatureSelects(visId);
    }else if(visId.startsWith("MONTH_TEMPERATURE")){
        prepareMonthTemperatureSelects(visId);
    }
}

function setVisualisation() {
    var visId = getResource('visId');
    if (!visId) {
        return
    }
    
    if (visId.startsWith("TEMPERATURE")) {
        var fromYear = $("#fromYearPicker").find("option:selected").text();
        var toYear = $("#toYearPicker").find("option:selected").text();
        postData('temperature/findByYear',
            {
                "fromYear": fromYear,
                "toYear": toYear,
                "type": visId
            },
            showYearTemperatures);
    } else if (visId.startsWith("MONTH_TEMPERATURE")) {
        var month = $("#monthPicker").find("option:selected").text();
        postData('temperature/findByMonth',
            {
                "month": getMonthsNumber(month),
                "type": visId
            },showTemperaturesByMonth);
    }
}
