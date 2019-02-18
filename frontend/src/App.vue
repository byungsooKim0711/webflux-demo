<template>
  <div id="app">
    <div class="wrap">
      <Header v-show="$route.path != '/pagenotfound'"></Header>
      <router-view/>
      <Footer v-show="$route.path != '/pagenotfound'"></Footer> 
      <modals-container/>
    </div> 
  </div>
</template>

<script>
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';

export default {
  name: 'App',
  
  components: {
    Header,
    Footer
  },

  created() {
    console.log(this.$route.path);
  }
}
</script>

<style src="./assets/css/common.css">

</style>
