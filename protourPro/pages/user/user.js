// pages/user/user.js
const app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("one")
    var that=this
    var user=app.globalData.user
    that.setData({
      user: user
    })
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
    console.log("tow")
    var that = this
    var user = app.globalData.user
    that.setData({
      user: user
    })
    if(user!=null){
      var id=app.globalData.user.id
      wx.request({
        url: 'https://www.zzhcool.top/post/service/v1/posts/collect/'+id,
        method: 'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          // console.log(res.data)//打印到控制台
          var posts = res.data;
          console.log(posts)
          if (posts == null) {
            var toastText = '数据获取失败';
            wx.showToast({
              title: toastText,
              icon: '',
              duration: 2000
            });
          } else {
            app.globalData.postcollect=posts
          }
        }
      })

      wx.request({
        url: 'https://www.zzhcool.top/attraction/service/v1/attractions/collect/'+id,
        method: 'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          // console.log(res.data)//打印到控制台
          var attractions = res.data;
          console.log("attractions:"+attractions)
          if (attractions == null) {
            var toastText = '数据获取失败';
            wx.showToast({
              title: toastText,
              icon: '',
              duration: 2000
            });
          } else {
            app.globalData.attrcollect=attractions
          }
        }
      })
    }
  },
  insertImage(e) {
    
    var that=this
    var id=e.currentTarget.dataset.id
    wx.chooseImage({
      count: 1,
      success: (res) => {
        const tempFilePaths=res.tempFilePaths;
        
        console.log("temppaths:" + tempFilePaths);
        wx.uploadFile({
          url: 'https://www.zzhcool.top/upload/service/upload',
          filePath: tempFilePaths[0],
          name: 'file',
          header: { "Content-Type": "multipart/form-data" },
          success: function (res) {
            var cur_data = JSON.parse(res.data) ;
            var fileName = cur_data.fileName
            console.log("filename:" + fileName);
    

            var files = new Object();
            files.path = fileName
            files.filename = '头像图片'
            wx.request({
              url: 'https://www.zzhcool.top/base/service/v1/files',
              method: 'POST',
              data: {
                fileJson: JSON.stringify(files)
              },
              header: {
                "Content-Type": "application/x-www-form-urlencoded" // 默认值
              },   
              success: function (res) {
                if (res != null) {
                  console.log("插入成功");
                }
              }
            })
            var user=new Object();
            user.id=id
            user.headImage=fileName

            wx.request({
              url: 'https://www.zzhcool.top/admin/service/v1/admins/'+id,
              data: {
                userJson: JSON.stringify(user)
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

                getCurrentPages()[getCurrentPages().length - 1].onShow()
              }
            })

          },
          fail: function (res) {
            console.log('上传失败');
          }

        })
      }
    });
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
  toRegister: function(){
    app.globalData.user=null;
    wx.removeStorageSync('user')
    wx.switchTab({
      url: '../user/user' //页面路径
    })
  }
})