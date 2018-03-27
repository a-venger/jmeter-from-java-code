# README #

This is example project demonstrating creation of JMeter script via Java API followed by execution.

This version covers:

* Running more than one sample
* Store generated JMeter test as .jmx file
* Store test execution results as .jtl file
* Store and open HTML report in default browser
* Running existing jmx scenario

JMX example structure:
TestPlan
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

* Download repository
* Unzip downloaded file and go into that folder
* Execute **mvn clean install** command
* Go into *target* folder
* Execute script as **java -Djmeter.home=YOUR_JMETER_LOCATION -jar example-jar-with-dependencies.jar**
* Open *example.jmx* file under your "jmeter.home" folder for generated script and *example.jtl* for test results
* HTML report is generated under your "jmeter.home" folder in HTMLReport folder. You can simply open *index.html* to check report.
* Inspect **JMeterFromScratch.java** source file at [Source](https://github.com/a-venger/jmeter-from-java-code/src/) page for details on how it is implemented.

OpenJMX
java -Djmeter.home=YOUR_JMETER_LOCATION -DtestPlan.location=YOUR_JMX_LOCATION -jar openJMX-jar-with-dependencies.jar
Example
java -Djmeter.home=YOUR_JMETER_LOCATION -jar example-jar-with-dependencies.jar

