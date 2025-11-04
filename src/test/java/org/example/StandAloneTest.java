package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://rahulshettyacademy.com/client");


        driver.findElement(By.id("userEmail")).sendKeys("ziad@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Hello123");
        driver.findElement(By.id("login")).click();





        List <WebElement> products  = driver.findElements(By.className("mb-3"));


        WebElement selectedProduct = products.stream().filter(product ->
                product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);

        assert selectedProduct != null;
        selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();


        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));

       boolean isMatch =  cartItems.stream().anyMatch(item-> item.getText().equalsIgnoreCase("ZARA COAT 3"));

        Assert.assertTrue(isMatch);

        WebElement checkoutBtn = driver.findElement(By.cssSelector(".cart .btn-primary"));
        checkoutBtn.click();


        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Egypt");

        driver.findElement(By.cssSelector(".ta-results button")).click();

WebElement placeOrderBtn =  driver.findElement(By.cssSelector(".actions a"));


        placeOrderBtn.click();

        driver.quit();

    }

}
