<template lang="html">
  <div class="beer-list" v-if="beers.length">
    <vuedog-beer-list-item
      v-for="(beer, index) in beers"
      :beer="beer"
      :key="index"
    >
    </vuedog-beer-list-item>
  </div>
</template>

<script>
import VuedogBeerListItem from "@/components/VuedogBeerListItem";

export default {
  name: "vuedog-beer-list",
  props: ["beers"],
  components: {
    "vuedog-beer-list-item": VuedogBeerListItem
  }
};
</script>

<style lang="css" scoped>

.beer-list {
  width: 45%;
  height: 550px;
  overflow-y: scroll;
  display: flex;
  flex-wrap: wrap;
}
</style>
