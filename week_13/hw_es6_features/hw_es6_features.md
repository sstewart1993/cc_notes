# Homework: Additional ES6 Language Features

### Learning Objectives

- Be able to use `class` syntax
- Be able to use `import` and `export` syntax
- Be able to use object destructuring
- Understand what a transpiler, such as Babel, is useful for

## Brief

### Additional ES6 Language Features

There are a some language features that were introduced with ES6 that we havn't looked at that. These features are used when writing React idiomatically. Your task is to read the MDN documentation for these features (classes, import and export, and destructuring) and to refactor the included Library application to use them.

To run the Library application:

- Install dependencies: `npm install`
- Run the unit tests: `npm test`

#### `class`

[https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes)

Replace the constructor functions and prototypal methods in the `Book` and `Library` modules with the `class` syntax. Note: JavaScript still uses prototypal inheritance under the hood, JavaScript classes are just syntactic sugar.

#### `import` & `export`

- [https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/import](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/import)
- [https://developer.mozilla.org/en-US/docs/web/javascript/reference/statements/export](https://developer.mozilla.org/en-US/docs/web/javascript/reference/statements/export)

Replace the `module.exports` statements with `export default` statements and `require` statements with `import` statements. You will need to make changes to both the `Book` and `Library` modules and their respective spec files.

#### Object Destructuring

[https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment#Object_destructuring](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment#Object_destructuring)

In library.js refactor the `printInventory` method using destructuring. Inside of the callback that we're passing to `forEach` unpack the fields `title` and `author` from the parameter `book`, so that they can be accessed directly via the variables `title` and `author`, rather than having to access them through `book.title` and `book.author`.

### Transpilers

At the bottom of each of the above MDN pages there is a section called 'Browser Compatibility' that shows which browsers support these language features.

When using newer language features that are not supported by all browsers, we can use a transpiler to convert our code into a backwards compatible version JavaScript that we know is more widely supported. A JavaScript transpiler will take the source code that we write, and transpile it into the version of JavaScript that we specify in the configuration settings. We can use the configuration settings to specific exactly which browsers we want to target. This allows us to use all the latest language features, while still being sure our application will run for our users.

Babel is a JavaScript transpiler that does just this.

1. Read the Babel page, 'What is Babel?': [https://babeljs.io/docs/en/](https://babeljs.io/docs/en/)
2. Babel has a REPL that we can use to see how it transpiles our code: [https://babeljs.io/en/repl.html](https://babeljs.io/en/repl.html).
Experiment with it by typing in some es6 JavaScript (for example, an arrow function) and seeing what it transpiles to.

We will be using Babel when develop our React apps to transpile both the newer language features and other framework specific syntax into a widely supported version of JavaScript.
