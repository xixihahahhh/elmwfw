// 创建样式
function createFooterStyle() {
    const style = document.createElement('style');
    style.textContent = `
        .footer {
            width: 100%;
            height: 50px;
            background-color: #fff;
            position: fixed;
            left: 0;
            bottom: 0;
            display: flex;
            justify-content: space-around;
            align-items: center;
            border-top: 1px solid #e4e4e4;
        }

        .footer div {
            display: flex;
            flex-direction: column;
            align-items: center;
            color: #666;
            font-size: 14px;
        }

        .footer div.active {
            color: #0097ff;
        }

        .footer div i {
            font-size: 22px;
            margin-bottom: 3px;
        }
    `;
    document.head.appendChild(style);
}

// 创建并插入底部导航栏
function createFooter() {
    // 先创建样式
    createFooterStyle();
    
    // 获取当前页面的路径
    const currentPath = window.location.pathname;
    const pageName = currentPath.split('/').pop().replace('.html', '');
    
    // 根据页面设置激活状态
    let activeClass = '';
    if (pageName === 'index') {
        activeClass = 'active';
    }
    
    const footerHtml = `
        <footer class="footer">
            <div onclick="location.href='index.html'" class="${pageName === 'index' ? 'active' : ''}">
                <i class="i material-symbols:house-outline"></i>
                <span>首页</span>
            </div>
            <div class="${pageName === 'discover' ? 'active' : ''}">
                <i class="i material-symbols:location-on-outline"></i>
                <span>发现</span>
            </div>
            <div onclick="location.href='orderList.html'" class="${pageName === 'orderList' || pageName === 'payment' ? 'active' : ''}">
                <i class="i material-symbols:apk-document-outline"></i>
                <span>订单</span>
            </div>
            <div onclick="checkLoginAndRedirect()" class="${pageName === 'userCenter' ? 'active' : ''}">
                <i class="i material-symbols:deployed-code-account-outline-rounded"></i>
                <span>我的</span>
            </div>
        </footer>
    `;

    // 在页面加载完成后插入底部导航栏
    document.addEventListener('DOMContentLoaded', function() {
        document.querySelector('.wrapper').insertAdjacentHTML('beforeend', footerHtml);
    });
}

// 检查登录状态并跳转
function checkLoginAndRedirect() {
    const userId = localStorage.getItem('userId');
    if (userId) {
        location.href = 'userCenter.html';
    } else {
        location.href = 'login.html';
    }
}

// 导出函数
window.createFooter = createFooter;
window.checkLoginAndRedirect = checkLoginAndRedirect; 