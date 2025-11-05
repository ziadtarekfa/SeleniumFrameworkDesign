package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "input[placeholder='Select Country']")
    WebElement countryDropDown;

    @FindBy(css = ".ta-results button")
    WebElement countryBtn;

    @FindBy(css = ".actions a")
    WebElement placeOrderBtn;

    public void selectCountry(String country) {
        countryDropDown.sendKeys(country);
        countryBtn.click();
    }

    public void placeOrder() {
        placeOrderBtn.click();
    }

}
