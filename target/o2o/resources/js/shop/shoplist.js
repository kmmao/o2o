$(function () {
    getlist();
    function getlist() {
        $.ajax({
            url: "/o2o/shopadmin/getshoplist",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    handleList(data.shopList);
                    handleUser(data.user);
                }
            }
        });
    }

    function handleUser(data) {
        $("#user-name").text(data.name);
    }

    function handleList(data) {
        var html = '';
        data.map(function (item,index) {
            html+='<div class="row row-shop"><div class="col-40">'
                +item.shopName+'</div><div class="col-40">'
                +enable(item.enableStatus)+'</div><div class="col-20">'
                +goShopId(item.enableStatus,item.shopId)+'</div></div>';
        });
        $(".shop-wrap").html(html);
    }

    function enable(status) {
        if (status == 0) {
            return "审核中";
        }else if (status == -1) {
            return "店铺非法";
        } else if (status==1){
            return "审核通过";
        }
    }

    function goShopId(status, id) {
        if (status == 1) {
            return '<a href="/o2o/shopadmin/shopmanagement?shopId='+id+'">进入</a>';
        }else {
            return '';
        }
    }

});