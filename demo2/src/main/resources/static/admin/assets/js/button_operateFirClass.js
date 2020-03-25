/*一级保存新增按钮*/
$("#save_append").click(function(){
	
    	var submit_url=$(this).attr("submit_url");	
    	var short_url=$(this).attr("short_url");
		var classname=$("#classname").val();		
		if(classname!=''){
			$.post(submit_url,{'classname': classname },function(data){		
				save(data,short_url);
						
		     },"json");	
				$("#append_table").prop("style","align:left;display:none"); 
		 		$("#search_table").prop("style","align:left;display:block"); 
		 		$("#save_append").prop("style","align:left;display:inline");
		 		$("#save_modify").prop("style","align:left;display:none"); 
		 		$("#classname").val('');
		 		$("#seq").val('');
		 		$("#pid").val('');	
		}else
			alert("一级分类名不能为空！")
		    return false;
 }) 
    

    
/*一级编辑按钮取值 */   
function modify(element){
	var submit_url=$(element).attr("submit_url");
	$.get(submit_url,function(data){
		
		$("#id").val(data.id);
		$("#classname").val(data.classname);
		$("#seq").val(data.seq).prop("style","height: 35px; vertical-align: middle; display:inline;")
	},"json");
	
	 
	    $("#append_table").prop("style","align:left;display:block"); 
		$("#search_table").prop("style","align:left;display:none"); 
		$("#save_append").prop("style","align:left;display:none"); 
		$("#save_modify").prop("style","align:left;display:inline"); 
		
	}
	
/* 一级保存编辑按钮*/
 $("#save_modify").click(function(){ 
	    var submit_url=$(this).attr("submit_url");
 	    var short_url=$(this).attr("short_url");
		var classname=$("#classname").val();
		var seq=$("#seq").val();
		var id=$("#id").val();
		$.post(submit_url,{'id':id,'classname': classname,'seq':seq,'_method':'put' },function(data){		
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
								'<td>'+item.first_addtime+'</td>'+	
								'<td>'+item.first_seq+'</td>'+	
								'<td>'+(item.first_display==1?'显示':'不显示')+'</td>'+	
								'<td>'+		
								'<button type="button"  class="btn btn-success" onclick="modify(this)" submit_url="'+short_url+'/first/'+item.first_id+'" short_url="'+short_url+'"> 编辑 </button>'+
								'&nbsp;'+
								'<button type="button"  class="btn btn-success" onclick="r(this)"      submit_url="'+short_url+'/first/'+item.first_id+'" short_url="'+short_url+'"> 删除 </button>'+
								'</td>'+		
								'</tr>;';		
											
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
							'<button type="button" class="btn btn-outline-danger btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'es/first/'+pager.first+'") >First </button>'+
							'<button type="button" class="btn btn-outline-success btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'es/first/'+pager.pre+'") >Previous </button>'+
							'<button type="button" class="btn btn-outline-info btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'es/first/'+pager.next+'")  >Next</button>'+
							'<button type="button" class="btn btn-outline-warning btn-round waves-effect waves-light m-1" onclick="split_submit(this)" submit_url="'+short_url+'es/first/'+pager.last+'") >Last page</button>'+
							'</th>'+
						'</tr>;';  
											
					$("#tbody").empty().html(tbody_content); 
				    $("#tfoot").empty().html(tfoot_content); 
				            
				
				}else{	
					
					alert("失败！")
				}	
						 
 }
 


     
