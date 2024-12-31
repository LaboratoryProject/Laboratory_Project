package com.laboratoire.patient_service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientControllerSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-web-security",
                "--allow-insecure-localhost",
                "--ignore-certificate-errors",
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--disable-gpu"
        );

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        keycloakLogin();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddPatient() {
        try {
            driver.get("http://localhost:4200/add-patient");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));

            // Fill in all form fields
            fillFormFields();

            // Handle the checkbox with special care
            handleCheckbox();

            // Submit the form
            submitForm();

            // Handle success alert and validation
            handleSuccessAlert();

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            // Take screenshot on failure
            if (driver instanceof TakesScreenshot) {
                try {
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    System.out.println("Screenshot taken");
                } catch (Exception se) {
                    System.out.println("Failed to take screenshot: " + se.getMessage());
                }
            }
            throw e;
        }
    }

    private void fillFormFields() {
        // Fill text fields with explicit waits and scrolling
        fillField(By.cssSelector("input[formControlName='nomComplet']"), "patrick Doe");
        fillField(By.cssSelector("input[formControlName='dateNaissance']"), "1990-01-01");
        fillField(By.cssSelector("input[formControlName='lieuDeNaissance']"), "Paris");
        selectOption(By.cssSelector("select[formControlName='sexe']"), "Masculin");
        fillField(By.cssSelector("input[formControlName='typePieceIdentite']"), "Passport");
        fillField(By.cssSelector("input[formControlName='numPieceIdentite']"), "AQ234567");
        fillField(By.cssSelector("input[formControlName='adresse']"), "123 MPin St");
        fillField(By.cssSelector("input[formControlName='numTel']"), "0177456789");
        fillField(By.cssSelector("input[formControlName='email']"), "john1.doe@example.com");
    }

    private void handleCheckbox() {
        try {
            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[formControlName='visible_pour']")));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkbox);
            Thread.sleep(500);

            try {
                wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
            } catch (ElementClickInterceptedException e) {
                js.executeScript("arguments[0].click();", checkbox);
            }
        } catch (Exception e) {
            System.out.println("Error handling checkbox: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void submitForm() {
        try {
            WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(text(), ' suivant')]")));

            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", submitButton);
            Thread.sleep(500);

            try {
                wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
            } catch (ElementClickInterceptedException e) {
                js.executeScript("arguments[0].click();", submitButton);
            }
        } catch (Exception e) {
            System.out.println("Error submitting form: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void handleSuccessAlert() {
        try {
            // Wait for the alert to be present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // Verify the alert message
            String alertText = alert.getText();
            assertEquals("Patient ajouté avec succès", alertText, "Unexpected alert message");

            // Accept the alert
            alert.accept();

            // Additional validation if needed
            // Wait for any post-alert UI updates
            Thread.sleep(1000);

            // You can add additional validation here if needed
            // For example, check if the form is cleared or if you're redirected to a new page

        } catch (Exception e) {
            System.out.println("Error handling success alert: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void fillField(By locator, String value) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            Thread.sleep(300);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            System.out.println("Error filling field: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void selectOption(By locator, String value) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", dropdown);
            Thread.sleep(300);
            dropdown.click();
            WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//option[text()='" + value + "']")));
            option.click();
        } catch (Exception e) {
            System.out.println("Error selecting option: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void keycloakLogin() {
        driver.get("http://localhost:9090/realms/Laboratory-realm/protocol/openid-connect/auth?client_id=Angular&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2Fadd-patient&state=f91fc17c-b574-4b20-bfc4-fc8a74f3553c&response_mode=fragment&response_type=code&scope=openid&nonce=0beb5d8b-6b63-4c4d-b1b4-fb8978d907b5&code_challenge=muXZmdVWIzHxi6lIDa2uWbR1s4YIx2KUxTtu_Us9LFg&code_challenge_method=S256");

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

    private void waitUntilOverlayIsGone() {
        try {
            // Wait until any potential overlay or modal is gone
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-overlay"))); // Adjust based on your actual overlay class
        } catch (TimeoutException e) {
            System.out.println("No overlay detected.");
        }
    }

}
