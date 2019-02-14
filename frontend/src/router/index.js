import Vue from 'vue'
import Router from 'vue-router'

/* Components */
import CRUDComponent from '@/components/CRUDComponent'
import PageNotFound from '@/components/PageNotFound'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'CRUDComponent',
      component: CRUDComponent,
      children: [
        // {path: '...', component: ...},
      ]
    },

    // 맨 아래에 *이 있으면 위에 리스트를 제외한 나머지
    {path: '/pagenotfound', component: PageNotFound},
    {path: '*', redirect: '/pagenotfound'}
  ]
})
