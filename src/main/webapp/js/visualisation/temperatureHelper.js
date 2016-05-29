function prepareTemperatureSelects(visId) {
    $('#temperatureYearSelect').show();
    var fromYear = $('#fromYearPicker');
    var toYear = $('#toYearPicker');
    var maxYear = getResource('maxYear');
    var minYear = getResource('minYear');

    for (var i = maxYear; i >= minYear; i--) {
        fromYear.append($('<option />').val(i).html(i));
        toYear.append($('<option />').val(i).html(i));
    }

    fromYear.val(maxYear - 10);

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
