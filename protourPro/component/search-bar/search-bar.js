// pages/component/search-bar.js
const app=getApp()
Component({
  /**
   * 组件的属性列表
   */
  properties: {
   
  },

  /**
   * 组件的初始数据
   */
  data: {
  },

  /**
   * 组件的方法列表
   */
  methods: {    
    search: function (event) {
      console.log(event.detail.value);

      var that = this;
      var province = event.detail.value;
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
  },
})
