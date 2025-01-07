window.onload = function() {
    // 获取用户ID
    const userId = localStorage.getItem('userId');
    
    if (!userId) {
        alert('请先登录');
        window.location.href = 'login.html?redirect=' + encodeURIComponent(window.location.href);
        return;
    }

    // 从URL获取商家ID
    const urlParams = new URLSearchParams(window.location.search);
    const businessId = urlParams.get('businessId');
    
    // 尝试从localStorage获取商家信息
    const businessInfoStr = localStorage.getItem('currentBusiness');
    
    let businessInfo = null;
    try {
        if (businessInfoStr) {
            businessInfo = JSON.parse(businessInfoStr);
            
            // 验证商家ID是否匹配
            if (businessInfo.businessid != businessId) {
                throw new Error('商家信息不匹配');
            }
        } else {
            throw new Error('未找到商家信息');
        }
    } catch (error) {
        // 尝试重新获取商家信息
        if (businessId) {
            axios.get(`${config.baseUrl}/getBusinessById/${businessId}`)
                .then(response => {
                    if (response.data) {
                        businessInfo = response.data;
                        localStorage.setItem('currentBusiness', JSON.stringify(businessInfo));
                        initializeOrderPage(businessInfo, businessId);
                    } else {
                        handleError('获取商家信息失败');
                    }
                })
                .catch(() => {
                    handleError('获取商家信息失败');
                });
            return;
        } else {
            handleError('商家信息不完整');
            return;
        }
    }

    initializeOrderPage(businessInfo, businessId);
    
    // 绑定支付按钮点击事件
    const payButton = document.getElementById('payButton');
    if (payButton) {
        payButton.addEventListener('click', createOrder);
    }
}

// 初始化订单页面
function initializeOrderPage(businessInfo, businessId) {
    // 更新商家信息
    updateBusinessInfo(businessInfo);
    // 获取购物车信息并显示
    getCartInfo(businessId);
}

// 处理错误情况
function handleError(message) {
    alert(message);
    window.location.href = 'index.html';
}

// 更新商家信息
function updateBusinessInfo(business) {
    // 更新商家名称
    const businessName = document.querySelector('h3');
    if (businessName) {
        businessName.textContent = business.businessname;
    }
}

// 获取购物车信息
function getCartInfo(businessId) {
    const userId = localStorage.getItem('userId');
    axios.post(config.baseUrl + '/getCartInfo', {
        businessid: parseInt(businessId),
        userid: userId
    })
    .then(response => {
        if (response.data && response.data.length > 0) {
            updateOrderUI(response.data);
        } else {
            alert('购物车为空');
            window.location.href = `businessInfo.html?businessId=${businessId}`;
        }
    })
    .catch(error => {
        alert('获取购物车信息失败');
    });
}

// 更新订单UI
function updateOrderUI(cartItems) {
    // 过滤掉数量为0的商品
    const validItems = cartItems.filter(item => item.quantity > 0);
    
    // 如果没有有效商品，返回到商家页面
    if (validItems.length === 0) {
        const urlParams = new URLSearchParams(window.location.search);
        const businessId = urlParams.get('businessId');
        alert('购物车为空');
        window.location.href = `businessInfo.html?businessId=${businessId}`;
        return;
    }
    
    // 更新商品列表
    const orderDetailed = document.querySelector('.order-detailed');
    orderDetailed.innerHTML = '';
    
    let total = 0;
    validItems.forEach(item => {
        const li = document.createElement('li');
        li.innerHTML = `
            <div class="order-detailed-left">
                <img src="${item.food.foodimg || 'assets/img/sp01.png'}">
                <span>${item.food.foodname} x ${item.quantity}</span>
            </div>
            <span>&#165;${(item.food.foodprice * item.quantity).toFixed(2)}</span>
        `;
        orderDetailed.appendChild(li);
        total += item.food.foodprice * item.quantity;
    });

    // 更新总价
    const totalPrice = document.querySelector('.total-left span');
    totalPrice.textContent = `￥${(total + 3).toFixed(2)}`; // 加上配送费
}

// 创建订单
function createOrder() {
    const userId = localStorage.getItem('userId');
    const urlParams = new URLSearchParams(window.location.search);
    const businessId = urlParams.get('businessId');
    const businessInfo = JSON.parse(localStorage.getItem('currentBusiness'));

    if (!businessId || !businessInfo) {
        alert('商家信息不完整');
        return;
    }

    // 获取购物车信息
    axios.post(config.baseUrl + '/getCartInfo', {
        businessid: parseInt(businessId),
        userid: userId
    })
    .then(response => {
        if (response.data && response.data.length > 0) {
            // 过滤掉数量为0的商品
            const validItems = response.data.filter(item => item.quantity > 0);
            
            if (validItems.length === 0) {
                alert('购物车为空');
                window.location.href = `businessInfo.html?businessId=${businessId}`;
                return;
            }

            const total = validItems.reduce((sum, item) => sum + (item.food.foodprice * item.quantity), 0) + 3; // 加上配送费

            // 创建订单
            const order = {
                userid: userId,
                businessid: parseInt(businessId),
                ordertotal: total,
                orderstate: 0
            };

            // 调用创建订单接口
            axios.post(config.baseUrl + '/orders/add', order)
            .then(response => {
                const orderId = response.data;
                if (orderId > 0) {
                    // 创建订单详情，只包含有效商品
                    const orderDetails = validItems.map(item => ({
                        orderid: orderId,
                        foodid: item.foodid,
                        quantity: item.quantity
                    }));

                    // 调用创建订单详情接口
                    axios.post(config.baseUrl + '/orders/addList', orderDetails)
                    .then(response => {
                        if (response.data) {
                            // 清空购物车
                            axios.post(config.baseUrl + '/cart/delAll', {
                                businessid: parseInt(businessId),
                                userid: userId
                            })
                            .then(() => {
                                // 清除临时存储的商家信息
                                localStorage.removeItem('currentBusiness');
                                // 跳转到支付页面
                                window.location.href = `payment.html?orderId=${orderId}`;
                            })
                            .catch(() => {
                                // 即使清空购物车失败，也继续跳转到支付页面
                                localStorage.removeItem('currentBusiness');
                                window.location.href = `payment.html?orderId=${orderId}`;
                            });
                        } else {
                            alert('创建订单详情失败');
                        }
                    })
                    .catch(() => {
                        alert('创建订单详情失败');
                    });
                } else {
                    alert('创建订单失败');
                }
            })
            .catch(() => {
                alert('创建订单失败');
            });
        } else {
            alert('购物车为空');
            window.location.href = `businessInfo.html?businessId=${businessId}`;
        }
    })
    .catch(() => {
        alert('获取购物车信息失败');
    });
} 