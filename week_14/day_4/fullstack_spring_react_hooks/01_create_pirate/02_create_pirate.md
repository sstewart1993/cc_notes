# React Requests - Create Pirate

## Learning Objectives

- Understand how to create a new pirate in our API

## Duration
1 hour.

# Intro

So we have been able to get all of the Pirates from our API but we may also want to create a pirate.

Well we can define a route in our `PirateContainer.js` to take us to a form and then `POST` to the API when that form is submitted.

# Show form route.

> Use end point from delete lesson.

Let' start off by creating the route in `PirateContainer` to send us to our form and also add a link to the NavBar.

We will then make sure we can render the form.

The route we will define will be `/pirates/new`. As this closely matches `/pirates/:id` we will put this above the show one pirate route and our Switch will handle the details. Otherwise it will hit `/pirates/:id` first and assume that `new` is the id.

We will have this route render a `PirateForm` which we will import at the top.

When we are creating this form we will need to give the user a list of ships that already exist to choose from.

Remember: there is a One-to-Many relationship between pirates and ships. Therefore we need to ensure that we give a new pirate a ship that exists in our api.

We have already requested our ships so we can pass these as props to the form container in order to display the options in a drop down.

Let's code up the route. This will need to be the top route so that `new` doesn't get confused for an id in `/pirates/:id`.

```js
// PirateContainer.js

import PirateForm from '../components/pirates/PirateForm';


//AS BEFORE


    <Switch>
    <Route exact path = '/pirates/new' render={() =>{
      return <PirateForm ships = {ships}/>
    }}/> // ADDED

    // AS BEFORE
    </Switch>
```

And add a link in our `NavBar`.

```js
//NavBar.js

<ul>
<li className="navLink">
<a href="/pirates/">Pirates</a>
</li>
<li className="navLink">
<a href="/pirates/new">Create Pirate</a> //NEW
</li>
<li className="navLink">
<a href="/ships">Ships</a>
</li>
<li className="navLink">
<a href="/raids" >Raids</a>

</ul>

```

## Pirate Form Component

Let's create our controlled component that will handle creating our new pirate.

```bash
touch src/components/pirates/PirateForm.js

```

And create this as a new class.

```js
// PirateForm.js

import React, {useState, useEffect} from 'react';

const PirateForm = ({ships}) => {
  return(
    <p>"I'm the form"</p>
  )
}

export default PirateForm;
```

And check that when we click our nav link it takes us to this form.

The ships will be part of our props.

Check the React DevTools and make sure the props now has the array of ships.

### Rendering the form

Let's start by making sure the ships array isn't empty before we try to render the form.

```js
// PirateForm.js
if (!ships.length === 0){
    return <p>Loading...</p>  //added
    }
  return(  //before
    <p>"I'm the form"</p>
  )
}

```

First of all we will set some state to manage the new pirate.

Previously we had separate state for each of the form fields but this can be a lot of work.

Let's set up an initial state for a pirate instead. Then we can have one change handler to handle all the inputs.

Later we will be passing down a pirate as a prop, so lets call this pirate "statePirate" to differentiate.

```js
// PirateForm.js

    const [statePirate, setStatePirate] = useState(
        {
          firstName: "",
          lastName: "",
          age: 0,
          ship: null
      }
    )
```

And add handlers.

 As the ship will be an object we will deal with this separately later.

```js
// PirateForm.js


const handleChange = function(event){

}

const handleShip = function(event){

}


```

We will fill in these details later.


For now we can create a form and create inputs for the names and age first of all. Use the `handleChange` method to handle changes in all of these inputs.

```js
// PirateForm.js


  return (
    <>
       <form >
       <input type="text" placeholder="First Name" name="firstName" onChange={handleChange} value={statePirate.firstName} />
       <input type="text" placeholder="Last Name" name="lastName" onChange={handleChange} value={statePirate.lastName} />
       <input type="number" placeholder="Age" name="age" onChange={handleChange} value={statePirate.age}/>

       <button type="submit">Save</button>
       </form>
    </>
  )

```

### Handling changes

Whenever we make a change to any of our inputs the event is sent to `handleChange`.

Within the event we can get the name of the target that triggered the event. So if it was the input with name `age` then event.target.name would return us 'age'

We can use the name to set the appropriate property of the pirate with square brackets.

So either:

```js
pirate['firstName']
pirate['lastName']
pirate['age']
```

Let's do that in `handleChange`.

We will get the name of the input, get the pirate from the state and set that property using the name and value of the target.


