//index.js
//获取应用实例
let app = getApp();
let wechat = require("../../utils/wechat");
let amap = require("../../utils/amap");
let markersData = [];
Page({
  click: function (e) {
    wx.openLocation({
      latitude: 23.362490,
      longitude: 116.715790,
      scale: 18,
      name: '华乾大厦',
      address: '金平区长平路93号'
    })
  }

})
