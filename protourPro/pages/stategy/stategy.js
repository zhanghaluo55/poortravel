// pages/stategy/stategy.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    attractions: '',
    attraction: '',
    province: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    
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
    if(app.globalData.provchange==true){
      this.setData({
        attractions: null
      })
    }
    app.globalData.provchange=false;

    var that = this;
    var province = app.globalData.province
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
        console.log("attraction result:"+attractions.result)
      }
    })
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
  toDetails: function (e){
    var id=e.currentTarget.dataset.src;
    var that = this;
    wx.request({
      url: 'https://www.zzhcool.top/attraction/service/v1/attractions/'+id,
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)//打印到控制台
        var attraction = res.data;
        if (attraction == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          that.setData({
            attraction: attraction
          })
        }
        // wx.removeStorageSync('attraction')
        // wx.setStorageSync('attraction', attraction)
        wx.navigateTo({
          url: '../details/details?attraction=' + JSON.stringify(attraction)   //页面路径
        }) 
      }
      
    })
    
    
   
  }
})