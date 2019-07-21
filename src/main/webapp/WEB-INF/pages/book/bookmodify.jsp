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
            <h1 style="font-size: 20px;">修改商品书籍</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class=" layui-tab-item layui-show">
                <form id="myform" method="post" class="layui-form" enctype="multipart/form-data" action="/sendbookmodify" style="margin:20px 20px 0px">
                    <input type="hidden"  name="id" value="${book.id}">
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
                            <td><input type="text" class="layui-input cmsName" name="bookName" value="${book.bookName}" lay-verify="required" ></td>
                        </tr>
                        <tr>
                            <td>作者：</td>
                            <td><input type="text" name="author" value="${book.author}" class="layui-input version"></td>
                        </tr>
                        <tr>
                            <td>出版社：</td>
                            <td><input type="text" name="press" value="${book.press}" class="layui-input author" ></td>
                        </tr>
                        <tr>
                            <td>原价：</td>
                            <td><input type="text" name="oldPrice" value="${book.oldPrice}" class="layui-input homePage"></td>
                        </tr>
                        <tr>
                            <td>现价：</td>
                            <td><input type="text" name="newPrice" value="${book.newPrice}" class="layui-input server"></td>
                        </tr>
                        <tr>
                            <td>库存：</td>
                            <td><input type="number" name="number" value="${book.number}" class="layui-input dataBase"></td>
                        </tr>
                        <tr>
                            <td>类型：</td>
                            <td>
                                <input type="hidden" id="type2" value="${book.tid}">
                                <select class="layui-select" id="type1" name="tid"></select>
                            </td>
                        </tr>
                        <tr>
                            <td>卖家：</td>
                            <td><input type="text" readonly value="${book.userName}" class="layui-input userRights" ></td>
                        </tr>
                        <tr>
                            <td>简介：</td>
                            <td>
                                <textarea name="description" class="layui-textarea description"> ${book.description}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>图片：</td>
                            <td>
                                <input type="hidden" id="pictPath" value="${book.pictPath}">
                                <input type="file" id="pict" name="a_pictPath"  style="display:none;">
                                <div class="pict" style="display: none;">
                                    <img width="200" src="${book.pictPath}" alt="响应式图片">
                                    <button type="button"  class="delPict layui-btn layui-btn-sm layui-btn-primary" bid="${book.id}">
                                        <i class="layui-icon">&#xe640;</i>
                                    </button>
                                </div>
                            </td>
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
<script type="text/javascript" src="static/localjs/bookmodify.js"></script>