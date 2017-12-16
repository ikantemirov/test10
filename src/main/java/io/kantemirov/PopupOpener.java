package io.kantemirov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class PopupOpener {
    WebDriverWait wait;

    public PopupOpener(WebDriver driver) {
        wait = new WebDriverWait(driver, 20);
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='left_settings_inner']")));
        driver.findElement(By.xpath("//div[@class='left_settings_inner']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='popup_box_container']")));
    }
}
