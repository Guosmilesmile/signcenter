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
<title>班级学生名单管理</title>

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
	//初始化数据函数
	function getData(queryParams){
		$('#grid').datagrid({
			url: '<%=basePath%>getClassStudentDataServlet',
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
	        pagination: true,//是否这是分页
			rownumbers:true,
			frozenColumns:[[
				{field: 'ck', checkbox: true},          
			]],
			columns: [[
				{field:'id',title:'ID',sortable:true,width:60,sortable:true,hidden:true},
				{field:'userid',title:'用户名',sortable:true,width:200,sortable:true,
					editor: { type: 'validatebox' }
				},
				{field:'nickname',title:'名称',sortable:true,width:150,sortable:true,
					editor: { type: 'validatebox' }
				},
			]],
			toolbar:[
				{//返回
					   text:"返回",
					   iconCls: "icon-back",
					   handler: returnData,
				},'-',
				{//添加数据
					   text:"添加",
					   iconCls: "icon-add",
					   handler: addData,
				},'-',
				{//删除数据
					   text:"删除",
					   iconCls: "icon-remove",
					   handler: removeData,
				},'-',
				{//导入数据
					   text:"导入",
					   iconCls: "icon-add",
					   handler: importData,
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
		//分页设置
		var p = $('#grid').datagrid('getPager');
		$(p).pagination({
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	        BeforeRefresh:function(){
				$(this).datagrid('reload'); 
				//获取数据库全部数据
			},
		});
		$('#searchdialog').dialog('close');
		$('#adddialog').dialog('close');
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
    //-----------------------编辑------------------------------------------------
    function editData(){//编辑
    	var row = $('#grid').datagrid('getSelected');
		if(row){
			if(doedit!=null){
				$('#grid').datagrid('endEdit',doedit);
				var rowIndex = $('#grid').datagrid('getRowIndex', row);
				$('#grid').datagrid('beginEdit',rowIndex);
				doedit = rowIndex;
			}
			if(doedit == undefined){
				var rowIndex = $('#grid').datagrid('getRowIndex', row);
				$('#grid').datagrid('beginEdit',rowIndex);
				doedit = rowIndex;
			}
		}else{
			$.messager.alert('警告','请选择需要编辑的数据','error');
		};
    }
    //---------------------------------添加----------------------------------------
    function addData(){
    	$('#adddialog').dialog('open');
    }
    //----------------------------导入------------------------------------------
    function importData(){
    	$('#formclassid').val(classid);
    	$('#formcourseid').val(courseid);
    	$('#searchdialog').dialog('open');
    }
    //-------------------------------删除-------------------------------------------
    function removeData(){
    	var rows = $('#grid').datagrid('getSelections');
		if(rows.length <= 0){
			$.messager.alert('警告','您没有选择','error');
		}
		else if(rows.length >= 1){
			$.messager.confirm("操作警告", "确定删除后将不可恢复！！", function(data){
				if(data){
					//原来代码开始的位置
					var ids = [];
					for(var i = 0; i < rows.length; ++i){
							ids[i] = rows[i].id;
					}	
					$.ajax({
			    		type:'post',
			    		url:"<%=basePath%>removeClassStudentServlet",
			    		data:{ids: ids.toString()},
			    		success:function(data){
			    			if(1==data){//成功
			    				$.messager.alert('提示','删除成功','info');
			    			}else{
			    				$.messager.alert('提示','删除失败','error');
			    			}
			    			$('#grid').datagrid('reload');
			    		},error:function(){
			    			console.log("fail");
			    		}
			    	});	
					
				}
			});
		}
    }
	//------------------返回---------------------
    function  returnData(){
    	var url = "<%=basePath%>admin/managecourseclass.jsp?courseid="+courseid;
		location.href=url;
    }
	//----------------------获取链接数据---------------------
	function getUrlParam(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	    if (r != null) return unescape(r[2]); return null; //返回参数值
	}; 
	//------------------------获取学生名单-------------------
	function getStudentlist(){//获取所有的metaCode
    	$.ajax({
    		type:'post',
    		async:false,
    		url:"<%=basePath%>GetStudentListServlet",
    		success:function(data){
    			var list = eval("("+data+")");
    			if(list.length>0){
    				var str1 = "";
    				for(var i =0;i<list.length;i++){
    					str1+="<option value='"+list[i].id+"'>"+list[i].userId+"</option>";
    				}
    				$('#studentlist').html(str1);
    			}
    			$('#studentlist').show();
    			$("#studentlist").multiselect({
    				noneSelectedText: "==请选择==",
    				multiple: false,
    		        checkAllText: "全选",
    		        uncheckAllText: '全不选',
    		        selectedText:'#项被选中',
    			}).multiselectfilter(); 
    		},error:function(){
    			console.log("fail");
    		}
    	})
    }
	//----------------------------主体----------------------------------
	var courseid = -1;
	var classid = -1;
	var mess = -1;
    $(document).ready(function(){
    	$('#SelectBtn').click(function(){
    	    $('#importform').submit();
    	});
    	getStudentlist();
    	mess = getUrlParam('mess');
    	if(mess==1){
    		$.messager.alert('提示','导入成功','info');
    	}else if(mess==2){
    		$.messager.alert('提示','导入失败','error');
    	}
    	courseid = getUrlParam('courseid');
    	classid = getUrlParam('classid');
    	if(null==courseid){
    		var url = "<%=basePath%>admin/managecourse.jsp";
    		location.href=url;
    	}else{
    		if(null==classid){
    			var url = "<%=basePath%>admin/managecourseclass.jsp?courseid="+courseid;
    			location.href=url;
        	}else{
        		var queryParams;
        		queryParams = {'classid':classid};
        		getData(queryParams);
        	}
    	}
    	$('#addClick').click(function(){
    		var studentid = $("#studentlist").val();
    		if(null==studentid){
    			$.messager.alert('提示','选择添加的学生','error');
    		}else{
    			$.ajax({
    	    		type:'post',
    	    		url:"<%=basePath%>AddStudentToClassServlet",
    	    		data:{'userid':studentid[0],'classid':classid},
    	    		success:function(data){
    	    			if(1==data){//成功
		    				$.messager.alert('提示','添加成功','info');
		    			}else{
		    				$.messager.alert('提示','添加失败','error');
		    			}
		    			$('#grid').datagrid('reload');
		    			$('#adddialog').dialog('close');
    	    		},error:function(){
    	    			console.log("fail");
    	    		}
    	    	});
    		}
    		
    	});
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
	<!-- <div id ="topdiv">
			job名称: 
			<select title="Basic example" multiple="multiple" name="example-basic" size="5" id="metacode" style="width:300px">
			</select>
	  		<a href="javascript:void(0)" id="SelectBtn" class="easyui-linkbutton" style="width:50px;float:right;height:25px;margin-right: 30px">查询</a>
	</div> -->
	<table id="grid"></table>
	
	<div id="searchdialog" class="easyui-dialog" title="搜索" style="width:400px;height:200px;"
    data-options="iconCls:'icon-save',resizable:true,modal:true">
    	<div id="totalplane" style="margin-top: 55px;padding-left: 60px;">
    		<form id="importform"  action="<%=basePath%>importClassStudentServlet" enctype="multipart/form-data" method="post">
	  			<input  name="classid" id ="formclassid"  style="display: none;">
	  			<input  name="courseid" id ="formcourseid"  style="display: none;">
	  			<input class="easyui-filebox" name="file1" data-options="prompt:'Choose a file...'" style="width:90%">
	  			<a href="javascript:void(0)" id="SelectBtn" class="easyui-linkbutton" iconCls="icon-ok" style="width:150px;height:32px;margin-top: 10px;margin-left: 65px">确定</a>
  			</form>
  		</div>
	</div>
	
	<div id="adddialog" class="easyui-dialog" title="添加" style="width:400px;height:200px;"
    data-options="iconCls:'icon-save',resizable:true,modal:true">
    	<div id="totalplane" style="margin-top: 55px;padding-left: 60px;">
    		<select  multiple="multiple" name="example-basic" size="5" id="studentlist" style="width:250px;display: none;">
			</select>
    		<a href="javascript:void(0)" id="addClick" class="easyui-linkbutton" iconCls="icon-ok" style="width:150px;height:32px;margin-top: 10px;margin-left: 65px">确定</a>
  		</div>
	</div>
	
</body>
</html>