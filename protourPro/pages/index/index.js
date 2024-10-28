//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    swiperList: ["http://111.230.171.37:8888/group1/M00/00/01/rBAADl6HXiKAa3K0AAKR3a9vOIE375.jpg",
          "http://111.230.171.37:8888/group1/M00/00/01/rBAADl6HX-uAXYy7AAyaVOkuzLk771.png",
          "http://111.230.171.37:8888/group1/M00/00/01/rBAADl6HX7qAfEA1AADDT7Ljpg8956.jpg",
          "http://111.230.171.37:8888/group1/M00/00/01/rBAADl6HXdiAPQW7AABSB_VGwjo126.jpg",
          "http://111.230.171.37:8888/group1/M00/00/01/rBAADl6HX92AfYVsAASvyAI60jQ937.jpg",],
      aaa: "",
      province: ''
  },
  onLoad: function () {

  },
  onShow: function(){
    var that = this
    var province = app.globalData.province;
    that.setData({
      province: province
    })
    console.info("province:" + province)
  },
  bindButtonTap: function () {
    var that = this
    wx.chooseVideo({
      sourceType: ['album'],
      compressed: true,
      success: function (res) {
        that.setData({
          src: res.tempFilePath
        })
        that.setData({
          aaa: res
        })

      }
    })
  },
  toDetails: function (e) {
    var that = this;
    var province = e.currentTarget.dataset.province;
    console.info("province:" + province)
    if(app.globalData.province!= province){
      app.globalData.provchange=true;
    }

    app.globalData.province = province
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
          url: '../stategy/stategy',
        })
      }
    })
  }

})
