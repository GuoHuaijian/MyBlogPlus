import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/aliicon/iconfont.css'
import './assets/aliicon/iconfont'
import 'vue-social-share/dist/client.css';
import vshare from 'vshare'

Vue.config.productionTip = false
Vue.use(vshare)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
