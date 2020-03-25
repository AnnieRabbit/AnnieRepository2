$("#submit_first").click(function(){
	var action=$(this).attr("submit_url");
	$("#form").prop("action",action).submit();
});

$("#submit_pre").click(function(){
	var action=$(this).attr("submit_url");
	$("#form").prop("action",action).submit();
});

$("#submit_next").click(function(){
	var action=$(this).attr("submit_url");
	$("#form").prop("action",action).submit();
});

$("#submit_last").click(function(){
	var action=$(this).attr("submit_url");
	$("#form").prop("action",action).submit();
});


function split_submit(element){
	var action=$(element).attr("submit_url");
	$("form").prop("action",action).submit();	
}

function get(element){

	var action=$(element).attr("hr");
	window.location=action;
}

function resetpass(element){
	alert("重置密码成功!")
	var action=$(element).attr("hr");
	window.location=action;
}





