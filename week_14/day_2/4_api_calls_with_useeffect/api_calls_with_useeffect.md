# React in Space

## Learning Objectives

- Be able to make HTTP requests to fetch data inside a React component
- Further practice passing around data between React components
- Use the `useEffect` hook to respond to changes in our application's state

## Intro

We can make API calls ("fetches", "requests") using React. To do this we'll use the `useEffect` hook to enable us to make the request at the right time.


 By the end of this lesson we'll have an app which will fetch new information whenever part of our apllication's state changes.

## Design

We are going to build an app that makes requests to the [SpaceX REST API](https://github.com/r-spacex/SpaceX-API) and displays information about their rocket launches. 

The user will be able to click buttons to move on to the next launch or back to the previous one, with a fresh API call being made each time. Let's think about the components we might need to make this, and what state and props each would require.

> Discuss design and get to a structure that looks something this.

- LaunchContainer 
	- state: launch, selectedLaunch
- LaunchSelector

- LaunchDetail 
	- props: launch

## Implementation

Let's start by creating a React project with Create React App. We'll set up our directory structure and create some components.



```sh
npx create-react-app spacex
cd spacex
rm src/logo.svg
rm src/App.css
mkdir src/components
touch src/components/LaunchDetails.js
```

```javascript
// src/components/LaunchDetails.js

import React from "react";

// This component will allow us to show details about a launch
const LaunchDetails = () => {

  return (
  	<p> Launch details go here </p>  
  )

}

export default LaunchDetails;
```

```sh
touch src/components/LaunchSelector.js
```

```javascript
// src/components/LaunchSelector.js

import React from "react";

// This component will allow us to select different launches
const LaunchSelector = () => {

  return (
  	<p> Launch selector goes in here </p>
  )

}

export default LaunchSelector;
```

```sh
mkdir src/containers
touch src/containers/LaunchContainer.js
```

```javascript
// src/containers/LaunchContainer.js

import React from "react";
import LaunchSelector from "../components/LaunchSelector";
import LaunchDetails from "../components/LaunchDetails";

const LaunchContainer = () => {

  return (
    <>
      <h1>SpaceX Launch Details</h1>
      <LaunchSelector />
      <LaunchDetails />
    </>
  )

}

export default LaunchContainer;
```

```javascript
// src/App.js

import React from 'react';
// DELETED
import LaunchContainer from "./containers/LaunchContainer";			// NEW

function App() {
  return (
    <LaunchContainer />						// MODIFIED
  );
}

export default App;

```


Let's look at our `LaunchContainer`, which will be our main parent component. This should control the state of our application. Let's set up the initial state of the Container so that it has variables representing the launch we want to display and its ID number according to the API. 

```js
// LaunchContainer.js

import React, {useState} from "react;			// MODIFIED

const LaunchContainer = () => {

  const [launch, setlaunch] = useState({});						// NEW
  const [selectedLaunchId, setSelectedLaunchId] = useState(1);	// NEW
  
// ...

```

We'll start with an empty object for our launch (since we haven't got it from the API yet) and an ID of 1. We've rendered this component in `App`, so we can use the React Dev tools in the console to check that it has initialised correctly.

## Getting Data from an API

We've set up a place to store our launch information, now we need to actually get it. We can still use `fetch` like we did before:

```js
// src/containers/LaunchContainer.js

//...

const getLaunch = () => {
	console.log("getting launch information");
    fetch(`https://api.spacexdata.com/v3/launches/${selectedLaunchId}`)
      .then(res => res.json())
      .then(data => setLaunch(data));
  }

```

This use the JS built in JS `fetch()` will make a GET request to the API to get information about a specific launch. When calling `fetch()` the default HTTP verb is `GET`.

 When the response comes back we deserialise the JSON then use the function provided by `useState` to store it in state. We've added a `console.log` for now so that we can see when the API is being called.

This function will get our launch data, but how do we call it? Once again, hooks to the rescue! The `useEffect` hook takes a callback which will fire when one of a set of given values change, which sounds useful here. Let's add it to our container:

```js
// src/containers/LaunchContainer.js

import React, {useState, useEffect} from "react;			// MODIFIED

//...

  useEffect(() => {
    getLaunch();
  });

//...

