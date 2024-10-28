
const util = require( '../../utils/util.js' );
const app=getApp()
Page( {
    data: {
        // text:"这是一个页面"
        data: [],
        databody: null,
        comments : [],  // 评论
        post: '',
        winHeight: 0,   // 设备高度
        isCollect: false,
        comments: '',
        content: '',
        inputValue: null,
        // 弹窗
        modalHidden: true,
        modalValue: null,

        /**
         * 分享配置
         */
        shareShow: 'none',
        shareOpacity: {},
        shareBottom: {},

    },
    onLoad: function( options ) {
      var that=this
      var post1 = decodeURIComponent((options.post));
      var post = JSON.parse(post1)
      var pid = post.data.id
      that.setData({
        comments: ''
      })

      wx.request({
        url: 'https://www.zzhcool.top/post/service/v1/postcomments/list/'+pid,
        method: 'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          var comments = res.data
          console.log("resultdata:"+comments.data)
          that.setData({
            comments: comments
          })
        }
      })

      console.log("post:"+post)
      console.log("pid:"+pid)
      that.setData({
        post: post,
      });

      app.globalData.posthistory.push(post.data);
      console.log("history"+app.globalData.posthistory) 

      var pos=app.globalData.postcollect.data
      console.log("onLoad")
      if(pos!=null){
        for(var i = 0; i <pos.length; ++i){
          if(pos[i].id==pid){
              this.setData({
                isCollect: true
              })
          }
        }
      }

     
    },
    /**
     * 显示分享
     */
    showShare: function( e ) {

        // 创建动画
        var animation = wx.createAnimation( {
            duration: 100,
            timingFunction: "ease",
        })
        this.animation = animation;

        var that = this;
        that.setData( {
            shareShow: "block",
        });

        setTimeout( function() {
            that.animation.bottom( 0 ).step();
            that.setData( {
                shareBottom: animation.export()
            });
        }.bind( this ), 400 );

        // 遮罩层
        setTimeout( function() {
            that.animation.opacity( 0.3 ).step();
            that.setData( {
                shareOpacity: animation.export()
            });
        }.bind( this ), 400 );

    },

    /**
     * 关闭分享
     */
    shareClose: function() {
        // 创建动画
        var animation = wx.createAnimation( {
            duration: 0,
            timingFunction: "ease"
        })
        this.animation = animation;

        var that = this;

        setTimeout( function() {
            that.animation.bottom( -210 ).step();
            that.setData( {
                shareBottom: animation.export()
            });
        }.bind( this ), 500 );

        setTimeout( function() {
            that.animation.opacity( 0 ).step();
            that.setData( {
                shareOpacity: animation.export()
            });
        }.bind( this ), 500 );

        setTimeout( function() {
            that.setData( {
                shareShow: "none",
            });
        }.bind( this ), 1500 );
    },

    /**
     * 点击分享图标弹出层
     */
    modalTap: function( e ) {
        var that = this;
        that.setData( {
            modalHidden: false,
            modalValue: e.target.dataset.share
        })
    },
    
    /**
     * 关闭弹出层
     */
    modalChange: function( e ) {
        var that = this;
        that.setData( {
            modalHidden: true
        })
    },

    onReady: function() {
        // 页面渲染完成
        // 修改页面标题
        wx.setNavigationBarTitle( {
            title: this.data.data.title
        })


    },
    onShow: function (options) {
        // 页面显示
      // var post = JSON.parse(option.post)
      // this.setData({
      //   post: post
      // });

    },
    onHide: function() {
        // 页面隐藏
    },
    onUnload: function() {
        // 页面关闭
    },
  like: function(e) {
    this.setData({
      isShowToast: true
    });
    let _this = this;
    var post1=e.currentTarget.dataset.src;
    var post = new Object();
    post.commentNum=post1.commentNum + 1
    post.id=post1.id
    var pid=post1.id
    //var pid=e.currentTarget.dataset.src;
    var uid=app.globalData.user.id
    var userPostcollect=new Object();
    userPostcollect.pid=pid
    userPostcollect.uid=uid
    wx.request({
      url: 'https://www.zzhcool.top/admin/service/v1/userpostcollects',
      data: {
        userPostcollectJson: JSON.stringify(userPostcollect)
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
          url: 'https://www.zzhcool.top/post/service/v1/posts/collect/'+uid,
          method: 'GET',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success: function (res) {
            // console.log(res.data)//打印到控制台
            var posts = res.data;
            console.log("posts:"+posts)
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

      }
    })
    
    wx.request({
      url: 'https://www.zzhcool.top/post/service/v1/posts/'+pid,
      data: {
        postJson: JSON.stringify(post)
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

    //var pid=e.currentTarget.dataset.src;
    var post1=e.currentTarget.dataset.src;
    var post = new Object();
    post.commentNum=post1.commentNum -1
    post.id=post1.id
    var pid=post1.id
    var uid=app.globalData.user.id
    var userPostcollect=new Object();
    userPostcollect.id=null
    userPostcollect.pid=pid
    userPostcollect.uid=uid
    wx.request({
      url: 'https://www.zzhcool.top/admin/service/v1/userpostcollects/delete/'+ uid+'/'+pid,
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
          url: 'https://www.zzhcool.top/post/service/v1/posts/collect/'+uid,
          method: 'GET',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success: function (res) {
            // console.log(res.data)//打印到控制台
            var posts = res.data;
            console.log("posts:"+posts)
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
      }
    })

    wx.request({
      url: 'https://www.zzhcool.top/post/service/v1/posts/'+pid,
      data: {
        postJson: JSON.stringify(post)
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
      }
    })

  },
  commentup: function (e) {
    var pid = e.currentTarget.dataset.pid;
    var that = this;
    var postComment=new Object()
    postComment.uid=app.globalData.user.id
    postComment.likes=0
    postComment.content=this.data.content.value
    postComment.pid=pid
    console.log("contnet:"+postComment.content)
    wx.request({
      url: 'https://www.zzhcool.top/post/service/v1/postcomments',
      data: {
        postCommentJson: JSON.stringify(postComment)
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded" // 默认值
      },
      method: 'POST',
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
          url: 'https://www.zzhcool.top/post/service/v1/postcomments/list/'+pid,
          method: 'GET',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success: function (res) {
            var comments = res.data
            that.setData({
              comments: comments
            })
          }
        })
    
      }
    })
    this.setData({
      inputValue: ''
    }) 
  },
  getInputValue(e){
    var content=e.detail
    this.setData({
      content: content
    })
  }
})
