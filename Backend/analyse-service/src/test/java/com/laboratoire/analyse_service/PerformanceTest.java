package com.laboratoire.analyse_service;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class PerformanceTest {
    @Test
    public void testAdditionEndpointPerformance() {
        try {
            // Programmatically set JMeter home and properties
            // Use a temporary directory or your project's resource directory

<<<<<<< HEAD
            // Use this to set the properties path
            String propertiesPath = new File("src/test/resources/jmeter.properties").getAbsolutePath();
            JMeterUtils.loadJMeterProperties(propertiesPath);
=======
        // JMeter initialization (properties, log levels, locale)
        JMeterUtils.loadJMeterProperties("C:/apache-jmeter-5.6.3/bin/jmeter.properties");
>>>>>>> 4f9e37d436a043a82dc23f40bdf526d2a06ef991


            JMeterUtils.initLocale();
            JMeterUtils.initLogging();

            // JMeter Engine
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            // Test Plan Tree
            HashTree testPlanTree = new HashTree();

            // HTTP Sampler
            HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
            httpSampler.setDomain("localhost");
            httpSampler.setPort(8080);
            httpSampler.setPath("/api/v1/resource");
            httpSampler.setMethod("GET");
            httpSampler.setName("HTTP Request");
            httpSampler.setFollowRedirects(true);

            // Header Manager (optional)
            HeaderManager headerManager = new HeaderManager();
            httpSampler.setHeaderManager(headerManager);

            // Thread Group
            ThreadGroup threadGroup = new ThreadGroup();
            threadGroup.setNumThreads(10);
            threadGroup.setRampUp(5);
            threadGroup.setSamplerController(new LoopController());

            // Test Plan
            TestPlan testPlan = new TestPlan("Simple Test Plan");

            // Build Test Plan
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);
            threadGroupTree.add(httpSampler);

            // Run Test Plan
            jmeter.configure(testPlanTree);
            jmeter.run();

        } catch (Exception e) {
            e.printStackTrace();
            fail("Performance test failed: " + e.getMessage());
        }
    }
}