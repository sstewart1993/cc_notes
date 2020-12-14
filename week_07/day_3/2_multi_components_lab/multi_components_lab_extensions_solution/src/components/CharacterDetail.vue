<template lang="html">
  <div v-if="character" id="characterDetail">
    <div id="detailWrapper">
      <h2>{{character.name}}</h2>
      <div id="flexWrapper">
        <div id="left">
          <p><span>Status</span>: {{character.status}}</p>
          <p><span>Species</span>: {{character.species}}</p>
          <p><span>Location</span>: {{character.location.name}}</p>
        </div>
        <div id="right">
          <img :src="character.image" :alt="character.name">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'character-detail',
  props: ['character']
}
</script>

<style lang="css" scoped>
  #characterDetail {
    box-sizing: border-box;
    height: 400px;
    width: 60%;
    background: #eee;
    color: #222;
    padding: 10px;
    border: 1px solid #ccc;
  }

  #detailWrapper {
    box-sizing: border-box;
    background: #fff;
    padding: 10px;
    height: 100%;
    border-radius: 3px;
  }

  #flexWrapper {
    display: flex;
    justify-content: space-between;
  }

  #left, #right {
    width: 45%;
  }

  #right img {
    width: 100%;
  }

  #left p:first-child {
    margin-top: 0;
  }

  h2 {
    margin: 10px 0 20px;
    padding: 0;
  }

  p span {
    font-weight: bold;
  }

</style>
