function showTemperaturesByMonth(data) {
    var visId = getResource('visId');
    if (!visId) {
        return
    }

    var chartData = null;

    if (data && data.length > 0) {
        chartData = prepareDataByMonth(data);
    }

    if (chart != null) {
        chart.destroy();
    }

    chart = new Chart(getContext(), {
        type: 'bar',
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

function prepareDataByMonth(data) {
    var result = {
        datasets: [],
        labels: null
    };

    var sorted = sortTemperatureObjects(data);

    result.labels = sorted.map(function (temp) {
        return temp.year + '';
    });
    var datasets = sorted.map(function (temp) {
        return temp.temperature;
    });

    var month = $('#monthPicker').find("option:selected").text();

    result.datasets.push({
        label: month,
        data: datasets,
        backgroundColor: "#ffba4f"
    });

    return result;
}
