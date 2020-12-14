<template lang="html">
  <div id="charactersList">
    <ul>
      <list-component  v-for="(character, index) in characters" :character="character" :key="index"></list-component>
    </ul>
  </div>

</template>

<script>
import ListComponent from './ListComponent.vue'
export default {
  name: 'characters-list',
  props: ['characters'],
  components: {
    "list-component": ListComponent
  }
}
</script>

<style lang="css" scoped>
  #charactersList {
    background: #eee;
    height: 400px;
    overflow-y: scroll;
    border: 1px solid #ccc;
  }

  #charactersList ul {
    margin: 10px;
    padding: 0;
  }

  ::-webkit-scrollbar {
    -webkit-appearance: none;
    width: 7px;
  }

  ::-webkit-scrollbar-thumb {
    border-radius: 4px;
    background-color: rgba(0,0,0,.5);
    -webkit-box-shadow: 0 0 1px rgba(255,255,255,.5);
  }
</style>
