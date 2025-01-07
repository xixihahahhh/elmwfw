window.onload = function() {
    // 从URL中获取商家ID
    const urlParams = new URLSearchParams(window.location.search);
    const businessId = urlParams.get('businessId');
    console.log('页面加载时的商家ID:', businessId);
    
    if (businessId) {
        // 获取商家详情
        getBusinessInfo(businessId);
        // 获取购物车信息
        getCartInfo(businessId);
        // 初始化购物车按钮
        initCartButtons(businessId);
    } else {
        console.error('未找到商家ID');
        alert('商家信息不完整');
        window.location.href = 'index.html';
    }
}

// 初始化购物车按钮
function initCartButtons(businessId) {
    const cartRightItem = document.querySelector('.cart-right-item');
    if (cartRightItem) {
        cartRightItem.onclick = () => {
            console.log('点击去结算按钮');
            if (businessId) {
                // 获取商家详情后再跳转
                axios.get(`${config.baseUrl}/getBusinessById/${businessId}`)
                    .then(response => {
                        console.log('获取到的商家信息:', response.data);
                        if (response.data) {
                            // 将商家信息存储到localStorage
                            localStorage.setItem('currentBusiness', JSON.stringify(response.data));
                            console.log('已存储商家信息到localStorage');
                            // 确保localStorage中的数据已经保存
                            const savedData = localStorage.getItem('currentBusiness');
                            if (savedData) {
                                console.log('验证localStorage中的数据:', savedData);
                                // 跳转到订单页面，确保使用正确的businessId
                                window.location.href = `order.html?businessId=${businessId}`;
                            } else {
                                console.error('存储商家信息失败');
                                alert('存储商家信息失败，请重试');
                            }
                        } else {
                            console.error('商家信息为空');
                            alert('获取商家信息失败');
                        }
                    })
                    .catch(error => {
                        console.error('获取商家信息失败:', error);
                        alert('获取商家信息失败，请重试');
                    });
            } else {
                console.error('businessId为空');
                alert('商家信息不完整');
            }
        };
    }
}

// 获取商家详情数据
function getBusinessInfo(businessId) {
    // 显示加载状态
    showLoading();
    
    axios.get(`${config.baseUrl}/getBusinessById/${businessId}`)
        .then(response => {
            console.log('Business info response:', response.data);
            if (response.data) {
                renderBusinessInfo(response.data);
                // 检查foodList是否存在
                if (response.data.foodList) {
                    console.log('Food list:', response.data.foodList);
                    processFoodCategories(response.data.foodList);
                } else {
                    console.error('No foodList in response');
                    showError('未找到商家的食品信息');
                }
            }
        })
        .catch(error => {
            console.error('获取商家详情失败:', error);
            showError('获取商家信息失败，请重试');
        })
        .finally(() => {
            hideLoading();
        });
}

// 获取食品分类
function getFoodCategories(foodList) {
    axios.get(config.baseUrl + '/food/categories')
        .then(response => {
            if (response.data) {
                // 过滤出当前商家拥有的分类
                const availableCategories = response.data.filter(category => 
                    foodList.some(food => food.categoryid === category.categoryid)
                );
                renderFoodCategories(availableCategories, foodList);
            }
        })
        .catch(error => {
            console.error('获取食品分类失败:', error);
            // 如果获取分类失败，使用本地分类数据作为备选
            processFoodCategories(foodList);
        });
}

// 渲染食品分类
function renderFoodCategories(categories, foodList) {
    const categoryList = document.querySelector('.category-list');
    if (categoryList) {
        categoryList.innerHTML = categories.map((category, index) => `
            <li class="${index === 0 ? 'active' : ''}" 
                data-category="${category.categoryid}"
                style="opacity: 0; transform: translateY(-10px);">
                <img src="${category.categoryimg || 'assets/img/default_category.png'}" 
                     alt="${category.categoryname}"
                     class="category-icon"/>
                <span>${category.categoryname}</span>
            </li>
        `).join('');

        // 添加动画效果
        categoryList.querySelectorAll('li').forEach((li, index) => {
            setTimeout(() => {
                li.style.opacity = '1';
                li.style.transform = 'translateY(0)';
            }, index * 100);
        });

        // 添加分类点击事件
        categoryList.querySelectorAll('li').forEach(li => {
            li.addEventListener('click', () => {
                // 更新active状态
                categoryList.querySelectorAll('li').forEach(item => {
                    item.classList.remove('active');
                    item.style.transform = 'scale(1)';
                });
                li.classList.add('active');
                li.style.transform = 'scale(1.05)';
                
                // 过滤显示对应分类的食品
                const categoryId = li.dataset.category;
                filterFoodsByCategory(foodList, categoryId);
                
                // 滚动到分类位置
                li.scrollIntoView({ behavior: 'smooth', block: 'nearest', inline: 'center' });
            });
        });

        // 默认显示第一个分类的食品
        if (categories.length > 0) {
            filterFoodsByCategory(foodList, categories[0].categoryid);
        }
    }
}

