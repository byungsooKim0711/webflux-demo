import Vue from 'vue'
import Router from 'vue-router'

/* Components */
import CustomerList from '@/components/CustomerList.vue'
import PageNotFound from '@/components/PageNotFound.vue'
import LoginComponent from '@/components/LoginComponent.vue'
import Index from '@/components/Index.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      children: [
        // {path: '...', component: ...},
      ]
    },
    {
      path: '/customer',
      name: 'CustomerList',
      component: CustomerList
    },
    {
      path: '/login',
      name: 'LoginComponent',
      component: LoginComponent,
    },

    // 맨 아래에 *이 있으면 위에 리스트를 제외한 나머지
    {path: '/pagenotfound', component: PageNotFound},
    {path: '*', redirect: '/pagenotfound'}
  ]
})
