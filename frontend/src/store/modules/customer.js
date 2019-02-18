
import Vue from 'vue';
const state = {
    customers: []
}

const getters = {
    getCustomers (state) {
        return state.customers;
    }
}

const mutations = {
    LOAD_CUSTOMERS (state, customers) {
        state.customers = customers;
    },

    DELETE_CUSTOMER (state, customer) {
        state.customers = state.customers.filter(c => c.id != customer.id);
    },

    UPDATE_CUSTOMER (state, customer) {
        Vue.set(state.customers, state.customers.findIndex(c => c.id == customer.id), customer);
    },

    INSERT_CUSTOMER (state, customer) {
        state.customers.push(customer);
    }
}

const actions = {
    LOAD_CUSTOMERS ( {commit}, search ) {
        return axios.get('/api/customer', { 
            params: {
                ...(search ? {search: search}: {})
            }
        }).then((response) => {
            commit('LOAD_CUSTOMERS', response.data);
        }).catch((error) => {
            console.log(error);
        });
    },

    DELETE_CUSTOMER ( {commit}, customer ) {
        axios.delete('/api/customer/delete/' + customer.id, {

        }).then((response) => {
            commit('DELETE_CUSTOMER', customer);
        }).catch((error) => {
            console.log(error);
        });
    },

    UPDATE_CUSTOMER ( {commit}, customer) {
        axios.put('/api/customer/put/' + customer.id, customer, {

        }).then((response) => {
            commit('UPDATE_CUSTOMER', response.data);
        }).catch((error) => {
            console.log(error);
        });
    },

    INSERT_CUSTOMER ( {commit}, customer) {
        axios.post('/api/customer/post', customer, {

        }).then((response) => {
            commit('INSERT_CUSTOMER', response.data);
        }).catch((error) => {
            console.log(error);
        });
    }
}

export default {
    state,
    getters,
    mutations,
    actions
}