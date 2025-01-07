window.onload = function() {
    // 获取注册按钮并添加点击事件
    document.querySelector('.button-login button').onclick = function(e) {
        e.preventDefault();
        
        // 获取表单数据
        const userId = document.querySelector('input[placeholder="请输入账号"]').value;
        const password = document.querySelector('input[placeholder="输入密码"]').value;
        const confirmPassword = document.querySelector('input[placeholder="确认密码"]').value;
        const usersex = document.querySelector('input[name="sex"]:checked').value === '男' ? 1 : 0;
        
        // 表单验证
        if (!userId || !password || !confirmPassword) {
            alert('请填写完整信息');
            return;
        }
        
        if (password !== confirmPassword) {
            alert('两次输入的密码不一致');
            return;
        }
        
        // 构造请求数据
        const user = {
            userid: userId,
            password: password,
            username: userId,
            usersex: usersex
        };
        
        // 发送注册请求
        axios.post(config.baseUrl + '/users/register', user)
            .then(response => {
                if (response.data === true) {
                    alert('注册成功！');
                    window.location.href = 'login.html';
                } else {
                    alert('注册失败，该账号可能已存在');
                }
            })
            .catch(error => {
                console.error('注册失败:', error);
                alert('注册失败，请稍后重试');
            });
            
        return false; // 阻止默认跳转
    }
} 