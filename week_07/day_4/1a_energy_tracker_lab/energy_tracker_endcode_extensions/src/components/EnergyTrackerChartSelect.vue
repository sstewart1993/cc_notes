<template lang="html">
  <div>
    <label for="chart-select">Select a Chart Type:  </label>
    <select id="chart-select" v-model="selectedChartType" v-on:change="handleChange">
      <option v-for="chartType in chartTypes" :value="chartType.googleRef">{{ chartType.name }}</option>
    </select>
  </div>
</template>

<script>
import { eventBus } from '@/main.js'

export default {
  name: 'energy-tracker-chart-select',
  data(){
    return {
      chartTypes: [
        {name: "Column", googleRef: "ColumnChart"},
        {name: "Bar", googleRef: "BarChart"},
        {name: "Pie", googleRef: "PieChart"}
      ],
      selectedChartType: ""
    }
  },
  methods: {
    handleChange: function(){
      eventBus.$emit('chart-select', this.selectedChartType)
    }
  }
}
</script>

<style lang="css" scoped>
</style>
