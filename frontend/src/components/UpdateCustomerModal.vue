<template>
    <div class="popupWrap popEnroll">
        <div class="popupContent small">
            <div class="popup">
                <div class="popupHead">
                    <strong class="tit">고객 수정</strong>
                </div>
                <div class="popupCont">
                    <!-- 내용 : s -->
                    <div class="tblForm">
                        <table>
                            <caption></caption>
                            <colgroup>
                                <col style="width:117px">
                                <col style="width:auto">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row" class="vt">
                                        <span>이름</span>
                                    </th>
                                    <td>
                                        <div class="formHorizental">
                                            <span>
                                                <input type="text" class="inpText" name="firstname" v-model="tmpCustomer.firstname">
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="vt">
                                        <span>성씨</span>
                                    </th>
                                    <td>
                                        <div class="formHorizental">
                                            <span>
                                                <input type="text" class="inpText" name="lastname" v-model="tmpCustomer.lastname">
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="vt">
                                        <span>나이</span>
                                    </th>
                                    <td>
                                        <div class="formHorizental">
                                            <span>
                                                <input type="text" class="inpText" name="age" v-model="tmpCustomer.age">
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="btnArea right btm">
                        <a @click="updateCustomer" class="btns btnBlack">
                            <span>저장</span>
                        </a>
                        <a @click="$emit('close')" class="btns btnWhite">
                            <span>닫기</span>
                        </a>
                    </div>
                    <!-- 내용 : e -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'update-customer-modal',

    props: ['customer'],

    data() {
        return {
            tmpCustomer: {}
        }
    },

    created() {
        this.tmpCustomer = {...this.customer};
    },

    methods: {
        updateCustomer: function () {
            if (confirm("수정하시겠습니까??")) {
                this.$store.dispatch('UPDATE_CUSTOMER', this.tmpCustomer);
                this.$emit('close');
            }
        }
    }
}
</script>