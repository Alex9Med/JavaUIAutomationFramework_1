package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import com.opencart.pageobjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TestLoginFlowWithJunit {
    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    AccountCreatedPage accountCreatedPage;
    LoginPage loginPage;
    String randomEmail = RandomDataManager.generateRandomEmail();
    String randomPassword = RandomDataManager.generatePassword();

    @BeforeAll
    public static void executeThisMethodBeforeAllTheTests() {
        System.out.println("The execution of the test suite has started");
    }

    @BeforeEach
    public void executeTheCodeBeforeEachTest() throws InterruptedException {
        System.out.println("The code is executed before each test case");
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://opencart.antropy.co.uk/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        loginPage = new LoginPage(driver);
        homePage.navigateToRegisterPageFromHeader();
        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(), RandomDataManager.generateLastName(),
                randomEmail, RandomDataManager.generateTelephoneNumber(), randomPassword, randomPassword, true);
        System.out.println("The registration took place with: " + randomEmail + "  " + randomPassword);
        accountCreatedPage.clickContinueButton();
        accountCreatedPage.navigateToLogoutFromHeader();
        accountCreatedPage.clickContinueButton();
    }

    @Test
    @DisplayName("The login of an existent user with valid data redirects to My Account Page")
    public void loginWithValidCredentialsTest() {
        System.out.println("This is the test number 1");

        homePage.navigateToLoginFromHeader();
        loginPage.fillInTheLoginForm(randomEmail, randomPassword);
        System.out.println("The login took place with: " + randomEmail + "  " + randomPassword);
        loginPage.clickLoginBtn();
        System.out.println("The logging was successful!");

        boolean doesTheCurrentUrlContainsCommonHomeRoute = driver.getCurrentUrl().contains("route=account/account");
        Assertions.assertTrue(doesTheCurrentUrlContainsCommonHomeRoute, "The current URL contains: route=account/account");

    }

    @Test
    @DisplayName("The user is remaining on Login Page when trying to log in without filling in the fields")
    public void loginWithoutFillingInTheFields() {
        System.out.println("This is the test number 2");

        homePage.navigateToLoginFromHeader();
        loginPage.fillInTheLoginForm("", "");
        loginPage.clickLoginBtn();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/login";
        Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");

        String expectedErrorMessageForEmptyFields = "Warning: No match for E-Mail Address and/or Password.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
        Assertions.assertEquals(expectedErrorMessageForEmptyFields, actualErrorMessage);
    }

    @Test
    @DisplayName("The existent user is remaining on Login Page when trying to login with no password")
    public void loginWithNoPassword() {
        System.out.println("This is the test number 3");

        homePage.navigateToLoginFromHeader();
        loginPage.fillInTheLoginForm(randomEmail, "");
        loginPage.clickLoginBtn();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/login";
        Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");

        String expectedErrorMessageForEmptyFields = "Warning: No match for E-Mail Address and/or Password.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
        Assertions.assertEquals(expectedErrorMessageForEmptyFields, actualErrorMessage);
    }

    @Test
    @DisplayName("The existent user is remaining on Login Page when trying to login with no email")
    public void loginWithNoEmail() {
        System.out.println("This is the test number 4");

        homePage.navigateToLoginFromHeader();
        loginPage.fillInTheLoginForm("", randomPassword);
        loginPage.clickLoginBtn();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/login";
        Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");

        String expectedErrorMessageForEmptyFields = "Warning: No match for E-Mail Address and/or Password.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
        Assertions.assertEquals(expectedErrorMessageForEmptyFields, actualErrorMessage);
    }

    @Test
    @DisplayName("The existent user is remaining on Login Page when trying to login when includes spaces in fields")
    public void loginWithUsingSpaces() {
        System.out.println("This is the test number 5");

        homePage.navigateToLoginFromHeader();
        loginPage.fillInTheLoginForm(randomEmail + " ", randomPassword + " ");
        loginPage.clickLoginBtn();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/login";
        Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");

        String expectedErrorMessageForEmptyFields = "Warning: No match for E-Mail Address and/or Password.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
        Assertions.assertEquals(expectedErrorMessageForEmptyFields, actualErrorMessage);
    }

    @Test
    @DisplayName("The existent user is remaining on Login Page when trying to login with uppercase characters")
    public void loginWithUsingUppercaseCharacters() {
        System.out.println("This is the test number 6");

        homePage.navigateToLoginFromHeader();
        loginPage.fillInTheLoginForm(randomEmail.toUpperCase(), randomPassword.toUpperCase());
        loginPage.clickLoginBtn();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opencart.antropy.co.uk/index.php?route=account/login";
        Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be equals!");

        String expectedErrorMessageForUsingUppercaseCharacters = "Warning: No match for E-Mail Address and/or Password.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
        Assertions.assertEquals(expectedErrorMessageForUsingUppercaseCharacters, actualErrorMessage);
    }


    @AfterEach
    public void afterEachTestCase() {
        DriverManager.getInstance().tearDown();
        System.out.println("The test case execution has been finished");
    }

    @AfterAll
    public static void afterAllTestCases() {
        System.out.println("The test suite execution is finished");
    }
}
