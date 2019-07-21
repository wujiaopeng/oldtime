$(function() {
    //判断图片
    var pictPath=$("#pictPath").val();
    if(pictPath != null && pictPath!=""){
        $(".pict").css("display","block");
    }else{
        $("#pict").css("display","block");
    }

    //动态加载所属平台列表
    $.ajax({
        type: "GET",//请求类型
        url:"/typelist.json",//请求的url
        data: {},//请求参数
        dataType: "json",//ajax接口（请求url）返回的数据类型
        success: function (data) {//data：返回数据（json对象）
            var type2 = $("#type2").val();
            $("#type1").html("");
            var options = "<option value=\"\">——请选择书籍类型——</option>";
            for (var i = 0; i < data.length; i++) {
                if (type2 != null && type2 != undefined && data[i].id == type2) {
                    options += "<option selected=\"selected\" value=\"" + data[i].id + "\" >" + data[i].name + "</option>";
                } else {
                    options += "<option value=\"" + data[i].id + "\">" + data[i].name + "</option>";
                }
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
    $(".delPict").bind("click",function () {
        var obj=$(this);
        $.ajax({
            type: "POST",//请求类型
            url:"/delpict",//请求的url
            data: {bid:obj.attr("bid")},//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
               if(data.result=="true"){
                   alert("删除成功!");
                   $("#pict").css("display","block");
                   $(".pict").css("display","none");
               }else {
                   alert("删除失败！");
               }
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("删除失败！");
            }
        });
    });

    $("#send").bind("click",function () {
            $("#myform").submit();
    });
});