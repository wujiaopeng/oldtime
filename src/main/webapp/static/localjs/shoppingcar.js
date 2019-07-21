$(function() {

    $(".backUserList").bind("click",function () {
        window.location.href="/userlist";
    });

    $(".shoppingCarDelete").bind("click",function () {
        var obj=$(this);
        if(confirm("确定删除该条购物车吗？")){
            $.ajax({
                type:"POST",
                url:"/deleteShoppingCarById",
                data:{sId:obj.attr("sid")},
                dataType:"json",
                success:function (data) {
                    if(data.result=="true"){
                        alert("删除成功！");
                        obj.parents("tr").remove();
                    }else{
                        alert("删除失败！");
                    }
                },error:function (data) {
                    alert("删除出错！");
                }
            });
        }
    });
});