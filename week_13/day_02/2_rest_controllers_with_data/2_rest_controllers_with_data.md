# Connecting REST Controllers to Data

**Lesson Duration: 60 minutes**

### Learning Objectives

- Understand how to create controllers that conform to REST principles

## Introduction

**Why are we learning this?** When there's a relationship between two or more models, it is valuable to be able to access that data via RESTful routes

**By the end of this lesson you will be able to**
Setup routing to be able to access the collections of raids in pirates and pirates in ships


### Seeding the Database

When doing both integration and unit testing, we will need a way to seed the database. Doing this will allow us to check relationships are setup correctly and that the data we expect is coming back. This will become especially valueable when testing custom queries.

With Spring, we can tap into Spring Boot's `ApplicationRunner`. By creating a class that inherits from `ApplicationRunner` we can override the `run` method which is called implicitly on Spring startup. It will be picked up by Spring and run if we use the `@Component` annotation.

Making use of the fact that this is run each time our Spring application starts. Create a package called `components` a new class `DataLoader` in it. Just slack the `main` for this out.

```java

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PirateRepository pirateRepository;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    RaidRepository raidRepository;


    public DataLoader() {

    }

    public void run(ApplicationArguments args) {
       Ship dutchman = new Ship("The Flying Dutchman");
      shipRepository.save(dutchman);

      Ship pearl = new Ship("The Black Pearl");
      shipRepository.save(pearl);

      Ship blackPig = new Ship("The Black Pig");
      shipRepository.save(blackPig);

      Ship dustman = new Ship("The Flying Dustman");
      shipRepository.save(dustman);

      Ship galley = new Ship("Adventure Galley");
      shipRepository.save(galley);

      Ship revenge = new Ship("Queen Anne's Revenge");
      shipRepository.save(revenge);

      Ship fancy = new Ship("Fancy");
      shipRepository.save(fancy);

      Ship fortune = new Ship("Royal Fortune");
      shipRepository.save(fortune);

      Pirate jack = new Pirate("Jack", "Sparrow", 32, pearl);
      pirateRepository.save(jack);

      Pirate john = new Pirate("John", "Silver", 55, dutchman);
      pirateRepository.save(john);

      Pirate pugwash = new Pirate("Horatio", "Pugwash", 55, blackPig);
      pirateRepository.save(pugwash);

      Pirate maggie = new Pirate("Maggie", "Lafayette", 35, dustman);
      pirateRepository.save(maggie);

      Pirate william = new Pirate("William", "Kidd", 40, galley);
      pirateRepository.save(william);

      Pirate blackbeard = new Pirate("Edward", "Teach", 45, revenge);
      pirateRepository.save(blackbeard);

      Pirate henry = new Pirate("Henry", "Avery", 25, fancy);
      pirateRepository.save(henry);

      Pirate bart = new Pirate("Bartholomew", "Roberts", 47, fortune);
      pirateRepository.save(bart);


      Raid raid1 = new Raid("Tortuga", 100);
      raidRepository.save(raid1);

      Raid raid2 = new Raid("Treasure Island", 690);
      raidRepository.save(raid2);

      Raid raid3 = new Raid("Barbados", 500);
      raidRepository.save(raid3);

      Raid raid4 = new Raid("St. Kitts", 500);
      raidRepository.save(raid4);

      Raid raid5 = new Raid("Havana", 200);
      raidRepository.save(raid5);

      Raid raid6 = new Raid("Port Royal", 1000);
      raidRepository.save(raid6);

      jack.addRaid(raid1);
      jack.addRaid(raid2);
      pirateRepository.save(jack);

      raid2.addPirate(john);
      raidRepository.save(raid2);

      raid3.addPirate(pugwash);
      raid3.addPirate(maggie);
      raidRepository.save(raid3);

      raid4.addPirate(pugwash);
      raid3.addPirate(jack);
      raidRepository.save(raid4);

      blackbeard.addRaid(raid5);
      blackbeard.addRaid(raid6);
      pirateRepository.save(blackbeard);

      raid5.addPirate(william);
      raidRepository.save(raid5);

      raid6.addPirate(henry);
      raidRepository.save(raid6);
        
    }
}

```

### Now use create-drop setting

Since, we're now seeding the database, the `spring.jpa.hibernate.ddl-auto = update` setting should be set to `create-drop`.

```
spring.jpa.hibernate.ddl-auto = create-drop
```

This drops the db including it's schema every time the application is terminated. It's then created every time the application starts.




### REST Routes we need

> Get the students to help put together this list of routes we need to create on the board. Talk them through this and re-explain REST if you need to.

* Get all pirates/raids/ships
  * GET `/pirates` - returns JSON array of all pirates
  * GET `/raids` - returns JSON array of all raids
  * GET `/ships` - returns JSON array of all ships
* Get one pirate / raid /ship
   * GET `/pirates/{id}` - returns JSON for a single pirate
   * GET `/raids/{id}` - returns JSON for single raid
   * GET `/ships/{id}` - returns JSON for single ship
* Get a pirate's raids 
   * GET `/pirates/{id}/raids` - returns a list of raids for a given pirate id
* Get a specific raid from a pirate
   * GET `/pirates/{id}/raids/{id}` - returns a specific single raid for a given pirate.


## Pirates Controller

First let's create all the routes that will be in the `PirateController` controller.

If we don't have it already, we can add the route that gets all the pirates.


```java

	 @Autowired
    PirateRepository pirateRepository;
    
   @GetMapping(value = "/pirates")
    public List<Pirate> getAllPirates(){
        return pirateRepository.findAll();
    }

```

The GET mapping `getAllPirates` will take the **ArrayList** of `Pirate` objects using the `PirateRepository` and **serailize** it into JSON.

