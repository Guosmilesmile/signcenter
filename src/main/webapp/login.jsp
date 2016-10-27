<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/login.css">
</head>
<body>
<div class="container">
    <div class="row">
        <form id="loginform" action="<%=basePath%>LoginServlet" class="form-group col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4" method="post">
            <fieldset>
                <legend>登录签到中心</legend>
                <div class="control-group">
                    <input name="username" class="input-xlarge form-control" placeholder="username" required autofocus>
                </div>
                <div class="control-group">
                    <input name="password" type="password" id="password" class="input-xlarge form-control" placeholder="password" required>
                </div>
                <div class="control-group row">
                    <div class="col-lg-12">
                        <button id="loginsubmit" type="button" class="btn btn-lg btn-block btn-primary">登录</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<script src="<%=basePath%>/js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath%>css/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/security.js"></script>
<script type="text/javascript">
    	$('#loginsubmit').click(function(){
    		$.ajax({
    			type:'post',
        		url:"<%=basePath%>GetRasRepair",
        		async:false,
        		success:function(datas){
        			var data = eval("("+datas+")");
        			var modulus = data.modulus, exponent = data.exponent;
					var epwd = $('#password').val();
		    		if (epwd.length != 256) {
						var publicKey = RSAUtils.getKeyPair(exponent, '', modulus);
						$('#password').val(RSAUtils.encryptedString(publicKey, epwd));
		    		}
					$("#loginform").submit();
        		},error:function(){
        			
        		}
        	});
    	});
</script>
</body>
</html>