<template lang="html">
  <div v-if='munro'>
    <h3>{{munro.name}}</h3>
    <p>Height: {{munro.height}}</p>
    <p>Region: {{munro.region}}</p>
    <p>Meaning: {{munro.meaning}}</p>
  </div>
</template>

<script>
import { eventBus } from '../main.js';

export default {
  name: 'munro-detail',
  props: ['munro']
}

</script>

<style lang="css" scoped>
</style>
