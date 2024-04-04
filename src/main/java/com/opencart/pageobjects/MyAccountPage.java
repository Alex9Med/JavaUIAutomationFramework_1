package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Page {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"column-right\"]/div/a[13]")
    private WebElement logoutBtn;

    public void clickLogoutBtn() {
        logoutBtn.click();
    }
}
