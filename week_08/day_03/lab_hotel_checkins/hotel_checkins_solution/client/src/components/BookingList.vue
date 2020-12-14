<template lang="html">
  <section>
    <h2>Booking List</h2>
    <div id="bookings-wrapper">
      <booking-info v-for="(booking, index) in bookings" :key="index" :booking="booking" />
    </div>
  </section>
</template>

<script>
import BookingInfo from '@/components/BookingInfo';

export default {
  name: 'booking-list',
  props: ['bookings'],
  components: {
    'booking-info': BookingInfo
  }
}
</script>

<style lang="css" scoped>
section {
  margin-top: 40px;
}
#bookings-wrapper {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
</style>
