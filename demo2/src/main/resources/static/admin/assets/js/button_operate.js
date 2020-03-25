/*多级取消按钮*/
$("#cancel").click(function(){
	$("#append_table").prop("style","align:left;display:none"); 
	$("#search_table").prop("style","align:left;display:block"); 
	$("#seq").prop("style","align:left;display:none"); 
	$("#classname").val('');
	$("#seq").val('');
	$("#pid").val('');
})


/*多级新增按钮*/
$("#append").click(function(){

		$("#append_table").prop("style","align:left;display:inline");
		$("#search_table").prop("style","align:left;display:none");
		$("#save_modify").prop("style","align:left;display:none"); 
		$("#save_append").prop("style","align:left;display:inline");
		$("#seq").prop("style","align:left;display:none"); 
		
})

/* 多级删除按钮*/
function r(element){
	var submit_url=$(element).attr("submit_url");
	var short_url=$(element).attr("short_url");
	$.post(submit_url,{'_method':'delete' },function(data){		
		save(data,short_url);
	},"json");	
	$("#append_table").prop("style","align:left;display:none"); 
	$("#search_table").prop("style","align:left;display:block");
	
 }

