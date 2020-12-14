# Testing Vue Components

**Lesson Duration**: 90 minutes

### Learning Objectives

- Understand what Jest and Cypress are and how to use them
- Be able to unit test properties and methods.
- Be able to perform e2e testing on ui components

## Intro

In this lesson we are going to learn how to test Vue components.

We will test in 2 ways:

- Unit testing - Test our components and functions work properly.
- e2e testing - Test the full app to make sure it renders correctly.

### Unit testing with Vue test utils and Jest

Jest is a JavaScript testing framework that allows the developer to test API calls, mockups and UI components.

Jest is pretty simple to install on its own. It is also fast. Very fast.

Jest comes with built-in matchers, spies, and its own extensive mocking library.


## Testing our Countries app.

> Download and open the start point.

Take a while to have a look at the code in the project. It is an application similar to the countries app you worked on in the lab but without the neighbouring countries and populations.

Take a particular look at the `app.spec.js` and `countries.js` files in the `tests > unit` folder. 


## Knowing what to unit test

The most common question about unit testing Vue components is "what exactly should I test?"

For unit testing we will test the methods that we have written and their effect on the data. 

For end to end testing we will test the events and the effect on what is rendered. 

We should always start here by identifying the business logic in our app.

For `App.vue` we have:

Unit Tests:

- It should populate a list of countries from API call.
- It should be able to add a selected country to favourites list

E2E tests:

- It should render a dropdown of countries
- On change it should display a selected country.
- On button click it should display the selected country in a favourites list.


## Setting up a unit test

In order to test our app component we will have to mount it so that we have a working instance to test. We can do this in one of two ways.

`Mount` - We can fully mount the component and all sub-components that it renders.

`Shallow Mount` - We can mount only the one component without mounting the sub-components.

For our purposes we will only be testing methods and data relating to App.vue so we will shallow mount it. 

Let's start by importing `shallowMount` from the Vue test utils. 

```js
// app.spec.js

import App from "@/App.vue";
import { countriesData } from './countries.js';
import { shallowMount } from '@vue/test-utils'; // ADDED
```

Now in our `beforeEach` block we can shallow mount the App component. When we shallow mount we are returned a wrapper which contains our virtually mounted component. 


```js
// app.spec.js

let wrapper; // ADDED
beforeEach(()  => {
        wrapper = shallowMount(App); // ADDED
    })
```

If we actually try running the test file now we will get an error: 

```bash
npm run test:unit
```

> Error in mounted hook: "ReferenceError: fetch is not defined"

The trouble here is that when we mount App we are trying to make a fetch request to the countries API. Unfortunately we are working in node and fetch is only available to us in the context of the browser window. 

So how do we get round this?


## Testing where there are Fetch requests

We will have to mock up the fetch request in our App component. 

To do this we can use a library called `fetch-mock` to make a mock request to the same url and return us some dummy data that we can control. 

If you look in the `countries.js` file you will see that we have created an array with a couple of countries in it. We haven't included all the country data... just the properties we will use in our app. 

Let's install `fetch-mock`. As it uses another package called `node-fetch` we will need to install that as well. 

```bash
npm install --save -dev fetch-mock node-fetch
```

And import the `fetch` object from `fetch-mock` into our test file. 

```js
// app.spec.js
...
import { shallowMount } from '@vue/test-utils';
import fetch from 'fetch-mock' // ADDED
```

And now before we mount the App component we can set up our mock request that we want to use. 

We have imported the array from the `countries.js` file so we will send those back as a response from the request. We only need this to happen once so we can use `fetch.once()` to do this passing in the `url` and `response`. 

We will also need to convert the data to a JSON string.

After the wrapper has mounted we will also clear the fetch mock using `reset()`.


```js
// app.spec.js

beforeEach(()  => {
        fetch.once('https://restcountries.eu/rest/v2/all', JSON.stringify(countriesData));
        wrapper = shallowMount(App);
        fetch.reset();
    })

```
So effectively, as far as App is concerned, instead of doing the real fetch to the countries API we will mock the fetch and send back our data instead.

Now if we run the test we should not see that error anymore. 

## Tesing our data!

So what should happen with our dummy data?

Well the app continues as normal so it will get the data from the response and assign it to the countries property of the component. We can test that this has worked by asserting in our test. 

