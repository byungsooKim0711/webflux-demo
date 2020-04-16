// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import { store } from './store/store'

import VModal from 'vue-js-modal'

Vue.config.productionTip = false

/* axios global configuration */
window.axios = require('axios');

/**
 * 개발 및 운영 환경에서의 baseURL 확인 방법
 *
 * [개발]: build/webpack.dev.config.js / config/dev.env.js => HOST, PORT 번호 확인, 없으면 기본값 119.207.76.95:38011
 * [운영]: build/webpack.prod.config.js / config/prod.env.js => HOST, PORT 번호 확인, 없으면 기본값 119.207.76.95:38010
 *
 */

// TODO: cors 해결 전 까진 주석
// axios.defaults.baseURL = baseURL;

axios.interceptors.request.use(function (request) {
  return request;
}, function(error) {
  return Promise.reject(error);
});

axios.interceptors.response.use(function(response) {
  return response;
}, function(error) {
  return Promise.reject(error);
})

Vue.use(VModal, {dynamic: true, injectModalsContainer: true, dialog:true})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
