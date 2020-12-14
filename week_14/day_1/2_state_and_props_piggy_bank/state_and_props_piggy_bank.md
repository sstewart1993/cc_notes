# Piggy Bank Application

**Duration: 90 minutes**

### Learning Objectives

- Be able to create a single component application
- Be able to use the JSX syntax
- Use props and state to managing data flow in the application
- Install and be able to use React Dev Tools

## Introduction

In this lesson we are going to build a Piggy Bank application that displays a total and allows a user to deposit and withdraw an amount, which will update the total. We want the the total to change in response to a user's interaction and we will use the React framework to do what it does so well - manipulate the DOM easily and efficiently.

Previously we have been dividing up our view logic into separate view files to keep our applications modular, and working with React is no different. Throughout the week you will be separating your UI into separate view files, which in React are called `Component`s. A `Component` is part of the React framework, and its role is to display a section of our user interface. Well-designed React applications have many small components with a single responsibility. However, in this lesson we will be focussing on learning the syntax used by React, so we will only have one component that describe the UI.

The component in our application will be responsible for displaying and keeping track of the total, as well as displaying a 'deposit' and 'withdraw' button that will update the state of the component with clicked, updating the displayed total. The React `Component` has some methods that will help us do this.

## Create Piggy Bank Application

Let's begin by using create-react-app to give us a start-point.

```sh
npx create-react-app piggy_bank
cd piggy_bank
npm start
```

And we will delete the boilerplate code we don't need:

1. the logo file
2. the logo input in App.js
3. the JSX from App.js' return method

```sh
rm src/logo.svg
```

```js
// src/App.js

import React from 'react';
// DELETED
import './App.css';

function App() {
  return (
    // ...
  );
}

export default App;
```

If we look at the function's return statement, we can see there is some JSX there. We are going to be taking a more in-depth look at JSX soon, but for now let's delete the boilerplate:

```js
import React from 'react';
import './App.css';

function App() {
  return (
    // DELETED
  );
}

export default App;
```

We'll replace it with a simple "Hello World!" to ensure we don't have any errors at this stage.

```js
import React from 'react';
import './App.css';

function App() {
  return (
    <h1>Hello, World!</h1>			// ADDED
  );
}

export default App;
```

We have seen how to render to the page using React. Let's create a PiggyBank component that is going to be responsible for rendering the UI. Our component rendering hierarchy is going to be `App` renders `PiggyBank`.

### Creating a Component

Let's create a component which will show the details of our savings:

```sh
touch src/PiggyBank.js
```

We need to import the `React` module, and also `export` our component

```js
// src/PiggyBank.js

import React from "react";

const PiggyBank = () => {

}

export default PiggyBank;
```


Remember that our Component must return a DOM element, an array of DOM elements or null. Here we'll return a single element:

```js
// src/PiggyBank.js

import React from "react";

const PiggyBank = () => {
  return(								// ADDED
    <h1>I'm a PiggyBank!</h1>			// ADDED
  )										// ADDED
}

export default PiggyBank;
```

This will not be rendered to the page, until is it rendered by another component. To add it to the rendering hierarchy, we need to tell the `App` component to render it.

Next, we need to import and use our PiggyBank into our App.js file.

```js
// src/App.js

import React from 'react';
import './App.css';
import PiggyBank from "./PiggyBank";	// ADDED

function App() {
  return (
    <PiggyBank />						// CHANGED
  );
}

export default App;
```

> Note: all JSX tags must be closed, either with a separate closing tag or self-closed.

We should now see that the PiggyBank is being rendered on the page.

## Props and State

Our PiggyBank is currently great for displaying static information, but that's not what we had in mind for it. We want to display information about our savings, which means we need to investigate how we transfer data between different parts of our application. Components have two attributes that they can use to receive and store data respectively: **props** and **state**.

### Props (or Properties)

Props (or properties) are attributes that are given to a component by their parent component. Props cannot be changed, they are *immutable*. Props enable us to pass data from one component to another. Let's pass a `title` attribute from the `App` component to `PiggyBank` component, so that `PiggyBank` can display it.

We define the prop in JSX like we set attributes on an HTML component, but we get to define the name of the prop (we will use 'title'). We can either pass the prop as a string or use the braces (`{}`) to pass in other JavaScript values.

