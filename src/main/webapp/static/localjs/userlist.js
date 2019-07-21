$(function() {

    $(".useradd").bind("click",function () {
        var obj=$(this);
        window.location.href="/useradd";
    });
    $(".usermodify").bind("click",function () {
        var obj=$(this);
        window.location.href="/usermodify?uid="+obj.attr("uid");
    });
    $(".userdelete").bind("click",function () {
        var obj=$(this);
        if(confirm("确定删除用户【"+obj.attr("uname")+"】吗？")){
            $.ajax({
                type:"POST",
                url:"/deleteUser",
                data:{uid:obj.attr("uid")},
                dataType:"json",
                success:function (data) {
                    if(data.result=="true"){
                        alert("删除成功！");
                        obj.parents("tr").remove();
                    }else{
                        alert("删除失败！");
                    }
                },error:function (data) {
                    alert("删除失败！");
                }
            });
        }
    });


    $(".toBuyerOrdersList").bind("click",function () {
        var obj=$(this);
        window.location.href="/toBuyerOrdersList?buyerId="+obj.attr("buyerId");
    });
    $(".toSellerOrdersList").bind("click",function () {
        var obj=$(this);
        window.location.href="/toSellerOrdersList?sellerId="+obj.attr("sellerId");
    });
    $(".toShoppingCar").bind("click",function () {
        var obj=$(this);
        window.location.href="/toShoppingCarList?uId="+obj.attr("uId");
    });
});