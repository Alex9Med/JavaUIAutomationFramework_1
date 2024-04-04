package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import com.opencart.pageobjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class TestRunnerWithPageObjects {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://opencart.antropy.co.uk/");

        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPageFromHeader();

        RegisterPage registerPage = new RegisterPage(driver);

        String randomEmail = RandomDataManager.generateRandomEmail();
        String randomPassword = RandomDataManager.generatePassword();
        System.out.println("Generated data: " + randomEmail + "  " + randomPassword);

        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(), RandomDataManager.generateLastName(),
                randomEmail, RandomDataManager.generateTelephoneNumber(), randomPassword, randomPassword, true);
        System.out.println("The registration took place with: " + randomEmail + "  " + randomPassword);

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.clickContinueButton();

        accountCreatedPage.navigateToLogoutFromHeader();

        AccountLogoutPage accountLogoutPage = new AccountLogoutPage(driver);
        accountLogoutPage.clickContinueBtn();

        homePage.navigateToLoginFromHeader();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInTheLoginForm(randomEmail, randomPassword);
        System.out.println("The login form is completed by: " + randomEmail + " " + randomPassword);
        loginPage.clickLoginBtn();
        System.out.println("The logging was successful.");

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.navigateToLogoutFromHeader();
        myAccountPage.clickLogoutBtn();
        DriverManager.getInstance().tearDown();
        System.out.println("The execution is over !");

    }

}