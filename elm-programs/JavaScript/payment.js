window.onload = function() {
    // 获取订单ID
    const urlParams = new URLSearchParams(window.location.search);
    const orderId = urlParams.get('orderId');
    
    if (!orderId) {
        alert('订单ID不存在');
        window.location.href = 'orderList.html';
        return;
    }

    // 获取订单信息
    axios.get(`${config.baseUrl}/orders/getOrder?orderid=${orderId}`)
        .then(response => {
            if (response.data) {
                updateOrderInfo(response.data, orderId);
            }
        })
        .catch(() => {
            alert('获取订单信息失败');
        });
}

// 更新订单信息显示
function updateOrderInfo(order, orderId) {
    // 更新商家名称和总价
    document.querySelector('.business-name').textContent = order.business.businessname;
    document.querySelector('.order-total').textContent = `￥${order.ordertotal}`;

    // 更新订单明细
    const detailBox = document.getElementById('detailedBox');
    let detailHtml = '';
    
    // 添加食品明细
    order.detailList.forEach(detail => {
        detailHtml += `
            <li>
                <span>${detail.food.foodname} x ${detail.quantity}</span>
                <span>￥${(detail.food.foodprice * detail.quantity).toFixed(2)}</span>
            </li>
        `;
    });

    // 添加配送费
    detailHtml += `
        <li>
            <span>配送费</span>
            <span>￥${order.business.deliveryprice.toFixed(2)}</span>
        </li>
    `;

    detailBox.innerHTML = detailHtml;

    // 添加点击事件，显示/隐藏订单明细
    const showButton = document.querySelector('.order-info-pay i');
    const detailedBox = document.getElementById('detailedBox');
    
    showButton.addEventListener('click', function() {
        if (detailedBox.style.display === 'none' || !detailedBox.style.display) {
            detailedBox.style.display = 'block';
            showButton.style.transform = 'rotate(180deg)';
        } else {
            detailedBox.style.display = 'none';
            showButton.style.transform = 'rotate(0deg)';
        }
    });

    // 添加支付按钮点击事件
    const payButton = document.getElementById('confirmPayButton');
    payButton.addEventListener('click', function() {
        if (confirm('确认支付该订单吗？')) {
            axios.post(`${config.baseUrl}/orders/edit/${orderId}`)
                .then(response => {
                    if (response.data === true) {
                        alert('支付成功！');
                        window.location.href = 'orderList.html';
                    } else {
                        alert('支付失败，请重试');
                    }
                })
                .catch(() => {
                    alert('支付失败，请稍后重试');
                });
        }
    });
}