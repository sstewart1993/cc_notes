# Vue CLI and Templates Introduction

**Lesson Duration: 30mins**

### Learning Objectives

- Understand how to use the Vue CLI to create a boilerplate app template
- Understand how to create a Vue Component

## Introduction

We've seen that manipulating the DOM with JavaScript has a few issues.

- It is repetitive
- It is mistake-prone
- It is verbose and difficult to manage

In addition, it is very challenging to architect larger JavaScript applications, while making sure they are structured properly.

In the last couple of years, a number of frameworks have arisen to solve this problem. These frameworks help us to minimise the type of DOM manipulation calls (`createElement`, `querySelector` and `appendChild`, to name a few) we've been making so far, and in some cases can increase the performance of our applications. Out of the frameworks that have arisen in recent history, React (maintained by Facebook) and Angular (largely maintained by Google) are two of the key players, and the third is [VueJS](https://vuejs.org/).

Vue is a popular, community-driven framework whose job it is to give structure to front-end JavaScript applications. It is an unopinionated library that provides a number of different ways of doing things. This week, we'll look at one of the main approaches to building a Vue application; where our app lives in a seperate component files.

## Single file vs Components

In many Vue projects, components will be defined in a single file by using syntax like: `new Vue({ el: '#container' })`.

This syntax will create a commponent and attach it to an HTML element, such as a `div`, in the body of every page.

This can work very well: In more complex projects however, or when your frontend is entirely driven by JavaScript, this comes with some  disadvantages:

- String templates lack syntax highlighting and require ugly slashes for multiline HTML
- No CSS support means that while HTML and JavaScript are modularized into components, CSS is conspicuously left out
- No build step restricts us to HTML and ES5 JavaScript, rather than preprocessors like Babel
- More complicated than it needs to be to add  testing or to use a pre-processor like SASS or even write in TypeScript.

All of these are solved by single-file components with a .vue extension, made possible with build tools such as Webpack or Browserify.

Luckily for us, Vue comes with a Command Line Interface which is the standard build tool for Vue applications which aims to reduce the amount of configuration the developer has to go through. So we don't need to worry about configuring webpack or setting up a folder structure.

