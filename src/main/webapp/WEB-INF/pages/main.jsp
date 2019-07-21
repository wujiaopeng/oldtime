<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/14
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<!-- 右侧内容 -->
<div class="layui-body layui-form">
    <div class="layui-tab marg0" lay-filter="bodyTab">
        <ul class="layui-tab-title top_tab">
            <h1 style="font-size: 20px;">标题</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class="layui-tab-item layui-show">
                <div class="childrenBody">
                <blockquote class="layui-elem-quote news_search">
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-normal newsAdd_btn">添加文章</a>
                    </div>
                    <div class="layui-inline" style="margin-left:60px;">
                        <div class="layui-input-inline">
                            <input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
                        </div>
                        <a class="layui-btn search_btn">查询</a>
                    </div>
                </blockquote>
                <div class="layui-form news_list">
                    <table class="layui-table">
                        <colgroup>
                            <col width="40">
                            <col width="9%">
                            <col width="9%">
                            <col width="9%">
                            <col width="9%">
                            <col width="9%">
                            <col width="20%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th style="text-align:left;">文章标题</th>
                                <th>发布人</th>
                                <th>审核状态</th>
                                <th>浏览权限</th>
                                <th>是否展示</th>
                                <th>发布时间</th>
                                <th style="text-align:center;"> 操作</th>
                            </tr>
                        </thead>
                        <tbody class="news_content">
                            <tr>
                                <td>12121</td>
                                <td>12121</td>
                                <td>12121</td>
                                <td>12121</td>
                                <td>12121</td>
                                <td>12121</td>
                                <td>
                                    <div class="layui-btn-group">
                                        <button class="layui-btn layui-btn-primary layui-btn-sm" lay-event="detail">查看</button>
                                        <button class="layui-btn layui-btn-sm" lay-event="edit">编辑</button>
                                        <button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="page"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/common/footer.jsp"%>
