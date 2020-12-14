<template>
  <div id="app">
    <h1>Energy Tracker</h1>
    <energy-tracker-header v-if="from && to" :from="from" :to="to"></energy-tracker-header>
    <energy-tracker-chart v-if="mixData" :mixData="mixData"></energy-tracker-chart>
  </div>
</template>

<script>
import EnergyTrackerHeader from '@/components/EnergyTrackerHeader'
import EnergyTrackerChart from '@/components/EnergyTrackerChart'

export default {
  name: 'app',
  components: {
    'energy-tracker-chart': EnergyTrackerChart,
    'energy-tracker-header': EnergyTrackerHeader
  },
  data(){
    return {
      from: null,
      to: null,
      mixData: null
    }
  },
  mounted(){
    fetch("https://api.carbonintensity.org.uk/generation")
    .then(response => response.json())
    .then(json => {
      this.from = json.data.from
      this.to = json.data.to
      this.mixData = json.data.generationmix
    })
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
