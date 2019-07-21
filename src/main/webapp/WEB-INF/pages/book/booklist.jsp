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
            <h1 style="font-size: 20px;">书籍管理</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class="layui-tab-item layui-show">
                <div class="childrenBody">
                <blockquote class="layui-elem-quote news_search">
                    <div class="layui-inline">
                        <a class="bookadd layui-btn layui-btn-normal newsAdd_btn">添加书籍</a>
                    </div>
                    <div class="layui-inline" style="margin-left:60px;">
                        <form id="myform" method="post">
                            <div class="layui-input-inline">
                                <input type="text" name="queryname" value="${queryname}" placeholder="请输入书籍名" class="layui-input search_input">
                            </div>
                            <div class="layui-input-inline">
                                <input type="hidden" id="querytype" value="${querytype}">
                                <select id="type1" class="layui-input search_input" name="querytype"></select>
                            </div>
                            <input type="hidden" name="pageIndex" value="1">
                            <button type="submit" class="layui-btn search_btn">查询</button>
                        </form>
                    </div>
                </blockquote>
                <div class="layui-form news_list">
                    <table class="layui-table">
                        <colgroup>
                            <col width="20%">
                            <col width="10%">
                            <col width="10%">
                            <col width="15%">
                            <col width="15%">
                            <col width="9%">
                            <col width="20%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th style="text-align:left;">书籍名</th>
                                <th>发布人</th>
                                <th>类型</th>
                                <th>作者</th>
                                <th>出版社</th>
                                <th>库存</th>
                                <th style="text-align:center;"> 操作</th>
                            </tr>
                        </thead>
                        <tbody class="news_content">
                            <c:forEach var="book" items="${bookList}" varStatus="status">
                                <tr>
                                    <td>${book.bookName}</td>
                                    <td>${book.userName}</td>
                                    <td>${book.typeName}</td>
                                    <td>${book.author}</td>
                                    <td>${book.press}</td>
                                    <td>${book.number}</td>
                                    <td>
                                        <div class="layui-btn-group">
                                            <button class="bookview layui-btn layui-btn-primary layui-btn-sm" bid="${book.id}" lay-event="detail">查看</button>
                                            <button class="modifybook layui-btn layui-btn-sm" bid="${book.id}" lay-event="edit">编辑</button>
                                            <button class="deletebook layui-btn layui-btn-danger layui-btn-sm" bname="${book.bookName}" bid="${book.id}" lay-event="del">删除</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/static/localjs/booklist.js"></script>
