# Intro to Vue

**Duration: 45 minutes**

### Learning Objectives

- Understand why the need for frameworks has arisen
- Be able to configure and install Vue, with Webpack
- Be able to set up a single page Vue app
- Be able to use events and methods in our apps


## Intro

In this lesson we will set up a really simple "Hello World" example. To do this, we need to understand some of the basics of Vue.

## Two Way Binding

One of the key features of Vue is its use of "Two Way Binding". Let's consider a software application that has two parts:

1. Our view; the part the user sees.
2. Our model, where any logic is carried out.

Two way binding sets up a relationship between the two, so that if the model changes, the view automatically updates! (And vice-versa, changes to the view will be updated to the model. For example, within a form.)

This is _really_ helpful for us - it means that we can work in JavaScript to update our model, and the result will be displayed to the user! To begin with, we'll look at binding our Vue instance to our HTML in one direction - specifically that when our JavaScript changes, our HTML updates. We'll look at how we can change things in the other direction in the next lesson.

Let's see how to set this up, going back to our app.

### Binding Our Vue Instance To Our HTML

It's really easy to set up binding in Vue. Firstly, we're going to declare a dynamically bound variable in the template of our component.

```html
<!-- App.vue -->
<template>
  <div id="app">
  <h1>{{ greeting }}</h1>
</div>
</template>

```

Next, we're going to set up the variable on our Vue instance. We need to declare a function called `data`, and set it to return an object.  You will also see this data object referred to as `state`.

By returning an object from our data function it ensures that any change made to this data remains local to that instance of the component.

```js
//App.vue

<script>
export default {
  data(): {
    return {

    }
  }  // ADDED
} 
</script>
```

Within this `data` object, we can set any variables we want to use in the DOM:

```js
//App.vue

<script>
export default {
  data(){ 
    return {
      greeting: 'Hello World' // ADDED
    }
  }
}  
</script>
```

Now, if we refresh the page, we should see our greeting. That's a lot of work to set up a "Hello World". What have we gained here?

Any changes we make to the `greeting` variable will immediately be reflected in our app! To see the power of this, let's look at events.

## Capturing your first event

So far, our `App.vue` file is pretty straightforward, just consisting of a single dynamically bound variable. Our Vue templates can also consist of HTML, as normal. Let's adjust our HTML to demonstrate.

```html
 <!-- App.vue -->
<template>
<div id="app">
  <h1>{{ greeting }}</h1>
  <button>Change Greeting</button> <!--NEW-->
</div>
</template>
```

So we've added a few HTML elements; a heading, and a button. Our next task is to make something happen when we click on the button. We know how to do this with plain JavaScript - we can use `addEventListener` to hook into certain events. In Vue, it's arguably easier. Let's add an event listener to the button directly.

```html
<!-- App.vue -->

<div id="app">
  <h1>{{ greeting }}</h1>
  <button v-on:click="updateGreeting">Change Greeting</button> <!-- MODIFIED -->
</div>
```

`v-on:` is a Vue _directive_. There are lots of directives, but `v-on:` lets us listen for events. The second part tells view that we're listening for a `click` event, and this can be any of the events we've seen so far - `click`, `submit`, `change` etc.

You should note that as well as using `v-on:click`, you might see the following too:

```html
<!-- App.vue -->

<div id="app">
  <h1>{{ greeting }}</h1>
  <button @click="updateGreeting">Change Greeting</button> <!-- MODIFIED -->
</div>
```

In this form of the syntax, we're simply replacing the `v-on:eventName` with `@eventName`. We'll be using `v-on:click` in our course material, but you can use whatever syntax you're more comfortable with.

There are lots of other directives, but for now, let's focus on events.

We've said that when the button is clicked, we should execute a function called `updateGreeting`. Where should this be written?

We need to declare any methods we want to use on our Vue object, as follows:

- Add an object to your Vue instance, with the key of `methods`
- write any methods you need here as keys / values

We can comma separate our functions and objects on our Vue instance. 

```js
//App.vue

<script>
export default {
  data(){
    return {
      greeting: "Hello World"
    }
  },
  methods: {
    updateGreeting: function(){
    this.greeting = "Changed!"
    }
  }
}
</script>
```

Notice that within these methods, we need to refer to `this.variableName` to get access to the `greeting` variable. So `this.greeting`, for example.

We can try this out - go to your browser window and click the button!

### Arguments

We can also pass arguments to the methods we call.

```html
<!-- App.vue -->

<button v-on:click="updateGreeting('John')">Change Greeting</button> <!-- MODIFIED -->
```

We have to watch out for single / double quotes here! Finally, let's update our method.

```js
//App.vue

methods: {
  updateGreeting: function(name) {
    this.greeting = "Hello, " + name; // MODIFIED
  }
}
```

So we've fulfilled the promise of two-way binding: _when we change the model variable, our view automatically updates_. Whatever the `greeting` key contains, that's what will be displayed in the template.

## Recap

What is the benefit of the two-way binding our Vue instance affords us?

<details>

<summary>Answer</summary>

- It sets up a relationship between our view (the DOM) and model (the data in our Vue instance), so that if the model changes, the view automatically updates.

</details>



## Conclusion

Using Vue allows us to focus on the _logic_ of our application, rather than on boilerplate DOM manipulation. Through the power of two-way binding, we can think in terms of our JavaScript, allowing our views to take care of themselves.

Next, we'll take a look at a few more directives, and see how can work with arrays, loops, and conditionals.
