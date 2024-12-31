package com.laboratoire.patient_service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.fail;

public class PerformanceTest {

    @BeforeAll
    public static void setupJMeter() {
        try {
            // Initialize JMeter
            String jmeterHome = "src/test/resources/jmeter";
            String propertiesFile = "src/test/resources/jmeter.properties";

            JMeterUtils.loadJMeterProperties(propertiesFile);
            JMeterUtils.setJMeterHome(jmeterHome);
            JMeterUtils.initLocale();

            // Initialize XStream converter setup (if necessary)
            XStream xstream = new XStream();
            xstream.registerConverter(new ReflectionConverter(xstream.getMapper(), xstream.getReflectionProvider()) {
                @Override
                public boolean canConvert(Class type) {
                    return AtomicInteger.class.isAssignableFrom(type);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to initialize JMeter: " + e.getMessage());
        }
    }

    @Test
    public void testPatientServicePerformance() {
        try {
            // Create JMeter Engine
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            // Create Test Plan
            TestPlan testPlan = new TestPlan("Patient Service Performance Test Plan");
            testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
            testPlan.setProperty(TestElement.GUI_CLASS, TestPlan.class.getName());
            testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

            // Create HTTP Requests
            // 1. GET Patients
            HTTPSamplerProxy getPatients = createHttpRequest(
                    "GET Patients",
                    "localhost",
                    8081,
                    "/api/patients",
                    "GET"
            );

            // 2. POST New Patient
            HTTPSamplerProxy createPatient = createHttpRequest(
                    "Create Patient",
                    "localhost",
                    8081,
                    "/api/patients",
                    "POST"
            );
            // Add request body for POST
            String jsonBody = """
                {
                    "nomComplet": "John Doe",
                    "dateNaissance": "1990-01-01",
                    "lieuDeNaissance": "Paris",
                    "sexe": "Masculin",
                    "typePieceIdentite": "Passport",
                    "numPieceIdentite": "A1234567",
                    "adresse": "123 Main St",
                    "numTel": "0123456789",
                    "email": "john.doe@example.com",
                    "visible_pour": true
                }
                """;
            createPatient.setPostBodyRaw(true);
            createPatient.addNonEncodedArgument("", jsonBody, "");
            createPatient.setContentEncoding("UTF-8");

            // Create Header Manager
            HeaderManager headerManager = new HeaderManager();
            headerManager.add();
            headerManager.add();

            // Create Thread Group
            ThreadGroup threadGroup = new ThreadGroup();
            threadGroup.setName("Patient Service Thread Group");
            threadGroup.setNumThreads(50); // Number of users
            threadGroup.setRampUp(10); // Ramp-up period in seconds
            threadGroup.setSamplerController(createLoopController(5)); // 5 iterations per thread

            // Build Test Plan Tree
            HashTree testPlanTree = new HashTree();
            HashTree threadGroupHashTree = testPlanTree.add(testPlan)
                    .add(threadGroup);

            // Add samplers to thread group
            threadGroupHashTree.add(headerManager);
            threadGroupHashTree.add(getPatients);
            threadGroupHashTree.add(createPatient);

            // Add Summariser to get test results
            Summariser summer = new Summariser("Patient Service Performance Results");
            String logFile = "target/jmeter/results_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".jtl";
            ResultCollector logger = new ResultCollector(summer);
            logger.setFilename(logFile);
            testPlanTree.add(testPlanTree.getArray()[0], logger);

            // Save test plan to file
            SaveService.saveTree(testPlanTree, new FileOutputStream("target/jmeter/testplan.jmx"));

            // Run Test Plan
            jmeter.configure(testPlanTree);
            jmeter.run();

            System.out.println("Performance test completed. Results saved to: " + logFile);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Performance test failed: " + e.getMessage());
        }
    }

    private HTTPSamplerProxy createHttpRequest(String name, String domain, int port, String path, String method) {
        HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
        httpSampler.setDomain(domain);
        httpSampler.setPort(port);
        httpSampler.setPath(path);
        httpSampler.setMethod(method);
        httpSampler.setName(name);
        httpSampler.setFollowRedirects(true);
        return httpSampler;
    }

    private LoopController createLoopController(int loops) {
        LoopController loopController = new LoopController();
        loopController.setLoops(loops);
        loopController.setFirst(true);
        loopController.initialize();
        return loopController;
    }
}
