<%--
  Created by IntelliJ IDEA.
  User: cartoonface
  Date: 2016/8/4
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="./js.jsp"></jsp:include>
    <jsp:include page="./header.jsp"></jsp:include>
    <script src="/track/resources/js/echarts.js"></script>
    <script>

        $().ready(function () {
            var now = echarts.init(document.getElementById('new'));

            var myChart = echarts.init(document.getElementById('echarts'));
            // 显示标题，图例和空的坐标轴
            myChart.setOption({
                title: {
                    text: '新经纬度'
                },
                tooltip: {},
                legend: {
                    data: ['经纬度']
                },
                xAxis: {
                    name: '经度'
                },
                yAxis: {
                    name: '纬度'
                },
                series: [{

                    type: 'line',
                    data: []
                }]
            });

            $("#write").click(function () {
                $.ajax({
                    type: 'GET',
                    url: "writeXML",
                    success: function (result) {
                        if (result.success) {
                            alert("success" + result.message);
                        } else {
                            alert(result.message);
                        }
                    }
                })
            });

            $("#test").click(function () {
                $.ajax({
                    type: 'GET',
                    url: "test",
                    dataType: 'json',
                    success: function (data) {
                        var dataSet=[data.length];

                        var x = [data.length];
                        var y = [data.length];
                        var z = [1,2,3,45,67];
                       for(var i=0;i<data.length;i++){
                            var longLater = [];
                           x[i]=data[i].longitude;
                           y[i]=data[i].latitude;
                           longLater.push(data[i].longitude);
                           longLater.push(data[i].latitude);
                           longLater.push(4);
                           dataSet.push(longLater);
                       }
                        // 填入数据
                        myChart.setOption({
                            xAxis: {

                            },
                            series: [{
                                // 根据名字对应到相应的系列

                                data: z
                            }]
                        });

                    }
                })
            });
        })

    </script>
    <title>Title</title>
</head>
<body>
<button id="write" class="button">写xml</button>
<button id="test" class="button">test</button>

<div id="echarts" style="width: 600px;height:400px;"></div>
<div id="new" style="width: 600px;height:400px;"></div>
</body>
</html>
