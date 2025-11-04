package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductCatalogue {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(className = "mb-3")
    List<WebElement> products;


    public List<WebElement> getProductList(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
        return products;
    }

    public WebElement getProductByName(String productName){

        return  products.stream().filter(product ->
    product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    }

}