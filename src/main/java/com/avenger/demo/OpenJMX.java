package com.avenger.demo;

import org.apache.commons.io.FileUtils;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.awt.*;
import java.io.File;

public class OpenJMX {

    public static void main(String[] argv) throws Exception {

        // Jmeter location
        File jmeterHome = new File(System.getProperty("jmeter.home"));
        String slash = System.getProperty("file.separator");

        // Ready to start JMX scenario location
        File testPlan = new File(System.getProperty("testPlan.location"));

        if (jmeterHome.exists()) {
            if (testPlan.exists()) {
                File jmeterProperties = new File(jmeterHome.getPath() + slash + "bin" + slash + "jmeter.properties");
                if (jmeterProperties.exists()) {
                    // JMeter Engine
                    StandardJMeterEngine jmeter = new StandardJMeterEngine();

                    // Initialize Properties, locale, etc.
                    JMeterUtils.setJMeterHome(jmeterHome.getPath());
                    JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
                    JMeterUtils.initLocale();

                    // Set directory for HTML report
                    String repDir = jmeterHome.getPath() + slash + "HTMLReport";
                    JMeterUtils.setProperty("jmeter.reportgenerator.exporter.html.property.output_dir", repDir);

                    // Initialize JMeter SaveService
                    SaveService.loadProperties();

                    // Load existing .jmx Test Plan
                    HashTree testPlanTree = SaveService.loadTree(testPlan);

                    //add Summarizer output to get test progress in stdout like:
                    // summary =      2 in   1.3s =    1.5/s Avg:   631 Min:   290 Max:   973 Err:     0 (0.00%)
                    Summariser summer = null;
                    String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
                    if (summariserName.length() > 0) {
                        summer = new Summariser(summariserName);
                    }

                    // Store execution results into a .jtl file
                    String logFile = jmeterHome + slash + "example.jtl";
                    FileUtils.forceDelete(new File(logFile)); //delete log file
                    ResultCollector logger = new ResultCollector(summer);
                    ReportGenerator reportGenerator = new ReportGenerator(logFile, logger); //creating ReportGenerator for creating HTML report
                    logger.setFilename(logFile);
                    testPlanTree.add(testPlanTree.getArray()[0], logger);

                    // Run JMeter Test
                    jmeter.configure(testPlanTree);
                    jmeter.run();

                    // Report Generator
                    FileUtils.deleteDirectory(new File(repDir)); //delete old report
                    reportGenerator.generate();

                    System.out.println("Test completed. See " + jmeterHome + slash + "example.jtl file for results");

                    //Open HTML report in default browser
                    File htmlFile = new File(repDir + "\\index.html");
                    Desktop.getDesktop().browse(htmlFile.toURI());

                    System.exit(0);
                }
            }
            System.err.println("testPlan.location property is not set or pointing to incorrect location");
            System.exit(1);
        }
        System.err.println("jmeter.home property is not set or pointing to incorrect location");
        System.exit(1);
    }
}
