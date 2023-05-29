function confirmed(orderNo){
    console.log("1111111111");
    console.log(orderNo);
    $.ajax({
        url:'updateStatus',
        type: 'post',
        data: {"order_no" : orderNo},
        success: function(){
            alert("확정 되었습니다")
            location.href ="order";
        },
        error: function(){
            alert("에러");
        }
    });
}

function saveOrder(){
    //수주일
    let orderDate = $("#orderDate").val();
    //제품ID
    let select = document.getElementById("selectedBox");
    let productId = select.options[select.selectedIndex].value;
    //거래처
    let companySelect = document.getElementById("company");
    let company = companySelect.options[companySelect.selectedIndex].value;
    //개수
    let orderQty = $("#orderQuantity").val();


    $.ajax({
        url:'/mes/addOrder',
        type: 'post',
        data:{
            "orderDateStr" : orderDate,
            "companyId" : company,
            "productId" : productId,
            "orderQty" : orderQty
        },
        success: function (){
            location.href = "order";
        },
        error: function (){
            alert("에러");
        }

    })

}