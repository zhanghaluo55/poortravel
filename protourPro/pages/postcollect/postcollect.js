// pages/postlist/postlist.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
      posts: '',
      post: ''
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
    var posts=app.globalData.postcollect
    that.setData({
      posts: posts
    })
    
  },
  toDetails: function (e) {
    var id = e.currentTarget.dataset.src;
    var that = this;
    console.log("id:"+id)
    wx.request({
      url: 'https://www.zzhcool.top/post/service/v1/posts/' + id,
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)//打印到控制台
        var post = JSON.stringify(res.data);
        if (post == null) {
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
        wx.navigateTo({
          url: '../article/article?post=' + encodeURIComponent(post)   //页面路径
        })
      }
    })

  }
})