<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=basePath%>js/echarts.js"></script>
<script src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath%>js/chartinit.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/historymongo.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyUI/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>/js/easyUI/jquery.easyui.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>
<body>
	<div class='charts'  id="chart" style="width: 1200px; height: 500px;margin-left:5% "></div>
	<script type="text/javascript">
		//获取链接数据
		function getUrlParam(name) {
		    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		    if (r != null) return unescape(r[2]); return null; //返回参数值
		}; 
		var countid = getUrlParam("countid");
		var classid = getUrlParam("classid");
		
		$.ajax({
			type:'post',
    		url:"<%=basePath%>GetSignTotalServlet",
    		async:false,
    		data:{'countid':countid,'classid':classid},
    		success:function(data){
    			var item = eval("("+data+")");
    			console.log(item);
    			var chart =  echarts.init(document.getElementById('chart'));
    			option = {
    				    title : {
    				        text: '签到情况',
    				        subtext: '可视化',
    				        x:'center'
    				    },
    				    tooltip : {
    				        trigger: 'item',
    				        formatter: "{a} <br/>{b} : {c} ({d}%)"
    				    },
    				    legend: {
    				        orient: 'vertical',
    				        left: 'left',
    				        data: ['正常签到','旷课','迟到','跨班上课']
    				    },
    				    series : [
    				        {
    				            name: '访问来源',
    				            type: 'pie',
    				            radius : '55%',
    				            center: ['50%', '60%'],
    				            data:[
    				                {value:item.arraynumber, name:'正常签到'},
    				                {value:item.passnumber, name:'旷课'},
    				                {value:item.latenumber, name:'迟到'},
    				                {value:item.acrossnumber, name:'跨班上课'},
    				            ],
    				            itemStyle: {
    				                emphasis: {
    				                    shadowBlur: 10,
    				                    shadowOffsetX: 0,
    				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
    				                }
    				            }
    				        }
    				    ]
    				};
    				chart.setOption(option);
    		},error:function(){
    			alert('可视化失败');
    		}
		});
		
			
	</script>
</body>
</html>