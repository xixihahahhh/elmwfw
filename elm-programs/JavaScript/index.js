/**
 * 1.使用哪一个事件
 *   使用onscroll事件，此事件移动端与pc端均有效
 * 2.如何获取滚动条位置
 *   获取pc端滚动条位置：document.documentElement.scrollTop
 *   获取移动端滚动条位置：document.body.scrollTop
 * 3.如何获取视口宽度
 *   document.documentElement.clientWidth,移动端与pc端均有效
 */
window.onload = function() {
    // 获取首页推荐商家列表
    getIndexBusiness();
    // 获取点餐分类列表
    getFoodCategories();
}

// 获取首页推荐商家数据
function getIndexBusiness() {
    axios.get(config.baseUrl + '/getIndexBusiness')
        .then(response => {
            if (response.data) {
                renderBusinessList(response.data);
            }
        })
        .catch(error => {
            console.error('获取推荐商家列表失败:', error);
        });
}

// 获取点餐分类数据
function getFoodCategories() {
    axios.get(config.baseUrl + '/food/categories')
        .then(response => {
            if (response.data) {
                renderFoodCategories(response.data);
            }
        })
        .catch(error => {
            console.error('获取点餐分类失败:', error);
        });
}

// 渲染点餐分类
function renderFoodCategories(categories) {
    const categoryContainer = document.querySelector('.food-category');
    categoryContainer.innerHTML = ''; // 清空现有内容
    
    categories.forEach(category => {
        const div = document.createElement('div');
        div.className = 'category-item';
        div.onclick = () => {
            location.href = `business_list.html?categoryId=${category.categoryid}`;
        };
        
        div.innerHTML = `
            <img src="${category.categoryimg}"/>
            <span>${category.categoryname}</span>
        `;
        
        categoryContainer.appendChild(div);
    });
}

// 渲染商家列表
function renderBusinessList(businessList) {
    const businessContainer = document.querySelector('.bussiness');
    businessContainer.innerHTML = ''; // 清空现有内容
    
    businessList.forEach(business => {
        const li = document.createElement('li');
        li.onclick = () => {
            location.href = `businessInfo.html?businessId=${business.businessid}`;
        };
        
        li.innerHTML = `
            <img src="${business.businessimg || 'assets/img/sj01.png'}"/>
            <div class="bussiness-info">
                <div class="bussiness-info-h">
                    <h3>${business.businessname}</h3>
                    <div class="bussiness-info-like">&#8226;</div>
                </div>
                <div class="bussiness-info-star">
                    <div class="bussiness-info-star-left">
                        <i class="i material-symbols-light:kid-star"></i>
                        <i class="i material-symbols-light:kid-star"></i>
                        <i class="i material-symbols-light:kid-star"></i>
                        <i class="i material-symbols-light:kid-star"></i>
                        <i class="i material-symbols-light:kid-star"></i>
                        <span>4.9 月售345单</span>
                    </div>
                    <div class="bussiness-info-star-right">
                        蜂鸟专送
                    </div>
                </div>
                <div class="bussiness-info-delivery">
                    <span>&#165;${business.starprice}起送 | &#165;${business.deliveryprice}配送</span>
                    <span>3.22km | 30分钟</span>
                </div>
                <div class="bussiness-info-explain">
                    <div>${business.businessexplain || ''}</div>
                </div>
                <div class="bussiness-info-promotion">
                    <div class="bussiness-info-promotion-left">
                        <div class="bussiness-info-promotion-left-incon">新</div>
                        <span>饿了么新用户首单立减9元</span>
                    </div>
                    <div class="bussiness-info-promotion-right">
                        <span>2个活动</span>
                        <i class="i material-symbols:arrow-drop-down"></i>
                    </div>
                </div>
            </div>
        `;
        
        businessContainer.appendChild(li);
    });
}