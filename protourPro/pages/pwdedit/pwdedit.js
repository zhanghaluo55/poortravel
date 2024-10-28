//index.js
//获取应用实例
const app = getApp()

Page({
  radioChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value)
  },
  formSubmit: function (e) {
    // var user = new Object();
    var value = e.detail.value;
    var that = this;
    var id = app.globalData.user.id
    value.id = id
    wx.request({
      url: 'https://www.zzhcool.top/admin/service/v1/admins/'+id,
      data: {
        userJson: JSON.stringify(value)
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded" // 默认值
      },
      method: 'PUT',
      success: function (res) {
        var result = res.data.data;
        if (result == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          
        }
        wx.switchTab({
          url: '../user/user'  //页面路径
        })
      }
    })
  }
})
