# Java Streams

## Learning Objectives
  - Demonstrate Streams API.
  - Show lambda expressions and method references.
  - Show how to use the different kind of available stream operations.


## What are streams and how they work

Streams give us a way of processing data ‘in flight’. In other words, we can process data while it’s moving through our program.

In this lesson you will learn how to work with Java streams and how to use the different kind of available stream operations. This will also cover the more powerful stream operations reduce and collect. 

Start a new IntelliJ project called JavaStreams.

```
#IntelliJ
Create a new Java Class in main folder called Streams
```

Now let's write a piece of code that allows us to `filter` and `sort` a list of names using streams and then print the result.

```java
//Streams.java

public class Streams {

    public static void main(String[] args) {

        List<String> myList = new ArrayList<>(Arrays.asList("amanda", "alex", "sandy", "alina", "sky"));

        myList
                .stream()
                .map(String::toUpperCase) //method reference
                .forEach(result -> System.out.println(result)); //lambda expression
//                 or
//                .forEach(System.out::println);

    }
}

// AMANDA
// ALEX
// SANDY
// ALINA
// SKY
```
The above example is using two new types of Java syntax:

>Lambda expressions are short blocks of code which take in parameters and return a value - very similiar to _callbacks_ -. Lambda expressions are similars to methods, but they don't need a name and they can be implemented right in the body of the method.

>Java enables us to pass references of methods or constructors via the `::` keyword.

Stream operations are either intermediate or terminal. Intermediate operations return a stream so we can chain multiple intermediate operations without using semicolons. Terminal operations are either void or return a non-stream result. In the above example `map` is an intermediate operation whereas `forEach` is a terminal operation (and by the way, `filter` and `sorted` are also intermediate operations). Such a chain of stream operations as seen in the example above is also known as operation pipeline.

Most stream operations accept some kind of _lambda expression_ parameter, a functional interface specifying the exact behavior of the operation. Most of those operations must be both non-interfering and stateless. What does that mean?

A function is non-interfering when it does not modify the underlying data source of the stream, e.g. in the above example no lambda expression does modify myList by adding or removing elements from the collection.

A function is stateless when the execution of the operation is deterministic, e.g. in the above example no lambda expression depends on any mutable variables or states from the outer scope which might change during execution.


### Different kind of streams

Streams can be created from various data sources, especially collections. Lists and Sets support new methods `stream()` to either create a sequential or a parallel stream. Parallel streams are capable of operating on multiple threads. In this lesson we'll focus on sequential streams.

As we saw in the first example calling the method `stream()` on a list of objects returns a regular object stream. But we don't have to create collections in order to work with streams as we see in the next code sample:

```java
//Streams.java

Stream.of("amanda", "alex", "sandy", "alina", "sky")
                .findFirst()
                .ifPresent(name -> System.out.println(name.toUpperCase()));

// AMANDA
```

Just use `Stream.of()` to create a stream from a bunch of object references.

Besides regular object streams ships with special kinds of streams for working with the primitive data types `int`, `long` and `double`. As you might have guessed it's `IntStream`, `LongStream` and `DoubleStream`.

`IntStream`s can replace the regular for-loop utilizing `IntStream.range()`:

```java
//Streams.java

IntStream.range(1, 5)
                .mapToObj(num -> "number: " + num)
                .forEach(System.out::println);

// number: 1
// number: 2
// number: 3
// number: 4
```
Primitive streams can be transformed to object streams via `mapToObj()`.

Sometimes it's useful to transform a regular object stream to a primitive stream or vice versa. For that purpose object streams support the special mapping operations `mapToInt()`, `mapToLong()` and `mapToDouble`:

```java
//Streams.java

Stream.of("E38", "E39", "E40", "E41")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(s -> System.out.println(s));

// 41
```

Here's a combined example: the stream of doubles is first mapped to an `int` stream and then mapped to an object stream of strings:

```java
//Streams.java

Stream.of(38.0, 39.0, 40.0, 41.0)
                .mapToInt(Double::intValue)
                .mapToObj(cohort -> "E" + cohort)
                .forEach(System.out::println);
                
// E38
// E39
// E40
// E41
```

### Advanced Operations

Streams support plenty of different operations. We've already learned about the most important operations like `filter` or `map`. I leave it up to you to discover all other available operations (see Stream [Javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)). Instead let's dive deeper into the more complex operations collect, and reduce.

Let's create two more classes `Person.java` and `Runner.java`.

```java
//Person.java

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

```java
//Runner.java

public class Runner {

    public static void main(String[] args) {

        List<Person> people =
                Arrays.asList(
                        new Person("Sandy", 40),
                        new Person("Hannah", 25),
                        new Person("Sky", 35),
                        new Person("Juan", 35));
    }
}
```

**Collect** is an extremely useful terminal operation to transform the elements of the stream into a different kind of result, e.g. a `List`, `Set` or `Map`. Collect accepts a Collector which consists of four different operations: a supplier, an accumulator, a combiner and a finisher. This sounds super complicated at first, but the good part is Java supports various built-in collectors via the Collectors class. So for the most common operations you don't have to implement a collector yourself.

Let's use streams to filter our collection of names and collect only those starting with _"S"_.


```java
//Runner.java

//...same as above

 //Use streams to get only the names starting with "S"
        List<Person> filtered;
        filtered = people
                .stream()
                .filter(person -> person.getName().startsWith("S"))
                .collect(Collectors.toList());
        System.out.println("names: " + filtered);
```
This will return the list object `names: [Person@7291c18f, Person@34a245ab]`. In order to see the value of the string its contains we will have to `@Override` `toString` in our Person class. `toString` will be called by `System.out.println` on any type of data that Java identifies as _String_.

```java
//Person.java

//...same as above

@Override
    public String toString() {
        return name.toUpperCase();
    }
```
Now, when we run `Runner.java` we will see the names being printed as a list `names: [SANDY, SKY]`.

We could also group all people by age. We will use the Map interface as we would like to get a set of _key-value_.

```java
//Runner.java

//...same as above

Map<Integer, List<Person>> personsByAge = people
                .stream()
                .collect(Collectors.groupingBy(p -> p.getAge()));

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
                
                
// age 35: [SKY, JUAN]
// age 40: [SANDY]
// age 25: [HANNAH]
```

**Reduce** combines all elements of the stream into a single result. The reduce method accepts three parameters: an identity value, a `BiFunction` accumulator and a combiner function of type `BinaryOperator`. Since the identity values type is not restricted to the Person type, we can utilize this reduction to determine the sum of ages from all people.

```java
//Runner.java

//...same as above

int totalAge = people.stream()
                .map(person -> person.getAge())
                .reduce(0, (sum, age ) -> sum += age);
        System.out.println(totalAge);
        
// 135
```

## That's it

As you saw, Streams are a very powerful tool to chain multiple operations in Java that can make your application faster during runtime and also help us to avoid the sometimes unnecessary mutation of our data.

For more information, check [Stream Javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#NonInterference) package documentation.



