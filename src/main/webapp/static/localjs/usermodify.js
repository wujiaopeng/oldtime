$(function () {
    //判断图片
    var pictPath=$("#pictPath").val();
    if(pictPath != null && pictPath!=""){
        $(".pict").css("display","block");
    }else{
        $("#pict").css("display","block");
    }
    $("#pwd").bind("blur",function () {
        var pwd = $(this).val();
        if(pwd.length<6)
            $(".message2").html("密码长度不能小于6位！").css("color","red");
        else
            $(".message2").html("").css("color","green");
    });
    $("#uname").bind("blur",function () {
        var uname = $(this).val();
        if(uname==null || uname=="")
            $(".message3").html("用户名不能为空！").css("color","red");
        else
            $(".message3").html("").css("color","green");
    });
    $("#uphone").bind("blur",function(){
        var uphone=$(this).val();
        var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
        if(uphone.match(patrn)){
            $(".message4").html("");
        }else{
            $(".message4").html("手机格式不对！").css("color","red");;
        }
    });

    //删除图片
    $(".delPict").bind("click",function () {
        var obj=$(this);
        $.ajax({
            type: "POST",//请求类型
            url:"/delUserPict",//请求的url
            data: {uid:obj.attr("uid")},//请求参数
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
    $("#back").bind("click",function () {
        window.location.href="/userlist";
    })
});