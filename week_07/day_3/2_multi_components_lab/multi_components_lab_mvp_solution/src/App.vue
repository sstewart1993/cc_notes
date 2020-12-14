<template lang="html">
  <div>
    <h1>Characters</h1>
    <div class="main-container">
      <characters-list v-if="characters.length":characters="characters"></characters-list>
      <character-detail :character="selectedCharacter"></character-detail>
    </div>
  </div>
</template>

<script>
import { eventBus } from './main.js'
import CharacterDetail from './components/CharacterDetail.vue'
import CharactersList from './components/CharactersList.vue'

export default {
  data(){
    return {
      characters: [],
      selectedCharacter: null    }
  },
  components: {
    "characters-list": CharactersList,
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
  .main-container {
    display: flex;
    justify-content: space-between;
    width: 80%;
    margin: 0 auto;
  }
</style>
