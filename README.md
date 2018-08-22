# rational-ex14


This repo is a work in progress on demonstrating Lambdas and Functional Interfaces in Java.

See also: <https://www.baeldung.com/java-8-functional-interfaces>

"Site" published by `mvn site`: <https://ucsb-cs56-pconrad.github.io/cs56-rational-ex14/apidocs>


Javadoc: <https://ucsb-cs56-pconrad.github.io/cs56-rational-ex14/apidocs>

Test Coverage: <https://ucsb-cs56-pconrad.github.io/cs56-rational-ex14/jacoco>

# A table of Rationals

We are going to implement a method

```java
  String tableOfRationalsMarkdown(int rows, int cols) { ...
```

This method will produce a table such as this one for the call:

Rational.tableOfRationalsMarkdown(3,4)

Numerators

```
 |1|2|3|4|
-|-|-|-|-|
1|1|2|3|4|
2|1/2|1|3/2|2|
3|1/3|2/3|1|4/3|
```