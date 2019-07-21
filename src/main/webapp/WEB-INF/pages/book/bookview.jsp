<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/19
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<!-- 右侧内容 -->
<div class="layui-body layui-form">
    <div class="layui-tab marg0" lay-filter="bodyTab">
        <ul class="layui-tab-title top_tab">
            <h1 style="font-size: 20px;">查看商品书籍</h1>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class="layui-tab-item layui-show">
                <div class="sysNotice col">
                    <blockquote class="layui-elem-quote title">
                        书籍详细信息
                    </blockquote>
                    <table class="layui-table">
                        <colgroup>
                            <col width="260">
                            <col>
                        </colgroup>
                        <tbody>
                        <tr>
                            <td>书籍名称：</td>
                            <td class="version">${book.bookName}</td>
                        </tr>
                        <tr>
                            <td>作者：</td>
                            <td class="author">${book.author}</td>
                        </tr>
                        <tr>
                            <td>出版社：</td>
                            <td class="homePage">${book.press}</td>
                        </tr>
                        <tr>
                            <td>原价：</td>
                            <td class="server">${book.oldPrice}</td>
                        </tr>
                        <tr>
                            <td>现价：</td>
                            <td class="dataBase">${book.newPrice}</td>
                        </tr>
                        <tr>
                            <td>库存：</td>
                            <td class="maxUpload">${book.number}</td>
                        </tr>
                        <tr>
                            <td>类型：</td>
                            <td class="userRights">${book.typeName}</td>
                        </tr>
                        <tr>
                            <td>卖家：</td>
                            <td class="userRights">${book.userName}</td>
                        </tr>
                        <tr>
                            <td>简介：</td>
                            <td class="userRights">${book.description}</td>
                        </tr>
                        <tr>
                            <td>图片：</td>
                            <td class="userRights">
                                <img width="200" src="${book.pictPath}" alt="响应式">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <blockquote style="float: right;">
                        <button type="button" id="back" class="layui-btn layui-btn-radius layui-btn-normal">返回</button>
                    </blockquote>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/pages/common/footer.jsp"%>
<script type="text/javascript">
    var backBtn = null;

    $(function(){
        $("#back").bind("click",function(){
            window.location.href="/booklist";
        });
    });
</script>
