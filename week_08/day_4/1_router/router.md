# Vue Router

**Lesson Duration: 40mins**

### Learning Objectives

- Understand how to navigate SPA with router


## Intro

Vue is great for creating [single page applications](https://en.wikipedia.org/wiki/Single-page_application), or SPAs.

Single page applications load your application once, and rewrite the content of the page dynamically based on the current context of the application.

In order to make this work, you need a way to distinguish the different views or pages from each other. You can do this with conditional rendering, or you can do this with a router. Routers check the route of the application - effectively, the URL - and update the content of the app appropriately.

Vue comes with a fully supported first-party router called [vue-router](https://router.vuejs.org/).

> Instructor note: hand out start code

**Task - Take 5 mins to look over folder structure**

## Views and Components

The start code includes two views, and one component. This begs the question. _What is a view? And what is a component?_

Views can be thought of as pages within your application. When the route (URL) changes, we're going to swap from one view to another.

Components can be thought of as re-usable user interface elements. We can use these to compose our views, with all the benefits that re-usable code brings, such as maintainability, efficiency, and productivity.

In order to switch between views, we need to use vue-router.

There's another piece of new syntax in here that we've not yet seen. `import Header from '@/components/Header';`.  

What does `@` route to?

Under the hood of the Vue cli build, in the webpack config there is a setting which aliases the `src` folder for the `@` symbol.  This means whenever we import from `@` we're looking straight at the src folder, we don't have to worry about relative paths.  


## Installing Router

Let's start off by installing `vue-router` as a dependency.

```bash
npm i vue-router
```

Next we need to create a router configuration file to define our routes.

```bash
touch src/router.js
```

Here, we need to require both `Vue` and `Router` and then tell Vue to use the Router. Next, we're going to set up our `router` object, where we will define our routes.

```js
//router.js
import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router)

const router = new Router({
  routes: [

  ]
})

export default router;

```
If we think back to the wireframe, we have 2 views that we want to be able to navigate between: Home and About. Let's define these routes.

Each element in our `routes` array will be an object, with a `path`, a `name` and the `component` to render.

Let's set up the `Home` route. For this, we will need to require in the `Home` component at the top of the file.

```js
//router.js
import Home from '@/views/Home'; //NEW

Vue.use(Router)

const router = new Router({
  routes: [
    {           //NEW
      path: '',
      name: 'home',
      component: Home
    }
  ]
})

```

The path can be an empty string here, as this will be the landing page when the user hits the home route, e.g. `http://localhost:8080`.

The `name` key makes it easy to create links to routes, as we'll see shortly.

Let's go ahead and add the /about route.

```js
//router.js
import Home from '@/views/Home';
import About from '@/views/About'; //NEW

Vue.use(Router)

const router = new Router({
  routes: [
    {           
      path: '',
      name: 'home',
      component: Home
    },
    // NEW
    {
      path: '/about',
      name: 'about',
      component: About,
    }
  ]
})

```

Now that we've defined our routes we need to give our app access to them. We do this in `main.js`, where we create our Vue instance, by importing the router that defines our routes and passing it into the Vue instance.

```js
//main.js
import Vue from 'vue';
import App from './App.vue';
import router from './router'; //NEW

Vue.config.productionTip = false

new Vue({
  router,   // NEW
  render: h => h(App),
}).$mount('#app')
```

If we navigate to the browser you'll see we're on the home page, but the `Home` component is not rendering.  That's because we need to do one more thing to render the given route. We need to insert a `router-view`.

The `<router-view>` component is a functional component that renders the matched component for the given path.

We'll put this in `App.vue` under the nav tags with the id of view to match the styling from the start point.

```html
<!-- App.vue -->


<template>
  <div id="app">
    <nav>

    </nav>
    <router-view id="view"></router-view> <!-- NEW -->
  </div>
</template>
```

Now in your browser you should see the `Home` component rendering.

To finish off all we need to do is set up our links to navigate to the other pages. We do this with `<router-link>` components.

Within the nav tags let set up these links for the home page and the about page.

```html
<!-- App.vue -->

<template>
  <div id="app">
    <nav>
      <router-link :to="{ name: 'home'}">Home</router-link> <!-- NEW -->
      <router-link :to="{ name: 'about'}">About</router-link> <!-- NEW -->
    </nav>
    <router-view id="view"></router-view>
  </div>
</template>
```

Here we're using the name we've defined in our routes to tell the link which component to navigate to.

> `router-link`s are styled via the `a` tag

We can now navigate between each page, with each page rendering the header component relevant to the page its on.

### Task: 10 minutes

Add a `Contact` page that uses the `Header` component to render information about the page the user is looking at.

Solution:

```js
//router.js
//AS BEFORE
import Contact from '@/views/Contact'; //NEW

Vue.use(Router)

const router = new Router({
  routes: [
    //AS BEFORE
    {  //NEW
      path: '/contact',
      name: 'contact',
      component: Contact
    }
  ]
})

export default router;

```

```html
<!-- Contact.vue -->
<template>
  <div id="">
    <Header title='Contact' />
  </div>
</template>
<script>
import Header from '@/components/Header';

export default {
  components: {
    Header
  }
}
</script>
<style scoped>
</style>
```

```html
<!-- App.vue -->

<template>
  <div id="app">
    <nav>
      <router-link :to="{ name: 'home'}">Home</router-link>
      <router-link :to="{ name: 'about'}">About</router-link>
      <router-link :to="{ name: 'contact'}">Contact</router-link> <!-- NEW -->

    </nav>
    <router-view id="view"></router-view>
  </div>
</template>
```

## Recap

What components do we use from `vue-router` to link to and render our pages?

<details>
<summary>Answer</summary>

`router-link` to act as a navigation link.  `router-view` acts as a container to render the component for the selected link

</details>

<br/>
When we define our routes within our routes array, what three properties should a route have/

<details>
<summary>Answer</summary>

path, name and component

</details>

<br/>
What does the import `@` syntax do?

<details>


<summary>Answer</summary>

It routes direct to the src folder from anywhere in the application.  It also negates the need to use the `.vue` extension

</details>


## Conclusion
In this lesson we've seen how to do client side routing on a single page Vue app via `vue-router`.  This allows us to use the native navigation and refresh buttons.

We've also seen the conventional import syntax used in Vue apps using the `@` symbol.
