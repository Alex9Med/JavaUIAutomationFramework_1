package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLogoutPage extends Page {
    public AccountLogoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/a")
    private WebElement continueBtn;

    public void clickContinueBtn() {
        continueBtn.click();
    }
}

