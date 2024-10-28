//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    items: [],
    hidden: false,
    loading: false,
    // loadmorehidden:true,
    plain: false,
    posts: '',
    post: ''
  },

  onItemClick: function (event) {
    var targetUrl = "/pages/image/image";
    if (event.currentTarget.dataset.url != null)
      targetUrl = targetUrl + "?url=" + event.currentTarget.dataset.url;
    wx.navigateTo({
      url: targetUrl
    });
  },

  // loadMore: function( event ) {
  //   var that = this
  //   requestData( that, mCurrentPage + 1 );
  // },

  onReachBottom: function () {
    console.log('onLoad')
    var that = this
    that.setData({
      hidden: false,
    });
    requestData(that, mCurrentPage + 1);
  },

  onLoad: function () {
    // console.log('onLoad')
    // var that = this
    // requestData(that, mCurrentPage + 1);
  },
   onShow: function () {
    var that = this;
    wx.request({
      url: 'https://www.zzhcool.top/post/service/v1/posts/getall',
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        // console.log(res.data)//打印到控制台
        var posts = res.data;
        if (posts == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          that.setData({
            posts: posts
          })
        }
      }
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

/**
 * 定义几个数组用来存取item中的数据
 */
var mUrl = [];
var mDesc = [];
var mWho = [];
var mTimes = [];
var mTitles = [];

var mCurrentPage = 0;

// 引入utils包下的js文件
var Constant = require('../../utils/constant.js');

/**
 * 请求数据
 * @param that Page的对象，用来setData更新数据
 * @param targetPage 请求的目标页码
 */
// function requestData(that, targetPage) {
//   wx.showToast({
//     title: '加载中',
//     icon: 'loading'
//   });
//   wx.request({
//     url: Constant.GET_MEIZHI_URL + targetPage,
//     header: {
//       "Content-Type": "application/json"
//     },
//     success: function (res) {
//       if (res == null ||
//         res.data == null ||
//         res.data.results == null ||
//         res.data.results.length <= 0) {

//         console.error("god bless you...");
//         return;
//       }


//       for (var i = 0; i < res.data.results.length; i++)
//         bindData(res.data.results[i]);

//       //将获得的各种数据写入itemList，用于setData
//       var itemList = [];
//       for (var i = 0; i < mUrl.length; i++)
//         itemList.push({ url: mUrl[i], desc: mDesc[i], who: mWho[i], time: mTimes[i], title: mTitles[i] });

//       that.setData({
//         items: itemList,
//         hidden: true,
//         // loadmorehidden:false,
//       });

//       mCurrentPage = targetPage;

//       wx.hideToast();
//     }
//   });
// }

/**
 * 绑定接口中返回的数据
 * @param itemData Gank.io返回的content;
 */
function bindData(itemData) {

  var url = itemData.url.replace("//ww", "//ws");
  var desc = itemData.desc;
  var who = itemData.who;
  var times = itemData.publishedAt.split("T")[0];

  mUrl.push(url);
  mDesc.push(desc);
  mWho.push(who);
  mTimes.push(times);
  mTitles.push("publish by：" + "@" + who + " —— " + times);
}