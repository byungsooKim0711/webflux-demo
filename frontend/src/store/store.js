import Vue from 'vue'
import Vuex from 'vuex'
import customer from './modules/customer.js'

Vue.use(Vuex);

export const store = new Vuex.Store({
    modules: {
        customer
    },
    
    state: {
        currentRoute: '',
    },

    mutations: {},
    
    getters: {},

    actions: {},

    getters: {},
});