import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
      //轮播图地址
      slideImagesURL:'http://localhost:8890/images/slide/',
      //商品类型图片地址
      productTypeURL:'http://localhost:8890/images/productType/',
      //商品图片地址
      productURL:'http://localhost:8890/images/product/',
      //用户头像地址
      headImageURL:'http://localhost:8890/images/headImage/'
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
