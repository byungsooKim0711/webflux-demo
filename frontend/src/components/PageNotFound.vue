<template>
    <div class="error error-404">
        <div class="position">
            <p class="error code">
                404 Not Found
            </p>
            
            <p class="mt-30">
                방문하시려는 페이지의 주소가 잘못 입력되었거나, 내부 오류가 있어 정상적인 페이지를 표시할 수 없습니다. 불편을 드려 죄송합니다.
            </p>
            <p class="mt-10">
                입력하신 주소가 정확한지 다시 한 번 확인해 주시기 바랍니다.
                지속적인 에러 발생시, 고객지원(org.kimbs)으로 신고주시면 빠르게 조치하겠습니다.
            </p>

            <ul class="mt-30 btnBorder"><router-link to="/" style="font-size: 20px;">홈으로 가기</router-link></ul>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'page-not-found'
    }
</script>

<style scoped>
    .error.error-404 {background: linear-gradient(45deg, rgba(59,173,227,0.2) 0%, rgba(87,111,230,0.2) 25%, rgba(152,68,183,0.2) 51%, rgba(255,53,127,0.2) 100%);height: 100vh;text-align: center;font-weight: bold;}
    .error .position {position: fixed; top:50%; left:50%; margin-left: -390px; margin-top: -190px;}
    .error.code {font-size: 90px;text-align: center;color: white;margin: 0;}
    
    .mt-30 {margin-top: 30px;}
    .mt-10 {margin-top: 10px}

    .mt-30.btnBorder {border-radius: 8px; border: solid 1px black; display: inline-block; min-width: 80px; padding: 5px; cursor: pointer;}
</style>