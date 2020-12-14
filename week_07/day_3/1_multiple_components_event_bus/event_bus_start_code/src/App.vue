<template>
  <div>
    <h1>Munros</h1>
    <div class="main-container">
      <munros-list :munros='munros'></munros-list>
    </div>
  </div>
</template>

<script>
import MunrosList from './components/MunrosList.vue';

export default {
  name: 'app',
  data(){
    return {
      munros: [],
      selectedMunro: null
    };
  },
  mounted(){
    fetch('https://munroapi.herokuapp.com/munros')
    .then(res => res.json())
    .then(munros => this.munros = munros)
  },
  components: {
    "munros-list": MunrosList
  }
}
</script>

<style>
  .main-container {
    display: flex;
    justify-content: space-between;
  }
</style>
