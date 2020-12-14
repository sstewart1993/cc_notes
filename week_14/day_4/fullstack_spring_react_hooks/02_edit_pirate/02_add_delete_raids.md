# React Requests - Add / Remove Raids

## Learning Objectives

- Understand how to add or remove raids to a pirate.

## Duration
60 minutes

# Intro

So our form works to edit a pirates basic details but we still want the ability to add / remove raids.

Instead of doing this in the form we will add this functionality to our pirate details view.

Next to each raid that pirate is on we will add a delete button.

And we will add a drop down of raids to add that pirate to.

# Delete Raids

Let's start by deleting raids.

When we delete a raid from our pirate we will need to update that pirate in the database. So we will pass our handleUpdate method from the container to our pirate details.

```js
// PirateContainer

<Route exact path="/pirates/:id" render={(props) =>{
  const id = props.match.params.id;
  const pirate = findPirateById(id);
  return <PirateDetail pirate={pirate}
  onDelete={handleDelete}
  onUpdate={handleUpdate} // ADDED
  />
}}/>

```

Now in the PirateDetail we will add a delete button to our list of raids.



```js
// PirateDetail.js

const piratesRaids = pirate.raids.map((raid, index) => {
  return <li key={index}>
  {raid.location} <button>Delete</button>
  </li>
}) // AMENDED
```

Ok we can see this button and when we click nothing should happen yet.

When we click this button we will need to remove the matching raid in the pirates list of raids.

In order to do this we will need to pass the raid index to a method and use `splice` to remove from our pirates raid. Then call the update method we passed in as a prop.

We will destructure the onUpdate from the props as well.

We will write the method first.
```js
// PirateDetail
const PirateDetail = ({pirate, onDelete, onUpdate}) => { // MODIFIED

  const handleDelete = () => {
    onDelete(pirate.id)
  }

  const deleteRaid = (raidIndex) => {
    let copiedPirate = {...pirate}
    copiedPirate.raids.splice(raidIndex, 1)
    onUpdate(pirate)
  } // ADDED

  // AS BEFORE
}
```

Lastly set our button to trigger this method on click. In order to pass the index as a callback we can't simply write something like

```js
<button onClick={deleteRaid(index)} >
```

As this would trigger write away.

What we __can__ do is pass it into a callback for onClick. Like this

```js
onClick={() => deleteRaid(index)}
```

Let's add that to the button.

```js
// PirateDetail

const piratesRaids = pirate.raids.map((raid, index) => {
  return <li key={index}>
  {raid.location} <button onClick={() => deleteRaid(index)}>Delete</button>
  </li>
})
```

Awesome We can now delete the raids attached to our pirate.

Let's add the ability to give them some more.


## Adding Raids


We can add a drop down to our pirate to list the raids they don't already have.

Let's start by writing a method that checks if a pirate already has a raid. We can use the arrays `some` method to do this easily.

```js
// PirateDetail

const deleteRaid = (raidIndex) => {
     // before
  }

const pirateHasRaid = (raid) =>{
  return pirate.raids.some((pirateRaid) => {
    return raid.id === pirateRaid.id
  })
} // ADDED
```

And now we will build up the options from our select only adding one if the pirate doesn't have the raid already.

We will need to pass all the raids into PirateDetail as props.

In PirateContainer add the prop to the route.

```js
// PirateContainer

<Route exact path="/pirates/:id" render={(props) =>{
        const id = props.match.params.id;
        const pirate = findPirateById(id);
        return <PirateDetail pirate={pirate}
        onDelete={handleDelete}
        onUpdate={handleUpdate}
        raids={raids} //added
        />
      }}/>
```

Ok so now we have the raids to populate the drop down. Again we will destructure the raids.

```js
// PirateDetail

const PirateDetail = ({pirate, raids, onDelete, onUpdate}) => {  // MODIFIED

  // AS BEFORE


  const editUrl = "/pirates/" + pirate.id + "/edit"

  const raidOptions = raids.map((raid, index) => {
    if (!pirateHasRaid(raid)){
      return (
        <option key={index} value={index}>{raid.location}</option>
      )
    } else {
      return null
    }
  })
}
```

Now we can add the drop down.

This will be added inside a form just after our pirates raid list.

```js
// PirateDetail

return (
  <div className = "component">
  // AS BEFORE
  </ul>
    <form >
      <select name="raids" >
      {raidOptions}
      </select>
      <input type="submit" value="Add Raid"/>
    </form> // ADDED
  <button onClick={deletePirate}>Delete {pirate.firstName}</button>
  // AS BEFORE
)
```

Now when a raid is added we can get it from the index and push to our pirates raids. Then call update. We will do this in a method called `handleSubmit`.

```js
// PirateDetail


const pirateHasRaid = (raid) => {
  // AS BEFORE
}

const handleSubmit = (event) => {
     event.preventDefault();
     const index = parseInt(event.target.raids.value)
     const raid = raids[index];
     pirate.raids.push(raid)
     onUpdate(pirate);
   }

```

And add the submit event to our form

```js
// PirateDetail

<form onSubmit={handleSubmit}> // AMENDED
<select name="raids" >
{raidOptions}
</select>
<input type="submit" value="Add Raid"/>
</form>

```

We should now be able to add the raids to the pirate as well.

# Summary
- Learned how to add a raid to the pirate.
