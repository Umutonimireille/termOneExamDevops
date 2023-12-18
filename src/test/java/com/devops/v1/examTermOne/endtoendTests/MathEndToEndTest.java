package com.devops.v1.examTermOne.endtoendTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathEndToEndTest {

    private int port;

    @Value("${webdriver.chrome.driver}")  // Path to your ChromeDriver executable
    private String chromeDriverPath;

    @Test
    public void testEndToEnd() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();

        try {
            // Start the Selenium WebDriver and navigate to the application
            driver.navigate().to("http://localhost:" + port);

            // Fill in the form and click the Calculate button
            WebElement operand1Input = driver.findElement(By.id("operand1"));
            WebElement operationSelect = driver.findElement(By.id("operation"));
            WebElement operand2Input = driver.findElement(By.id("operand2"));
            WebElement calculateButton = driver.findElement(By.tagName("button"));

            operand1Input.sendKeys("3");
            operationSelect.sendKeys("+");
            operand2Input.sendKeys("4");
            calculateButton.click();

            // Wait for the result to be updated
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Check if the result is as expected
            WebElement resultElement = driver.findElement(By.id("result"));
            assertEquals("7", resultElement.getText());

        } finally {
            // Close the WebDriver
            driver.quit();
        }
    }
}
