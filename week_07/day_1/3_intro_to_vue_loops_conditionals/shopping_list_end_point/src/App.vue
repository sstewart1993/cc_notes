<template>
  <div id="app">
    <ul>
      <li v-for="(item, index) in items" :key="index" :class="item.isPurchased ? 'purchased':'not-purchased'">
        <span>{{ item.name }}</span>
        <span v-if="item.isPurchased">Purchased!</span>
        <button v-if="!item.isPurchased" v-on:click="buyItem(index)">Purchase</button>
      </li>
    </ul>

    <form v-on:submit.prevent="saveNewItem">
      <label for="new-item">Add a new item:</label>
      <input id="new-item" type="text" v-model="newItem" />
      <input type="submit" value="Save New Item" />
    </form>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      items: [
        //MODIFIED
        { name: "Milk", isPurchased: false },
        { name: "Cheese", isPurchased: true },
        { name: "Beans", isPurchased: false },
      ],
      newItem: "",
    };
  },
  methods: {
    saveNewItem: function () {
      this.items.push({ 
        name: this.newItem,
        isPurchased: false
      });
      this.newItem = "";
    },
     buyItem: function(index){
      this.items[index].isPurchased = true;
    }
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

li {
  margin: 20px 0;
  padding: 10px;
  display: flex;
  justify-content: space-between;
}

li span {
  padding: 8px;
}

li button {
  background: #f2360c;
  color: #fff;
  border: none;
  padding: 10px;
  cursor: pointer;
}

li.purchased {
  border: 2px solid #1a681e;
  color: #1a681e;
}

li.not-purchased {
  border: 2px solid #f2360c;
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