```js
// PirateForm

const handleChange = function(event){
    let propertyName = event.target.name;
    let copiedPirate = {...statePirate}
    copiedPirate[propertyName] = event.target.value;
    setStatePirate(copiedPirate)
  }
```

And try this out in the form with the React Devtools. When you change a text input the pirate in the state should update!

Awesome. Now let's deal with the ship.

### Handling drop downs

Ok so we want a drop down for the ships. We will use the ships name to display in the drop down but the value can't be the ship object as React doesn't return these properly. All we would be able to tell is that the value is an object.

There is a better way to deal with this.

We have an array of ships. So let's make the value selected be the index of the ship in the props array. Then when we handle the ship being selected we will get the ship with that index.

We will create the array of `options` to go into the select and then the select itself.

```js
// PirateForm.js

const shipOptions = ships.map((ship, index) => {
    return <option key={index} value={index}>{ship.name}</option>
    })

if (!ships.length === 0){
    return <p>Loading...</p>
    }
return(
    <div>
    <form >
    <input type="text" placeholder="First Name" name="firstName" onChange={handleChange} value={statePirate.firstName} />
    <input type="text" placeholder="Last Name" name="lastName" onChange={handleChange} value={statePirate.lastName} />
    <input type="number" placeholder="Age" name="age" onChange={handleChange} value={statePirate.age}/>
    <select name="ship" onChange={handleShip}> //added
        {shipOptions} //added
    </select> //added
    <button type="submit">Save</button>
    </form>
    </div>
)
}
```

And to make sure we always have to select a ship make a default selected option. To do this we will set an `option` and a matching `defaultValue` in the select.

```js
<select name="ship" onChange={handleShip} defaultValue="select-ship">
<option disabled value='select-ship'>Select a ship</option>
  {shipOptions}
</select>
```

Now if we look at our form it should have a dropdown with our ships as options and a disabled option telling us to select a ship.

Lastly, in order to find the ship we want from it's index, we will finish off the `handleShip` method.

This will get the ship from the props at that index and then set the ship property of the pirate in state.

(As the option value returns a string we will need to convert it to an integer. )

```js
// PirateForm

// AS BEFORE

const handleShip = function(event){
    const index = parseInt(event.target.value)
    const selectedShip = ships[index]
    let copiedPirate = {...statePirate};
    copiedPirate['ship'] = selectedShip
    setStatePirate(copiedPirate)
    }
```

## POST method

We want to post to the API so we will need to write a POST method in our request helper. This will take in a url and a payload to save. Much like delete we will define the method, headers and also a body to save. If you remember when we posted using insomnia our body looked something like this:

```json
{
  "firstName": "Mr",
  "lastName": "Gibbs",
  "age": 45,
  "ship": {
    "name": "The Flying Dutchman",
    "id": 2
  }
}
```

So this is what will need to be passed in as the payload. When we send the payload we need to use `JSON.stringify()` to convert the JSON object to a string to allow the data to be sent to the API.

In request helper code up a post function just below the delete function.

```js
// request.js

post(url, payload){
  return fetch(url, {
    method: "POST",
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(payload)
  })
}
```

Next we will create a method in the `PirateContainer` to call the post.

The url to post to the api will be `/api/pirates`. After the post we will redirect back to our list of pirates.

```js
// PirateContainer.js

const handleDelete = function(id){
  // AS BEFORE
}


const handlePost = function(pirate){
    const request = new Request();
    request.post("/api/pirates", pirate)
    .then(() => window.location = '/pirates')
  }//  ADDED
```

Lastly we will pass in the handler method to our PirateContainer as props.

```js
// PirateContainer.js

  <Route exact path="/pirates/new"  render={(props) => {
        return <PirateForm ships={ships} onCreate= {handlePost} />
      }} />

```

## Invoking the callback.

OK so now let's finish the form component by adding in the `onSubmit` and creating a method to be triggered when the form is submitted.

```js
// PirateForm.js

const handleShip = function(event){
  // AS BEFORE
}

const handleSubmit = function(event){
        event.preventDefault();
    } // ADDED
```

And add this to the form element.

```js
// PirateForm.js

<form onSubmit={handleSubmit}> // MODIFIED
```

So when we trigger the `onSubmit` we need to get our pirate from state and pass that to the prop function we passed in.

```js
// PirateForm.js

const PirateForm = ({ships, onCreate}) => { //added
  ...


const handleSubmit = function(event){
        event.preventDefault();
        onCreate(statePirate); // added
    }
```

So now we should be able to create our pirate using the form.

# Task
- Add a ship.
- Add a pirate to a new ship.

# Summary
- Learned how to create a pirate and post to the API.

# Next Lesson
- Edit a pirate.
