package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement emailAddressInput;
    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginBtn;

    public void fillInTheLoginForm(String emailAddress, String password) {
        emailAddressInput.sendKeys(emailAddress);
        passwordInput.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
