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
            <h1 style="font-size: 20px;">用户售出订单列表</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class="layui-tab-item layui-show">
                <div class="childrenBody">
                    <blockquote class="layui-elem-quote news_search">
                        <div class="layui-inline">
                            <a class="backSellerOrdersList layui-btn layui-btn-normal newsAdd_btn" sellerId="${sellerId}"><i class="layui-icon"></i> 返回</a>
                        </div>
                        <div class="layui-inline" style="margin-left:60px;">
                            <form action="${pageContext.request.contextPath}/toSellerOrdersView" method="post">
                                <input type="hidden" name="sellerId" value="${sellerId}">
                                <input type="hidden" name="orderId" value="${orderId}">
                                <input type="hidden" name="pageIndex" value="${currentPageNo}">
                            </form>
                        </div>
                    </blockquote>
                    <div class="layui-form news_list">
                        <table class="layui-table">
                            <colgroup>
                                <col width="30%">
                                <col width="30%">
                                <col width="20%">
                                <col width="20%">
                            </colgroup>
                            <thead>
                            <tr>
                                <th style="text-align:left;">书名</th>
                                <th>封面</th>
                                <th>书总价</th>
                                <th>售出数量</th>
                            </tr>
                            </thead>
                            <tbody class="news_content">
                            <c:forEach var="orderBook" items="${orderBookList}" varStatus="status">
                                <tr>
                                    <td>${orderBook.bookName}</td>
                                    <td><img width="100" src="${orderBook.bookImage}" alt="响应式图片"></td>
                                    <td>${orderBook.bookPrice}</td>
                                    <td>${orderBook.number}</td>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/static/localjs/sellerOrdersView.js"></script>