To check that we get the correct assertion we use the in-built `expect` method rather than an `assertEquals`.

We pass the `actual` into the expect then we can call a number of methods to check various conditions.

Some of these methods are:

- `toBe()` Checks equality of a return value
- `toHaveLength()` Checks array lengths
- `toMatch()` Checks for string equality

We will use `toHaveLength()` on the apps countries array and make sure that the 2 countries are there.

To access the data we will next need access to the Vue instance itself. To get access to this we call the `vm` property on our wrapper to access the instance. We can then access our data, props and methods in the instance. In our case the `countries` array.

```js
// app.spec.js

it('should have 2 countries', () => {
        expect(wrapper.vm.countries).toHaveLength(2) // ADDED
    });
```

Let's run our test again.

Aargh we get yet another error! But this time it is an assertion error. We are expecting a length of 2 but getting 0.... why? 

Well the trouble here is an asyncronous issue. The test is running before the mock fetch is completed.

To avoid this we will need to wait for the component to finish updating. 

Enter `async` and `await`....

## async.... await

Like promises we can use `async await` to mark a function as being asyncronous and wait for a specific operation to complete before continuing the code execution. (Like `.then()` does.)

Our wrapper has a `$nextTick()` function that signifies that the component has been updated so we will need to wait until that has been executed. 

When we use await inside a function we have to mark the function as being asynchronous. We use the async keyword before we write the function. In our case it is the callback passed into `beforeEach`.

```js
// app.spec.js

beforeEach(async ()  => { // Modified
        fetch.once('https://restcountries.eu/rest/v2/all', JSON.stringify(countriesData));
        wrapper = shallowMount(App);
        fetch.reset();
    })
```

Now we can use await on the call to `$nextTick()`. We will do this before we reset the fetch mock. 

```js
// app.spec.js

beforeEach(async ()  => { // Modified
        fetch.once('https://restcountries.eu/rest/v2/all', JSON.stringify(countriesData));
        wrapper = shallowMount(App);
        await wrapper.vm.$nextTick() // ADDED
        fetch.reset();
    })
```

Now if we run the test all should be good!

So we have tested that the data is set up OK now let's test the function to add a country to favourites. 

Unskip the second test.

To add a country to favourites we need a selectedCountry. We can set this directly from our test using the wrappers `setData()` function. 

We will set it to the first country in our array of countries. 

We can then check that it has been set OK by checking the names. 

```js
// app.spec.js

 it('should be able to add a favourite', () => {
        wrapper.setData({ selectedCountry: countriesData[0] }) // ADDED
        expect(wrapper.vm.selectedCountry.name).toMatch(countriesData[0].name) // ADDED
    });
```

Run the tests again to see it pass. 

Now that we have a `selectedCountry` let's call the `addToFavourites` function and check that the `favouriteCountries` array has 1 entry. 

```js
// app.spec.js

 it('should be able to add a favourite', () => {
        wrapper.setData({ selectedCountry: countriesData[0] }) 
        expect(wrapper.vm.selectedCountry.name).toMatch(countriesData[0].name) 
        wrapper.vm.addToFavourites() // ADDED
        expect(wrapper.vm.favouriteCountries).toHaveLength(1) // ADDED
    });
```

Run the tests again and we should be all green! 

So we can test any of the data and methods in our component. Next we will test that the UI is working as expected!

> Break!

## Testing the UI.

We are going to run End to End tests on our application. 

End to End Testing, or UI testing is one the many approaches for testing a web application.

An end to end test checks whether a web application works as expected or not, by testing the so called user flow.

To do this we are going to use a tool called Cypress, which we configured when we create our Vue app with the CLI. 

Cypress is an E2E testing framework that allows us to quickly test anything that runs in a browser.

Open up the `app.spec.js` file in the `tests > e2e > specs` folder.

Let's run this file and see what we get.

```bash
npm run test:e2e
```
> Note it may ask you to download the Cypress software. 

When we run this it starts up what is known as the `Cypress test Runner`. It may show some start up tips but we can close this. 

This is where we will spend the majority of our time testing. 

You will see our `app.spec.js` test file listed. If you click on this file it will open a chrome window. In here it will have a pane on the right listing our tests and the results and on the right a browser window. 

What we want to do here is go to our apps url and start to automate some UI interaction. 

