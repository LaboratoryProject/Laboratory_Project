package com.laboratoire.laboratoire_service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class LaboratoireControllerSeleniumTest {

    private WebDriver webDriver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*", // Important for newer Chrome versions
                "--headless",               // Headless mode to run without UI
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );

        // Use actual IP or hostname where Selenium Grid is running
        webDriver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),  // Adjust for your Selenium Hub location
                options
        );
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit(); // Quit the browser session after the test
        }
    }

    @Test
    public void testCreateLaboratoire() {
        String baseUrl = "http://localhost:8080";  // Update with the correct port if different

        // Access the page where the form to create a new "Laboratoire" exists
        webDriver.get(baseUrl + "/createLaboratoire");

        // Fill out the form (Assuming there's a form to create a new Laboratoire)
        webDriver.findElement(By.name("nom")).sendKeys("Laboratoire Test");
        webDriver.findElement(By.name("logo")).sendKeys("logo.png");
        webDriver.findElement(By.name("nrc")).sendKeys("NRC123");
        webDriver.findElement(By.name("active")).click(); // Assuming there's a checkbox or button for 'active'

        // Submit the form
        webDriver.findElement(By.name("submit")).click();

        // Verify if the result is as expected (e.g., success message or new page)
        String confirmationText = webDriver.findElement(By.id("confirmationMessage")).getText().trim();
        assertEquals("Laboratoire created successfully!", confirmationText, "The confirmation message should match.");
    }

    @Test
    public void testGetLaboratoireById() {
        String baseUrl = "http://localhost:8080";  // Update with the correct port if different

        // Simulate visiting the page that retrieves a "Laboratoire" by ID
        webDriver.get(baseUrl + "/laboratoires/1");

        // Verify that the details of the laboratoire are shown
        String laboratoireName = webDriver.findElement(By.id("laboratoireName")).getText().trim();
        assertEquals("Laboratoire Test", laboratoireName, "The laboratoire name should be 'Laboratoire Test'");
    }

    @Test
    public void testDeleteLaboratoire() {
        String baseUrl = "http://localhost:8080";  // Update with the correct port if different

        // Visit the page that handles the deletion of a laboratoire
        webDriver.get(baseUrl + "/laboratoires/delete/1");

        // Verify the success message after deletion
        String confirmationText = webDriver.findElement(By.id("confirmationMessage")).getText().trim();
        assertEquals("Laboratoire deleted successfully.", confirmationText, "The deletion message should match.");
    }
}
