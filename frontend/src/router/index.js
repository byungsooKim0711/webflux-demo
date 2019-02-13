import Vue from 'vue'
import Router from 'vue-router'
import CRUDComponent from '@/components/CRUDComponent'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'CRUDComponent',
      component: CRUDComponent
    }
  ]
})
