<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/20
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<!-- 右侧内容 -->
<div class="layui-body layui-form">
    <div class="layui-tab marg0" lay-filter="bodyTab">
        <ul class="layui-tab-title top_tab">
            <h1 style="font-size: 20px;">用户列表</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class="layui-tab-item layui-show">
                <div class="childrenBody">
                    <blockquote class="layui-elem-quote news_search">
                        <div class="layui-inline">
                            <a class="useradd layui-btn layui-btn-normal newsAdd_btn"><i class="layui-icon">&#xe608;</i> 添加用户</a>
                        </div>
                        <div class="layui-inline" style="margin-left:60px;">
                            <form id="myform" method="post">
                                <div class="layui-input-inline">
                                    <input type="text" name="queryname" value="${queryname}" placeholder="请输入用户名" class="layui-input search_input">
                                </div>
                                <input type="hidden" name="pageIndex" value="1">
                                <button type="submit" class="layui-btn search_btn">查询</button>
                            </form>
                        </div>
                    </blockquote>
                    <div class="layui-form news_list">
                        <table class="layui-table">
                            <colgroup>
                                <col width="10%">
                                <col width="10%">
                                <col width="12%">
                                <col width="16%">
                                <col width="10%">
                                <col width="7%">
                                <col width="7%">
                                <col width="28%">
                            </colgroup>
                            <thead>
                            <tr>
                                <th style="text-align:left;">用户账号</th>
                                <th>用户名</th>
                                <th>电话号码</th>
                                <th>简介</th>
                                <th>头像</th>
                                <th>金额</th>
                                <th>积分</th>
                                <th style="text-align:center;"> 操作</th>
                            </tr>
                            </thead>
                            <tbody class="news_content">
                            <c:forEach var="user" items="${userList}" varStatus="status">
                                <tr>
                                    <td>${user.code}</td>
                                    <td>${user.name}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.summary}</td>
                                    <td><img width="100" src="${user.pictPath}" alt="响应式图片"></td>
                                    <td>${user.money}</td>
                                    <td>${user.points}</td>
                                    <td>
                                        <div class="layui-btn-group">
                                            <button class="usermodify layui-btn layui-btn-xs" uid="${user.id}" lay-event="edit">
                                                <i class="layui-icon">&#xe642;</i>
                                            </button>
                                            <button class="userdelete layui-btn layui-btn-danger layui-btn-xs" uname="${user.name}" uid="${user.id}" lay-event="del">
                                                <i class="layui-icon">&#xe640;</i>
                                            </button>

                                            <button class="toBuyerOrdersList layui-btn layui-btn-normal" buyerId="${user.id}">    <!-- 用户购买订单列表页 -->
                                                <i class="layui-icon">&#xe63c;</i>
                                            </button>
                                            <button class="toSellerOrdersList layui-btn layui-icon-rmb" sellerId="${user.id}">   <!-- 用户售出订单列表页 -->
                                                <i class="layui-icon">&#xe60a;</i>
                                            </button>
                                            <button class="toShoppingCar layui-btn layui-btn-warm layui-icon-cart" uId="${user.id}">    <!-- 用户购物车列表页 -->
                                                <i class="layui-icon">&#xe600;</i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
                    <c:import url="/WEB-INF/pages/common/rollpage.jsp">
                        <c:param name="totalCount" value="${totalCount}"/>
                        <c:param name="currentPageNo" value="${currentPageNo}"/>
                        <c:param name="totalPageCount" value="${totalPageCount}"/>
                    </c:import>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/localjs/userlist.js"></script>
