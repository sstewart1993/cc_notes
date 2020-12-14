<template>
  <div id="app">
    <h1>{{ greeting }}</h1>
    <button v-on:click="updateGreeting('John')">Change Greeting</button>
  </div>
</template>

<script>
export default {
  data(){
    return {
      greeting: "Hello World"
    }
  },
  methods: {
    updateGreeting: function(name){
      this.greeting = "Hello " + name;
    }
  }
}
</script>

<style>

</style>