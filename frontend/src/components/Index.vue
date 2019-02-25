<template>
    <div class="index page">
        <div class="position">
            <p class="index main">
                Index Page
            </p>
            
            <p class="mt-30">
                꾸며야 함
            </p>
            <p class="mt-10">
                ㅎㅎ
            </p>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'index'
    }
</script>

<style scoped>
    .index.page {background: linear-gradient(45deg, rgba(255, 255, 255, 0.2) 0%, rgba(255, 255, 255, 0.2) 25%, rgba(255, 255, 255, 0.2) 51%, rgba(255, 255, 255, 0.2) 100%);height: 100vh;text-align: center;font-weight: bold;}
    .index .position {position: fixed; top:50%; left:50%; margin-top: -60px; margin-left:-234px;}
    .index.main {font-size: 90px;text-align: center;color: black; margin: 0;}

    .mt-30 {margin-top: 30px;}
    .mt-10 {margin-top: 10px;}
</style>