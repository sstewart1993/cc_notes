<template>
  <div id="app">
    <h1>RANDOGMISER</h1>
    <img id="dog-img" v-if="dogImgURL" :src="dogImgURL">
    <button v-on:click="fetchDog">Gimme those dogs!</button>
  </div>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {
      dogImgURL: null
    }
  },
  methods: {
    fetchDog: function(){
      console.log('Hello from fetchDog!');
    }
  }
}
</script>

<style>
#app {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 40%;
}

#dog-img {
  width: 500px;
}

</style>
