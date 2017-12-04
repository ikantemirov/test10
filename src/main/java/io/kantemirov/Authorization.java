package io.kantemirov;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class Authorization {
    private WebDriverWait wait;
    private Account account = ConfigFactory.create(Account.class, System.getProperties());

    public Authorization(WebDriver driver) {
        wait = new WebDriverWait(driver, 20);
        driver.get("https://vk.com/");
        wait.until(presenceOfElementLocated(By.xpath("//form[@id='index_login_form']")));
        driver.findElement(By.xpath("//input[@id='index_email']")).sendKeys(account.username());
        driver.findElement(By.xpath("//input[@id='index_pass']")).sendKeys(account.psw());
        driver.findElement(By.xpath("//button[@id='index_login_button']")).click();
    }
}
