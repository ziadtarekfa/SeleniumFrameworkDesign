package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");


        driver.findElement(By.id("userEmail")).sendKeys("ziad@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Hello123");
        driver.findElement(By.id("login")).click();


        List <WebElement> products  = driver.findElements(By.className("mb-3"));


        WebElement selectedProduct = products.stream().filter(product ->
                product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);

        assert selectedProduct != null;
        selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

    }

}
