package com.laboratoire.analyse_service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class MathControllerSeleniumTest {

    private WebDriver webDriver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*", // Important for newer Chrome versions
                "--headless",
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );

        // Use actual IP or hostname where Selenium Grid is running
        webDriver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                options,
                false
        );
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testAdditionEndpoint() {

        String baseUrl = "http://host.docker.internal:8082";  // For local testing

        webDriver.get(baseUrl + "/add?a=3&b=5");

        String resultText = webDriver.findElement(By.tagName("body")).getText().trim();
        assertEquals("8", resultText, "The addition result should be 8");
    }
}
