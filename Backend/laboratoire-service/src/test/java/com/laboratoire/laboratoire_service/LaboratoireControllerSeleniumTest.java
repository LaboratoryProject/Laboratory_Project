package com.laboratoire.laboratoire_service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaboratoireControllerSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private WebDriverWait shortWait; // Add short wait for faster operations

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-web-security",
                "--allow-insecure-localhost",
                "--ignore-certificate-errors",
                "--disable-dev-shm-usage", // Add performance options
                "--no-sandbox",
                "--disable-gpu"
        );

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Reduce default wait time from 30 to 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Add short wait for quick operations
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        keycloakLogin();
    }


    @AfterEach
    void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateLaboratoire() {
        try {
            driver.get("http://localhost:4200/add");
            shortWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            // Use JavaScript to fill form fields faster
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.get("http://localhost:4200/add");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            System.out.println("Page loaded successfully.");

            // Fill in the form fields
            fillField(By.id("nom"), "Laboratoire Test");
            uploadFile(By.id("logo"), "src/test/ressources/test-logo.png");
            fillField(By.id("nrc"), "NA3331155");
            toggleCheckbox(By.id("active"), true);
            fillField(By.id("dateActivation"), "11-02-2002");
            fillField(By.id("numTel"), "0131128911");
            fillField(By.id("fax"), "0983117021");
            fillField(By.id("email"), "test21189@laboratoire.com");
            fillField(By.id("numVoie"), "4031");
            fillField(By.id("nomVoie"), "Rue de Test");
            fillField(By.id("codePostal"), "75021");
            fillField(By.id("ville"), "Paris");
            fillField(By.id("commune"), "Paris");


            // Quick check for spinner
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));

            // Click submit button
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Ajouter']")));
            js.executeScript("arguments[0].click();", addButton);

            // Validate success (this will now handle the alert)
            validateSuccess("Laboratoire ajouté avec succès");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            throw e;
        }
    }

    private void keycloakLogin() {
        driver.get("http://localhost:9090/realms/Laboratory-realm/protocol/openid-connect/auth?client_id=Angular&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2Fadd&state=f91fc17c-b574-4b20-bfc4-fc8a74f3553c&response_mode=fragment&response_type=code&scope=openid&nonce=0beb5d8b-6b63-4c4d-b1b4-fb8978d907b5&code_challenge=muXZmdVWIzHxi6lIDa2uWbR1s4YIx2KUxTtu_Us9LFg&code_challenge_method=S256");

        // Wait for Keycloak login page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

        // Enter username and password
        driver.findElement(By.id("username")).sendKeys("hibamoustaoukkkdi@gmail.com");
        driver.findElement(By.id("password")).sendKeys("hibamoustaoudi");

        // Submit login form
        driver.findElement(By.id("kc-login")).click();

        // Wait for redirection to the Angular app
        wait.until(ExpectedConditions.urlContains("http://localhost:4200"));
    }

    private void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
    }

    private void uploadFile(By locator, String relativePath) {
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        fileInput.sendKeys(getAbsolutePath(relativePath));
    }

    private void toggleCheckbox(By locator, boolean state) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected() != state) {
            checkbox.click();
        }
    }

    private void validateSuccess(String successMessage) {
        try {
            // Wait for and check alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);

            if (alertText.contains(successMessage)) {
                // Accept the alert if it contains our success message
                alert.accept();
                return;
            }

            // If we get here, the alert didn't contain our success message
            alert.dismiss();
            throw new AssertionError("Alert found but didn't contain expected message. Alert text: " + alertText);

        } catch (TimeoutException e) {
            // No alert found, throw error
            throw new AssertionError("No success alert found: " + successMessage);
        }
    }

    private String getAbsolutePath(String relativePath) {
        File file = new File(relativePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + relativePath);
        }
        return file.getAbsolutePath();
    }
}
