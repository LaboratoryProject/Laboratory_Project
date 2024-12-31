package com.laboratoire.dossier_service;

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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DossierControllerSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait shortWait;

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
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        keycloakLogin();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateDossier() {
        try {
            driver.get("http://localhost:4200/add-dossier");
            shortWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            // Use JavaScript to fill form fields faster
            JavascriptExecutor js = (JavascriptExecutor) driver;

            System.out.println("Page loaded successfully.");

            // Fill in the form fields
            fillField(By.id("numDossier"), "D123456");
            fillField(By.id("fkEmailUtilisateur"), "user@example.com");
            fillField(By.id("fkIdPatient"), "1");
            fillField(By.id("date"), "2024-12-28");

            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));

            // Click submit button
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Ajouter']")));
            js.executeScript("arguments[0].click();", addButton);

            // Validate success message
            validateSuccess("Dossier ajouté avec succès");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            throw e;
        }
    }

    private void keycloakLogin() {
        driver.get("http://localhost:9090/realms/Laboratory-realm/protocol/openid-connect/auth?client_id=Angular&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2Fadd-dossier&response_mode=fragment&response_type=code&scope=openid");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

        driver.findElement(By.id("username")).sendKeys("nouvel.email@example.com");
        driver.findElement(By.id("password")).sendKeys("nouveauMotDePasse");

        driver.findElement(By.id("kc-login")).click();

        wait.until(ExpectedConditions.urlContains("/add-dossier"));
    }

    private void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
    }

    private void validateSuccess(String successMessage) {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);

            if (alertText.contains(successMessage)) {
                alert.accept();
                return;
            }

            alert.dismiss();
            throw new AssertionError("Alert found but didn't contain expected message. Alert text: " + alertText);

        } catch (TimeoutException e) {
            throw new AssertionError("No success alert found: " + successMessage);
        }
    }
}
