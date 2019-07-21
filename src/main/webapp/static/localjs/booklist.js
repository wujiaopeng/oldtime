$(function() {
    //动态加载所属平台列表
    $.ajax({
        type: "GET",//请求类型
        url:"/typelist.json",//请求的url
        data: {},//请求参数
        dataType: "json",//ajax接口（请求url）返回的数据类型
        success: function (data) {//data：返回数据（json对象）
            var querytype = $("#querytype").val();
            $("#type1").html("");
            var options = "<option value=\"\">——请选择书籍类型——</option>";
            for (var i = 0; i < data.length; i++) {
                if (querytype != null && querytype != undefined && data[i].id == querytype) {
                    options += "<option selected=\"selected\" value=\"" + data[i].id + "\" >" + data[i].name + "</option>";
                } else {
                    options += "<option value=\"" + data[i].id + "\">" + data[i].name + "</option>";
                }
            }
            options += "<option value=\"\">——请选择书籍类型——</option>";
            $("#type1").html(options);
        },
        error: function (data) {//当访问时候，404，500 等非200的错误状态码
            alert("加载分类列表失败！");
        }
    });
    $(".bookadd").bind("click",function () {
        var obj=$(this);
        window.location.href="/bookadd";
    });
    $(".bookview").bind("click",function () {
        var obj=$(this);
        window.location.href="/bookview?bid="+obj.attr("bid");
    });
    $(".modifybook").bind("click",function () {
        var obj=$(this);
        window.location.href="/bookmodify?bid="+obj.attr("bid");
    });
    $(".deletebook").bind("click",function () {
        var obj=$(this);
        if(confirm("确定删除书籍【"+obj.attr("bname")+"】吗？")){
            $.ajax({
                type:"POST",
                url:"/deleteBook",
                data:{bid:obj.attr("bid")},
                dataType:"json",
                success:function (data) {
                    if(data.result=="true"){
                        alert("删除成功！");
                        obj.parents("tr").remove();
                    }else{
                        alert("删除失败！");
                    }
                },error:function (data) {

                }
            });
        }
    });
});