// pages/position/position.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    positions1: ["甘肃","陕西","西藏","四川","云南","河北"],
    positions2: ["江西","山西","内蒙古","新疆","贵州"],
    province: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  oneChange(event) {
    this.setData({
      'oneChecked': event.detail.checked
    })
  },
  onChange(event) {
    const detail = event.detail;
    this.setData({
      ['tags[' + event.detail.name + '].checked']: detail.checked
    })

  },
  toDetails: function(e){
    var that = this;
    var province = e.currentTarget.dataset.province;
    console.info("province:"+province)
    if(app.globalData.province!= province){
      app.globalData.provchange=true;
    }
    
    app.globalData.province=province
    that.setData({
      province: province
    })
    wx.request({
      url: 'https://www.zzhcool.top/attraction/service/v1/attractions/searchroute',
      method: 'GET',
      data: {
        route: province
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        // console.log(res.data)//打印到控制台
        var attractions = res.data;
        if (attractions == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          that.setData({
            attractions: attractions
          })
        }
        wx.switchTab({
          url: '../stategy/stategy'
        })
      }
    })
  }
})