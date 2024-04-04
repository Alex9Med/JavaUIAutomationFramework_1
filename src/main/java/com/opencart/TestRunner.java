package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();

        String currentWindowName = driver.getWindowHandle();

        // New Window Code
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://opencart.antropy.co.uk/");
        Thread.sleep(1000);

        WebElement myAccountIcon = driver.findElement(By.xpath("//i[@class='fa fa-user']"));
        myAccountIcon.click();

        WebElement registerOption = driver.findElement(By.xpath("(//a[normalize-space()='Register'])[1]"));
        registerOption.click();

        System.out.println(driver.getCurrentUrl());


        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        String firstName = RandomDataManager.generateFirstName();
        firstNameInput.sendKeys(firstName);
        System.out.println(firstName);

        WebElement lastNameInput = driver.findElement(By.cssSelector("#input-lastname"));
        String lastName = RandomDataManager.generateLastName();
        lastNameInput.sendKeys(lastName);
        System.out.println(lastName);

        WebElement emailInput = driver.findElement(By.cssSelector("#input-email"));
        String randomEmail = RandomDataManager.generateRandomEmail();
        emailInput.sendKeys(randomEmail);
        System.out.println(randomEmail);

        WebElement telephoneInput = driver.findElement(By.cssSelector("#input-telephone"));
        String randomTelephone = RandomDataManager.generateTelephoneNumber();
        telephoneInput.sendKeys(randomTelephone);
        System.out.println(randomTelephone);

        WebElement passwordInput = driver.findElement(By.cssSelector("#input-password"));
        String randomPassword = RandomDataManager.generatePassword();
        passwordInput.sendKeys(randomPassword);

        WebElement confirmPasswordInput = driver.findElement(By.cssSelector("#input-confirm"));
        confirmPasswordInput.sendKeys(randomPassword);
        System.out.println(randomPassword);

        // Manual captcha solving
        Thread.sleep(10000);

        WebElement privacyPolicyCheckBox = driver.findElement(By.xpath("//input[@name='agree']"));
        privacyPolicyCheckBox.click();

        WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
        continueButton.click();


        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();

        System.out.println("The execution is over !");

    }


}