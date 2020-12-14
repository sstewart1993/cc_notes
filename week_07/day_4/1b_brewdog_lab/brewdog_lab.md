# Lab: Make an app with the Brewdog API

**Lab Duration: 180 mins**

## Learning Objectives

- Be able to make API requests in a Vue app
- Be able to design a Vue component hierarchy from scratch 
- Be able to implement Vue components

Your task is to build an app that uses [this API](https://punkapi.com/documentation/v2) to display information on a variety of beers.

[This endpoint](https://api.punkapi.com/v2/beers) will provide you with some data detailing multiple beers.

**It is important for this task you consider what your views will be, but also how you can reuse components to construct these different views.**

## MVP

Your app should be able to:

- Allow the user to view all the beers
- Allow the user to view more detailed information on a selected beer
- Allow the user to mark beers as 'favourites'
- Display the user's favourite beers

## Extensions

- Prevent the user from marking the same beer as a favourite more than once
- Allow the user to deselect favourite beers
- Display the selected beer's ingredients (without duplicates)
- The endpoint provided will only return 20 beers at a time. Modify your initial request to fetch all 300+ beers the API provides.