// 显示加载状态
function showLoading() {
    const loading = document.createElement('div');
    loading.className = 'loading-overlay';
    loading.innerHTML = `
        <div class="loading-spinner"></div>
        <p>加载中...</p>
    `;
    document.body.appendChild(loading);
}

// 隐藏加载状态
function hideLoading() {
    const loading = document.querySelector('.loading-overlay');
    if (loading) {
        loading.remove();
    }
}

// 显示错误信息
function showError(message) {
    const error = document.createElement('div');
    error.className = 'error-message';
    error.textContent = message;
    document.body.appendChild(error);
    setTimeout(() => error.remove(), 3000);
}

// 处理食品分类
function processFoodCategories(foodList) {
    // 获取所有食品的分类
    const categories = [...new Set(foodList.map(food => food.categoryid))].filter(Boolean);
    console.log('Unique categories:', categories);
    
    // 渲染分类列表
    const categoryList = document.querySelector('.category-list');
    if (categoryList) {
        categoryList.innerHTML = categories.map((categoryId, index) => `
            <li class="${index === 0 ? 'active' : ''}" data-category="${categoryId}">
                ${getCategoryName(categoryId)}
            </li>
        `).join('');

        // 添加分类点击事件
        categoryList.querySelectorAll('li').forEach(li => {
            li.addEventListener('click', () => {
                // 更新active状态
                categoryList.querySelectorAll('li').forEach(item => item.classList.remove('active'));
                li.classList.add('active');
                // 过滤显示对应分类的食品
                filterFoodsByCategory(foodList, li.dataset.category);
            });
        });

        // 默认显示第一个分类的食品
        if (categories.length > 0) {
            filterFoodsByCategory(foodList, categories[0]);
        }
    }
}

// 获取分类名称
function getCategoryName(categoryId) {
    // 从数据库中获取的分类映射
    const categoryNames = {
        1: '水饺',
        2: '蒸饺',
        3: '炒菜',
        4: '小吃'
    };
    return categoryNames[categoryId] || '其他';
}

// 根据分类过滤食品
function filterFoodsByCategory(foodList, categoryId) {
    console.log('Filtering foods for category:', categoryId);
    console.log('Available foods:', foodList);
    
    // 确保foodList是数组
    if (!Array.isArray(foodList)) {
        console.error('foodList is not an array:', foodList);
        return;
    }
    
    const filteredFoods = foodList.filter(food => {
        console.log('Food:', food);
        console.log('Comparing food category:', food.categoryid, 'with selected category:', categoryId);
        return Number(food.categoryid) === Number(categoryId);
    });
    
    console.log('Filtered foods:', filteredFoods);
    renderFoodList(filteredFoods);
}

// 渲染商家详情
function renderBusinessInfo(business) {
    // 更新商家logo
    const logoImg = document.querySelector('.business-logo img');
    if (logoImg) {
        logoImg.src = business.businessimg || 'assets/img/sj01.png';
    }

    // 更新商家信息
    const businessInfo = document.querySelector('.business-info');
    if (businessInfo) {
        businessInfo.innerHTML = `
            <h1>${business.businessname}</h1>
            <span>&#165;${business.starprice}起送 &#165;${business.deliveryprice}配送</span>
            <span>${business.businessexplain || ''}</span>
        `;
    }
}

