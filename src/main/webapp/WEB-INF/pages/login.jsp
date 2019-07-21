<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\12\12 0012
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OldTime后台管理登录页</title>
    <!-- Bootstrap -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="static/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="static/css/custom.min.css" rel="stylesheet">
</head>
<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form action="${pageContext.request.contextPath}/doLogin" method="post">
                    <h1>OldTime后台管理登录</h1>
                    <div>
                        <input type="text" class="form-control" placeholder="Username" required="" name="code"/>
                    </div>
                    <div>
                        <input type="password" class="form-control" placeholder="Password" required=""  name="password"/>
                    </div>
                    <span style="color:red">${error}</span>
                    <div>
                        <button type="submit" class="btn btn-success" >登录</button>
                        <button type="reset" class="btn btn-default" >重置</button>
                    </div>
                    <div class="clearfix"></div>
                    <div class="separator">
                        <div class="clearfix"></div>
                        <br />
                        <div>
                            <p>copyright @2019 个人所有</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>
