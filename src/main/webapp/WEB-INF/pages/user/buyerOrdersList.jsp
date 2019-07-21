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
            <h1 style="font-size: 20px;">用户购买订单列表</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class="layui-tab-item layui-show">
                <div class="childrenBody">
                    <blockquote class="layui-elem-quote news_search">
                        <div class="layui-inline">
                            <a class="backUserList layui-btn layui-btn-normal newsAdd_btn" ><i class="layui-icon"></i> 返回</a>
                        </div>
                        <div class="layui-inline" style="margin-left:60px;">
                            <form action="${pageContext.request.contextPath}/toBuyerOrdersList" method="post">
                                <input type="hidden" name="buyerId" value="${buyerId}">
                                <input type="hidden" name="pageIndex" value="${currentPageNo}">
                            </form>
                        </div>
                    </blockquote>
                    <div class="layui-form news_list">
                        <table class="layui-table">
                            <colgroup>
                                <col width="15%">
                                <col width="10%">
                                <col width="10%">
                                <col width="23%">
                                <col width="7%">
                                <col width="15%">
                                <col width="7%">
                                <col width="15%">
                            </colgroup>
                            <thead>
                            <tr>
                                <th style="text-align:left;">订单编号</th>
                                <th>买家姓名</th>
                                <th>卖家姓名</th>
                                <th>收货地址</th>
                                <th>总金额</th>
                                <th>创建日期</th>
                                <th>订单状态</th>
                                <th style="text-align:center;"> 操作</th>
                            </tr>
                            </thead>
                            <tbody class="news_content">
                            <c:forEach var="order" items="${ordersList}" varStatus="status">
                                <tr>
                                    <td>${order.orderNo}</td>
                                    <td>${order.buyerName}</td>
                                    <td>${order.sellerName}</td>
                                    <td>${order.address}</td>
                                    <td>${order.total}</td>
                                    <td>${order.createDate1}</td>
                                    <td>${order.stateName}</td>
                                    <td>
                                        <div class="layui-btn-group">
                                            <button class="ordersView layui-btn layui-btn-xs" orderId="${order.id}" buyerId="${buyerId}" lay-event="edit">   <!-- 查看该订单 -->
                                                <i class="layui-icon">&#xe642;</i>
                                            </button>
                                            <button class="ordersDelete layui-btn layui-btn-danger layui-btn-xs" orderId="${order.id}" orderNo="${order.orderNo}" lay-event="del">   <!-- 删除该订单 -->
                                                <i class="layui-icon">&#xe640;</i>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/static/localjs/buyerOrdersList.js"></script>
