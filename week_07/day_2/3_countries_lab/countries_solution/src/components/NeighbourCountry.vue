<template lang="html">
  <div id="neighbouring_countries">
  <h3>Neighbouring Countries</h3>
  <p>Total population for neighbouring countries: {{ neighbouringCountriesPopulation }} </p>
    <ul id="neighbouring_countries">
      <li v-for="neighbouringCountry in neighbouringCountries">
        <p>{{neighbouringCountry.name}}</p>
        <img class="med-flag" :src="neighbouringCountry.flag">
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'neighbouring-countries',
  props: ['neighbouringCountries', 'populationCalculator'],
  computed: {
    neighbouringCountriesPopulation: function(){
      return this.populationCalculator(this.neighbouringCountries);
    }
  }
}
</script>

<style lang="css" scoped>

.med-flag {
  height: 40px
}
</style>
