//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    user: '',
    gender: 1
  },
  radioChange: function (e) {
    var that=this
    console.log('radio发生change事件，携带value值为：', e.detail.value)
    that.setData({
      gender: e.detail.value
    })
  },
  formSubmit: function (e) {
    var value = e.detail.value;
    var that = this;
    var id = app.globalData.user.id
    var gender=this.data.gender
    value.gender=gender
    value.id=id
    console.log(value)
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
        var result = res.data;
        console.log(result);
        if (result == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          
        }

        wx.request({
          url: 'https://www.zzhcool.top/admin/service/v1/admins/'+id,
          method: 'GET',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success: function (res) {
            var user1 = res.data.data
            app.globalData.user = user1;
          }
        })
        wx.switchTab({
          url: '../user/user'  //页面路径
        })
      }
    })
  },
  onShow: function () {
    var that = this
    var user = app.globalData.user
    that.setData({
      user: user
    })
  }
})
