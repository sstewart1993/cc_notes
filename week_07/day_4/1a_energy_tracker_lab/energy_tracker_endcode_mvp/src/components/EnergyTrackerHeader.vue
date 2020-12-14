<template lang="html">
  <h2>Generation Mix for the GB power system for {{from | fullDate}} to {{to | hoursMinutes}}</h2>
</template>

<script>
export default {
  name: 'energy-tracker-header',
  props: ['from', 'to'],
  filters: {
    fullDate: function(dateString){
      return new Date(dateString).toLocaleString()
    },
    hoursMinutes: function(dateString){
      const date = new Date(dateString)
      return `${date.getHours()}:${date.getMinutes()}0`
    }
  }
}
</script>

<style lang="css" scoped>
</style>
