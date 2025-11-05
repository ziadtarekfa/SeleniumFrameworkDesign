package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartItems;

    @FindBy(css = ".cart .btn-primary")
    WebElement checkoutBtn;

    public boolean verifyProductDisplayed(String productName) {
        return cartItems.stream().anyMatch(item-> item.getText().equalsIgnoreCase(productName));
    }

    public void goToCheckout() {
        checkoutBtn.click();
    }

}
