<template lang="html">
  <form v-on:submit.prevent>
    <input type="text" v-model="search" placeholder="search for character..." v-on:keyup="searchForCharacter">
    <select v-on:change="handleSelect" v-model="selectedCharacter">
      <option disabled value="">Select a character...</option>
      <option v-for="character in characters" :value="character">{{character.name}}</option>
    </select>
  </form>
</template>

<script>
import { eventBus } from '../main.js'

export default {
  name: "character-filter-form",
  data(){
    return {
      "search": "",
      "selectedCharacter": {},
    }
  },
  props: ["characters"],
  methods: {
    searchForCharacter(){
      let foundCharacter = this.characters.find((character) => {
        return character.name.toLowerCase().indexOf(this.search.toLowerCase()) > -1
      })
      this.selectedCharacter = foundCharacter

      eventBus.$emit('character-selected', this.selectedCharacter)
    },
    handleSelect(){
      this.search = ""
      eventBus.$emit('character-selected', this.selectedCharacter)
    }
  }
}
</script>

<style lang="css" scoped>
form{
  text-align: center;
  margin: 40px 0;
}

select, input[type="text"]{
  font-size: 18px;
}

select {
  margin-left: 20px;
}
</style>
