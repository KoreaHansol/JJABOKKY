// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import { store } from './store/store'
import checkExpireToken from './util/checkExpireToken'
Vue.config.productionTip = false


axios.interceptors.request.use( config => {
  const token = sessionStorage.getItem( 'token' )
  if( token ) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
}, err => {
  
} )

checkExpireToken()

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})


