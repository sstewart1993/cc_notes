<template lang="html">
  <li v-on:click="handleClick">{{character.name}}</li>
</template>

<script>
import { eventBus } from '../main.js'
export default {
  name: 'list-component',
  props: ['character'],
  methods: {
    handleClick(){
      eventBus.$emit('character-selected', this.character)
    }
  }
}
</script>

<style lang="css" scoped>
  li {
    list-style-type: none;
    display: block;
    background: #fff;
    font-size: 16px;
    margin: 10px 0;
    padding: 10px;
    cursor: pointer;
    color: #555;
    transition: 0.1s all ease-in;
    border-radius: 3px;
  }

  li:hover {
    background: #dbe8ff;
    color: #222;
  }
</style>
