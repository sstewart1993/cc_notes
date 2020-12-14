<template lang="html">
  <div id="selected_country">
    <h2> {{selectedCountry.name}} </h2>
    <p> Population: {{selectedCountry.population}} </p>
    <img class="lrg-flag" :src="selectedCountry.flag" />

    <neighbouring-countries
     v-if="neighbouringCountries.length" :neighbouringCountries="neighbouringCountries"
    :populationCalculator="populationCalculator" >
    </neighbouring-countries>

</div>
</template>

<script>
import NeighbourCountry from './NeighbourCountry.vue';
export default {
  name: 'country-detail',
  props: ['selectedCountry', 'neighbouringCountries', 'populationCalculator'],
  components: {
    'neighbouring-countries': NeighbourCountry
  }
}
</script>

<style lang="css" scoped>

.lrg-flag {
  height: 100px
}

</style>
