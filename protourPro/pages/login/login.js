// pages/login/login.js
const app=getApp();
Page({
  data: {
    disabled: true,
    btnstate: 'default',
    account: '',
    password: '',
    user: ''
  },

  accountInput: function (event) {
    var content = event.detail.value.trim();
    if (content !== '') {
      this.setData({
        disabled: false, 
        btnstate: 'primary', 
        account: content
      });
    } else {
      this.setData({
        disabled: true,
        btnstate: 'default'
      });
    }
  },
  formSubmit: function (e) {
    // var user = new Object();
    var value = e.detail.value;
    var that = this;

    var username = value.username;
    var password = value.password;
    var user = null
    wx.request({
      url: 'https://www.zzhcool.top/admin/service/v1/admins/login',
      data: {
        username: username,
        password: password
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded" // 默认值
      },
      method: 'POST',
      success: function (res) {
        user = res.data.data;
        app.globalData.user=user;
        console.log(user);
        if (user == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          that.setData({
            user: user
          })
        }
        wx.removeStorageSync('user')
        wx.setStorageSync('user', user)
        wx.switchTab({
          url: '../user/user' //页面路径
        })
      }
    })
  },
  pwdBlur: function(e) {
    var password = e.detail.value;
    if (password != '') {
      this.setData({
        password: password
      });
    }
  },

})