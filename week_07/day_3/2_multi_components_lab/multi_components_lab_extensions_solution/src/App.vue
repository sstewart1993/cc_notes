<template lang="html">
  <div>
    <h1>Characters</h1>
    <character-filter-form :characters="characters" />
    <character-detail :character="selectedCharacter"/>
  </div>
</template>

<script>
import CharacterFilterForm from './components/CharacterFilterForm.vue'
import CharacterDetail from './components/CharacterDetail.vue'
import {eventBus} from './main.js';

export default {
  data(){
    return {
      characters: [],
      selectedCharacter: null
    }
  },
  components: {
    "character-filter-form": CharacterFilterForm,
    "character-detail": CharacterDetail
  },
  mounted(){
    fetch('https://rickandmortyapi.com/api/character/')
    .then(res => res.json())
    .then(data => this.characters = data.results)

    eventBus.$on('character-selected', (character) => {
      this.selectedCharacter = character
    })
  }
}
</script>

<style lang="css" scoped>
h1 {
  text-align: center;
  color: #333;
}
</style>
