<template lang="html">
  <form v-on:submit.prevent="handleSubmit">
    <h1>Add a booking</h1>
    <label for="name">Guest Name:</label>
    <input type="text" id="name" name="name" v-model="name" required/>

    <label for="email">Guest Email:</label>
    <input type="email" id="email" name="email" v-model="email" required/>

    <label for="checked_in">Checked In:</label>
    <input type="checkbox" id="checked_in" name="checked_in" v-model="checked_in">

    <input type="submit" name="submit" value="Save" />
  </form>
</template>

<script>
import { eventBus } from '@/main';

export default {
  name: 'booking-form',
  data() {
    return {
      name: '',
      email: '',
      checked_in: false
    };
  },
  methods: {
    handleSubmit() {
      eventBus.$emit('submit-booking', this.$data);
      this.name = this.email = '';
      this.checked_in = false;
    }
  }
};
</script>

<style lang="css" scoped>
form {
  font-size: 16px;
}

input[type="text"],
input[type="email"] {
  padding: 7px;
  margin: 0 10px;
}

input[type="submit"] {
  background: lightblue;
  border-radius: 0;
  border: none;
  font-size: 16px;
  color: #333;
  padding: 5px 10px;
  display: block;
  margin-top: 20px;
  cursor: pointer;
}
</style>
