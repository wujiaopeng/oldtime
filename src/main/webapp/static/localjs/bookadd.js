$(function() {

    //动态加载所属类型列表
    $.ajax({
        type: "GET",//请求类型
        url:"/typelist.json",//请求的url
        data: {},//请求参数
        dataType: "json",//ajax接口（请求url）返回的数据类型
        success: function (data) {//data：返回数据（json对象）
            $("#type1").html("");
            var options = "<option value=\"\">——请选择书籍类型——</option>";
            for (var i = 0; i < data.length; i++) {
                options += "<option value=\"" + data[i].id + "\">" + data[i].name + "</option>";
            }
            $("#type1").html(options);
        },
        error: function (data) {//当访问时候，404，500 等非200的错误状态码
            alert("加载分类列表失败！");
        }
    });
    $("#back").bind("click",function () {
        window.location.href="/booklist";
    });

    $("#send").bind("click",function () {
            $("#myform").submit();
    });
});