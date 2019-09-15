<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path=request.getContextPath(); %>
<html lang="en">
<head>
    <script type="text/javascript" src="./js/jquery.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="./js/jquery-easyui-1.4.1/jquery.easyui.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../js/jquery-easyui-1.4.1/easyui-lang-zh_CN.js" charset="UTF-8"></script>
    <meta charset="utf-8"/>
    <title>欢迎您！</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="gao" name="author"/>
    <link href="./css/login.css" rel="stylesheet" type="text/css"/>
    <script>
        //判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，
        window.onload = function (){
            if (window.top!=null && window.top.document.URL!=document.URL){
                window.top.location= document.URL; //这样就可以让登陆窗口显示在整个窗口了
            }
        }
        document.onkeydown = function (event) {
            e = event ? event : (window.event ? window.event : null);
            if (e.keyCode == 13) {
            	login();
            }
        }
        
    	function login() {
    		var username = $("#input-username").val();
    		var password = $("#input-password").val();
    		if(username.length ==0  || password.length == 0){
    			$("#msg").text("用户名或密码为空!");
    			return ;
    		}
    		$.ajax({
    			type : "POST",
    			data : {
    				"username" : username,
    				"password" : password,
    			},
    			dataType : "json",
    			url : "<%=path%>/ajaxLogin",
    			success : function(result) {
    				if (result.code != 0) {
    					swal("失败",result.msg,"error");
    				} else {
    					location.href = "<%=path%>/index";
    				}
    			}
    		});
    	}
    	function resetMsg(){
    		$("#msg").text("");
    	}
    </script>
</head>
<body style="background:#099eff" >
<div class="login_top"><div style="width: 1200px; margin:0px auto"><img src="./css/img/logo1.png"/></div></div>
<div class="bg"  >
    <div class="login_left"><img src="./css/img/login_img.png"/></div>
    <div class="Login">
        <div class="login_main">
            <div class="main">
                <form id="loginform">
                    <table cellpadding="0" cellspacing="0" style="margin:25px 0px">
                        <tr>
                            <td style="font-size: 16px!important;">用&nbsp;&nbsp;&nbsp;户：</td>
                        </tr>
                        <tr>
                            <td><input id="input-username" type="text" name="username" tabindex="1" onfocus="resetMsg()"/></td>
                        </tr>
                        <tr>
                            <td style="font-size: 16px!important;">密&nbsp;&nbsp;&nbsp;码：</td>
                        </tr>
                        <tr>
                            <td><input id="input-password" type="password" name="password"  tabindex="2" onfocus="resetMsg()"/></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="button" id="login-btn1" class="login_button" value="登录 " onclick="login()">
                                <input type="reset" id="login-btn2" class="reset_button" value="重置 ">
                            </td>
                        </tr>
                        <tr>

                            <td style="padding-top:5px;font-size:12px; color:#C00"><span id="msg"></span>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<div  class="login_bottom" >    Copyright © 2006-2018 北京新纽科技有限公司</div>
</body>
</html>
