<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="http://static.hdslb.com/images/favicon.ico">
<title>课程时间管理</title>

<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/style.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/prettify.css" />
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>assets/prettify.js"></script>
<script type="text/javascript" src="<%=basePath%>js/multiselect/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%=basePath%>js/multiselect/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%=basePath%>js/multiselect/jquery.multiselect.filter.js"></script>


<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyUI/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>/js/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/easyUI/locale/easyui-lang-zh_CN.js"></script>
<style>
	.datagrid-btable tr{height: 30px;}
</style>
<script>

	var chine = ["一","二","三","四","五","六","七",]
	//获取链接数据
	function getUrlParam(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	    if (r != null) return unescape(r[2]); return null; //返回参数值
	}; 
	//初始化数据函数
	function getData(queryParams){
		$('#grid').datagrid({
			url: '<%=basePath%>getClassTimeCountDataServlet',
			queryParams: queryParams,
			remoteSort:false,
			nowrap: false, //换行属性
			striped: true, //奇数偶数行颜色区分
			height:600,
			singleSelect:true,
			fitColumns:true,
			collapsible : true, //可折叠
			pageSize: 50,//每页显示的记录条数，默认为10  
	        pageList: [5,10,15,20,25,50,100],//可以设置每页记录条数的列表  
	        pagination: false,//是否这是分页
			rownumbers:true,
			frozenColumns:[[
				{field: 'ck', checkbox: true},          
			]],
			columns: [[
				{field:'id',title:'ID',sortable:true,width:60,sortable:true,hidden:true},
				{field:'index',title:'课时',sortable:true,width:120,sortable:true,
					editor: { type: 'validatebox' }
				},
			]],
			toolbar:[
				{//返回
					   text:"返回",
					   iconCls: "icon-back",
					   handler: returnData,
				},'-',
				{//修改数据
					   text:"选择",
					   iconCls: "icon-edit",
					   handler: chooseCount,
				},'-',
			],
			onAfterEdit: function(rowIndex,rowData,changes){
				doedit = undefined;
			},
			onDblClickRow:function(rowIndex, rowData){    
				//$('#grid').datagrid('endEdit',doedit);
				if(doedit==undefined)   //如果存在在编辑的行，就不可以再打开第二个行进行编辑
				{					
					$('#grid').datagrid('selectRow',rowIndex);
		        	$('#grid').datagrid('beginEdit',rowIndex);
		        	doedit=rowIndex;
				}
			},
			onLoadSuccess:function(data){//数据刷新的时候，编辑的坐标设为空
				doedit = undefined;
			},
			
		});
	};
	
	function myformatter(value) {//时间转换函数
		if(value != null && value != ""){
			var date = new Date(value*1000);
			var hour = date.getHours();
			var minute = date.getMinutes();
			var second = date.getSeconds();
			if(hour<10){
				hour = "0"+hour;
			}
			if(minute<10){
				minute = "0"+minute;
			}
			if(second<10){
				second = "0"+second;
			}
			return date.getFullYear()+"-"+(date.getMonth() + 1) +"-"+ date.getDate()+" "+ hour + ":" + minute +":"+ second;
		}
	}
	function formatterDate(date) {//时间转换
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return month+ '/' + day + '/' + date.getFullYear() ;
	};
	function GetDateStr(AddDayCount) {//根据时间间隔计算 相距今天的日期
	    var dd = new Date();
	    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	    var y = dd.getFullYear();
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();
	    return y+"-"+m+"-"+d;
	}
	function myparser(s) {
		var ss = (s.split('/'));
		if(ss.length > 1)
			return ss[2] + "-" + ss[0] + "-" + ss[1];
		else return s;
	}
	//--------------------------------------主体部分！！！-----------------------------
    var doedit = undefined;//用来记录当前编辑的行，如果没有编辑的行则置为undefined
    $(function(){
		//获取数据的查询参数----过滤数据
		var queryParams;
		queryParams = {};
		getData(queryParams);
	});
   
	//------------------返回---------------------
    function  returnData(){
    	var url = "<%=basePath%>admin/managesigntime.jsp?classid="+classid+"&courseid="+courseid;
		location.href=url;
    }
    //------------------选择课时---------------------
    function chooseCount(){
    	var row = $('#grid').datagrid('getSelected');
		if(row){
			var countid = row.id;
			var url = "<%=basePath%>admin/managesignfinal.jsp?classid="+classid+"&courseid="+courseid+"&ctid="+ctid+"&countid="+countid;
			location.href=url;
		}else{
			$.messager.alert('警告','请选择需要编辑的数据','error');
		};
    }
    
  //----------------------获取链接数据---------------------
	function getUrlParam(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	    if (r != null) return unescape(r[2]); return null; //返回参数值
	}; 
	var courseid = -1;
	var classid = -1;
	var ctid = -1;
    $(document).ready(function(){
    	courseid = getUrlParam('courseid');
    	classid = getUrlParam('classid');
    	ctid =  getUrlParam('ctid');
    	if(null == ctid){
    		var url = "<%=basePath%>admin/managesigntime.jsp?classid="+classid+"&courseid="+courseid;
			location.href=url;
    	}
    	if(null==courseid){
    		var url = "<%=basePath%>admin/managesign.jsp.jsp";
    		location.href=url;
    	}else{
    		if(null==classid){
    			var url = "<%=basePath%>admin/managesignclass.jsp?courseid="+courseid;
    			location.href=url;
        	}else{
        		var queryParams;
        		queryParams = {'ctid':ctid};
        		getData(queryParams);
        	}
    	}
    });
</script>
<style type="text/css">
	#topdiv{
		background-color: #f3f2f2;
		padding:10px;
		/* Gecko browsers */
		-moz-border-radius-topleft: 10px;
		-moz-border-radius-topright: 10;
		-moz-border-radius-bottomleft: 0;
		-moz-border-radius-bottomright: 0px;
		 
		/* Webkit browsers */
		-webkit-border-top-left-radius: 10px;
		-webkit-border-top-right-radius: 10;
		-webkit-border-bottom-left-radius: 0;
		-webkit-border-bottom-right-radius: 0px;
		 
		/* W3C syntax */
		border-top-left-radius: 10px;
		border-top-right-radius: 10px;
		border-bottom-right-radius: 0;
		border-bottom-left-radius:  0px;
    }
</style>
</head>
<body  class = "h2">
	<table id="grid"></table>
</body>
</html>