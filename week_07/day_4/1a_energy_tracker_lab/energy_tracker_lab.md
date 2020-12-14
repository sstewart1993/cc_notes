# Energy Mix Lab

**Lab Duration: 180 mins**

## Learning Objectives

- Be able to read data from an API, and manipulate it for use
- Be able to handle user interaction and practice business logic
- Be able to use third party libraries

### Brief / MVP

The "energy mix" is the mixture of energy sources that the UK derives its energy from at any given time. For example:

```
Hydro: 25%
Nuclear: 18%
Wind: 40%
```

And so on.

You have been asked to display information relating to the UK's energy mix, using the National Grid's API:

https://carbon-intensity.github.io/api-definitions/#get-generation

Set up a Vue application that will display this information, and the relevant times in a suitable format - specifically, a chart. The [Vue Google Charts](https://www.npmjs.com/package/vue-google-charts) library is something you can use to do this.

The issue you will face is that your data should look like this:

```
generationMix: [
  ["Fuel", "Percentage"],
  ["Nuclear", 20.4],
  ["Hydro", 18.9],
  ...
]
```

...But the API provides it in a very different format. You will have to re-shape the data to meet your needs. You will also need to read the documentation on Vue Google Charts to fully understand how to implement it in your app.

### Extensions

Choose some or all of these:

- Allow the user to select the start and finish time and dates for the API data using [this endpoint](https://carbon-intensity.github.io/api-definitions/?shell#get-generation-from-to) - this will return a different response which will require some more in-depth manipulation to provide the chart with the data it requires.
- Investigate other types of chart - perhaps a pie chart?
- Graph out any other data you are interested in from the API
