<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
 <head>
        <title>Your Admin Panel</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
        
        <!-- jQuery AND jQueryUI -->
        <script type="text/javascript" src="<%=basePath%>js/libs/jquery/1.6/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/libs/jqueryui/1.8.13/jquery-ui.min.js"></script>
        
        <script type="text/javascript">
        	$(document).ready(function(){
        		  $('.subul').find('li').find('a').click(function(){
        				//$(this).parent().siblings().removeClass('current');
        				$('.subul').find('li').removeClass('current');
        				$(this).parent().addClass('current');
        		  });
        	});
        	
        </script>
        <link rel="stylesheet" href="<%=basePath%>css/min.css" />
        <script type="text/javascript" src="<%=basePath%>js/min.js"></script>
    </head>
    <body>
        
		<% 
			String role = request.getSession().getAttribute("role")+"";
		%>
        <div id="sidebar">
            <ul id="sidebarul">
            	<% 
            		if("0".equals(role)){
            	%>
            	<li><a href="#"><img src="<%=basePath%>img/icons/menu/brush.png" alt="" />系统管理</a>
                    <ul class="subul">
                         <li class="current"><a target="rightFrame" href="./manageuser.jsp">用户管理</a></li>
                    </ul>
                </li>
                <%}%>
                <li><a href="#"><img src="<%=basePath%>img/icons/menu/calendar.png" alt="" />签到管理</a>
                    <ul class="subul">
                         <li class="current"><a target="rightFrame" href="./managecourse.jsp">课程管理</a></li>
                         <li ><a target="rightFrame" href="./managesign.jsp">签到管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
                
                        
    </body>
</html>