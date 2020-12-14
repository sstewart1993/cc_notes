<template>
  <div id="app">
    <sightings-form />
    <sightings-grid :sightings="sightings"/>
  </div>
</template>

<script>
import { eventBus } from './main';
import SightingsForm from './components/SightingsForm';
import SightingsGrid from './components/SightingsGrid';
import SightingService from './services/SightingService'

export default {
  name: 'app',
  components: {
    'sightings-form': SightingsForm,
    'sightings-grid': SightingsGrid
  },
  data() {
    return {
      sightings: []
    };
  },
	mounted() {
    this.fetchSightings();

    eventBus.$on('submit-sighting', payload => {
      SightingService.postSighting(payload)
				.then(sighting => this.sightings.push(sighting));
    });

    eventBus.$on('delete-sighting', id => {
      SightingService.deleteSighting(id)
        .then(() => {
          const index = this.sightings.findIndex(sighting => sighting._id === id);
          this.sightings.splice(index, 1);
        });
    });
  },
  methods: {
    fetchSightings() {
      SightingService.getSightings()
        .then(sightings => this.sightings = sightings);
    }
  }
}
</script>

<style>
html {
  height: 100%;
}

body {
  background: url('./assets/birds-background.jpg') no-repeat;
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
