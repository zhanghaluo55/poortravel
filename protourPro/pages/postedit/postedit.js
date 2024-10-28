// 查看事件文档https://developers.weixin.qq.com/miniprogram/dev/api/media/editor/EditorContext.html
const app = getApp();
Page({
  data: {
    formats: {},
    bottom: 0,
    readOnly: false,
    placeholder: '介绍一下你的详情吧，支持文字和图片...',
    _focus: false,
    fileName: '',

    imgs: [],
    imgsnum: [],
    count: 1,
    countnum: 2
  },
  readOnlyChange() {
    this.setData({
      readOnly: !this.data.readOnly
    })
  },
  onLoad() {
    this.pop = this.selectComponent("#pop");
    this.popnum = this.selectComponent("#popnum");
  },
  // 编辑器初始化完成时触发
  onEditorReady() {
    const that = this;
    wx.createSelectorQuery().select('#editor').context(function(res) {
      that.editorCtx = res.context;
    }).exec();
  },
  undo() {
    this.editorCtx.undo();
  },
  redo() {
    this.editorCtx.redo();
  },
  format(e) {
    let {
      name,
      value
    } = e.target.dataset;
    if (!name) return;
    // console.log('format', name, value)
    this.editorCtx.format(name, value);
  },
  // 通过 Context 方法改变编辑器内样式时触发，返回选区已设置的样式
  onStatusChange(e) {
    const formats = e.detail;
    this.setData({
      formats
    });
  },
  // 插入分割线
  insertDivider() {
    this.editorCtx.insertDivider({
      success: function() {
        console.log('insert divider success')
      }
    });
  },
  // 清除
  clear() {
    this.editorCtx.clear({
      success: function(res) {
        console.log("clear success")
      }
    });
  },
  // 移除样式
  removeFormat() {
    this.editorCtx.removeFormat();
  },
  // 插入当前日期
  insertDate() {
    const date = new Date()
    const formatDate = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`
    this.editorCtx.insertText({
      text: formatDate
    });
  },
  // 插入图片
  insertImage() {
    
    var that=this
    
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
            if (fileName != null) {
              that.editorCtx.insertImage({
                src: fileName,
                width: '100%',
                success: () => {
                  console.log('insert image success')
                  console.log("filename:" + fileName);
                }
              })
            }

            var files = new Object();
            files.path = fileName
            files.filename = '文章图片'
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
          },
          fail: function (res) {
            console.log('上传失败');
          }

        })
      }
    });
  },
  //选择图片
  chooseImage(e) {
    wx.chooseImage({
      sizeType: ['original', 'compressed'], //可选择原图或压缩后的图片
      sourceType: ['album', 'camera'], //可选择性开放访问相册、相机
      success: res => {
        const images = this.data.images.concat(res.tempFilePaths);
        this.data.images = images.length <= 3 ? images : images.slice(0, 3);
      }
    })
  },
  //查看详细页面
  toDeatil() {
    this.editorCtx.getContents({
      success: (res) => {
        console.log(res.html)
        app.globalData.html = res.html
        wx.navigateTo({
          url: '../details/details'
        })
     
      },
      fail: (res) => {
        console.log("fail：" , res);
      }
    });
  },


  //以下为文件上传

  chooseImg: function (e) {
    // console.log(this.pop.data.imgs);
    this.setData({
      imgs: this.pop.data.imgs
    })
  },
  deleteImg: function (e) {
    // console.log(this.pop.data.imgs);
    this.setData({
      imgs: this.pop.data.imgs
    })
  },
  chooseImgnum: function (e) {
    // console.log(this.pop.data.imgs);
    this.setData({
      imgsnum: this.popnum.data.imgs
    })
  },
  deleteImgnum: function (e) {
    // console.log(this.pop.data.imgs);
    this.setData({
      imgsnum: this.popnum.data.imgs
    })
  },
  formSubmit: function (e) {
    console.log(this.data.imgs);
    console.log(this.data.imgsnum);
    var tempFilePaths = this.data.imgs
    var fileName = null
    var html=''
    var post = e.detail.value
    var that = this
    this.editorCtx.getContents({
      success: (res) => {
        console.log(res.html)
        html = res.html

      },
      fail: (res) => {
        console.log("fail：", res);
      }
    });

    wx.uploadFile({
      url: 'https://www.zzhcool.top/upload/service/upload',
      filePath: tempFilePaths[0],
      name: 'file',
      // formData: {
      //   method: 'POST'   //请求方式
      // },
      header: { "Content-Type": "multipart/form-data" },
      success: function (res) {
        var cur_data = JSON.parse(res.data);
        fileName = cur_data.fileName
        console.log("filename:" + fileName);
        if (fileName != null) {
          that.editorCtx.insertImage({
            src: fileName,
            width: '100%',
            success: () => {
              console.log('insert image success')
              console.log("filename:" + fileName);
              post.content = html
              post.postImage = fileName
              post.uid = 1 //app.globalData.user.id
              console.log(post)
              wx.request({
                url: 'https://www.zzhcool.top/post/service/v1/posts',
                data: {
                  postJson: JSON.stringify(post)
                },
                header: {
                  "Content-Type": "application/x-www-form-urlencoded" // 默认值
                },
                method: 'POST',
                success: function (res) {
                  var result = res.data;
                  console.log("文章插入成功")
                  if (result == null) {
                    var toastText = '数据获取失败';
                    wx.showToast({
                      title: toastText,
                      icon: '',
                      duration: 2000
                    });
                  } else {

                  }

                  var files = new Object();
                  files.path = fileName
                  files.filename = '文章图片'
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

                  wx.switchTab({
                    url: '../user/user' //页面路径
                  })
                }
              })
            }
          })
        }
      },
      fail: function (res) {
        console.log('上传失败');
      }

    })
    
  },
  onShow: function () {
    console.log("tow")
    var that = this
    var user = app.globalData.user
    that.setData({
      user: user
    })
  }
})