```js
// src/App.js

import React from 'react';
import './App.css';
import PiggyBank from "./PiggyBank";

function App() {
  return (
    <PiggyBank title="Savings Pig"/>		// CHANGED
  );
}

export default App;
```

#### Destructuring

When we pass props to a component like this we can pass as many or as few as we like. Regardless of how many there are, they are sent to the child component in an object called `props`. This is just a JavaScript object and can be accessed in the same way as any other, ie. to get to our PiggyBank's `title` prop we would look for `props.title`. This is fine for a small app like this, but as our apps get larger and we pass more and more props to our component it will hinder both reading and writing our code if we have to write `props.ourProp` everywhere.

*Destructuring* will help us here. By destructuring the `props` object when we define our component's parameters we can access them simply by typing the name of the prop. With our PiggyBank we do this by placing `title` within braces in the function definition, then simply using `title` where we want to access it:


```js
// src/PiggyBank.js

import React from "react";

const PiggyBank = ({title}) => {        // CHANGED
  return(
    <h1>{title}</h1>                    // CHANGED
  )
}

export default PiggyBank;
```

Note that we can't simply write "title" as a string. In this case `title` is the value of the `title` key in our `props` object, ie. some JavaScript. If we want to use a JavaScript value inside JSX we need to enclose it in braces as we've done here.

## State: Displaying a total

State is where a component can store data. State, unlike props, is defined in the component itself and can be changed. When state is changed, React re-renders the page. Changing (or setting) the state to cause a re-render is the preferred method for quickly and easily updating the page.

We are going to use state to store the PiggyBank's total. The total is a value that will be updated and when it does (with a user clicking either deposit or withdraw) we want to re-render the page to display the new value.

To help us manage our state we're going to use a [hook](https://reactjs.org/docs/hooks-intro.html). Hooks are a fairly recent introduction to React, but they streamline many processes which previously required many lines of code and different methods. We'll start by using `useState()` to manage our PiggyBank's state.

The `useState` hook comes from `react`, and so we'll need to import it. Here we'll use destructuring again so that we don't have to write `React.useState()` every time:

```js
// src/PiggyBank.js

import React, {useState} from "react";      // CHANGED

const PiggyBank = ({title}) => {        
//... AS BEFORE    
```

We set up our initial state by calling the `useState` function and passing the desired value in to it. This could be anything: a string, a number or even an object -- in our case we'll use the number 0.

```js
// src/PiggyBank.js

import React, {useState} from "react";

const PiggyBank = ({title}) => {        
    
  const [total, setTotal] = useState(0);        // ADDED
    
  return(
    <h1>{title}</h1>                    
  )
}

export default PiggyBank;
```

Note how we're storing the values we get back from `useState`. It returns two things in an array: a variable storing the value of our state and a function to update it. Just like we do with our `props` object, we can destructure this to enable us to directly access the values.

We *could* call these variables anything we want: but it is convention, and good practice, to prefix the function name with "set" to make clear what it will be used for:  `[variableName, setVariableName]`.

We can display this on the page simply by using the `total` variable. Because a React Component must either return one DOM element, or an array of DOM elements, we need to wrap our two separate elements in a single element. We could use a `div` for this, but instead we will use a React **Fragment**.

### Fragments

We can think of Fragments as a way of grouping DOM elements together without adding another DOM element to the page. They don't provide any more functionality than `div`s, and they can't be styled with CSS. However, they provide the following advantages:
 - Fragments do not clutter the DOM by creating an overly complex element heirarchy. As well as keeping things tidy, this will (marginally) speed up render time.
 - Fragments are seen as significantly more accessible to users using screen readers.

We *could* explictly import `Fragment` in our code as follows: 

```js
// src/PiggyBank.js

import React, {useState, Fragment} from "react";        // CHANGED

const PiggyBank = ({title}) => {

  const [total, setTotal] = useState(0);

  return(
    <Fragment>                                           // ADDED
      <h1>{title}</h1>
      <p>Total: £{total}</p>                            // ADDED
    </Fragment>                                         // ADDED
  )
}

export default PiggyBank;
```

However, they have become so commonly used that React's developers have made it possible to simply use a set of angle brackets `<>` to denote a fragment:

```js
// src/PiggyBank.js

import React, {useState} from "react";        // CHANGED

const PiggyBank = ({title}) => {

  const [total, setTotal] = useState(0);

  return(
    <>                                                  // CHANGED
      <h1>{title}</h1>
      <p>Total: £{total}</p>
    </>                                                 // CHANGED
  )
}

export default PiggyBank;
```

