# Introduction to React

### Learning Objectives

- Know what React is
- Explain some of the key features of React
- Be able to create an application using Create React App

## Introduction

### React - a Javascript UI library

React is a library written in Javascript, that allows developers to write Javascript to easily build modern user interfaces for the web.

What is the difference between a framework and a library?

- A library is a set of methods and objects written by someone else that typically solves just one individual problem for us, rather than solving lots of problems.
- A framework is a collection of several libraries under the one name that together offers a **complete** solution for most application features that someone would want to build.

### Why do we need React

Writing well-structured vanilla JavaScript applications, with a clear separation of concerns is difficult to achieve. There are also common tasks that we want to carry out, such as manipulating the DOM, which become repetitive and time consuming. For these reasons, developers over the years have created libraries and frameworks that help with web development. We are going to be using React to help us with our front-end development. React specifically helps us to build UI's (User Interfaces)

## React

### Popularity

React says that it is 'A JavaScript library for building user interfaces'. Making applications that are fast and easier to develop has made React a very popular choice in the web community and it is widely used as shown by the following:
- [A list of companies that use React](https://stackshare.io/react)
- [State of JavaScript 2019 - Front-end](https://2019.stateofjs.com/front-end-frameworks/)

### Key Features

If we go to the [React website](https://reactjs.org/), we can see the main characteristics of React:

- Virtual DOM
- Component based UI - 'HTML' in JS with JSX

This week we are going to be using React to help us build well-structured view logic, using its DOM manipulation methods to make reading and writing to the (virtual) DOM more efficient than we have previously been able to achieve with vanilla JavaScript.




#### Virtual DOM

Updating the DOM in our browser using JS is an expensive operation, it tasks a huge amount of time compared to us multiplying numbers or computing some simple value.  If after every little change our React application re-rendered the whole DOM, our app would be very inefficient and very slow.

React applications are known for being fast. So, how does it achieve this? React keeps a virtual version of the DOM in memory. When we make a change it updates this and compares it to the real DOM (a diff). It only updates the real DOM where necessary.

It is this step which allow us to write fast efficient applications by only having to describe a simple render.

#### Component based UI

React encourages us to break down our user interface into smaller parts called components. By doing this we can make sure each part is only looking after one thing. (*which software principle does this relate to?*) Single Responsibility principle! These can be reusable and are really helpful in separating the functionality of your app into independent parts. **This composability is extremly important to the success of UI libraries like React**

These components may (but don't have to) use a React-specific syntax called JSX. JSX allows us to use HTML-like syntax when describing new elements, whether HTML elements or React components, which saves us time and can make our React code more readable. Some frameworks use a templating language for a similar purpose, writing HTML which can pull in information from a separate JavaScript file, but React allows us to do all of this in one place. (Some people prefer to have those things separate, it depends on the nature of your app as to what's most appropriate.)

## Create React App

When creating a React application we can configure our application manually, setting up our own server, installing React and any other packages we might need to get it up and running. However, Facebook have produced a package called 'Create React App' that allow us to create a boilerplate application with all the build configuration already complete. It comes with a number of useful features already configured:

- A built-in web server (so we don't need to set up Express)
- A build script that includes a pre-configured version of Webpack
- The ability to `import` CSS and images
- Hot reloading (Injects changes to your source code into the current state of the page, so we don't need to refresh the browser each time you make a change.)
- Testing - a testing framework that lets us test our app in various ways

...and lots more.

### Creating an Application with Create React App

Let's make use of these great features and create a "Hello World!" application with Create React App.

To use create-react-app to give us a start-point, we can use npx to get a temporary version of create-react-app to use to create the application.

```sh
npx create-react-app hello_world
cd hello_world
npm start
```

> Note: An alternative to using the `npx` prefix would be to globally install create-react-app and then use `npm create-react-app hello_world`

The `npm start` command has bundled the source code and started the server. It should have also opened the application in a new browser tab going to [http://localhost:3000](http://localhost:3000).


**Once `create-react-app` is finished** we can look at some of the files and directories that it produced and these should look familiar. There is the 'package.json', '.gitignore' and 'node_modules' in the top level directory and there are also the 'public' and 'src' directories. The 'src' directory is where our source code lives, and this is where we will be putting our React components.



## Components

The first component is in App.js which is being rendered to the page by index.js. In most cases we don't edit `index.js`.


Find the `src/App.js`, and delete all the code in it. We'll replace it with the following:

```javascript
import React from 'react';    // 1️⃣
import './App.css';           // 2️⃣

function App() {              // 3️⃣
  // we can write JS here :)  // 4️⃣
  
  return (                    // 5️⃣
     <h1>Hello World</h1>     {/*6️⃣*/}
  );                          // 7️⃣
}                             // 8️⃣

export default App;           // 9️⃣
```



This is a single react component. It's created as a function
If we look at what the component returns from its function 3️⃣, we can see there is some new syntax there 6️⃣. It looks like HTML, but we know that it's not because it is inside a JavaScript file. This syntax is called JSX (which stands for JavaScript Xml) and is what we can use in React to describe what we want to be rendered to the page. We are going to be looking more at JSX next. For now let's delete the boilerplate code, so we can write our own.

-

> optional: 

Looking at this line-by-line:

* 1️⃣ Every react component needs to import the React library. This is true even if you're not explicitally going to use that dependency.
* 2️⃣ Each component can have some CSS applied to it for styling - but it's not required
* 3️⃣ The start of a react component - it's just a function
* 4️⃣ The body of the component - the body of the function
* 5️⃣ React components need to return JSX, but often that spans multiple lines
* 6️⃣ JSX of our component - this is the part that's almost identical to HTML
* 7️⃣ The component returning JSX is as simple as `return (*jsx here*);` This is just the closing bracket
* 8️⃣ Closing bracket of the function
* 9️⃣ Export the component so it can be imported in other JS files.

-

> ❗️Takeaway points:

We need to remember a couple of very simple things when working with React:

* **React components are functions** but can also be classes (more on classes part later)
* **Every React component must return one of three things: a JSX element, an array of JSX elements or null**. 



If you check the browser, you should now see 'Hello World!' instead of the React logo.

## JSX vs HTML

JSX isn't HTML, but most HTML5 tags have been built into React to render as we'd expect them to.

> TASK: 5 mins: Get the class to play around with putting some html inside the return - between the `(` and `)` brackets.



```js
function App() {
  return (
  	<div>
  		<h1 className="title">Hello World!</h1>
  		<p> This is a paragraph of text</p>
  	</div>
    
  );
}
```

Doing this we might come across a restriction in React that says a component can only return a single JSX element. To get round this, we can simply wrap our other components in a `<div>`.


Note: React and JSX are two independent technologies, but JSX was built with React in mind. Create React App has Babel already configured and Babel is transpiling the JSX into JavaScript, making it compatible with browsers.

## Rendering data

So far we've only seen how to get React to render static data. Our "Hello World" is hardcoded into the JSX - but we need a way to connect our JavaScript code to the JSX that's being used to render to our browser window.

To render data from Javascript variables in JSX all we need to do is wrap them in `{` `}` - it's that easy.

* Add two variables `name` and `age` to the body of the function and give them a value. Remember - our component is just a JS function, and our functions can of course have code in them

* Add a line of JSX that says `<p> My name is {name} and I'm {age} years old</p>`


```js
function App() {
  let age = 25;              // ADDED
  let name = "Billy";        // ADDED
  
  return (
   <div>
  	  <h1 className="title">Hello World!</h1>
  	  <p> This is a paragraph of text</p>
  	  <p> My name is {name} and I'm {age} years old</p>
  	</div>  
  );
}
```

Rendering data in React is really simple becuase it's just a case of templating the variables that are in our code into JSX with `{}`.

This actually becomes very powerful, as we can use braces ( `{` and `}`) in JSX to actually execute JavaScript code, not just render variables.


### JSX Syntax Highlighting

The JSX syntax highlighting might be broken as it's being interpreted as JavaScript. To fix this you will need to get the Babel package for your editor. For Atom you can use Language Babel: [https://atom.io/packages/language-babel](https://atom.io/packages/language-babel)

## Conclusion

* **React components are functions** but can also be classes (more on classes as components later)
* **Every React component must return one of three things: a JSX element, an array of JSX elements or null**. 
* **JSX is very similar to HTML, but not exactly the same**
* **Varibles from in a component can be rendered in JSX with `{ }`**

## Additional Resources

There is also a React implementation for native mobile applications:
[https://facebook.github.io/react-native/](https://facebook.github.io/react-native/)
