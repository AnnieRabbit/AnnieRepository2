	function orderOperate(element){
		var submit_url=$(element).attr("submit_url");
		var short_url=$(element).attr("short_url");
		var page=$("#page").val(); 
 		 $.post(submit_url,{'page':page},function(data){		
			
 			save(data,short_url);
								 
		},"json");  
	}
	

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
								'<td>'+item.orderid+'</td>'+		
								'<td>'+item.addname+'</td>'+
								'<td>'+item.totalprice+'</td>'+	
								'<td>'+item.userid+'</td>'+	
								'<td>'+item.addname+'</td>'+	
								'<td>'+item.tel+'</td>'+	
								'<td>'+item.address+'</td>'+
								'<td>'+item.cityname+'</td>'+
								'<td>'+item.status+'</td>'+
								'<td>'+		
								'<button type="button" onclick="get(this)"  hr="'+short_url+'/'+item.orderid+'" short_url="'+short_url+'" class="btn btn-success">查看详情</button>'+
								'&nbsp;'+
								(item.state==2?'<button type="button"  onclick="orderOperate(this)" submit_url="'+short_url+'/send/'+item.orderid+'" short_url="'+short_url+'"   class="btn btn-success">发货</button>':'')+
								'&nbsp;'+
								(item.state==5?'<button type="button"  onclick="orderOperate(this)" submit_url="'+short_url+'/returnMoney/'+item.orderid+'" short_url="'+short_url+'"  class="btn  btn-primary">同意退款</button>':'')+
								'&nbsp;'+
								'</td>'+		
								'</tr>;';		
								});	
					var tfoot_content="";
				    tfoot_content+=
								'<tr>'+
							'<th scope="row" colspan="11">'+
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
	
	
	
