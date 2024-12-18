package com.laboratoire.dossier_service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test") // Ensure you're using the test profile
public class DossierControllerSeleniumTest {

    private WebDriver webDriver;
    private final String baseUrl = "http://localhost:8090"; // Assuming the web server is running on this port

    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*",
                "--headless", // Run tests in headless mode
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );

        // Connect to the Selenium Hub (Ensure that the Selenium Grid is running on localhost:4444)
        webDriver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"), // Selenium Hub address
                options
        );
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit(); // Close the WebDriver instance after each test
        }
    }

    // Test for GET /dossier to verify that the API returns a list of dossiers
    @Test
    public void testGetAllDossiers() {
        webDriver.get(baseUrl + "/dossier");

        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Dossier"), "Response text should contain 'Dossier'");
    }

    // Test for GET /dossier/{numDossier} to retrieve a dossier by its number
    @Test
    public void testGetDossierByNum() {
        String numDossier = "123";  // Replace with a valid dossier number
        webDriver.get(baseUrl + "/dossier/" + numDossier);

        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains(numDossier), "Response text should contain the dossier number");
    }

    // Test for POST /dossier to add a new dossier
    @Test
    public void testCreateDossier() {
        String jsonBody = "{\"fkEmailUtilisateur\":\"test@example.com\", \"fkIdPatient\":1, \"date\":\"2024-12-18\"}";

        // Use a POST request to create a dossier (ensure that this endpoint is implemented in your web app)
        webDriver.get(baseUrl + "/dossier/create?json=" + jsonBody);

        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Created"), "Response text should contain 'Created'");
    }

    // Test for PUT /dossier/{id} to update a dossier
    @Test
    public void testUpdateDossier() {
        Long dossierId = 1L;
        String jsonBody = "{\"fkEmailUtilisateur\":\"updated@example.com\", \"fkIdPatient\":1, \"date\":\"2024-12-19\"}";

        // Use a PUT request to update a dossier
        webDriver.get(baseUrl + "/dossier/" + dossierId + "/update?json=" + jsonBody);

        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Updated"), "Response text should contain 'Updated'");
    }

    // Test for DELETE /dossier/{id} to delete a dossier
    @Test
    public void testDeleteDossier() {
        Long dossierId = 1L;

        // Use a DELETE request to delete a dossier
        webDriver.get(baseUrl + "/dossier/" + dossierId + "/delete");

        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Deleted"), "Response text should contain 'Deleted'");
    }
}
