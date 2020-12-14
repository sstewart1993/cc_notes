<template lang="html">
  <section>
    <p>Please enter a date range:</p>
    <form v-on:submit.prevent="emitDates">
    <label for="from">From:</label>
    <input id="from" type="datetime-local" :max="dates.to" v-model="dates.from" required/>
    <label for="to">To:</label>
    <input id="to" type="datetime-local" :min="dates.from" v-model="dates.to" required/>
    <input type="submit" value="Search Dates"/>
  </form>
  </section>
</template>

<script>
import { eventBus } from '@/main.js'

export default {
  data(){
    return {
      dates: {
        to: null,
        from: null
      }
    }
  },
  methods: {
    emitDates: function(){
      eventBus.$emit('dates-selected', this.dates)
    }
  }
}
</script>

<style lang="css" scoped>
label {
  padding: 20px;
}
</style>
