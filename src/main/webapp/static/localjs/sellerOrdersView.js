$(function() {

    $(".backSellerOrdersList").bind("click",function () {
        var obj=$(this);
        window.location.href="/toSellerOrdersList?sellerId="+obj.attr("sellerId");
    });
});