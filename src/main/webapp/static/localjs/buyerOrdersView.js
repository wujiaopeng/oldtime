$(function() {

    $(".backBuyerOrdersList").bind("click",function () {
        var obj=$(this);
        window.location.href="/toBuyerOrdersList?buyerId="+obj.attr("buyerId");
    });
});