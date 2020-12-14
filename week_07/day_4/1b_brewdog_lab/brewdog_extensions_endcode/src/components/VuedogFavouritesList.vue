<template lang="html">
  <div id="favourites-list">
    <vuedog-header title="Your Favourites"></vuedog-header>
    <ul>
      <vuedog-favourites-listitem
        v-for="(beer, index) in favourites"
        :beer="beer"
        :key="index"
      ></vuedog-favourites-listitem>
    </ul>
  </div>
</template>

<script>
import VuedogFavouritesListitem from "@/components/VuedogFavouritesListItem";
import VuedogHeader from "@/components/VuedogHeader";

export default {
  props: ["favourites"],
  components: {
    "vuedog-favourites-listitem": VuedogFavouritesListitem,
    "vuedog-header": VuedogHeader
  }
};
</script>

<style lang="css" scoped>
ul {
  padding: 20px;
  display: flex;
  flex-wrap: wrap;
}
</style>
