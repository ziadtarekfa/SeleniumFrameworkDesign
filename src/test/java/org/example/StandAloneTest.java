package org.example;

import org.example.TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StandAloneTest extends BaseTest {


    @Test
    public void submitOrder() throws InterruptedException {
        LandingPage landingPage = launchApplication();


        landingPage.login("ziad@gmail.com", "Hello123");

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addProductToCart("ZARA COAT 3");

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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


