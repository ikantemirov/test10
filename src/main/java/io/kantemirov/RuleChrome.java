package io.kantemirov;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RuleChrome extends ExternalResource{

    public WebDriver driver;
    public WebDriverWait wait;

    @Override
    protected void before() throws Throwable {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Override
    protected void after() {
        driver.quit();
    }
}
