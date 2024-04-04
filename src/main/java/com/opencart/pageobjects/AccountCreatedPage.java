package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends Page {
    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/a")
    private WebElement continueButton;

    public void clickContinueButton() {
        continueButton.click();
    }
}
