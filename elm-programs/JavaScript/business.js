window.onload = function() {
    // 获取商家列表
    getBusinessList();
}

// 获取商家列表数据
function getBusinessList() {
    axios.get(config.baseUrl + '/getBusinessList')
        .then(response => {
            if (response.data) {
                renderBusinessList(response.data);
            }
        })
        .catch(error => {
            console.error('获取商家列表失败:', error);
        });
}

// 渲染商家列表
function renderBusinessList(businessList) {
    const businessContainer = document.querySelector('.business');
    businessContainer.innerHTML = ''; // 清空现有内容
    
    businessList.forEach(business => {
        const li = document.createElement('li');
        li.onclick = () => {
            location.href = `businessInfo.html?businessId=${business.businessid}`;
        };
        
        li.innerHTML = `
            <div class="business-img">
                <img src="${business.businessimg || 'assets/img/sj01.png'}">
            </div>
            <div class="business-info">
                <h3>${business.businessname}</h3>
                <span>&#165;${business.starprice}起送 | &#165;${business.deliveryprice}配送</span>
                <span>${business.businessexplain || ''}</span>
            </div>
        `;
        
        businessContainer.appendChild(li);
    });
} 