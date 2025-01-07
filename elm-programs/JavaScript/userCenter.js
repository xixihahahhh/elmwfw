window.onload = function() {
    // 检查登录状态
    const userId = localStorage.getItem('userId');
    if (!userId) {
        alert('请先登录');
        window.location.href = 'login.html';
        return;
    }

    // 获取用户信息
    axios.get(config.baseUrl + '/users/' + userId)
        .then(response => {
            console.log('返回数据:', response.data);
            if (response.data) {
                document.getElementById('username').innerText = response.data.username;
            }
        })
        .catch(error => {
            console.error('获取用户信息失败:', error);
        });

    // 退出登录按钮点击事件
    document.querySelector('.logout-btn').onclick = function() {
        if (confirm('确定要退出登录吗？')) {
            // 清除本地存储的用户信息
            localStorage.removeItem('userId');
            // 跳转到登录页面
            window.location.href = 'login.html';
        }
    }
} 