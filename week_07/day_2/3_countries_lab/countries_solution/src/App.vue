<template>
  <div id="app">
    <h2>Global population: {{ globalPopulation }}</h2>

    <label for="country_select">Select a Country:</label>
    <select id="country_select" v-model="selectedCountry">
      <option disabled value="">Select a country</option>
      <option v-for="country in countries" :value="country">{{country.name}}</option>
    </select>

    <country-detail v-if="selectedCountry" :selectedCountry="selectedCountry" :neighbouringCountries="neighbouringCountries"
    :populationCalculator="populationCalculator">
    </country-detail>

    <button v-if="!favouriteCountries.includes(selectedCountry)" v-on:click="addToFavourites">Add Country</button>

    <favourite-countries :favouriteCountries="favouriteCountries"></favourite-countries>
</div>

</template>

<script>
import CountryDetail from './components/CountryDetail.vue';
import FavouriteListItem from './components/FavouriteListItem.vue';

export default {
  name: 'App',
  data() {
    return {
      countries: [],
      selectedCountry: null,
      favouriteCountries: []
    }
  },
  components: {
    'country-detail': CountryDetail,
    'favourite-countries': FavouriteListItem
  },
    computed: {
      globalPopulation: function(){
        return this.populationCalculator(this.countries);
      },
      neighbouringCountries: function(){
        return this.countries.filter((country) => {
          return this.selectedCountry.borders.includes(country.alpha3Code);
        });
      }
    },
    mounted(){
      this.getCountries()
    },
    methods: {
      getCountries: function(){
        fetch("https://restcountries.eu/rest/v2/all")
        .then(res => res.json())
        .then(countries => this.countries = countries)
      },
      addToFavourites: function(){
        this.favouriteCountries.push(this.selectedCountry)
      },
      populationCalculator: function(countries){
        return countries.reduce((runningTotal, country) => runningTotal + country.population, 0);
      }
    }
}
</script>

<style>
.small-flag {
  height: 20px
}



</style>
