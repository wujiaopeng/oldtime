<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/19
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<!-- 右侧内容 -->
<div class="layui-body layui-form">
    <div class="layui-tab marg0" lay-filter="bodyTab">
        <ul class="layui-tab-title top_tab">
            <h1 style="font-size: 20px;">修改用户信息</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class=" layui-tab-item layui-show">
                <form id="myform" method="post" class="layui-form" enctype="multipart/form-data" action="/saveusermodify" style="margin:20px 20px 0px">
                    <input type="hidden" name="id" value="${user.id}">
                    <table class="layui-table">
                        <colgroup>
                            <col width="30%">
                            <col width="50%">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>参数说明</th>
                            <th>参数值</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>用户编码：</td>
                            <td><input id="userCode" type="text" class="layui-input cmsName" name="code"
                                       value="${user.code}" readonly  lay-verify="required" ></td>
                            <td><span class="message1"></span></td>
                        </tr>
                        <tr>
                            <td>密码：</td>
                            <td><input id="pwd" type="password" name="password"  value="${user.password}"  class="layui-input version"></td>
                            <td><span class="message2"></span></td>
                        </tr>
                        <tr>
                            <td>用户名：</td>
                            <td><input id="uname" type="text" name="name"  value="${user.name}"  class="layui-input author" ></td>
                            <td><span class="message3"></span></td>
                        </tr>
                        <tr>
                            <td>联系电话：</td>
                            <td><input id="uphone" type="text" name="phone"  value="${user.phone}"  class="layui-input homePage"></td>
                            <td><span class="message4"></span></td>
                        </tr>
                        <tr>
                            <td>简介：</td>
                            <td>
                                <textarea name="summary" class="layui-textarea description">${user.summary}</textarea>
                            </td>
                            <td><span style="color: red;">不能大于50个字</span></td>
                        </tr>
                        <tr>
                            <td>图片：</td>
                            <td>
                                <input type="hidden" id="pictPath"  value="${user.pictPath}">
                                <input type="file" id="pict" class="layui-input dataBase"  name="a_pictPath" style="display:none;">
                                <div class="pict" style="display: none;">
                                    <img width="150" src="${user.pictPath}" alt="响应式图片">
                                    <button type="button"  class="delPict layui-btn layui-btn-sm layui-btn-primary" uid="${user.id}">
                                        <i class="layui-icon">&#xe640;</i>
                                    </button>
                                </div>
                            </td>
                            <td><span style="color: red;">不能大500kb</span></td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="layui-form-item" style="text-align: right;">
                        <div class="layui-input-block">
                            <button id="send" type="button" class="layui-btn" lay-submit="" lay-filter="systemParameter">立即提交</button>
                            <button id="back" type="button" class="layui-btn layui-btn-primary">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/common/footer.jsp"%>
<script type="text/javascript" src="static/localjs/usermodify.js"></script>