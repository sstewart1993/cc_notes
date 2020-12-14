<template>
  <div id="beer-info-container">
    <vuedog-header :title="beer.name" />
    <div id="beer-info">
      <div id="picture">
        <vuedog-beer-image :imageUrl="beer.image_url"></vuedog-beer-image>
      </div>
      <div id="beer-text">
        <p>{{ beer.description }}</p>
        <p>{{ beer.abv }}% ABV</p>
        <div id="ingredientsContainer">
          <div
            class="ingredient"
            v-for="(value, key) in ingredients"
            :key="key"
          >
            <h3>{{ key | capitalize }}</h3>
            <ul>
              <li v-for="(ingredient, index) in value" :key="index">
                {{ ingredient }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import VuedogHeader from "@/components/VuedogHeader";
import VuedogBeerImage from "@/components/VuedogBeerImage";

export default {
  props: ["beer"],
  components: {
    "vuedog-header": VuedogHeader,
    "vuedog-beer-image": VuedogBeerImage
  },
  computed: {
    ingredients() {
      const ingredientData = {};
      for (let ingredientKey in this.beer.ingredients) {
        if (typeof this.beer.ingredients[ingredientKey] === "string") {
          ingredientData[ingredientKey] = [
            this.beer.ingredients[ingredientKey]
          ];
        } else {
          let ingredientsList = this.beer.ingredients[ingredientKey]
            .map(ingredient => ingredient.name)
            .filter(
              (ingredientName, index, array) =>
                array.indexOf(ingredientName) === index
            );
          ingredientData[ingredientKey] = ingredientsList;
        }
      }
      return ingredientData;
    }
  },
  filters: {
    capitalize(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    }
  }
};
</script>
<style scoped>
#beer-info-container {
  width: 55%;
}

#beer-info {
  display: flex;
}

p {
  font: 13px Futura;
}

li {
  font: 10px Futura;
}

#picture {
  display: flex;
  flex-direction: column;
  width: 200px;
  padding: 20px;
}

#ingredientsContainer {
  display: flex;
  justify-content: space-evenly;
  padding-bottom: 60px;
}

#beer-text {
  margin-left: 10%;
  margin-right: 10%;
  word-wrap: normal;
}

img {
  height: 325px;
  width: 150px;
}

h3 {
  font: bold 15px Futura;
}

.ingredient {
  padding-left: 15px;
  padding-right: 30px;
  border-radius: 25px;
  border: 2px solid dodgerblue;
  background-color: white;
}
</style>
