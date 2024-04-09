package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import com.opencart.pageobjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestRegistrationFlowWithJunit {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    AccountCreatedPage accountCreatedPage;


    @BeforeAll
    public static void executeThisMethodBeforeAllTheTests(){
        System.out.println("The execution of the test suite has started");
    }

    @BeforeEach
    public void executeTheCodeBeforeEachTest(){
        System.out.println("The code is executed before each test case");
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://opencart.antropy.co.uk/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        homePage.navigateToRegisterPageFromHeader();
    }

    @Test
    @DisplayName("The registration of a new user with valid data redirects to the Account Page")
    public void registerWithValidCredentialsTest() throws InterruptedException {
        System.out.println("This is the test number 1");

        String randomEmail = RandomDataManager.generateRandomEmail();
        String randomPassword = RandomDataManager.generatePassword();

        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(), RandomDataManager.generateLastName(),
                randomEmail, RandomDataManager.generateTelephoneNumber(), randomPassword, randomPassword, true);
        System.out.println("The registration took place with: " + randomEmail + "  " + randomPassword);

        accountCreatedPage.clickContinueButton();
        System.out.println("Account is successful created!");

        boolean doesTheCurrentUrlContainsAccountAccountRoute = driver.getCurrentUrl().contains("route=account/account");
        Assertions.assertTrue(doesTheCurrentUrlContainsAccountAccountRoute, "The current URL contains: route=account/account");

    }

    @Test
    @DisplayName("The user is remaining on Register Page when trying to register with invalid password")
    public void registerWithInvalidPasswordTest() throws InterruptedException {
        System.out.println("This is the test number 2");

        String randomEmail = RandomDataManager.generateRandomEmail();

        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(), RandomDataManager.generateLastName(),
                randomEmail, RandomDataManager.generateTelephoneNumber(), "1", "1", true);
        System.out.println("The registration took place with: " + randomEmail + "  " + "1");

//        if (driver.getCurrentUrl().contains("route=account/success")){
//            accountCreatedPage.clickContinueButton();
//            String actualUrl = driver.getCurrentUrl();
//            String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/register";
//            Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");
//        } else {
//            String actualUrl = driver.getCurrentUrl();
//            String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/register";
//            Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");
//        }

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/register";
        Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");

    }


    @Test
    @DisplayName("Error message is displayed on Register flow when password is less than 4 chars")
    public void errorMessageIsDisplayedWhenInvalidPasswordIsUsedForRegisterFlow() throws InterruptedException {
        System.out.println("This is the test number 3");

        String randomEmail = RandomDataManager.generateRandomEmail();

        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(), RandomDataManager.generateLastName(),
                randomEmail, RandomDataManager.generateTelephoneNumber(), "Aa1", "Aa1", true);
        System.out.println("The registration took place with: " + randomEmail + "  " + "Aa1");

        String expectedErrorMessageForInvalidPassword = "Password must be between 4 and 20 characters!";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset[2]/div[1]/div/div")).getText();
        Assertions.assertEquals(expectedErrorMessageForInvalidPassword, actualErrorMessage);

    }


    @Test
    @DisplayName("Verify that all mandatory fields are marked as such and cannot be left blank. The user is remaining on Register Page")
    public void verifyThatAllMandatoryFieldsAreMarkedAsSuchAndCannotBeLeftBlank() throws InterruptedException {
        System.out.println("This is the test number 4");

        registerPage.fillInTheRegisterForm("","","","","","",false);


        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/register";
        Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");


    }


    @AfterEach
    public void afterEachTestCase(){
        DriverManager.getInstance().tearDown();
        System.out.println("The test case execution has been finished");
    }

    @AfterAll
    public static void afterAllTestCases(){
        System.out.println("The test suite execution is finished");
    }

}