```

Now let's check the console and make sure `getLaunch()` is being called!

> At this point, the console log should be firing repeatedly. LaunchContainer's state should also have been updated with some data

Well, it's a start. We're making a request to the API and our state is updating with the data it returns, but we keep calling the function over and over again. That's not great for anyone: constantly resetting state and rerendering will put a strain on our system, not to mention the effects on the API's server.

We mentioned earlier that `useEffect` would only fire its callback when certain values changed, and currently we're not providing any: that's why it's constantly firing. We can limit it to firing once by giving a second argument to `useEffect`: an empty array.

```js
// src/containers/LaunchContainer

//...

  useEffect(() => {
    getLaunch();
  }, []);							// MODIFIED

//...

```
Now we should only see our `console.log` firing once.


## Requesting a Different Launch

We don't want to limit our users to only ever seeing information about the first SpaceX launch, we want to let them find out about all of the launches. We're going to add buttons to our app to let the user view the previous or next launch to the one currently displayed.

Adding the buttons themselves is a simple matter of adding them to the JSX returned from `LaunchSelector`:

```js
// src/components/LaunchSelector.js

import React from "react";

const LaunchSelector = () => {

  return (
    <>
      <button>Previous Launch</button>			// NEW
      <button>Next Launch</button>				// NEW
    </>
  )

}

export default LaunchSelector;

```

We can see our buttons on screen, but clicking them doesn't do anything yet. Just like before we can add an `onClick` listener to make them do something, but how do we make our `LaunchSelector` component update the state of its parent?

We'll define the functions in `LaunchContainer`, then use props again to pass them down to `LaunchSelector`. Those functions will use the method we get from `useState` to update our `selectedLaunchId` value. To increment that value we use:

```js
// src/containers/LaunchContainer.js

  const incrementSelectedLaunch = () => {
      setSelectedLaunchId(selectedLaunchId + 1);
  }

```

### Task (10 minutes)

1. Add a method `decrementSelectedLaunch` to decrease `selectedLaunchId` by 1
2. Pass the functions in props to `LaunchSelector`
3. Add `onClick` listeners to the buttons which will call the functions passed down
4. There have only been 90 SpaceX launches to date. Add guards to the functions to ensure the user can't ask for a launch that doesn't exist.

<details>
<summary>Solution</summary>

```js
// src/containers/LaunchContainer.js

  const incrementSelectedLaunch = () => {
    const nextLaunchIndex = selectedLaunchId + 1;

    if (nextLaunchIndex <= 90){
      setSelectedLaunchId(nextLaunchIndex);
    }
  }

  const decrementSelectedLaunch = () => {
    const nextLaunchIndex = selectedLaunchId - 1;

    if (nextLaunchIndex >= 1){
      setSelectedLaunchId(nextLaunchIndex);
    }
  }
  
    return (
    <>
      <h1>SpaceX Launch Details</h1>
      <LaunchSelector 
        onSelectedLaunchIncrement={() => incrementSelectedLaunch()}
        onSelectedLaunchDecrement={() => decrementSelectedLaunch()}
      />
      <LaunchDetails />
    </>
  )

```

```js
// src/components/LaunchSelector

const LaunchSelector = ({onSelectedLaunchIncrement, onSelectedLaunchDecrement}) => {			// MODIFIED

  return (
    <>
      <button onClick={onSelectedLaunchDecrement}>Previous Launch</button>		// NEW
      <button onClick={onSelectedLaunchIncrement}>Next Launch</button>			// NEW
    </>
  )

}
```
</details>

> Check using the dev tools that `selectedLaunchId` is updating correctly and staying in the [1, 90] range.

So now we can change `selectedLaunchId` by clicking the buttons in `LaunchSelector` and it can't be updated to a value which doesn't have a corresponding launch. The object stored in state under `launch` isn't updating though -- why not?

It comes down to our `useEffect` hook again. We set it up to prevent the infite API calls by adding an empty array when we call the hook, but it can do more than that. That array should contain all the variables which should trigger the callback. When we leave it empty we are saying that there is nothing which will cause it to fire. However, the function will always be called once when the page first loads regardless of any values specified.

So that's why we still get some launch data even though we haven't told `useEffect` when to call `getLaunch`. If we want to call `getLaunch` when `selectedLaunchId` changes, we need to add `selectedLaunchId` to the array inside `useEffect`:

```js
// src/containers/LaunchContainer.js

//...

  useEffect(() => {
    getLaunch();
  }, [selectedLaunchId]);			// MODIFIED
  
//...

