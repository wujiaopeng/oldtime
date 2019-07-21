$(function() {

    $(".backUserList").bind("click",function () {
        window.location.href="/userlist";
    });

    $(".ordersView").bind("click",function () {
        var obj=$(this);
        window.location.href="/toSellerOrdersView?orderId="+obj.attr("orderId")+"&"+"sellerId="+obj.attr("sellerId");
    });

    $(".ordersDelete").bind("click",function () {
        var obj=$(this);
        if(confirm("确定删除订单【"+obj.attr("orderNo")+"】吗？")){
            $.ajax({
                type:"POST",
                url:"/deleteOrdersById",
                data:{orderId:obj.attr("orderId")},
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