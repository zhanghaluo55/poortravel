//index.js
//获取应用实例
const app = getApp();
let wechat = require("../../utils/wechat");
let amap = require("../../utils/amap");
let markersData = [];
Page({
  data: {
    isShowToast: false,
    bannerCurrentIndex: 1,
    attraction: '',
    description: '',
    files: '',
    isCollect: false,
    array: [
      {
        url: "../images/tag_source.png"
      },
      {
        url: "../images/tag_allin.png"
      },
      {
        url: "../images/tag_college.png"
      }
    ],
    images: {}
  },
  onLoad: function (option) {
    // var attraction = wx.getStorageSync('attraction');
    // wx.removeStorageSync('attraction')
    var attraction = JSON.parse(option.attraction)
    var description = attraction.data.description.replace(/<br>/g, "\n");
    this.setData({
      attraction: attraction,
      description: description
    });
    app.globalData.attrhistory.push(attraction.data);
    var attr=app.globalData.attrcollect.data
    console.log("onLoad")
    if(attr!=null){
      for(var i = 0; i < attr.length; ++i){
        if(attr[i].id==attraction.data.id){
            this.setData({
              isCollect: true
            })
        }
      }
    }
    
  },
  onShow: function (option){
    
  },
  like: function(e) {
    this.setData({
      isShowToast: true
    });
    let _this = this;

    var aid=e.currentTarget.dataset.src;
    var uid=app.globalData.user.id
    var userAttrcollect=new Object();
    userAttrcollect.aid=aid
    userAttrcollect.uid=uid
    wx.request({
      url: 'https://www.zzhcool.top/admin/service/v1/userattrcollects',
      data: {
        userAttrcollectJson: JSON.stringify(userAttrcollect)
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded" // 默认值
      },
      method: 'POST',
      success: function (res) {
        console.log(res.data)//打印到控制台
        var result = res.data;
        if (result == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          _this.setData({
            isCollect: true
          })
        }

        wx.request({
          url: 'https://www.zzhcool.top/attraction/service/v1/attractions/collect/'+uid,
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
    })

    setTimeout(function() {
      _this.setData({
        isShowToast: false
      });
    }, 2000);

  },
  dislike: function(e) {

    let _this = this;

    var aid=e.currentTarget.dataset.src;
    var uid=app.globalData.user.id
    var userAttrcollect=new Object();
    userAttrcollect.aid=aid
    userAttrcollect.uid=uid
    wx.request({
      url: 'https://www.zzhcool.top/admin/service/v1/userattrcollects/delete/'+uid+'/'+aid,
      header: {
        "content-type": "application/json"// 默认值
      },
      method: 'DELETE',
      success: function (res) {
        console.log(res.data)//打印到控制台
        var result = res.data;
        if (result == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          _this.setData({
            isCollect: false
          })
        }

        wx.request({
          url: 'https://www.zzhcool.top/attraction/service/v1/attractions/collect/'+uid,
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
    })

  },
  click: function (e) {
    var attraction = e.currentTarget.dataset.attr;
    console.log("latitude:"+attraction.latitude)
    wx.openLocation({
      latitude: parseFloat(attraction.latitude),
      longitude: parseFloat(attraction.longitude),
      scale: 18,
      name: attraction.title,
      address: attraction.address
    })
  },

  imageLoad: function(e) {
    var $width = e.detail.width, //获取图片真实宽度
      $height = e.detail.height,
      ratio = $width / $height; //图片的真实宽高比例
    var viewHeight = 28, //设置图片显示宽度，左右留有16rpx边距
      viewWidth = viewHeight * ratio; //计算的高度值
    // console.log(viewHeight);
    // var image = this.data.images;
    //将图片的datadata-index作为image对象的key,然后存储图片的宽高值
    var image = this.data.images;
    console.log(image);
    let _this = this;
    //将图片的datadata-index作为image对象的key,然后存储图片的宽高值
    image[e.target.dataset.index] = {
      width: viewWidth,
      height: viewHeight
    };
    _this.setData({
      images: image
    });

    console.log(e.target.dataset.index);
    console.log(_this.data.images);
  },
  bannerSwiperChange: function (e) {
    let current = e.detail.current;
    this.setData({
      bannerCurrentIndex: current + 1
    });
  }
});
