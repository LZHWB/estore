/**
 * 
 */
function selsct(lcode,obj){
	$.ajax({
		type:"post",//请求方式get/post
		url:"LinkageServlet",//请求对应的地址
		data:{"levelcode":lcode},//往服务器传递的参数，
		success:function(data){
			var jdata = data.trim(); // 去前后空格
			if(jdata=="fail"){
				alert("查询失败!");
			}else{
				var json = eval(jdata);//将json字符串对象转化成json
				var dd = "";
				for(var i=0; i<json.length; i++) {
					dd+="<dd onclick='showck("+json[i].code+")'>"+json[i].name+"</dd>";
				}
				document.getElementById(obj).innerHTML=dd;
			}
		}
	});
}