You can read a lot more in depth about this [here](https://cli.vuejs.org/), but essentially the CLI allows us to set up our projects with a number of configuration options. We can save our chosen configuration as a preset, so that we can quickly roll a new app whenever we need to.

## Hello World

Let's build a simple Hello World app to see exactly what this build gives us.

The first thing we need to do is globally install the `Vue CLI` so that we can create our boilerplate builds from anywhere on our computers. 

```bash
npm install -g @vue/cli
```

This gives us the `vue` terminal command. Let's check it's working, by seeing the help that's available for the Vue CLI.

```
vue -h
```

Let's create our app.

```bash
vue create hello_world
```

> At this point, depending on circumstances, the following message might display:

```bash
? Your connection the the default npm registry seems to be slow.
  Use https://registry.npm.taobao.org for faster installation? y/N
```

> Ensure that 'no' is selected here, otherwise the option to create a default configuration for our Vue projects will be bypassed.

Now, we should be asked to choose what features we want to use, in the form of a preset. We're going to create our own.

**Use the arrow keys to select "Manually select features", then press return.**

We can now use the arrow keys and the space bar to choose the features we want to use.

**Make sure that Babel, Unit Testing and E2E Testing are selected, then press return.**

We're then asked to choose where we want to store various pieces of configuration.

Next we are asked to pick a unit testing solution.

**Choose Jest**

Then we need to choose an E2E testing solution

**Choose Cypress**

**Choose "In dedicated config files", then press return.**

We're then asked whether to save our settings as a preset.

**Type "y", then return.**

Next, we're asked what the name of the preset will be.

**Type "CodeClan" without the quotes, then press return.**


At this point, NPM will install our chosen dependencies. Once it's finished, do as the CLI suggests:

```bash
cd hello_world
npm run serve
```

You can then open a browser window at `http://localhost:8080` to see your app. Next time, we won't have to choose a preset, just use the CodeClan one that we've created.

In future you will likely wish to set your own manual configuration, however this is the most basic build we require for the time being.

Open a new tab in your terminal, and open up the project in VS Code. You'll see a few new files.

### How is this working?

Navigate into the `src` folder and open `main.js`.  This is where the app is being kicked off from. 

```js
//main.js
new Vue({
  render: h => h(App),
}).$mount('#app')

```

A new Vue instance has been created and is being placed in the root element of `#app`. This is a div in a main HTML file.  The render function is rendering our app within the `#app` div.

And to do that, Vue has had to be imported.

The import statement is an ES6 feature and works in essentially the same way as `require`, however it gives us more flexibility. To `import` features into a file they have to have been `export`ed elsewhere. `export` allows us to export various functions and objects from the same module, unlike `module.exports` in which you can only export the one object.

Now the confusing bit. `render: h => h(App)`.  This is just shorthand for:

```js
//main.js
new Vue({
  render: function(createElement) {  //MODIFIED
    return createElement(App);
  }
}).$mount('#app')
```

Which can be shortened to

```js
//main.js
new Vue({
  render (createElement) {  //MODIFIED
    return createElement(App);
  }
}).$mount('#app')
```

Which can also be shortened to

```js
//main.js
new Vue({
  render (h) {  //MODIFIED
    return h(App);
  }
}).$mount('#app')
```

> Why h?  h is the common convention alias for createElement in the Vue ecosystem.  
Why?  It comes from the term "hyperscript", which is commonly used in many virtual-dom implementations. "Hyperscript" itself stands for "script that generates HTML structures" because HTML is the acronym for "hyper-text markup language".

Which is then shortened further using es6 fat arrow syntax to

```js
//main.js
new Vue({
  render: h => h(App)  //MODIFIED
}).$mount('#app')
```

You'll notice we are also missing a `DOMContentLoaded` function.  This is a little bit of framework magic where the `$mount` function is attaching our Vue instance to the DOM in the `#app` element.

## Component Structure

Next, let's look at the `App` component which is being rendered.  You'll notice that the file type is a `.vue` file rather than a `.js` file.  It is the `vue-template-compiler` dependency which is converting this back into a plain JavaScript module.

There are three main parts of each `.vue` file.

We have the `template` which is where we'll manage our component's HTML.

Within the `script` tags we have all of our JavaScript functionality.  

Between the `style` tag is where we'll write any css relevant to this component.

### What is a component?

We can think of components as building block of our application. Components are single, independent units of an interface. They can have their own state, layout and style.  Building our apps with components allows us to ensure that we have individual modules of code that have sole responsibility for their aspects of the apps functionality.

### Writing our own components

Now we know how the component structure works, let's write our own.

We'll start off by deleting everything from `App.vue`.  Before we write our own template/script/style structure lets install a very helpful VSCode package called `Vetur`.  This package gives us a shortcut to creating this structure. 

In VS Code select the packages tab on left hand side and search for `Vetur`. Install this package.

> For this to take effect atom will have to be restarted.

Now when we are in a `.vue` file, when we type `vue` you should see the `Vue Component with default` suggestion.  Selecting this will give us the boilerplate setup of a Vue component.

One thing to point out here other than the language attributes in template and style is that the style is `scoped`.  This means that for that component you can target any DOM element and it will only style that element within that component.

For example, if we write a piece of CSS like this:

```css
h3 {
  color: red
}
```

This will only affect any `h3` elements within this component - not the rest of the app! Be careful about this - ensure you organise your CSS properly.

Now, we can see that each component contains its own template (HTML), behaviour (JavaScript), and styling (CSS).

Next we will build our component.

## Recap

How do we start a new Vue app using the CLI?
<details>
<summary>Answer:</summary>

`vue create my_app_name`

</details>
<br />
What is a "preset"?

<details>
<summary>Answer:</summary>

A preset is a collection of settings that allows us to set up a Vue application in our preferred way.

</details>

## Conclusion

We saw that we can use the Vue CLI to quickly create applications with various settings.
