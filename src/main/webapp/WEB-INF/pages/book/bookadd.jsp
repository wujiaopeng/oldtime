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
            <h1 style="font-size: 20px;">添加商品书籍</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class=" layui-tab-item layui-show">
                <form id="myform" method="post" class="layui-form" enctype="multipart/form-data" action="/savebookadd" style="margin:20px 20px 0px">
                    <table class="layui-table">
                        <colgroup>
                            <col width="30%">
                            <col width="50%">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>参数说明</th>
                            <th>参数值</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>书籍名称：</td>
                            <td><input type="text" class="layui-input cmsName" name="bookName"  lay-verify="required" ></td>
                        </tr>
                        <tr>
                            <td>作者：</td>
                            <td><input type="text" name="author"  class="layui-input version"></td>
                        </tr>
                        <tr>
                            <td>出版社：</td>
                            <td><input type="text" name="press"  class="layui-input author" ></td>
                        </tr>
                        <tr>
                            <td>原价：</td>
                            <td><input type="text" name="oldPrice"  class="layui-input homePage"></td>
                        </tr>
                        <tr>
                            <td>现价：</td>
                            <td><input type="text" name="newPrice"  class="layui-input server"></td>
                        </tr>
                        <tr>
                            <td>库存：</td>
                            <td><input type="number" name="number"  class="layui-input dataBase"></td>
                        </tr>
                        <tr>
                            <td>类型：</td>
                            <td>
                                <select class="layui-select" id="type1" name="tid"></select>
                            </td>
                        </tr>
                        <tr>
                            <td>简介：</td>
                            <td>
                                <textarea name="description" class="layui-textarea description"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>图片：</td>
                            <td>
                                <input type="file"  class="layui-input dataBase"  name="a_pictPath">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="layui-form-item" style="text-align: right;">
                        <div class="layui-input-block">
                            <button id="send" type="button" class="layui-btn" lay-submit="" lay-filter="systemParameter">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/common/footer.jsp"%>
<script type="text/javascript" src="static/localjs/bookadd.js"></script>