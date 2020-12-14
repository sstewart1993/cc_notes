<template lang="html">
	<div id="sightingsGrid">
		<sighting v-for="sighting, index in sightings" :key="index" :sighting="sighting" />
	</div>
</template>

<script>
import Sighting from './Sighting';

export default {
  name: 'sightings-grid',
  components: {
		'sighting': Sighting
	},
	props: ['sightings']
}
</script>

<style lang="css" scoped>
#sightingsGrid {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-evenly;
}
</style>
