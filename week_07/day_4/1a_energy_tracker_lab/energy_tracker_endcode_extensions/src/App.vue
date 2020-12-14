<template>
  <div id="app">
    <h1>Energy Tracker</h1>
    <energy-tracker-date-select></energy-tracker-date-select>
    <energy-tracker-header
      v-if="from && to"
      :from="from"
      :to="to"
    ></energy-tracker-header>
    <energy-tracker-chart-placeholder
      v-if="!from && !to"
    ></energy-tracker-chart-placeholder>
    <loading-indicator v-if="loading"></loading-indicator>
    <energy-tracker-chart
      v-if="mixData"
      :mixData="mixData"
    ></energy-tracker-chart>
    <energy-tracker-chart-select></energy-tracker-chart-select>
  </div>
</template>

<script>
//lodash is an enumeration library that can be installed which will be handy for manipulating the date we get back
import { map, sumBy, flatten, groupBy } from "lodash";
import EnergyTrackerHeader from "@/components/EnergyTrackerHeader";
import EnergyTrackerChart from "@/components/EnergyTrackerChart";
import EnergyTrackerDateSelect from "@/components/EnergyTrackerDateSelect";
import LoadingIndicator from "@/components/LoadingIndicator";
import EnergyTrackerChartSelect from "@/components/EnergyTrackerChartSelect";
import EnergyTrackerChartPlaceholder from "@/components/EnergyTrackerChartPlaceholder.vue";
import { eventBus } from "@/main.js";

export default {
  name: "app",
  data() {
    return {
      from: null,
      to: null,
      mixData: null,
      loading: null
    };
  },
  components: {
    "energy-tracker-header": EnergyTrackerHeader,
    "energy-tracker-chart": EnergyTrackerChart,
    "energy-tracker-chart-select": EnergyTrackerChartSelect,
    "energy-tracker-date-select": EnergyTrackerDateSelect,
    "loading-indicator": LoadingIndicator,
    "energy-tracker-chart-placeholder": EnergyTrackerChartPlaceholder
  },
  mounted() {
    eventBus.$on("dates-selected", dates => {
      this.from = dates.from;
      this.to = dates.to;
      this.loading = true;
      this.fetchMixData(dates);
    });
  },
  methods: {
    fetchMixData: function(dates){
      const url = `https://api.carbonintensity.org.uk/generation/${dates.from}/${dates.to}`;
      fetch(url).then(response => response.json())
      .then((result) => {
        const fuelData = map(result.data, 'generationmix');

        //Turns our array of arrays of objects into one array of objects
        // e.g. [[{a: 1}, {b: 2}], [{c: 3}, {d: 4}}]] => [{a:1}, {b:2}, {c:3}, {d:4}]
        const flattenedFuelData = flatten(fuelData);

        //Groups all our data together as an object by property name
        // e.g. [{a:1}, {b:2}, {a: 3}, {b: 4}] => {a: [{a:1}, {a:3}], b: [{b:2}, {b:4}]}
        const groupedFuelData = groupBy(flattenedFuelData, 'fuel');

        const averagedFuelData = map(groupedFuelData, (array, fuelType) => {
          //sumBy allows us to reduce an array by a specific property
          const totalPercentage = sumBy(array, 'perc');
          const averagePercentage = (totalPercentage/array.length).toFixed(2);
          return {'fuel': fuelType, 'perc': parseInt(averagePercentage)}
        })
        this.loading = false
        this.mixData = averagedFuelData;
      })
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
