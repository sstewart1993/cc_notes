<template>
  <div id="app">
    <vuedog-header title="VueDog" />
    <vuedog-header v-if="!beers.length" title="LOADING..." />
    <div id="list-info" v-if="beers.length">
      <vuedog-beer-list :beers="beers"></vuedog-beer-list>
      <vuedog-beer-info
        v-if="selectedBeer"
        :beer="selectedBeer"
      ></vuedog-beer-info>
    </div>
    <vuedog-favourites-list :favourites="favourites"></vuedog-favourites-list>
  </div>
</template>

<script>
import { eventBus } from "@/main.js";
import VuedogBeerList from "@/components/VuedogBeerList";
import VuedogBeerInfo from "@/components/VuedogBeerInfo";
import VuedogHeader from "@/components/VuedogHeader";
import VuedogFavouritesList from "@/components/VuedogFavouritesList";

export default {
  name: "app",
  components: {
    "vuedog-header": VuedogHeader,
    "vuedog-beer-info": VuedogBeerInfo,
    "vuedog-beer-list": VuedogBeerList,
    "vuedog-favourites-list": VuedogFavouritesList
  },
  data() {
    return {
      beers: [],
      selectedBeer: null
    };
  },
  computed: {
    favourites: function() {
      return this.beers.filter(beer => beer.isFavourite);
    }
  },
  methods: {
    getBeers: function() {
      const promises = [1, 2, 3, 4, 5].map(num => {
        return fetch(
          `https://api.punkapi.com/v2/beers?page=${num}&per_page=80`
        ).then(res => res.json());
      });

      Promise.all(promises)
        .then(data => {
          const beerData = data.reduce(
            (flat, toFlatten) => flat.concat(toFlatten),
            []
          );
          beerData.forEach(beer => (beer.isFavourite = false));
          this.beers = beerData;
        })
        .then(() => this.sortBeers("name"));
    },
    sortBeers: function(property) {
      this.beers.sort((a, b) => {
        return a[property] < b[property] ? -1 : 1;
      });
    },
    markFavourite: function(beer) {
      const index = this.beers.indexOf(beer);
      this.beers[index].isFavourite = true;
    },
    unmarkFavourite: function(beer) {
      const index = this.beers.indexOf(beer);
      this.beers[index].isFavourite = false;
    }
  },
  mounted() {
    this.getBeers();

    eventBus.$on("beer-selected", beer => (this.selectedBeer = beer));

    eventBus.$on("favourite-added", beer => this.markFavourite(beer));

    eventBus.$on("favourite-removed", beer => this.unmarkFavourite(beer));
  }
};
</script>

<style lang="css" scoped>
#app {
  background-color: #6EC3F4;
}

#list-info {
  display: flex;
}
</style>