**Reminder:** _A `PirateRepository` instance need to be Autowired or injected into the controller. We **don't** do `PirateRepository pirateRepository = new PirateRepository()` - instead we ask Spring for an instance of `PirateRepository` that we know it has_.

It will then send that as an HTTP response back to the client that requested it.

By default, the HTTP status code on this response will be 200 OK.

### HTTP Status Codes

HTTP Status codes represent various statuses of the back-end of the application. An HTTP response has an optional payload (response data) and a required code.


An incomplete list of common HTTP status codes.

|  Code | Type  |  Used for |
|---|---|---|
| 200  |  2xx | Success OK   |
|  404 |  4xx | Client Error   - data not found|
|  500 |  5xx |  Server Error |

There are dozens of errors through various categories. For a full list look here: 
[https://www.restapitutorial.com/httpstatuscodes.html ](https://www.restapitutorial.com/httpstatuscodes.html)

### Specifying a Status Code

We need to use a spring `ResponseEntity`. That gives us an object that allows us to specify the status code, allowing us to customise it to our needs.

Let's convert that `getAllPirates` method to return a `ResponseEntity` that takes a payload of type `List<Pirate`>


```java
//PirateController.java

 @GetMapping(value = "/pirates")
    public ResponseEntity<List<Pirate>> getAllPirates(){
        return new ResponseEntity<>(pirateRepository.findAll(), HttpStatus.OK);
    }

```


When creating a `ResponseEntity` object we need to give it a payload (our List of Pirate objects) and a status Code. Status code for success is always 200 - OK, but we can get to that by using `HttpStatus.OK`

Now run the main and check the response in Insomnia or Chrome. `http://localhost:8080/pirates` - it should be a list of `Pirates` in JSON form, the same as before, but now we have control over the response status code.


## The infinite recursion problem

When hitting `http://localhost:8080/ships/pirates/1` (or of course `localhost:8080/ships/pirates`) we see a problem in the resulting JSON output. This is caused by a recursive relationship between `Raid` and `Pirate`.

The serialisation becomes recursive as Raid serialisation tries to serialise a Pirate and it goes like this forever until there's an exception.

We can handle this in a way that works well.

**Any List in a relationship that's causing this should be annotated with `@JsonBackReference`** 

Go to the `Ship.java` class, find the `pirates` property and add the `@JsonBackReference` annotation to it as follows:

```java
//Ship.java

@JsonBackReference     // NEW - add this!
@OneToMany(mappedBy = "ship", fetch = FetchType.LAZY) // As before
private List<Pirate> pirates; // As before

```

Go to the `Raid.java` class, find the `pirates` property and add the `@JsonBackReference` annotation to it as follows:

```java
//Ship.java

    @JsonBackReference     // NEW - add this!
    @ManyToMany  // As before
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "pirates_raids",
            joinColumns = {@JoinColumn(name = "raid_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="pirate_id", nullable = false, updatable = false)}
    )
    private List<Pirate> pirates; // As before

```

**Note:** The `@JsonIgnoreProperties()` annotation can be used to a similar effect.

## Other routes

### `/pirates/{id}`

We need to handle the route where we pass in an ID and get back an individual pirate JSON back. For example if we GET `http://localhost:8080/pirates/3` we're GETting the pirate with ID **3** back.

We want to be able to get pirates with any ID so we need to write a route that allows us to grab the number part for the ID from the URL. We'll use the `@PathVariable` annotation. 

This allows us to match the `{id}` part of our URL and get that value into a value (we'll use a Long) but a String would also work. Let's code it out..

```java
	@GetMapping(value = "/pirates/{id}")
    public ResponseEntity<> getPirate(@PathVariable Long id){
       // TODO
    }
```

We now need to look at the `pirateRepository` object and see what methods we have available. We'll use `.findById()` which is a method already available to us through JPA.

```java
	@GetMapping(value = "/pirates/{id}")
    public ResponseEntity getPirate(@PathVariable Long id){
	 	return new ResponseEntity<>(pirateRepository.findById(id), HttpStatus.OK); 
    }
```

Let's go to `http://localhost:8080/pirates/3` and you should see an individual pirate JSON.



## TASK: 20 mins Complete routes for Ship and Raid

Now complete the following routes and test them with insomnia or in your browser.

* Get all raids/ships
  * GET `/raids` - returns JSON array of all raids
  * GET `/ships` - returns JSON array of all ships
* Get one pirate / raid /ship
   * GET `/raids/{id}` - returns JSON for single raid
   * GET `/ships/{id}` - returns JSON for single ship
   
> TIP:  * You'll need to create  `RaidController` and `ShipController` classes if you don't already have them. Raid routes go in the `RaidController` ship routes go in the `ShipController`

> TIP 2: Remember to use `@RestController` annotation on the controller class you create.


### Solution

```java
//ShipController.java - imports + package omitted for berevity

@RestController
public class ShipController {

    @Autowired
    ShipRepository shipRepository;

    @GetMapping(value = "/ships")
    public ResponseEntity<List<Ship>> getAllShips(){
        return new ResponseEntity<>(shipRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/ships/{id}")
    public ResponseEntity getShip(@PathVariable Long id){
        return new ResponseEntity<>(shipRepository.findById(id), HttpStatus.OK);
    }
}

```

```java
//RaidController.java - imports + package omitted for berevity

@RestController
public class RaidController {


    @Autowired
    RaidRepository raidRepository;

    @GetMapping(value = "/raids")
    public ResponseEntity<List<Raid>> getAllRaids(){
        return new ResponseEntity<>(raidRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/raids/{id}")
    public ResponseEntity getRaid(@PathVariable Long id){
        return new ResponseEntity<>(raidRepository.findById(id), HttpStatus.OK);
    }
}
```

