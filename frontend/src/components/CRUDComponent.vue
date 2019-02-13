<template>
	<div>
		{{customers}}
	</div>
</template>

<script>
import axios from 'axios';

export default {
	name: "crud-component",

	data() {
		return {
			customers: []
		}
	},

	components: {},

	computed: {},

	methods: {
		getAllCustomers: function() {
			axios.get('/api/customer', {

			}).then((response) => {
				this.customers = response.data;
			}).catch((error) => {
				console.log(error);
			});
		}
	},

	created () {
		this.getAllCustomers();
	},
	mounted () {},
	destroyed () {},
	updated () {}
};
</script>

<style scoped>
</style>