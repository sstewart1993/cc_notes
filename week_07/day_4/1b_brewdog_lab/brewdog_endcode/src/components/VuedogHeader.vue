<template>
  <h2>{{ title }}</h2>
</template>

<script>
export default {
  name: "vuedog-header",
  props: ["title"]
};
</script>

<style scoped>
h2 {
  font: bold 30px Futura;
  color: white;
  background-color: black;
  padding: 5px;
}
</style>
