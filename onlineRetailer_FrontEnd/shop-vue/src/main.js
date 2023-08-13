import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vant from 'vant';
import 'vant/lib/index.css';
import axios from 'axios';
import '@/assets/css/global.css';
import { Notify } from 'vant';
Vue.use(Notify);
Vue.use(Vant);
Vue.prototype.$http = axios;
Vue.config.productionTip = false;
import touch from 'vue-directive-touch'; Vue.use(touch);
import 'default-passive-events';
//引入qs模块
import qs from 'qs'
Vue.prototype.$qs = qs;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
