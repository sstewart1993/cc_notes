<template lang="html">
  <img :src="checkForImage"/>
</template>

<script>
import noImage from '@/assets/noimgfnd.jpg'

export default {
  name: 'vuedog-beer-image',
  props: ['imageUrl'],
  computed: {
    checkForImage(){
      return this.imageUrl != undefined ? this.imageUrl : noImage;
    }
  }
}
</script>

<style lang="css" scoped>

img {
  height: 100px;
  width: 33px;
  padding-bottom: 10px;
}

</style>