## Updating State

Now we want to add a button that will increase the total when we deposit money into the piggy bank. Let's start by adding a 'Deposit' button to the `render` method.

```js
// src/PiggyBank.js

//...
  return (
    <>
      <h1>{title}</h1>
      <p>Total: £{total}</p>
      <button>Deposit</button>              // ADDED
    </>
  );
```

When a user clicks on this button, we want an amount to be added to the total, and for this change to be reflected on the page. We can trigger this rerendering of the page by updating the state with the method `useState` provided for us: `setTotal`. Every time we call this method the page will be updated to reflect the change.

It might seem inefficient to render every time, but remember React uses the virtual DOM to only update the DOM elements that have changed, keeping it highly performant. There is where we start to see the real power of React. Let's write the method that is going to be responsible for incrementing the total by calling `setTotal`.

```js
// src/PiggyBank.js

import React, {useState, Fragment} from "react";

const PiggyBank = ({title}) => {

  const [total, setTotal] = useState(0);

  const deposit = () => {                           // ADDED
    setTotal(total + 10);                           // ADDED
  }                                                 // ADDED

  return(
    <>
      <h1>{title}</h1>
      <p>Total: £{total}</p>
      <button>Deposit</button>
    </>
  )
}

export default PiggyBank;
```

`setTotal` takes a value that the `total` value will be updated to. In this case we're taking the previous value and adding 10 to it.


Now we call this whenever the button is clicked using the `onClick` attribute.

```js
// src/PiggyBank.js

// ...

return(
    <>
      <h1>{title}</h1>
      <p>Total: £{total}</p>
      <button onClick={() => deposit()}>Deposit</button>		// CHANGED
    </>
  )
```


# Chrome Dev Tools

An other advantage of React is that there are powerful development tools in chrome. Download the following Chrome Dev Tool extension and you will have a new 'React' tab in the dev tool. (You might need to reopen the Chrome Dev Tools window after installing it).
[Link to React-Dev-Tools] (https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi?hl=en)

By clicking on the different components in the React Dev Tools we can see any props or state that they have. You can also see it being updated live as the state changes.

### Tasks: (15 minutes)
1. Define the amount to be deposited in the `App` component and pass it as a prop to the `PiggyBank` component. `PiggyBank`'s deposit method should then use the amount passed down.
2. Add a 'withdraw' button which decreases the total by the amount defined and passed down from the `App` component, preventing it from going below 0.

### Solutions:

1. Define the amount to be deposited in the `App` component and pass it has a prop to the `PiggyBank` component. `PiggyBank`'s deposit method should then access the amount off it's props.

```js
// src/App.js
<PiggyBank
  title="Savings Pig"
  depositAmount={5} // NEW
/>

// src/PiggyBank.js
deposit() {
  setTotal(total + depositAmount);
}
```

2. Add a 'withdraw' button which decreases the total by the amount defined and passed down from the `App` component, preventing it from going below 0.

```js
// src/PiggyBank.js

const PiggyBank = ({title, depositAmount}) => {
    // ...


const withdraw = () => {
  let newAmount = total - depositAmount;

  if(newAmount < 0){
    newAmount = 0;
  }

  setTotal(newAmount);
}
```

## Recap

<details>
<summary>
Which three things must a React component return one of?
</summary>
- A JSX element
- A collection of elements
- null
</details>

<details>
<summary>
Name two differences between props and state?
</summary>
1. Props are passed down from parent to child component, whereas state is initialised within a component.
2. Props are not changed throughout the lifetime of the component, whereas state can be updated to reflect the current state of the application.
</details>

<details>
<summary>
How do we trigger a rerendering in React?
</summary>
By updating the component's state
</details>

# Conclusion

React allows us to render our UI as components, providing structure to our front end. It’s use of the virtual DOM and rerendering triggered by updating state method makes updating the DOM really efficient.

We can now create single component applications, using JSX syntax to render information to the screen. We have seen how to trigger a rerender of the UI by updating a component's state on user interaction. We have also seen how to pass props down from a parent component.

Lastly, React Dev Tools offers a way of inspecting components' props and state during development.

## Additional Resources

JSX Gotchas - https://shripadk.github.io/react/docs/jsx-gotchas.html
