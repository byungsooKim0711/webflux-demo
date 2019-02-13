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
                                    <span class="selectboxWrap mr10" style="width:94px">
                                        <select>
                                            <option value="ALL">전체</option>
                                            <option value="Y">인증</option>
                                            <option value="N">미인증</option>
                                        </select>
                                    </span>
                                <input id="searchInput" placeholder="검색" type="text" class="inpText mr10" style="width:200px;">
                                <span>검색</span>
								<a class="btns btnLineGray plus">
									<span>등록</span>
								</a>
							</div>
							<!-- tblData : s -->
							<div class="tblData customerList">
								<table>
									<colgroup>
										<col style="width:auto">
										<col style="width:auto">
										<col style="width:auto">
										<col style="width:auto">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">번호</th>
											<th scope="col">이름</th>
											<th scope="col">성</th>
											<th scope="col">나이</th>
										</tr>
									</thead>
									<tbody>
										<tr v-for="customer in customers" :key="customer.id">
											<td class="overflowText" :title="customer.id">{{customer.id}}</td>
											<td class="overflowText" :title="customer.firstname">{{customer.firstname}}</td>   
                                            <td class="overflowText" :title="customer.lastname">{{customer.lastname}}</td>
											<td class="overflowText" >{{customer.age}}</td>
                                            <td>
                                                <em class="btn">수정</em>
                                                <em class="btn">삭제</em>
                                            </td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- tblData : e -->
							<!-- 페이징 : s -->
							
							<!-- 페이징 : e -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- container : e -->
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