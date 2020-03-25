/*二级保存新增按钮*/
 $("#save_append").click(function(){
	    var submit_url=$(this).attr("submit_url");	
 	    var short_url=$(this).attr("short_url");			
 		var classname=$("#classname").val();		
 		var pid=$("#pid").val();	
 		if(classname!=''&&pid!=0){
 			$.post(submit_url,{'classname': classname,'pid':pid },function(data){		
 	 			save(data,short_url);
 	 			
 	      },"json");		
 	 			
 	 		$("#append_table").prop("style","align:left;display:none"); 
 	  		$("#search_table").prop("style","align:left;display:block"); 
 	  		$("#save_append").prop("style","align:left;display:inline");
 	  		$("#save_modify").prop("style","align:left;display:none");
 	  		$("#classname").val('');
 	  		$("#seq").val('');
 	 		$("#pid").val('');
 	 		$("#firstClassId").val('');
 		}
 		
  })  
  
  
  /* 二级编辑按钮取值*/
function modify(element){
	    var submit_url=$(element).attr("submit_url");
	 	$.get(submit_url,function(data){
	 		$("#pid").val(data.pid);
	 		$("#id").val(data.id);
	 		$("#classname").val(data.classname);
	 		$("#seq").val(data.seq).prop("style","height: 35px; vertical-align: middle; display:inline;")
	 	},"json");  
	    $("#append_table").prop("style","align:left;display:block"); 
		$("#search_table").prop("style","align:left;display:none"); 
		$("#save_append").prop("style","align:left;display:none"); 
		$("#save_modify").prop("style","align:left;display:inline"); 
		
	}


  	  	
   
/* 二级保存编辑按钮*/
 $("#save_modify").click(function(){
    	var submit_url=$(this).attr("submit_url");	
    	var short_url=$(this).attr("short_url");
		var classname=$("#classname").val();
		var seq=$("#seq").val();
		var id=$("#id").val();
		var pid=$("#pid").val();
				
		$.post(submit_url,{'classname': classname,'seq':seq,'id':id,'pid':pid,'_method':'put' },function(data){		
			save(data,short_url);
			
		},"json");		
			
		$("#append_table").prop("style","align:left;display:none"); 
 		$("#search_table").prop("style","align:left;display:block");
 		$("#save_append").prop("style","align:left;display:none"); 
 		$("#save_modify").prop("style","align:left;display:none"); 
 		$("#classname").val('');
 		$("#seq").val('');
 		$("#pid").val('');
   }) 
   
   
/*二级分类 通过firstId显示二级分类下拉列表*/
$("#firstClassId").change(function(){
	 var submit_url=$(this).attr("submit_url")+this.value;	
	
	 $.post(submit_url,function(data){
   var content="<option value='0'>-二级分类-</option>";
			$(data).each(function(index,item){	
				content+="<option value='"+item.second_id+"'>"
				+item.second_classname+"</option>";
			});
	     $("#secondClassId").empty().html(content);

	});
	
});
   
/*二级分类 通过secondId显示三级分类下拉列表*/
$("#secondClassId").change(function(){
	 var submit_url=$(this).attr("submit_url")+this.value;	
	
	 $.post(submit_url,function(data){
   var content="<option value='0'>-三级分类-</option>";
			$(data).each(function(index,item){	
				content+="<option value='"+item.thrid_id+"'>"
				+item.thrid_classname+"</option>";
			});
	     $("#thridClassId").empty().html(content);
 
    
	});
	
}); 


   
 /*二级分类 新增时的下拉列表*/
 $("#fid").change(function(){
	 var submit_url=$(this).attr("submit_url")+this.value;	
	
	 $.post(submit_url,function(data){
		 console.log(data)
		
    var content="<option value='0'>---请选择二级分类-- </option>";
			$(data).each(function(index,item){	
				content+="<option value='"+item.second_id+"'>"
				+item.second_classname+"</option>";
			});
	     $("#pid").empty().html(content);
  
     
	});
	
}); 
	  

 function save(data,short_url){ 
	 var result=data.result;
 	 var pager=data.pager;
			
	   if(result== true){	
			alert("成功！")
		
				var	tbody_content="";
					$(pager.datas).each(function(index,item){								
						tbody_content+=	
						
						'<tr>'+		
								'<td scope="row">'+(index+1)+'</td>'+
								'<td>'+item.first_classname+'</td>'+
								'<td>'+item.second_classname+'</td>'+	
								'<td>'+item.second_addtime+'</td>'+
								'<td>'+item.second_seq+'</td>'+	
								'<td>'+(item.second_display==1?'显示':'不显示')+'</td>'+
								'<td>'+		
								'<button type="button"  class="btn btn-success"   onclick="modify(this)" submit_url="'+short_url+'/second/'+item.second_id+'" short_url="'+short_url+'"  > 编辑 </button>'+
								'&nbsp;'+
								'<button  type="button"   class="btn btn-success" onclick="r(this)"  submit_url="'+short_url+'/second/'+item.second_id+'" short_url="'+short_url+'"> 删除 </button>'+
								'</td>'+		
						'</tr>;';		
						console.log(item)		
							});		
			
				
				var tfoot_content="";
				    tfoot_content+=
								'<tr>'+
							'<th scope="row" colspan="6">'+
							'<span>'+pager.size+'Records</span>'+
							'&nbsp;&nbsp;&nbsp;&nbsp;'+
							'<span class="current">The'+pager.page+'Page</span>'+
							'&nbsp;&nbsp;&nbsp;&nbsp;'+
							'<span class="current">'+pager.pages+'Pages</span>'+									
							'&nbsp;&nbsp;&nbsp;&nbsp;'+
							'<button type="button" class="btn btn-outline-danger btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'es/second/'+pager.first+'") >First </button>'+
							'<button type="button" class="btn btn-outline-success btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'es/second/'+pager.pre+'") >Previous </button>'+
							'<button type="button" class="btn btn-outline-info btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'es/second/'+pager.next+'")  >Next</button>'+
							'<button type="button" class="btn btn-outline-warning btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'es/second/'+pager.last+'") >Last page</button>'+
							'</th>'+
						'</tr>;';  
											
					$("#tbody").empty().html(tbody_content); 
				    $("#tfoot").empty().html(tfoot_content); 
				            
				
				}else{	
					
					alert("失败！")
				}	
					
	 
 }
 
