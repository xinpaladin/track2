$().ready(function() {

	/*
	 * $('#itemTable').dataTable({ "autoWidth" : true, "processing" : true,
	 * "stateSave" : true, "pagingType" : "full_numbers", "serverSide" : true,
	 * "ajax" : "item/getItemPage.html", });
	 */

	$("#send").click(function() {
		var path = $("#path").val();

		$.ajax({
			type : 'GET',
			data : {
				path : path
			},
			//url : "item/insertData.html",
			url : "item/loadData",
			success : function(result) {
				if (result.succes) {
					alert("success");
				} else {
					alert(result.message);
				}
			}
		});
	});

})
function initTable() {
	
	$.ajax({
		type : 'get',
		data : data,
		url:"item/getItemPage.html",
		success : function(data){
			
		}
	});
	
	$('#itemTable').dataTable( {
	      "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",// 定义DataTable布局的一个强大属性
	      "sPaginationType": "bootstrap",// 分页样式使用bootstrap
	      "oLanguage": {// 语言设置
	              "sLengthMenu": "每页显示  _MENU_ 条记录",  
	              "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
	              "oPaginate": {
	                  "sFirst": "首页",
	                  "sPrevious": "前一页",
	                  "sNext": "后一页",
	                  "sLast": "尾页"
	                  },
	              "sZeroRecords": "抱歉， 没有找到",
	              "sInfoEmpty": "没有数据"
	            },
	     "bProcessing": true, // 当datatable获取数据时候是否显示正在处理提示信息。
	     "bServerSide": true, // 客户端处理分页
	     "sAjaxSource": "/rule/list", // ajax请求地址
	     'bStateSave': true, // 开关，是否打开客户端状态记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或重新打开浏览器，之前的状态都是保存下来的
	     "aoColumnDefs": [{ // 给每个单独的列设置不同的填充，或者使用aoColumns也行
	                      "aTargets": [3],
	                      "mData": null,
	                      "bSortable": false,
	                      "bSearchable": false,
	                      "mRender": function (data, type, full) {
	                          if(full[3] == 1){
	                             return "使用中"  
	                          }else if(full[3] == 0){
	                             return "禁用" 
	                          }
	                      }
	                  },{
	                      "aTargets": [4],
	                      "mData": null,
	                      "bSortable": false,
	                      "bSearchable": false,
	                      "mRender": function (data, type, full) {
	                          return '<a data-toggle="modal" data-target="#myModal"  data-title="' + full[0] + '"  class="btn btn-success" href="#"><i class="icon-edit icon-white"></i>修改</a>' +'&nbsp;&nbsp;'+'<a   data-title="' + full[0] + '"  class="btn btn-primary" href="/config/edit?aid=' + full[0] + '"><i class="icon-wrench icon-white" ></i>配置</a>' +'&nbsp;&nbsp;'+'<a   alt="' + full[2] + '"  class="btn btn-info" href="#"  data-toggle="modal" data-target="#daima"><i class="icon-tasks icon-white"></i>代码</a>' +'&nbsp;&nbsp;'+'<a   data-title="' + full[0] + '"  class="btn btn-warning" href="/service/show?aid=' + full[0] + '"><i class="icon-user icon-white"></i>客服</a>';
	                      }
	                  }]
	    
	    }); 	
	
}