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
<title>job配置</title>

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
			url: '<%=basePath%>GetUserDataServlet',
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
				{field:'userId',title:'用户名',sortable:true,width:200,sortable:true,
					editor: { type: 'validatebox' }
				},
				{field:'passWord',title:'密码',sortable:true,width:200,sortable:true,
					editor: { type: 'validatebox' }
				},
				{field:'role',title:'角色',sortable:true,width:120,sortable:true,
					editor:{
                		type : 'combobox',
                		options : {
                			valueField: "value", textField: "text"  ,
                        	data:[{"value": "0", "text": "管理员"},{"value": "1", "text": "学生"},{"value": "2", "text": "老师"}],
                        	editable:false ,
                		}
                    },
					formatter:function(value,row,index){
						if(1==value){
							return "学生";
						}else if(0==value){
							return "管理员";
						}else if(2==value){
							return "老师";
						}
						
					}
				},
				{field:'nickName',title:'名称',sortable:true,width:150,sortable:true,
					editor: { type: 'validatebox' }
				},
			]],
			toolbar:[
				{//添加数据
					   text:"添加",
					   iconCls: "icon-add",
					   handler: addData,
				},'-',
				{//修改数据
					   text:"编辑",
					   iconCls: "icon-edit",
					   handler: editData,
				},'-',
				{//修改数据
					   text:"保存",
					   iconCls: "icon-save",
					   handler: saveData,
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
    //------------------------------------------------------------------------------
    
    function getDistinctMetaCode(){//获取所有的jobname
    	$.ajax({
    		type:'post',
    		url:"<%=basePath%>ConfMetaGetDistinctJobnamesMetacode",
    		success:function(data){
    			var list = eval("("+data+")");
    			var jobname = list.jobname;
    			if(jobname.length>0){
    				var str1 = "";
    				for(var i =0;i<jobname.length;i++){
    					str1+="<option value='"+jobname[i]+"'>"+jobname[i]+"</option>";
    				}
    				$('#metacode').html(str1);
    			}
    			$('#metacode').show();
    			$("#metacode").multiselect({
    				noneSelectedText: "==请选择==",
    		        checkAllText: "全选",
    		        uncheckAllText: '全不选',
    		        selectedText:'#项被选中',
    			}).multiselectfilter(); 
    		},error:function(){
    			console.log("fail");
    		}
    	});
    }
 
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
    	if(doedit != undefined){
			//$('#grid').datagrid('endEdit',doedit);
		}
		if(doedit == undefined){
			var row = $('#grid').datagrid('getSelected');
			var rowIndex = $('#grid').datagrid('getRowIndex', row);
			if(row!=null){
				rowIndex = $('#grid').datagrid('getRowIndex', row);
				rowIndex = rowIndex + 1;
			}
			else{
				rowIndex = 0;
			}
			$('#grid').datagrid('insertRow',{
				index: rowIndex,
				row: {
				}
			});
			$('#grid').datagrid('beginEdit',rowIndex);
			doedit = rowIndex;
		}
    }
    //----------------------------导入------------------------------------------
    function importData(){
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
			    		url:"<%=basePath%>DeleteUserDataServlet",
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
    //-------------------------------保存-------------------------------------------
	function saveData(){//保存
		$.messager.confirm("操作警告", "确定保存后被修改的数据将不可恢复！！", function(data){
			if(data){
				$('#grid').datagrid('endEdit', doedit);
				var inserted = $('#grid').datagrid('getChanges', 'inserted');
				var updated = $('#grid').datagrid('getChanges', 'updated');
				var insertrow = JSON.stringify(inserted);
				var updatedrow = JSON.stringify(updated);
				if (updated.length > 0) {  
					$.ajax({
			    		type:'post',
			    		url:"<%=basePath%>UpdateUserDataServlet",
			    		data:{"rowstr":updatedrow},
			    		success:function(data){
			    			if(1==data){//成功
			    				$.messager.alert('提示','更新成功','info');
			    			}else{
			    				$.messager.alert('提示','更新失败','error');
			    			}
			    			$('#grid').datagrid('reload');
			    		},error:function(){
			    			console.log("fail");
			    		}
			    	});			       
			    }
				if (inserted.length > 0) {  
					$.ajax({
			    		type:'post',
			    		url:"<%=basePath%>InserUserDataServlet",
			    		data:{"rowstr":insertrow},
			    		success:function(data){
			    			if(1==data){//成功
			    				$.messager.alert('提示','添加成功','info');
			    			}else{
			    				$.messager.alert('提示','添加失败','error');
			    			}
			    			$('#grid').datagrid('reload');
			    		},error:function(){
			    			console.log("fail");
			    		}
			    	});			       
			    } 
			}
		});
    }
    $(document).ready(function(){
    	$('#SelectBtn').click(function(){
    		//alert("xx");
    	    $('#importform').submit();
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
    		<form id="importform"  action="<%=basePath%>UserImportFileServlet" enctype="multipart/form-data" method="post">
	  			<input class="easyui-filebox" name="file1" data-options="prompt:'Choose a file...'" style="width:90%">
	  			<a href="javascript:void(0)" id="SelectBtn" class="easyui-linkbutton" iconCls="icon-ok" style="width:150px;height:32px;margin-top: 10px;margin-left: 65px">确定</a>
  			</form>
  		</div>
	</div>
	
</body>
</html>