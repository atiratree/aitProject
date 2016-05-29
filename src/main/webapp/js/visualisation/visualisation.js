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
        prepareTemperatureSelects(visId);
    }//else if(visId.startsWith("SALINITY")){
    //
    // }
}

function setVisualisation() {
    var visId = getResource('visId');
    if (!visId) {
        return
    }

    if (visId.startsWith("TEMPERATURE")) {
        var fromYear = $("#fromYearPicker").find("option:selected").text();
        var toYear = $("#toYearPicker").find("option:selected").text();
        postData('temperature/find',
            {
                "fromYear": fromYear,
                "toYear": toYear,
                "type": visId
            },
            showTemperatures);
    }//else if(visId.startsWith("SALINITY")){
    //
    // }
}
