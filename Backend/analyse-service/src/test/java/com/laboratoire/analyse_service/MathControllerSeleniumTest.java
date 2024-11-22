package com.laboratoire.analyse_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class MathControllerSeleniumTest {

    private WebDriver webDriver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        // Initialize ChromeOptions and configure the driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Initialize the RemoteWebDriver with Selenium Hub URL and options
        webDriver = new RemoteWebDriver(
                new URL("http://selenium-hub:4444/wd/hub"), // URL to Selenium Hub
                options, false
        );

    }

    @Test
    public void testAdditionEndpoint() {
        String baseUrl = "http://localhost:8082/add?a=3&b=5"; // Replace with your actual endpoint
        webDriver.get(baseUrl); // Access the URL
        String result = webDriver.getPageSource(); // Get the page source (HTML)
        assertEquals("8", result, "The addition result should be 8."); // Assert the result is "8"
    }
}
