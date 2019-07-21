$(function () {
    $("#userCode").bind("blur",function () {
        var code=$(this).val();
        $.ajax({
            type:"POST",
            url:"isCode",
            data:{ucode:code},
            dataType:"json",
            success:function (data) {
                if(data.result=="empty")
                    $(".message1").html("用户编码不能为空！").css("color","red");
                else if(data.result=="exit")
                    $(".message1").html("用户编码已存在！").css("color","red");
                else
                    $(".message1").html("用户编码可用！").css("color","green");
            },error:function (data) {
                    alert("请求错误！");
            }
        });
    });
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
    $("#send").bind("click",function () {
        $("#myform").submit();
    });
});