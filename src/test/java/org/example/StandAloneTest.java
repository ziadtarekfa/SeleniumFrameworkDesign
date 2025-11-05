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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.login("ziad@gmail.com", "Hello123");

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addProductToCart("ZARA COAT 3");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));


        Header header = new Header(driver);
        header.clickCart();


        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.verifyProductDisplayed("ZARA COAT 3"));
        cartPage.goToCheckout();


        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("Egypt");
        checkoutPage.placeOrder();

        Thread.sleep(3000);

        driver.quit();

    }

}
