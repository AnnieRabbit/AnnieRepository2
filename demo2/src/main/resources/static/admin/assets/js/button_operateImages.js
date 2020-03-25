

/*index*/
function re(element){
	var submit_url=$(element).attr("submit_url");
	$.post(submit_url,{'_method':'delete'},function(data){		
		
		var result=data.result;
		if(result==true	){
			alert("删除成功")
			window.location.reload();
		}else{
			alert("删除失败")	
		}
	},"json");	
}

function split_submit(element){
	var goodsid=$("#goodsid").val();
	
	var action=$(element).attr("submit_url")+goodsid;
	$("#form").prop("action",action).submit();	
}
