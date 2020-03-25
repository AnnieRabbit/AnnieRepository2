$("#validate_img").click(function(){
	$(this).prop("src",$(this).prop("src")+"&t=new Date().getTime()")
});