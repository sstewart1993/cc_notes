<template lang="html">
  <div id="app">
    <h1>My Shopping List</h1>
    <ul>
      <shopping-list-item v-for="(item, index) in items" :key="index" :item="item" ></shopping-list-item>
    </ul>
  </div>
</template>

<script>
import ShoppingListItem from './components/ShoppingListItem.vue';

export default {
  name: 'app',
  data() {
    return {
      items: [
        {name: 'Milk', isPurchased: false},
        {name: 'Cheese', isPurchased: true},
        {name: 'Beans', isPurchased: false},
      ]
    };
  },
  components: {
    'shopping-list-item': ShoppingListItem
  }
}
</script>

<style>
#app {
  width: 60%;
  margin: 0 auto;
  font-family: 'Lato', sans-serif;
}

ul {
  margin: 0;
  padding: 0;
  list-style-type: none;
}


input[type="text"] {
  padding: 10px;
  width: 50%;
  margin:5px;
}

button, input[type="submit"]{
  padding: 10px;
  background: #000;
  color: #fff;
  cursor: pointer;
  border: 1px solid #000;
}
</style>
