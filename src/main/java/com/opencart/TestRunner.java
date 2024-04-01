package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();

        String currentWindowName = driver.getWindowHandle();

        // New Window Code
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://translate.google.com/");
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        driver.close();

        driver.switchTo().window(currentWindowName);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.youtube.com/");
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        driver.close();

        driver.switchTo().window(currentWindowName);
        driver.get("https://www.facebook.com/");
        Thread.sleep(2000);
        System.out.println(driver.getTitle());

        driver.switchTo().newWindow(WindowType.TAB);
        driver.switchTo().window(currentWindowName);
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);
        System.out.println(driver.getTitle());

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://web.telegram.org/k/");
        Thread.sleep(2000);
        System.out.println(driver.getTitle());

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://vk.com/");
        Thread.sleep(2000);
        System.out.println(driver.getTitle());

        driver.quit();

        System.out.println("The execution is over !");

    }


}