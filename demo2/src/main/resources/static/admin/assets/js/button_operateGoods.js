/*删除方法*/
function r(element){
	if(window.confirm("删除的信息将不可恢复，确定删除吗？")){
		var page=$("#page").val();
		var submit_url=$(element).attr("submit_url");
		var short_url=$(element).attr("short_url");
		
		$.post(submit_url,{'_method':'delete','page':page},function(data){		
			save(data,short_url);	
								 
		},"json");	
	}	
}	

/*删除节点*/
$(function(){
	$("#delete").live("click",function(){
		$(this).parent().remove();
		
	})
})

/*上下架*/
function changeState(element){
	var hr=$(element).attr("hr");
	var short_url=$(element).attr("short_url");
	var page=$("#page").val();
	$.post(hr,{'page':page},function(data){		
		save(data,short_url);	
							 
	},"json");	
}


function get(element){
	var action=$(element).attr("hr");	
	location.href=action;
}


/*判断商品状态是否为上架*/
function ajaxModify(element){
	var submit_url=$(element).attr("submit_url");
	var action=$(element).attr("hr");	

	$.post(submit_url ,function(data){	
		if(data) {
			location.href=action;
		} else {
			alert("上架商品不能被编辑!");
			
		}
	})
}


/*save之后ajax将一页5条记录传回页面*/
function save(data,short_url){
	 
	var result=data.result;
 	var pager=data.pager;
 	var msg=data.msg;
	   if(result== true){	
		   if(msg!=''&&msg!=null){
			   alert(msg)
		   }
			
				var	tbody_content="";
					$(pager.datas).each(function(index,item){								
						tbody_content+=	
						'<tr>'+		
								'<td scope="row">'+(index+1)+'</td>'+	
								'<td>'+item.goodsname+'</td>'+		
								'<td>'+item.first_classname+'</td>'+
								'<td>'+item.second_classname+'</td>'+	
								'<td>'+item.thrid_classname+'</td>'+	
								'<td>'+item.brandname+'</td>'+	
								'<td>'+item.goodscode+'</td>'+	
								'<td>'+(item.state==1?'上架':'下架')+'</td>'+	
								'<td>'+		
								'<button type="button" onclick="ajaxModify(this)"   hr="'+short_url+'/'+item.id+'" submit_url="'+short_url+'/ajaxModify/'+item.id+'" short_url="'+short_url+'" class="btn btn-success">编辑基本信息</button>'+
								'&nbsp;'+
					           	'<button type="button" onclick="ajaxModify(this)"   hr="'+short_url+'/good_Spec/'+item.id+'" submit_url="'+short_url+'/ajaxModify/'+item.id+'" short_url="'+short_url+'" class="btn btn-success">编辑规格</button>'+
					           	'&nbsp;'+
					            '<button type="button" onclick="ajaxModify(this)"   hr="'+short_url+'/image/'+item.id+'" submit_url="'+short_url+'/ajaxModify/'+item.id+'" short_url="'+short_url+'" class="btn btn-success">编辑附图</button>'+
					            '&nbsp;'+
					            (item.state==1?'<button type="button"  onclick="changeState(this)"  hr="'+short_url+'/state/'+item.id+'" short_url="'+short_url+'" class="btn btn-success waves-effect waves-light m-1">下架</button>':'<button type="button"  onclick="changeState(this)"  hr="'+short_url+'/state/'+item.id+'" short_url="'+short_url+'" class="btn btn-success waves-effect waves-light m-1">上架</button>')+
								'&nbsp;'+
								'<button type="button"  onclick="r(this)" submit_url="'+short_url+'/'+item.id+'" short_url="'+short_url+'"  class="btn  btn-primary">删除</button>'+
								'&nbsp;'+
								'</td>'+		
								'</tr>;';		
								});	
				
				var tfoot_content="";
				    tfoot_content+=
								'<tr>'+
							'<th scope="row" colspan="13">'+
							'<input type="hidden" id="page" value="'+pager.page+'">'+
							'<span>'+pager.size+'Records</span>'+
							'&nbsp;&nbsp;&nbsp;&nbsp;'+
							'<span class="current">The'+pager.page+'Page</span>'+
							'&nbsp;&nbsp;&nbsp;&nbsp;'+
							'<span class="current">'+pager.pages+'Pages</span>'+									
							'&nbsp;&nbsp;&nbsp;&nbsp;'+
							'<button type="button" class="btn btn-outline-danger btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'s/'+pager.first+'")  >First </button>'+
							'<button type="button" class="btn btn-outline-success btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'s/'+pager.pre+'")  >Previous </button>'+
							'<button type="button" class="btn btn-outline-info btn-round waves-effect waves-light m-1"     onclick="split_submit(this)" submit_url="'+short_url+'s/'+pager.next+'")  >Next</button>'+
							'<button type="button" class="btn btn-outline-warning btn-round waves-effect waves-light m-1"  onclick="split_submit(this)" submit_url="'+short_url+'s/'+pager.last+'") >Last page</button>'+
							'</th>'+
						'</tr>;';  
											
					$("#tbody").empty().html(tbody_content); 
				    $("#tfoot").empty().html(tfoot_content); 
				}else{	
					if(msg!=''&&msg!=null){
						alert(msg)
					}
				
				}	 
}


/*修改商品规格*/
$("#saveSpecs").on('click',function(){

	var goodsid=$("#goodsid").val();
	var goodscode=$("#goodscode").val();

	var specids=new Array();
	var specvids=new Array();


	$("select[name='specids']").each(function(){
      if($(this).val()!=0){
		 specids.push($(this).val());
	  }
	})

	$("select[name='specvids']").each(function(){
		if($(this).val()!=0){
		    specvids.push($(this).val());
		}
	})


	var submit_url=$("#saveSpecs").attr("submit_url");
		
$.post(submit_url,{'_method':'put','goodsid': goodsid,'goodscode': goodscode,'specids': specids.toString(),'specvids': specvids.toString()},function(data){		
		
			var result=data.result;
			if(result){
				alert("修改成功")
				window.location.reload();
			}else{
				alert("修改失败")	
			}
		},"json");
})		


/*修改页面二级联动,ajax中无法使用$(this),故声明变量current*/
	$(".specvs").change(function(){
		
		 var submit_url=$(this).attr("submit_url")+this.value;	
		 var content='';
		 var current=$(this);
	
		 $.post(submit_url,function(data){
			  content="<option value='0'>-请选择规格详情-</option>";
			
				$(data).each(function(index,item){	
					content+="<option value='"+item.id+"'>"
					+item.specvname+"</option>";
				})
			  
				current.next().empty().html(content) 
		});
	
});

/*复制节点*/
$("#add_spec").click(function(){
	
	 $("#addTable").clone(true).appendTo("#originalTable");

});
