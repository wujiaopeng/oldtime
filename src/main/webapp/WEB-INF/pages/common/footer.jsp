<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/14
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 底部 -->
<div class="layui-footer footer">
    <p>copyright @2019 个人所有</p>
</div>
</div>

<!-- 锁屏 -->
<div class="admin-header-lock" id="lock-box" style="display: none;">
    <div class="admin-header-lock-img"><img src="static/images/face.jpg"/></div>
    <div class="admin-header-lock-name" id="lockUserName">请叫我马哥</div>
    <div class="input_btn">
        <input type="password" class="admin-header-lock-input layui-input" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />
        <button class="layui-btn" id="unlock">解锁</button>
    </div>
    <p>请输入“123456”，否则不会解锁成功哦！！！</p>
</div>
<!-- 移动导航 -->
<div class="site-tree-mobile layui-hide"><i class="layui-icon">&#xe602;</i></div>
<div class="site-mobile-shade"></div>
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/nav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/leftNav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/index.js"></script>
</body>
</html>