// 渲染食品列表
function renderFoodList(foodList) {
    const foodContainer = document.querySelector('.food');
    if (foodContainer) {
        if (foodList.length === 0) {
            foodContainer.innerHTML = `
                <li class="no-food">
                    <p>该分类暂无商品</p>
                </li>
            `;
            return;
        }

        foodContainer.innerHTML = foodList.map(food => `
            <li class="food-item">
                <div class="food-left">
                    <img src="${food.foodimg || 'assets/img/default-food.png'}" alt="${food.foodname}">
                    <div class="food-info">
                        <h3>${food.foodname}</h3>
                        <p class="food-desc">${food.foodexplain || ''}</p>
                        <p class="food-price">￥${food.foodprice}</p>
                    </div>
                </div>
                <div class="food-right" data-foodid="${food.foodid}">
                    <div class="minus-btn" style="display: none;" onclick="updateCart('minus', ${food.foodid})">-</div>
                    <p><span>0</span></p>
                    <div class="add-btn" onclick="updateCart('add', ${food.foodid})">+</div>
                </div>
            </li>
        `).join('');

        // 初始化购物车数量显示
        const businessId = new URLSearchParams(window.location.search).get('businessId');
        getCartInfo(businessId);
    }
}

// 获取购物车信息
function getCartInfo(businessId) {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        console.warn('用户未登录');
        return;
    }

    axios.post(config.baseUrl + '/getCartInfo', {
        businessid: parseInt(businessId),
        userid: userId
    })
    .then(response => {
        if (response.data) {
            updateCartUI(response.data);
        }
    })
    .catch(error => {
        console.error('获取购物车信息失败:', error);
    });
}

// 更新购物车
function updateCart(action, foodId) {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        alert('请先登录');
        window.location.href = 'login.html?redirect=' + encodeURIComponent(window.location.href);
        return;
    }

    const urlParams = new URLSearchParams(window.location.search);
    const businessId = urlParams.get('businessId');

    const url = action === 'add' ? '/updateAddCart' : '/updateMinusCart';
    
    axios.post(`${config.baseUrl}${url}`, {
        businessid: parseInt(businessId),
        foodid: parseInt(foodId),
        userid: userId
    })
    .then(response => {
        if (response.data) {
            // 更新成功后重新获取购物车信息
            getCartInfo(businessId);
        }
    })
    .catch(error => {
        console.error('更新购物车失败:', error);
        alert('更新购物车失败，请重试');
    });
}

// 更新购物车UI
function updateCartUI(cartItems) {
    // 获取商家ID
    const urlParams = new URLSearchParams(window.location.search);
    const businessId = urlParams.get('businessId');

    // 更新购物车数量
    const cartQuantity = document.querySelector('.cart-quantity');
    if (cartQuantity) {
        const totalQuantity = cartItems.reduce((sum, item) => sum + item.quantity, 0);
        cartQuantity.textContent = totalQuantity;
        cartQuantity.style.display = totalQuantity > 0 ? 'flex' : 'none';
    }

    // 更新购物车总价
    const cartPrice = document.querySelector('.cart-left-info span:first-child');
    if (cartPrice) {
        const totalPrice = cartItems.reduce((sum, item) => sum + (item.food.foodprice * item.quantity), 0);
        cartPrice.textContent = `￥${totalPrice.toFixed(2)}`;
    }

    // 更新食品项的数量显示
    cartItems.forEach(item => {
        const foodItem = document.querySelector(`li [data-foodid="${item.foodid}"]`);
        if (foodItem) {
            const quantitySpan = foodItem.querySelector('p span');
            const minusBtn = foodItem.querySelector('.minus-btn');
            if (quantitySpan) {
                quantitySpan.textContent = item.quantity;
            }
            if (minusBtn) {
                minusBtn.style.display = item.quantity > 0 ? 'flex' : 'none';
            }
        }
    });

    // 更新结算按钮状态
    const cartRightItem = document.querySelector('.cart-right-item');
    const businessInfo = document.querySelector('.business-info span:first-child');
    if (cartRightItem && businessInfo) {
        const totalPrice = cartItems.reduce((sum, item) => sum + (item.food.foodprice * item.quantity), 0);
        const minPrice = parseFloat(businessInfo.textContent.match(/￥(\d+)/)[1]);
        
        if (totalPrice >= minPrice) {
            cartRightItem.textContent = '去结算';
            cartRightItem.style.backgroundColor = '#38CA73';
        } else {
            cartRightItem.textContent = `￥${minPrice}起送`;
            cartRightItem.style.backgroundColor = '#535356';
        }
    }
} 