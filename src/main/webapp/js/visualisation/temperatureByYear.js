function showYearTemperatures(data) {
    var visId = getResource('visId');
    if (!visId) {
        return
    }

    var chartData = null;

    if (data && data.length > 0) {
        var options = null;

        if (visId.contains("ALL_PLACES")) {
            options = {
                filterAttributeName: 'place',
                firstItem: {
                    filterValue: "BOLZANO",
                    name: 'Bolzano',
                    color: '#f77'
                },
                secondItem: {
                    filterValue: "MERANO",
                    name: 'Merano',
                    color: '#0e1'
                },
                thirdItem: {
                    filterValue: "SELVA_VAL_GARDENA",
                    name: 'Selva Val Gardena',
                    color: '#a7f'
                }
            }
        } else {
            options = {
                filterAttributeName: 'measurementType',
                firstItem: {
                    filterValue: "MIN",
                    name: 'Minimum',
                    color: '#58f'
                },
                secondItem: {
                    filterValue: "MAX",
                    name: 'Maximum',
                    color: '#f87'
                },
                thirdItem: {
                    filterValue: "AVG",
                    name: 'Average',
                    color: '#8f4'
                }
            }
        }


        if (options != null) {
            chartData = prepareYearTemperaturesData(data, options);
        }
    }

    if (chart != null) {
        chart.destroy();
    }

    chart = new Chart(getContext(), {
        type: 'line',
        data: {
            labels: chartData == null ? [] : chartData.labels,
            datasets: chartData == null ? [] : chartData.datasets
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        callback: function (value) {
                            return value + ' °C';
                        }
                    }
                }]
            },
            responsive: true,
            maintainAspectRatio: false
        }
    });
}

function prepareYearTemperaturesData(data, options) {
    var result = {
        datasets: [],
        labels: null
    };

    var first = options.firstItem;
    var second = options.secondItem;
    var third = options.thirdItem;

    var firstData = sortTemperatureObjects(filterByAttribute(data, options.filterAttributeName, first.filterValue));
    var secondData = sortTemperatureObjects(filterByAttribute(data, options.filterAttributeName, second.filterValue));
    var thirdData = sortTemperatureObjects(filterByAttribute(data, options.filterAttributeName, third.filterValue));

    var firstObjs = sortTemperatureObjects([getFirstOrNull(firstData), getFirstOrNull(secondData), getFirstOrNull(thirdData)]);
    var lastObjs = sortTemperatureObjects([getLastOrNull(firstData), getLastOrNull(secondData), getLastOrNull(thirdData)]);

    var startYear = firstObjs[0].year;
    var startMonth = firstObjs[0].month;
    var endYear = lastObjs[lastObjs.length - 1].year;
    var endMonth = lastObjs[lastObjs.length - 1].month;

    result.labels = getLabelsByYear(startYear, startMonth, endYear, endMonth);
    result.datasets.push({
        label: first.name,
        data: getTemperaturesByYear(firstData, startYear, startMonth, endYear, endMonth),
        borderColor: first.color,
        fill: false
    }, {
        label: second.name,
        data: getTemperaturesByYear(secondData, startYear, startMonth, endYear, endMonth),
        borderColor: second.color,
        fill: false
    }, {
        label: third.name,
        data: getTemperaturesByYear(thirdData, startYear, startMonth, endYear, endMonth),
        borderColor: third.color,
        fill: false
    });

    return result;
}

function getTemperaturesByYear(data, startYear, startMonth, endYear, endMonth) {
    if (!data || data.length == 0) {
        return [];
    }

    var values = [];
    var dataCounter = 0;
    while (startYear <= endYear && startMonth <= endMonth) {
        var temp = data[dataCounter];
        var value = null;
        if (temp.year == startYear && temp.month == startMonth) {
            value = temp.temperature;
            if (dataCounter + 1 < data.length) {
                dataCounter += 1;
            }
        }
        values.push(value);

        startMonth++;
        if (startMonth == 13) {
            startMonth = 1;
            startYear++;
        }
    }

    return values;
}

function getLabelsByYear(startYear, startMonth, endYear, endMonth) {
    var labels = [];
    while (startYear <= endYear && startMonth <= endMonth) {
        labels.push(getMonth(startMonth) + ' ' + startYear);

        startMonth++;
        if (startMonth == 13) {
            startMonth = 1;
            startYear++;
        }
    }

    return labels;
}
