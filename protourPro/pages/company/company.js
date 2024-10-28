// pages/company/company.js
Page({
  data: {
    disabled: true,
    btnstate: 'default',
    user: ''
  },

  accountblur:function (e) {
    var content = e.detail.value.trim();
    if (content != '') {
      this.setData({
        disabled: false,
        btnstate: 'primary'
      });
    } else {
      this.setData({
        disabled: true,
        btnstate: 'default'
      });
    }
  },
/* 
code
:
"6"
company
:
"3"
loginName
:
"1"
mobile
:
"5"
password
:
"2"
switch
:
false
userName
: */
  formSubmit: function(e) {
    // var user = new Object();
    var value = e.detail.value;
    var that=this;
    var json=new Object();
    json.username = value.username;
    json.password = value.password;
    json.email = value.email;
    json.nickname = value.nickname;
    json.airphone = value.airphone;

    wx.request({
      url: 'https://www.zzhcool.top/admin/service/v1/admins',
      data: {
        userJson: JSON.stringify(json)
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded" // 默认值
      },
      method: 'POST',
      success: function (res) {
        var user = res.data;
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
        wx.navigateTo({
          url: '../login/login'  //页面路径
        })
      }
    })

    /*wx.setStorageSync('user', user);
    wx.showToast({
      title: '注册成功',
      icon: 'success',
      duration: 1000,
      success: function() {
        wx.navigateTo({
          url: '../login/login'
        })
      }
    });*/
  }
})