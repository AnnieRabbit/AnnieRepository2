

/*规格detail*/
$("#add_specv").click(function(){
	var tag=$(this).attr("tag");
	console.log(tag)
	var content="";
	if(tag=='modify'){
		
	     content=  
	  '<div class="form-group row">'+
          '<div class="col-sm-5">'+
              '<input type="hidden" name="ids"> '+
              '<input type="text"   class="form-control" id="input-4" name="names" placeholder="请输入规格详情">'+
           '</div>'+
           '<div class="col-sm-3">'+
               '<input type="text"    class="form-control" id="input-1" name="seqs" placeholder="请输入显示顺序">'+
           '</div>'+
           '<div class="col-sm-2">'+
              '<button type="button"  id="delete" class="btn btn-success" >删除</button>'+
           '</div>'+
      '</div>';

	}else{	
		content=
			 '<div class="form-group row">'+
			 '<label for="input-4" class="col-sm-2 col-form-label">规格详情</label>'+     
		         '<div class="col-sm-5">'+
		              '<input type="text" class="form-control" id="input-4" name="names" >'+
		         '</div>'+
		     '</div>';
	} 
$("#specvs").append(content);             
	
})

$(function(){
	$("#delete").live("click",function(){
		$(this).parent().parent().remove();		
	})
})
	
function deleteSpecv(element){
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
		
$("#modify").on('click',function(){
	var id=$("#id").val();
	var specname=$("#specname").val();
	var seq=$("#seq").val();

	var ids=new Array();
	var names=new Array();
	var seqs=new Array();

	$("input[name='ids']").each(function(){
	if(	$(this).val()==''){
		$(this).val(0)
	}
		ids.push($(this).val());
	})

	$("input[name='names']").each(function(){
		names.push($(this).val());
	})

	$("input[name='seqs']").each(function(){
		seqs.push($(this).val());
	})

	var submit_url=$("#modify").attr("submit_url");
		
$.post(submit_url,{'_method':'put','specname': specname,'id': id,'seq': seq,'ids': ids.toString(),'names': names.toString(),'seqs': seqs.toString() },function(data){		
		
			var result=data.result;
			if(result){
				alert("修改成功")
				window.location.reload();
			}else{
				alert("修改失败")	
			}
		},"json");
})		


/*index*/
function re(element){
	var submit_url=$(element).attr("submit_url");
	$.post(submit_url,{'_method':'delete'},function(data){		
		
		var result=data.result;
		if(result==true	){
			alert("删除成功")
			window.location.reload();
		}else{
			alert("有商品使用该规格,不能删除!")	
		}
	},"json");	
}

