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
                            <a class="backUserList layui-btn layui-btn-normal"><i class="layui-icon"></i> 返回</a>
                        </div>
                        <div class="layui-inline" style="margin-left:60px;">
                            <form action="${pageContext.request.contextPath}/toShoppingCarList" method="post">
                                <input type="hidden" name="uId" value="${uId}">
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
                                <col width="8%">
                                <col width="8%">
                                <col width="29%">
                                <col width="8%">
                            </colgroup>
                            <thead>
                            <tr>
                                <th style="text-align:left;">书名</th>
                                <th>封面</th>
                                <th>卖家姓名</th>
                                <th>已选数量</th>
                                <th>总金额</th>
                                <th>创建日期</th>
                                <th style="text-align:center;"> 操作</th>
                            </tr>
                            </thead>
                            <tbody class="news_content">
                            <c:forEach var="shoppingCar" items="${shoppingCarList}" varStatus="status">
                                <tr>
                                    <td>${shoppingCar.bookName}${shoppingCar.id}</td>
                                    <td><img width="100" src="${shoppingCar.bookImage}" alt="响应式图片"></td>
                                    <td>${shoppingCar.sellName}</td>
                                    <td>${shoppingCar.number}</td>
                                    <td>${shoppingCar.price}</td>
                                    <td>${shoppingCar.createDate}</td>
                                    <td>
                                        <div class="layui-btn-group">
                                            <button class="shoppingCarDelete layui-btn layui-btn-danger layui-btn-xs" sid="${shoppingCar.id}" lay-event="del">   <!-- 删除该条购物车 -->
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
<script type="text/javascript" src="${pageContext.request.contextPath }/static/localjs/shoppingcar.js"></script>
