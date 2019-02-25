<template>
    <div id="header">
        <div id="gnb">
            <li><router-link to="/">Index</router-link></li>
            <li><router-link to="/customer">Customer</router-link></li>
        </div>

        <div class="utilMenu">
            <router-link to="/login">SIGN IN</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Header'
    }
</script>

<style scoped>

</style>

