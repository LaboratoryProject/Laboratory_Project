package com.laboratoire.dossier_service;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class DossierServicePerformanceTest {

    @Test
    public void testDossierEndpointPerformance() {
        try {
            // Charger les propriétés JMeter
            String propertiesPath = new File("src/test/resources/jmeter.properties").getAbsolutePath();
            JMeterUtils.loadJMeterProperties(propertiesPath);
            JMeterUtils.initLocale();
            JMeterUtils.initLogging();

            // Initialiser l'engine JMeter
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            // Créer l'arbre du plan de test
            HashTree testPlanTree = new HashTree();

            // HTTP Sampler pour le service dossier
            HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
            httpSampler.setDomain("localhost");
            httpSampler.setPort(8090);
            httpSampler.setPath("/api/v1/dossier");
            httpSampler.setMethod("GET");
            httpSampler.setName("HTTP Request - Dossier");
            httpSampler.setFollowRedirects(true);

            // Ajouter un Header Manager avec un en-tête
            HeaderManager headerManager = new HeaderManager();
            headerManager.add(new Header("Content-Type", "application/json"));
            httpSampler.setHeaderManager(headerManager);

            // Configurer le Thread Group
            ThreadGroup threadGroup = new ThreadGroup();
            threadGroup.setNumThreads(10); // 10 utilisateurs simultanés
            threadGroup.setRampUp(5);  // Temps pour démarrer les 10 utilisateurs
            threadGroup.setSamplerController(new LoopController());

            // Créer le Test Plan
            TestPlan testPlan = new TestPlan("Test de Performance Dossier");

            // Ajouter le plan de test à l'arbre
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);
            threadGroupTree.add(httpSampler);

            // Exécuter le test
            jmeter.configure(testPlanTree);
            jmeter.run();

        } catch (Exception e) {
            e.printStackTrace();
            fail("Le test de performance a échoué : " + e.getMessage());
        }
    }
}
