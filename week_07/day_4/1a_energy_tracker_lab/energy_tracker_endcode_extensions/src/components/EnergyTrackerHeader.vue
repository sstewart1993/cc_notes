<template lang="html">
  <span>
    <h2>Generation Mix for the GB power system for {{from | fullDate}} to {{to | fullDate}}</h2>
  </span>
</template>

<script>
export default {
  name: 'energy-tracker-header',
  props: ['from', 'to'],
  filters: {
    fullDate: function(dateString){
      return new Date(dateString).toLocaleString()
    }
  }
}
</script>

<style lang="css" scoped>
</style>
