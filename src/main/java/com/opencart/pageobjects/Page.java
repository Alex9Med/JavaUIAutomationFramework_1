package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//i[@class='fa fa-user']")
    protected WebElement myAccountIcon;

    @FindBy(xpath = "(//a[normalize-space()='Register'])[1]")
    protected WebElement registerBtn;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    protected WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]/a")
    protected WebElement logoutBtn;

    public void navigateToRegisterPageFromHeader() {
        myAccountIcon.click();
        registerBtn.click();
    }

    public void navigateToLoginFromHeader() {
        myAccountIcon.click();
        loginBtn.click();
    }

    public void navigateToLogoutFromHeader() {
        myAccountIcon.click();
        logoutBtn.click();
    }


}
