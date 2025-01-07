// 登录功能实现
document.addEventListener('DOMContentLoaded', function() {
    // 获取登录按钮和输入框
    const loginButton = document.querySelector('.button-login button');
    const userIdInput = document.querySelector('input[placeholder="请输入账号"]');
    const passwordInput = document.querySelector('input[type="password"]');

    // 添加登录按钮点击事件
    loginButton.addEventListener('click', function() {
        const userId = userIdInput.value.trim();
        const password = passwordInput.value.trim();

        // 验证输入
        if (!userId || !password) {
            alert('请输入账号和密码');
            return;
        }

        // 调用登录接口
        axios.post(config.baseUrl + '/users/login', {
            userid: userId,
            password: password
        })
        .then(response => {
            if (response.data && response.data.userid) {
                // 登录成功，保存用户信息到localStorage
                localStorage.setItem('userId', response.data.userid);
                localStorage.setItem('userInfo', JSON.stringify(response.data));
                
                // 获取重定向URL
                const urlParams = new URLSearchParams(window.location.search);
                const redirectUrl = urlParams.get('redirect');
                
                // 如果有重定向URL则跳转到该页面，否则跳转到首页
                if (redirectUrl) {
                    window.location.href = decodeURIComponent(redirectUrl);
                } else {
                    window.location.href = 'index.html';
                }
            } else {
                alert('登录失败，请检查账号和密码');
            }
        })
        .catch(error => {
            console.error('登录失败:', error);
            alert('登录失败，请稍后重试');
        });
    });
}); 