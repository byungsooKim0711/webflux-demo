<template>
    <div id="header">
        <div id="gnb">
            <li><router-link to="/">Customer</router-link></li>
            <li><router-link to="/">Test</router-link></li>
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

