# Rick and Morty - Multi Components Lab

**Lab Duration: 120 mins**

### Learning Objectives

- Be able to create a Vue app with multiple components and templates

## Brief

Your task is to create an app that shows info for all the characters of the TV show Rick and Morty using multiple components. Use the [Rick and Morty API](https://rickandmortyapi.com/api/character/) to make a request to get the data.

### MVP

- Display a list of character names.
- Add a click event to the list item which should then render more detail about that character (name, species, status).
- Use reusable components and event bus.


### Extensions

- Instead of rendering a list, populate a dropdown with all of the characters names.
- Add a change event to the select that renders information about the selected character.

### Advanced Extensions

- Add the characters image and origin name to the character detail component.
- Add a search bar to the page so that when a user enters the characters name the character detail component renders.
> Try to achieve this without the user having to enter the whole name.


## Planning

Draw a diagram of your files, detailing:

- the data, props, components and methods for each component.
- the flow of data throughout the application.

**Expected Components for MVP**
- CharacterList
- CharacterDetail
- CharacterSelect
