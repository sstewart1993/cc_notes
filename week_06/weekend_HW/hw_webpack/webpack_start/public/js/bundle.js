/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/app.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/app.js":
/*!********************!*\
  !*** ./src/app.js ***!
  \********************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("const Person = __webpack_require__(/*! ./models/person.js */ \"./src/models/person.js\");\nconst RandomAdjective = __webpack_require__(/*! ./models/random_adjective.js */ \"./src/models/random_adjective.js\")\n\n\ndocument.addEventListener('DOMContentLoaded', function () {\n  const form = document.getElementById('person-details-form');\n  const welcomeElement = document.querySelector('p.welcome');\n  const messageElement = document.querySelector('p.message');\n\n  form.addEventListener('submit', function (event) {\n    event.preventDefault();\n    const name = event.target['name-input'].value;\n    const colour = event.target['favourite-colour-input'].value;\n\n    const person = new Person(name, colour);\n    person.formatName();\n    person.formatColour();\n\n    const greeting = `Hey there, ${person.name}!`;\n    welcomeElement.textContent = greeting;\n\n    const randomAdjective = new RandomAdjective();\n    const adjective = randomAdjective.get();\n    const message = `${person.favouriteColour} is a ${adjective} colour, mate.`;\n    messageElement.textContent = message;\n  });\n});\n\n\n//# sourceURL=webpack:///./src/app.js?");

/***/ }),

/***/ "./src/helpers/text_format.js":
/*!************************************!*\
  !*** ./src/helpers/text_format.js ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("const TextFormat = {}\n\nTextFormat.capitalise = function (text) {\n  if (!text) return text;\n  const initialLetter = text[0];\n  const restOfWord = text.slice(1);\n  return initialLetter.toUpperCase() + restOfWord.toLowerCase();\n}\n\nmodule.exports = TextFormat;\n\n//# sourceURL=webpack:///./src/helpers/text_format.js?");

/***/ }),

/***/ "./src/models/person.js":
/*!******************************!*\
  !*** ./src/models/person.js ***!
  \******************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("const TextFormat = __webpack_require__(/*! ../helpers/text_format.js */ \"./src/helpers/text_format.js\");\n\nconst Person = function (name, favouriteColour) {\n  this.name = name;\n  this.favouriteColour = favouriteColour;\n};\n\nPerson.prototype.formatName = function () {\n  const names = this.name.split(' ');\n  const capitalisedNames = names.map((name) => TextFormat.capitalise(name));\n  this.name = capitalisedNames.join(' ');\n};\n\nPerson.prototype.formatColour = function () {\n  this.favouriteColour = TextFormat.capitalise(this.favouriteColour);\n};\n\nmodule.exports = Person\n\n//# sourceURL=webpack:///./src/models/person.js?");

/***/ }),

/***/ "./src/models/random_adjective.js":
/*!****************************************!*\
  !*** ./src/models/random_adjective.js ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("const RandomAdjective = function () {\n  this.adjectives = [\n    'bad',\n    // 'beautiful',\n    'boring',\n    'disasterous',\n    'dreary',\n    // 'fabulous',\n    // 'good',\n    // 'magic',\n    // 'top-notch',\n    // 'wonderful',\n  ];\n};\n\nRandomAdjective.prototype.get = function () {\n  const randomIndex = Math.floor(Math.random() * this.adjectives.length);\n  return this.adjectives[randomIndex];\n};\n\n\nmodule.exports = RandomAdjective;\n\n//# sourceURL=webpack:///./src/models/random_adjective.js?");

/***/ })

/******/ });