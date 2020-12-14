# React Requests - Edit Pirate

## Learning Objectives

- Understand how to edit a pirate in our API

## Duration
1.5 hour.

# Intro

Last, but not least, we want to be able to edit a pirate.

This may be to update name, age or ship or to add/remove raids.

# Edit form route.

>  use end point from create lesson.

Rather than having separate forms to edit and create pirates we can re-use our create form to edit as well.

We won't deal with the raids here as this can lead to some complicated code dealing with the many to many. We will deal with that in a nicer way later.

Let's start by creating a route for this to show our form for editing the pirate.

The route we will define will be `/pirates/:id/edit` to match the RESTful route.

We will have this route render the `PirateForm`.

When we are creating the form this time we will need to pass in the pirate and also the list of ships. So we will need to get the id from the params and use our `findPirateById` method again.

Our Edit form will need a pirate and ships so we will pass these in as props.

```js
// PirateContainer.js


// AS BEFORE
      <Route exact path = '/pirates/new' render={(props) =>{
        return <PirateFormContainer ships={ships} />
      }}/>

      {/* EDIT ONE PIRATE */}
      <Route exact path="/pirates/:id/edit" render={(props) =>{
            const id = props.match.params.id;
            const pirate = findPirateById(id);
            return <PirateForm pirate={pirate} ships={ships} />
          }}/>

```

Next we will add a new link to our `PirateDetail` component to hit this route. To style it like the button we can actually just pass in a button in-between the Link tags.

We also need to import Link at top of file.

```js
//PirateDetail.js

import {Link} from 'react-router-dom';

// AS BEFORE


const editUrl = "/pirates/" + pirate.id + "/edit"

return (
    <div className = "component">
    <Pirate pirate = {pirate}/>
    <p>Raids:</p>
    <ul>
    {piratesRaids}
    </ul>
      <button onClick={handleDelete}>Delete {pirate.firstName}</button>
      <Link to= {editUrl}><button type="button">Edit {pirate.firstName}</button></Link>
    </div>
  )
}
```

## Pirate Edit Form

Now let's code up the edit part of our PirateForm.

Let's start by giving our form a heading so we know we are in the right place.

To give the appropriate heading we will check if there is a pirate passed in as props

```js
// PirateForm.js

const PirateForm = ({pirate, ships, onCreate}) => { // added pirate

    // AS BEFORE

    let heading = ""; // ADDED

    if (!pirate){
      heading = "Create Pirate"
    } else {
      heading = "Edit " + pirate.firstName;
    } // ADDED

    return (
      <div>
    <h3>{heading}</h3>
    // Form as before
    </div>
    )

```

In this case we want the pirate in our state to be already populated with the pirate passed in as a prop.

We want to make sure it is a copy otherwise when we change the pirate in the state our prop pirate would also update.

To get a copy we can use the `spread` operator we have seen.

We will do all this in the `componentDidMount` method.

```js
// PirateForm

const addPiratePropToState = () => {
        if(pirate){
          let copiedPirate = {...pirate}
          setStatePirate(copiedPirate)
        }
      }

```
We then want to call that function when the pirate prop comes through.

We can do this with useEffect (make sure you import it). This will listen out for changes in pirate prop and call the
function.

```
      useEffect(()=>{
        addPiratePropToState()
      }, [pirate])
```


Now if we click on the Link in our `PirateDetail` component it should take us to the form and our pirates name and age should be pre-populated.

Ok but our ship is defaulting to the original option. It would be good if we could pre-select the pirates ship.

This does give us a bit of an issue with the default value of the ship as the value needs to be the same in both the pirate and the ships array. They aren't! Don't believe me check out the props.

In the ships array each ship has a list of pirates assigned to it. `ship` doesn't bring this back because of the JsonIgnoreProperties.

We will need to loop through the ships, find the one whose id matches the ship id from the pirate, then grab it's index.

We will use `findIndex` to get the correct ship from the pirate in props and then return the ships index. We can then call this function in the select straight away.

Let's write a method to get the ship index. Remember this should only happen if there is a pirate passed in the props.


```js
// PirateForm.js


const findShipIndex = function(){
   if(pirate){
     return ships.findIndex(ship => pirate.ship.id === ship.id)
   } else {
     return null;
   }
 }
```

And trigger this method in the select to return the index or our disabled option value and set this to be the defaultValue of the select.

```js
// PirateForm

<select name="ship" defaultValue={findShipIndex() || 'select-ship'}>
<option disabled value="select-ship">Select a ship</option>
{shipOptions}
</select>
```

## PATCH method

We want to update the API so we will need to write a PATCH method in our request helper.

We will use PATCH instead of PUT, as this handles many to many relationships better.

Again this will take in a url and a payload to update. Much like post we will define the method, headers and also a body to update. If you remember when we posted using insomnia our body looked something like this:

```js
// request.js

patch(url, payload){
  return fetch(url, {
    method: "PATCH",
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(payload)
  })
}
```

Next we will create a method in the `PirateContainer` to call the patch.

The url to update the api will be `/api/pirates/id`. After the update we will redirect back to our pirate.

This method will take in our updated pirate.

```js
// PirateContainer.js

const handlePost =(pirate)=>{
  //AS BEFORE
}

const handleUpdate = function(pirate){
   const request = new Request();
   request.patch('/api/pirates/' + pirate.id, pirate)
   .then(() => {
     window.location = '/pirates/' + pirate.id
   })
 }// ADDED
```

And pass this as a prop to our form

```js
// PirateContainer
<Route exact path="/pirates/:id/edit" render={(props) =>{
  const id = props.match.params.id
  const pirate = findPirateById(id);
  return <PirateForm pirate={pirate}
  ships={ships} raids={raids} onUpdate={handleUpdate}/> //added
}}/>
```

And lastly in our `PirateForm` we will update the `handleSubmit` to either call `onUpdate` or `onCreate` depending which route it came from (does the pirate in state have an id?)

```js
// PirateForm.js
const PirateForm = ({pirate, ships, onCreate, onUpdate}) => { // add to import


const handleSubmit = function(event){
    event.preventDefault();
    if(statePirate.id){    //added
      onUpdate(statePirate)
    } else {
      onCreate(statePirate);
    }
  } // UPDATED
```


So now we should be able to update our pirate using the form.


# Task
- Update a ship.


# Summary
- Learned how to edit a pirate and patch to the API.
