<template lang="html">
  <div class="loading-indicator">
    <h2>Loading</h2>
  </div>
</template>

<script>
export default {
  name: 'loading-indicator'
}
</script>

<style lang="css" scoped>
#loading-indicator {
  width: 800px;
  height: 240px;
  align-items: center;
  align-content: center;
}

body {
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #000;
}

h2 {
  position: relative;
  font-family: sans-serif;
  text-transform: uppercase;
  font-size: 2em;
  letter-spacing: 4px;
  overflow: hidden;
  background: linear-gradient(90deg, #000, #fff, #000);
  background-repeat: no-repeat;
  background-size: 80%;
  animation: animate 3s linear infinite;
  -webkit-background-clip: text;
  -webkit-text-fill-color: rgba(255, 255, 255, 0);
}

@keyframes animate {
  0% {
    background-position: -500%;
  }
  100% {
    background-position: 500%;
  }
}


</style>
