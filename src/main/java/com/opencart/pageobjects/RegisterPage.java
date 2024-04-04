package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Page {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='input-lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='input-telephone']")
    private WebElement telephoneInput;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='input-confirm']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement privacyToggle;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueBtn;

    public void fillInTheRegisterForm(String firstName, String lastName, String email, String telephone, String password, String confirmPassword, boolean toggle) throws InterruptedException {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        telephoneInput.sendKeys(telephone);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(confirmPassword);
        if (toggle) {
            privacyToggle.click();
        }
        // Manual captcha solving
        Thread.sleep(8000);
        continueBtn.click();
    }


}
