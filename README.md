# README #

This is example project demonstrating creation of JMeter script via Java API followed by execution. Also you are able to run existing JMeter script with Java code.

This example of JMeter script via Java API covers:

* Running more than one sample
* Using timers with random pauses
* Using CSV Data Set for achieving data from CSV files
* Cookie managment via Cookie Manager
* Using Transaction Controller with generating parent sample
* Store generated JMeter test as .jmx file
* Store test execution results as .jtl file
* Store and open HTML report in default browser

## Requirements

The following requirements exist for running Apache JMeter:

*  Java Interpreter:

    A fully compliant Java 8 Runtime Environment is required 
    for Apache JMeter to execute.
	
	
JMX example structure:
![Scenario Structure](https://github.com/a-venger/jmeter-from-java-code/blob/master/apache-jmeter-4.0/resources/ScenarioStructure.png)
	- Cookie Manager
	- CSV Data Set
	- Random Variable Config Element
	- Thread Group
		- Transaction Controller
			- 1st HTTP Sample
			- 2nd HTTP Sample
		- 1st HTTP Sample
		- Test action (Pause for random time)
		- 2nd HTTP Sample
		- If Controller (Condition - 'true')
			- 1st HTTP Sample

### Configuration instructions ###

You can simply execute ready to run examples.
The commands are simple.
To open and run existing JMX simply execute in command line from *jar* directory.

**java -jar openJMX-jar-with-dependencies.jar**

To run example of JMeter script via Java API execute in command line from *jar* directory.
**java -jar example-jar-with-dependencies.jar**

You can also run those via prepared scripts (.bat - for Windows and .sh - for Linux). They are provided in project root.

You can expand exectuion by providing additional parameters.

* -Djmeter.home=YOUR_JMETER_LOCATION - to use your jmeter properties and store result files there.
* -DtestPlan.location=YOUR_JMX_LOCATION - to run existing JMX via openJMX class

So expanded commands will look like this:

To open and run existing jmx and providing your JMeter location

**java -Djmeter.home=YOUR_JMETER_LOCATION -DtestPlan.location=YOUR_JMX_LOCATION -jar openJMX-jar-with-dependencies.jar**

To run example of JMeter script via Java API and providing your JMeter location

**java -Djmeter.home=YOUR_JMETER_LOCATION -jar example-jar-with-dependencies.jar**



