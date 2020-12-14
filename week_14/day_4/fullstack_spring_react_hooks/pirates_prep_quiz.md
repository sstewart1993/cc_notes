# React Requests - Pirate API Quiz

## Learning Objectives

- Understand the structure and the flow of data through the pirates application.
- Investigate the next steps to be able to POST and PATCH.
- Answer the questions below.

# Intro

For this homework, your task is to run both the client and the server for the pirates application and be able to navagite the code gaining a foundation to build on for tomorrows lesson. Dont worry about the React router elements of the app such as <Router> and <Switch> for now.

First, run the piratesServiceEnd.
> Note that in application.properties we have added at the bottom:
server.servlet.context-path=/api
This will help us differentiate our backend routes from out front end a bit more.

Then run your front end by using "npm install" and "npm start". On http://localhost3000/pirates you should see a list of pirates rendered to the page.

# Requesting All Pirates

Question 1

Which component is responsible for requesting all the pirates?


Question 2

Which hook do we use to carry out the requestAll() method and when does it get triggered?


Question 3

The requestAll() method creates a new instance of Request in order to gain the functionality to carry out various forms of request. Where is the Request class?


Question 4

Which method are we using from the Request class here?


Question 5

The PirateList component is in charge of rendering a list of pirate components. What is the pirateNodes function returning?


# Viewing a Single Pirate


Question 1

In the browser, when we click on one of the pirates names in the pirate list, our app renders a PirateDetail component. PirateDetail is in charge of rendering the information for a single pirate. But where is it getting its props passed down from?


Question 2
 ```js
 if (!pirate){
  return "Loading..."
}
 ```
What is the purpose of this code in PirateDetail?


Question 3

In PirateDetail, to delete a pirate, we have a button with a listener "onClick" which triggers a function called "handleDelete". The handleDelete function uses a function onDelete. Where is it receiving onDelete from?


# Bonus Points Questions


Question 1

In PirateContainer, what does Promise.all return?


Question 2

Ideally, we want our state to live at the top of our component chain, except in one other scenario. This is when our component contains a, what?
