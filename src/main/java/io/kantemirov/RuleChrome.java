package io.kantemirov;

import org.aeonbits.owner.ConfigFactory;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class RuleChrome extends ExternalResource{

    private WebDriver driver;
    private WebDriverWait wait;
    private Account account = ConfigFactory.create(Account.class, System.getProperties());

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }


    @Override
    protected void before() throws Throwable {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.get("https://vk.com/");
        wait.until(presenceOfElementLocated(By.xpath("//form[@id='index_login_form']")));
        driver.findElement(By.xpath("//input[@id='index_email']")).sendKeys(account.username());
        driver.findElement(By.xpath("//input[@id='index_pass']")).sendKeys(account.psw());
        driver.findElement(By.xpath("//button[@id='index_login_button']")).click();
    }

    @Override
    protected void after() {
        driver.quit();
    }

}
