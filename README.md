# jacoco-demo

# THIS IS THE BRANCH for 08.15.2018

TBD

#  Demonstration of code test coverage measurement

* See: https://ucsb-cs56-pconrad.github.io/tutorials/rational_ex12

Helpful tutorials:

* http://www.baeldung.com/jacoco
    * I got the part of the pom.xml that does the plugin thing from here


To see results:

* `mvn compile`
* `mvn test`
* `mvn jacoco:report`

Open: target/site/jacoco/index.html in a web browser.


OR, IF any ONLY IF your project is open source:

Use:

```
mvn test
mvn jacoco:report
mvn site:deploy
git add docs
git commit -m "commit contents of docs subdirectory"
git push origin master
```

Then make sure your repo is configured to publish to github-pages from the docs subdirectory.

