package com.laboratoire.analyse_service;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;

public class PerformanceTest {
    @Test
    public void testAdditionEndpointPerformance() {
        // JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        // JMeter initialization (properties, log levels, locale)
        JMeterUtils.loadJMeterProperties("C:/apache-jmeter-5.6.3/bin/jmeter.properties");

        // Test Plan
        TestPlan testPlan = new TestPlan("Performance Test");

        // Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setNumThreads(50);
        threadGroup.setRampUp(10);
        threadGroup.setSamplerController(new LoopController());

        // HTTP Sampler
        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setDomain("localhost");
        httpSampler.setPort(8082);
        httpSampler.setPath("/add");
        httpSampler.addArgument("a", "3");
        httpSampler.addArgument("b", "5");

        // Construct Test Plan from components
        HashTree testPlanTree = new HashTree();
        testPlanTree.add(testPlan);
        testPlanTree.add(threadGroup);
        testPlanTree.add(httpSampler);

        // Run Test Plan
        jmeter.configure(testPlanTree);
        jmeter.run();
    }
}