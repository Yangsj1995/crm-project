$(function () {
    loadCustomerServeType()
})

function loadCustomerServeType() {
    $.ajax({
        type:"post",
        url:ctx + "/customer_serve/queryCustomerServeType",
        dataType:"json",
        success:function (data) {

            if(data.code==200){
                //types={退款，售后支持}
                var types = data.types;
                //datas = {{name: 退款, value : 1},{{name: 售后支持, value : 3}}
                var datas = data.datas;
                //基于准备好的dom, 初始化echarts实例
                var chart = echarts.init(document.getElementById("main"));
                var option = {
                    title : {
                        text: '客户服务分析',
                        subtext: 'crm',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left:'left',
                        data: types

                    },
                    series : [
                        {
                            name: '服务类型',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '50%'],
                            data: datas,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                //设置图表的配置项和数据项
                chart.setOption(option);
            }else{
                $.messager.alert("crm","暂无数据","info");
            }
        }
    })
}