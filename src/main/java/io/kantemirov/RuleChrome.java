package io.kantemirov;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RuleChrome extends ExternalResource{

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() throws Throwable {
        driver = new ChromeDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }

}