```
Now when we click the buttons, we see the `launch` object updating!

## Displaying the Launch Details

We'll display the details of our chosen launch in the same way as we displayed comments in the previous lessons. First we need to pass the details to the `LaunchDetails` component:

```js
// src/containers/LaunchContainer.js

// ...
  return (
    <>
      <h1>SpaceX Launch Details</h1>
      <LaunchSelector 
        onSelectedLaunchIncrement={() => incrementSelectedLaunch()}
        onSelectedLaunchDecrement={() => decrementSelectedLaunch()}
      />
      <LaunchDetails 
        launch={launch}							// NEW
      />
    </>
  )

```

The launch details returned from the API are now available in the `LaunchDetails` component. Let's display the name of the mission:

```js
// src/components/LaunchDetails.js

const LaunchDetails = ({launch}) => {			// MODIFIED

  return (
    <>
      <h3>{launch.mission_name}</h3>			// MODIFIED
    </>
  )

}

```

We can see the name of the mission rendered as expected. What happens if we try to display the name of the rocket, though?

```js
// src/components/LaunchDetails.js

// src/components/LaunchDetails.js

const LaunchDetails = ({launch}) => {

  return (
    <>
      <h3>{launch.mission_name}</h3>	
      <p>Rocket: {launch.rocket.rocket_name}</p>
    </>
  )

}

```

> Should see an error in the console from trying to access `rocket_name` on `undefined`

That's not good, something's gone wrong. Why could we access `mission_name`, but `rocket` was undefined? When we look at the object in the dev tools they're both there, so what's going on?

You may have noticed that when the page first loaded the `LaunchDetails` component was briefly empty, before it refreshed with the mission name. It might seem strange but is in fact entirely normal, and is a result of how `useEffect` fits in to the larger React picture.

Before the callback we define in `useEffect` is called, we need to create the component using it. Part of creating that class is creating its children, which will be passed elements from our state. That means that the first time we pass `launch` to `LaunchDetails` it's still an empty object, and so *everything* we try to access from launch is undefined.

That's not a problem when we're only going one level deep, since we can still render `undefined` on the screen (it's just like trying to render `null` or an empty string). When we run into problems is when we try to do anything more complex with `undefined`, such as accessing a `rocket_name` key.

To address this we can add a `loaded` property to our state, pass it to `LaunchDetails` and only render any information if `loaded === true`. We won't set the property to `true` until we've got our data back from the API and have something to render.

```js
// src/containers/LaunchContainer.js

// ...

  const [loaded, setLoaded] = useState(false);			// NEW
  
// ...
  
  const getLaunch = () => {
    console.log("getting launch data");
    
    fetch(`https://api.spacexdata.com/v3/launches/${selectedLaunchId}`)
      .then(res => res.json())
      .then(data => setLaunch(data))
      .then(() => setLoaded(true))						// NEW
  }
  
 // ...
 
   return (
    <>
     // ...
      <LaunchDetails 
        launch={launch}
        loaded={loaded}									// NEW
      />
    </>
  )

```

```js
// src/components/LaunchDetails.js


const LaunchDetails = ({launch, loaded}) => {			// MODIFIED

  if (!loaded){											// NEW
    return <p>Loading...</p>
  }
  
  // ...

```

Now we should be able to see our rocket name!

### Task (10 minutes)

* Either by checking `launch` in state or by making a request to the API in the browser, further investigate the JSON returned
* Add some more information to the `LaunchDetails` component

<details>
<summary>Example</summary>

```js
// src/containers/LaunchDetails.js

//...

  return (
    <>
      <h3>{launch.mission_name}</h3>
      <p>Flight Number: {launch.flight_number}</p>
      <p>Year: {launch.launch_year}</p>
      <p>Rocket: {launch.rocket.rocket_name}</p>
      <p>{launch.details}</p>
    </>
  )
```
</details>


## Recap

Which hook do we use when we want to call a function, but only at certain times?

<details>
<summary>Answer:</summary>
`useEffect()`
</details>

How do we tell `useEffect` which variables it should fire the callback on?

<details>
<summary>Answer:</summary>
By passing them in an array to `useEffect` as its second argument, eg.

```js
useEffect(() => {
	myFunction()
}, [trigger1, trigger2])
```
</details>

<br>

## Conclusion

We can now build a multi-component React app which can request information from an external source. We can use the `useEffect` hook to monitor changes in the application's state. 