window.onload = function() {
    // 获取用户ID
    const userId = localStorage.getItem('userId');
    if (!userId) {
        alert('请先登录');
        window.location.href = 'login.html?redirect=' + encodeURIComponent(window.location.href);
        return;
    }

    // 获取未支付订单
    axios.get(`${config.baseUrl}/orders/getAll?userid=${userId}&orderstate=0`)
        .then(response => {
            if (response.data) {
                updateUnpaidOrdersUI(response.data);
            }
        })
        .catch(error => {
            console.error('获取未支付订单失败:', error);
        });

    // 获取已支付订单
    axios.get(`${config.baseUrl}/orders/getAll?userid=${userId}&orderstate=1`)
        .then(response => {
            if (response.data) {
                updatePaidOrdersUI(response.data);
            }
        })
        .catch(error => {
            console.error('获取已支付订单失败:', error);
        });
}

// 更新未支付订单UI
function updateUnpaidOrdersUI(orders) {
    const container = document.querySelector('.unpaid-orders');
    container.innerHTML = orders.map(order => `
        <div class="order-item">
            <div class="order-info">
                <span>
                    ${order.business.businessname}
                    <i class="i material-symbols:arrow-drop-down"></i>
                </span>
                <div class="order-info-right">
                    <span>&#165;${order.ordertotal}</span>
                    <div class="order-info-right-icon" onclick="location.href='payment.html?orderId=${order.orderid}'">去支付</div>
                    <div class="delete-btn" onclick="event.stopPropagation(); deleteOrder(${order.orderid})">
                        <i class="material-symbols-outlined">delete</i>
                    </div>
                </div>
            </div>
            <div class="order-detail">
                ${order.detailList.map(detail => `
                    <div>
                        <span>${detail.food.foodname} x ${detail.quantity}</span>
                        <span>&#165;${(detail.food.foodprice * detail.quantity).toFixed(2)}</span>
                    </div>
                `).join('')}
                <div>
                    <span>配送费</span>
                    <span>&#165;${order.business.deliveryprice}</span>
                </div>
            </div>
        </div>
    `).join('');

    // 绑定展开/收起事件
    bindToggleEvents();
}

// 更新已支付订单UI
function updatePaidOrdersUI(orders) {
    const container = document.querySelector('.paid-orders');
    container.innerHTML = orders.map(order => `
        <div class="order-item">
            <div class="order-info">
                <span>
                    ${order.business.businessname}
                    <i class="i material-symbols:arrow-drop-down"></i>
                </span>
                <div class="order-info-right">
                    <span>&#165;${order.ordertotal}</span>
                    <div class="delete-btn" onclick="event.stopPropagation(); deleteOrder(${order.orderid})">
                        <i class="material-symbols-outlined">delete</i>
                    </div>
                </div>
            </div>
            <div class="order-detail">
                ${order.detailList.map(detail => `
                    <div>
                        <span>${detail.food.foodname} x ${detail.quantity}</span>
                        <span>&#165;${(detail.food.foodprice * detail.quantity).toFixed(2)}</span>
                    </div>
                `).join('')}
                <div>
                    <span>配送费</span>
                    <span>&#165;${order.business.deliveryprice}</span>
                </div>
            </div>
        </div>
    `).join('');

    // 绑定展开/收起事件
    bindToggleEvents();
}

// 绑定展开/收起事件
function bindToggleEvents() {
    const orderInfos = document.querySelectorAll('.order-info');
    console.log('找到的订单数量:', orderInfos.length);  // 添加调试日志
    
    orderInfos.forEach((info, index) => {
        // 移除可能存在的旧事件监听器
        const clonedInfo = info.cloneNode(true);
        info.parentNode.replaceChild(clonedInfo, info);
        
        clonedInfo.addEventListener('click', function(e) {
            // 阻止删除按钮的点击事件冒泡
            if (e.target.closest('.delete-btn')) {
                return;
            }
            
            const detail = this.nextElementSibling;
            const icon = this.querySelector('i');
            console.log('点击订单:', index, '当前显示状态:', detail.style.display);  // 添加调试日志
            
            if (detail.style.display === 'none' || !detail.style.display) {
                detail.style.display = 'block';
                icon.style.transform = 'rotate(180deg)';
            } else {
                detail.style.display = 'none';
                icon.style.transform = 'rotate(0deg)';
            }
        });
    });
}

// 删除订单
function deleteOrder(orderId) {
    if (confirm('确定要删除这个订单吗？')) {
        axios.delete(`${config.baseUrl}/orders/delete/${orderId}`)
            .then(response => {
                if (response.data) {
                    // 刷新页面显示
                    window.location.reload();
                } else {
                    alert('删除订单失败');
                }
            })
            .catch(error => {
                console.error('删除订单失败:', error);
                alert('删除订单失败');
            });
    }
}