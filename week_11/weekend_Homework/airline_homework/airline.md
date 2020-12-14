# Airline!

Your task is to model a system for the world renowned online travel booking agent __TravelJava__. You should use the tools you have learnt this week _where appropriate and useful_. Remember to **TDD**!

TravelJava doesn't really exist (sadly) - don't stress about how a "real" example of this would work differently. This is just an exercise to practice some different concepts.

**Assumptions**:
* Each passenger bag weighs the same
* Planes reserve half of their total weight for passenger bags
* The weight of bag per person is the weight reserved for passenger bags divided by the capacity
* Passengers exist for a single flight only

## MVP
Create a `Passenger` class which has:
* a name
* a number of bags

Create a `Plane` class which has:
* an enum PlaneType (e.g. *BOEING747*) which stores capacity and total weight

Create a `Flight` class which has:
* an empty list of booked `Passenger`'s
* a `Plane`
* flight number (i.e. "FR756")
* destination (i.e. GLA, EDI)
* departure airport (i.e. GLA, EDI)
* departure time (use a String)

The `Flight` class should have methods to:
* return the number of available seats
* book a passenger (if there are remaining seats)

### Extensions
Create a `FlightManager` class which has methods to:
* calculate how much baggage weight should be reserved for each passenger for a flight
* calculate how much baggage weight is booked by passengers of a flight
* calculate how much overall weight reserved for baggage remains for a flight

### More Extensions
* Refactor the Flight's departure time to use the Date class (*HINT*: Look into **Type Migration** in IntelliJ to refactor faster)
* Add a 'flight' property to the `Passenger` class which is assigned when a passenger is added to a flight
* Add a 'seat number' property to the `Passenger` class as an integer. Set it to a random number which is assigned when a `Passenger` is booked on a flight
* Make sure the flight doesn't double book the same seat number to more than one passenger

### ***Super*** Extensions
* Write a method in `FlightManager` to sort the passengers of a `Flight` by seat number by implementing a Bubble Sort
* Write a method in `FlightManager` that uses a Binary Search to find a `Passenger` by seat number. Remember to use the previous method to sort the list first

### PDA Reminder:

As part of this homework you are required to take screenshots of the following:

```
- An example of encapsulation in a program.
```

Demonstrate testing in your program. Take screenshots of:

```
- Example of test code
- The test code failing to pass
- Example of the test code once errors have been corrected
- The test code passing
```

- Go to your [PDA Checklist](https://github.com/codeclan/pda/tree/master/Evidence%20Gathering%20Portfolio)

- Submit your PDA evidence (screenshots, etc.) to your own PDA repo

- PDA Reference: I.T 1, P18
