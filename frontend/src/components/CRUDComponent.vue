<template>
	<!-- container : s -->
	<div class="container">
		<div id="content">
			<div class="">
				<div class="serviceWrap">
					<div class="serviceCont side">
						<div class="">
							<h2 class="tit">고객관리</h2>
							<p class="countMsg">총 <em class="count txtBlue">{{customers.length}}</em> 건의 고객 검색되었습니다.</p>
							<div class="btnArea right">
                                <input id="searchInput" placeholder="검색" type="text" class="inpText mr10" style="width:200px;">
                                <span>검색</span>
								<a class="btns btnLineGray plus txtBlue">
									<span>등록</span>
								</a>
							</div>
							<!-- tblData : s -->
							<div class="tblData customerList">
								<table>
									<colgroup>
										<col style="width:80px">
										<col style="width:auto">
										<col style="width:auto">
										<col style="width:auto">
										<col style="width:200px">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">번호</th>
											<th scope="col">이름</th>
											<th scope="col">성</th>
											<th scope="col">나이</th>
											<th scope="col">작업</th>
										</tr>
									</thead>
									<tbody>
										<tr v-for="customer in customers" :key="customer.id">
											<td class="overflowText" :title="customer.id">{{customer.id}}</td>
											<td class="overflowText" :title="customer.firstname">{{customer.firstname}}</td>   
                                            <td class="overflowText" :title="customer.lastname">{{customer.lastname}}</td>
											<td class="overflowText" >{{customer.age}}</td>
                                            <td>
                                                <em class="btn txtGreen02" @click="getOneCustomerById(customer)">수정</em>
                                                <em class="btn txtRed" @click="deleteCustomerById(customer)">삭제</em>
                                            </td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- tblData : e -->
							<!-- 페이징 : s -->
							<div class="paging">
								<a class="btnPrev">prev</a>
								<span v-for="n in (customers.length)/5" :key="n">
									<a @click="getCustomerListOfCurrentPage(n)">{{n}}</a>
								</span>
								<a class="btnNext">next</a>
							</div>
							<!-- 페이징 : e -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- container : e -->

	<!-- pagination + N개 검색 (10, 20, 50, 100) -->
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
		},

		getOneCustomerById: function(customer) {
			console.log(customer)
			axios.get('/api/customer/' + customer.id, {

			}).then((response) => {
				console.log(response.data);
			}).catch((error) => {
				console.log(error);
			});
		},

		insertCustomer: function() {
			axios.post('/api/customer/post', {

			}).then((response) => {
				console.log(response.data);
			}).catch((error) => {
				console.log(error);
			});
		},

		deleteCustomerById: function(customer) {
			axios.delete('/api/customer/delete/' + customer.id, {

			}).then((response) => {
				this.customers = this.customers.filter(c => c.id != customer.id);
			}).catch((error) => {
				console.log(error);
			});
		},

		updateCustomerById: function(customer) {
			axios.put('/api/customer/put/' + customer.id, {

			}).then((response) => {
				console.log(response.data);
			}).catch((error) => {
				console.log(error);
			});
		},

		getCustomerListOfCurrentPage: function(n) {

		}
	},

	created () {},
	mounted () {
		this.getAllCustomers();
	},
	destroyed () {},
	updated () {}
};
</script>