In order for Cypress to be able to interact with the browser it gives us access to an object straight out of the box called `cy`. 

`cy` comes with some great functions to allow us to do this. 

The first thing we want to do is visit our app in chrome. 

To do this we will use `cy.visit()`.

As we are running on the same server as vue the route we will hit is simply `/`.

```js
// app.spec.js

  beforeEach(() => {
    cy.visit('/', () => {
        }) // ADDED
  })
```

Now if you go back to the browser window you should see the countries app loaded in! 

Notice that it hits the API and populates the select without having to mock a request as well. 

In fact we can use the app as if it were on our live server. 

But we don't want to have to interact with the UI to get the tests passing. We eant Cypress to automate that!

First we will test that the select has been populated. In order to do this we will need access to the select element. We can do this by calling `cy.get()`. This works similarly to `querySelector` in that we can pass in css identifiers to the function. 

We want to check that we have a populated set of options so we will get those back. 

```js
// app.spec.js

  it('Should have populated select', () => {
    const countrySelectOptions = cy.get('#country_select option')
  })
```

If the API has been called successfully we should have 251 options. (250 countries and 1 default option)

We can test this using a `should()` function on our array, `should()` is availble to us through Cypress. 

`should()` takes in a condition as a string and an expected value. Our condition is `have.length`.

```js
// app.spec.js

it('Should have populated select', () => {
        const countrySelectOptions = cy.get('#country_select option')
        countrySelectOptions.should('have.length', 251)
    })
```

Now if we look at the browser this test should be showing as passed. We can re-run from that browser by clicking the refresh symbol. 

Awesome! We have successfuly tested that our select is populated. Let;s now interact with the browser. 

If we select an option it should render the selected countries details on the page. 

When we get the select we can trigger an event on it called `select` and pass in the option we want to select. Let's choose `France` again.

```js
// app.spec.js

it('should show selected country on select change', () => {
        cy.get('#country_select').select('France') // ADDED
    })
```

Look at the browser. France should now be selected. 

We can also test that the country details render.

```js
// app.spec.js

it('should show selected country on select change', () => {
        cy.get('#country_select').select('France')
        cy.get('#selected_country > h2').contains('France') // ADDED
    })
```

Here we are getting the selected country div by it's ID and accessing the first `h2` element Cypress finds there. 

We can check that the h2 contains the text `France`

A list of interactions with elements can be found [here](https://docs.cypress.io/guides/core-concepts/interacting-with-elements.html#Actionability)

## But tell me why?!

But we can see this in the browser and know it works why am I testing this???

Well you won't always see this browser for a start. When you automate deployment no one is going to sit and watch the browser and make sure. We rely on the tests to make sure it has happened. Also later on you may change something elsewhere and the tests make sure that nothing breaks as a result. You can also test multiple scenarios very quickly. 

## Task

Complete the test `should add country to favourties on button click`.

- Select a country
- Get the button
- Click the button
- Check that country is rendered in the favourites list element. 


<details>
<summary>Solution</summary>
```js
 it('should add country to favourties on button click', () => {
        cy.get('#country_select').select('France')
        cy.get('button').click()
        cy.get('li').contains('France')
    })
```
</details>
<br/>

## Recap

What are some of the advantages of testing?

<details>
<summary>Answer</summary>
Tests help a developer think about how to design a component, or refactor an existing component, and are often run every time code is changed. It also instils confidence in your code.
</details>
<br/>
Which method do we call to get an element back from the DOM?

<details>
<summary>Answer</summary>
`cy.get`
</details>

<br />
When is the `$nextTick` method used for?

<details>
<summary>Answer</summary>
Pauses the code until our DOM has been updated
</details>
<br />

## Conclusion

Now that we can test our Vue components along with state, methods and also UI components there is no stopping us!

A good mindset to have when testing components is to assume a test is necessary until proven otherwise.

Here are the questions you can ask yourself:

- Is this part of the business logic?
- Does this directly test the inputs and outputs of the component?
- Is this testing my code, or third-party code?


## Further Resources

Async - [async-await](https://javascript.info/async-await)  

Vue Test - [vue test utils](https://vue-test-utils.vuejs.org/guides/#getting-started)

Cypress - [Cypress Documentation](https://docs.cypress.io/guides/overview/why-cypress.html#In-a-nutshell)
