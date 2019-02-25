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
								<!-- <span class="selectboxWrap mr10" style="width:94px">
                                    <select>
                                        <option value="ALL">10개씩 보기</option>
                                        <option value="Y">25개씩 보기</option>
                                        <option value="N">50개씩 보기</option>
										<option value="N">100개씩 보기</option>
                                    </select>
                                </span> -->
                                <input id="searchInput" v-model="search" @keyup="handleChange()" placeholder="검색" type="text" class="inpText mr10" style="width:200px;">
                                <span>검색</span>
								<a @click="insertCustomer" class="btns btnLineGray plus txtBlue">
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
											<th scope="col">성씨</th>
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
                                                <em class="btn txtGreen02" @click="updateCustomerById(customer)">수정</em>
                                                <em class="btn txtRed" @click="deleteCustomerById(customer)">삭제</em>
                                            </td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- tblData : e -->
							<!-- 페이징 : s -->
							<!-- <div class="paging">
								<a class="btnPrev">prev</a>
								<span v-for="n in ((customers.length))" :key="n">
									<a @click="getCustomerListOfCurrentPage(n)">{{n}}</a>
								</span>
								<a class="btnNext">next</a>
							</div> -->
							<!-- 페이징 : e -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- container : e -->

	<!-- pagination + N개 검색 (10, 25, 50, 100) -->
</template>

<script>
import { mapGetters } from 'vuex';
import InsertCustomerModal from './InsertCustomerModal.vue';
import UpdateCustomerModal from './UpdateCustomerModal.vue';

export default {
	name: "crud-component",

	computed: {
		...mapGetters({
			customers: 'getCustomers'
		})
	},

	data() {
		return {
			/* pageNum: 1,
			count: 10,
			limitNum: 5, */
			_timer: 5000,
			search: ''
		}
	},

	components: {},

	methods: {
		getCustomers: function() {
			this.$store.dispatch('LOAD_CUSTOMERS', this.search);
		},

		deleteCustomerById: function(customer) {
			if (confirm("삭제하시겠습니까?")) {
				this.$store.dispatch('DELETE_CUSTOMER', customer);
			}
		},

		getCustomerListOfCurrentPage: function(n) {

		},

		insertCustomer: function() {
			this.$modal.show(
                InsertCustomerModal, {
                    text: "insertCustomerModal"
                }, 
                {
			        width: "400px",
					height: "auto",
					scrollable: true
                }, 
                {
			        name: 'insert-customer',
					clickToClose: false,
					transition:true
			    }
		    );
		},

		updateCustomerById: function(customer) {
			this.$modal.show(
                UpdateCustomerModal, {
                    customer: customer
                }, 
                {
			        width: "400px",
					height: "auto",
					scrollable: true
                }, 
                {
			        name: 'insert-customer',
					clickToClose: false,
					transition:true
			    }
		    );
		},

		handleChange() {
			clearTimeout(this._timer);
			this._timer = window.setTimeout(() => {
				this.getCustomers();
			}, 500);
		}
	},

	created () {
		this.getCustomers();
	},
	mounted () {
		// pageNum: 1, limitNum: 5, count: 10
	},
	destroyed () {},
	updated () {}
};
</script>