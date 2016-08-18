/**
 * Created by cartoonface on 2016/6/19.
 */
$().ready(function () {

    $("#search").click(function(){

    });
    initTable();
});
function initTable(){
    $('#table').bootstrapTable({
        url: "/track/item/getItemPage",
        dataType: "json",
        striped: true,	 //使表格带有条纹
        pagination: true, //分页
        singleSelect: false,
        search: false, //显示搜索框
        sidePagination: "server", //服务端处理分页
        pageList: [10, 20, 50, 100, 200, 500],
        columns: [
            {
                title: '驾驶员',
                field: 'driverName',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '驾驶行为',
                field: 'type',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    switch (value) {
                        case 0:
                            return "直线行驶";
                        case 1:
                            return "左转弯"
                        case 2:
                            return "右转弯";
                        case 3:
                            return "左换道"
                        case 4:
                            return "右换道";
                        case 5:
                            return "U-Turn"
                    }

                }
            },
            {
                title: '舒适性',
                field: 'comfortable',
                align: 'center'
            },
            {
                title: '速度性',
                field: 'speed',
                align: 'center'
            },
            {
                title: '平稳性',
                field: 'locus',
                align: 'center',
            },
            {
                title: '总体评价',
                field: 'overallEval',
                align: 'center',
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {
                    var e = '<span><a href="#" mce_href="#" onclick="edit(\'' + row.id + '\')">编辑</a> ';
                    var d = '<a href="#" mce_href="#" onclick="del(\'' + row.id + '\')">删除</a> ';
                    return e + d;
                }
            }
        ],
        formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        },
        formatNoMatches: function () {  //没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadError: function (data) {
            $('#table').bootstrapTable('removeAll');
        },
    });
}