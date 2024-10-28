// pages/postlist/postlist.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    attractions: '',
    attraction: ''
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
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

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
  onLoad: function () {
    // console.log('onLoad')
    // var that = this
    // requestData(that, mCurrentPage + 1);
  },
   onShow: function () {
    var that = this;
    var attractions=app.globalData.attrhistory
    console.log("attractions:"+attractions);
    that.setData({
      attractions: attractions
    })
    
  },
  toDetails: function (e) {
    var id = e.currentTarget.dataset.src;
    var that = this;
    console.log("id:"+id)
    wx.request({
      url: 'https://www.zzhcool.top/attraction/service/v1/attractions/' + id,
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
          // that.setData({
          //   post: post
          // })
        }
        console.log("success!")
        wx.navigateTo({
          url: '../details/details?attraction=' + JSON.stringify(attraction)   //页面路径
        })
      }
    })

  }
})