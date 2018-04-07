$(function () {
    loadCustomerGC()
})

function loadCustomerGC() {
    $.ajax({
        type: "post",
        url: ctx + "/customer/queryCustomerGC",
        dataType: "json",
        success: function (data) {

            if (data.code == 200) {
                var levels= data.levels;
                var counts= data.counts;

                var myChart = echarts.init(document.getElementById('main'));
                var option = {
                    title: {
                        text: '客户构成分析 '
                    },
                    tooltip: {},
                    legend: {
                        data: ['数量']
                    },
                    xAxis: {
                        data: levels
                    },
                    yAxis: {},
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: counts
                    }]
                };
                myChart.setOption(option);
            }
        }
    })
}