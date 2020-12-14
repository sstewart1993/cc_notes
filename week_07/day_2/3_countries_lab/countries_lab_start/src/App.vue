<template>
  <div id="app">
    <h2>Global population: </h2>

    <label for="country_select">Select a Country:</label>
    <select id="country_select" v-model="selectedCountry">
      <option disabled value="">Select a country</option>
      <option v-for="country in countries" :value="country">{{country.name}}</option>
    </select>

    <country-detail></country-detail>

    <button>Add Country</button>

    <favourite-countries></favourite-countries>
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

    },
    mounted(){

    },
    methods: {
      
    }
}
</script>

<style>
.small-flag {
  height: 20px
}



</style>
