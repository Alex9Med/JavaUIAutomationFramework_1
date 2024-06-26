package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class GenericSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    @Then("the current url contains {string} keyword")
    public void theCurrentUrlContainsKeyword(String keyWordFromTheUrl) {
        String currentUrl = driver.getCurrentUrl();
        boolean currentUrlContainsKeyword = currentUrl.contains(keyWordFromTheUrl);
        Assertions.assertTrue(currentUrlContainsKeyword, "The keyword: " + keyWordFromTheUrl + " is present in " + currentUrl);
    }

    @Given("{string} endpoint is accessed")
    public void endpointIsAccessed(String endPointValue) {
        driver.get("https://opencart.antropy.co.uk/" + endPointValue);
    }
}
