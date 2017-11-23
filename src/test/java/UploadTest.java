import io.kantemirov.Account;
import io.kantemirov.RuleChrome;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class UploadTest {
    private Account account = ConfigFactory.create(Account.class, System.getProperties());
    private int photoCount;
    private WebDriver driver;
    private WebDriverWait wait;

    @Rule
    public RuleChrome ruleChrome = new RuleChrome();

    @Test
    public void shouldCountUploadedPhoto() {
        driver = ruleChrome.getDriver();
        wait = ruleChrome.getWait();
        wait.until(presenceOfElementLocated(By.xpath("//li[@id='l_ph']")));
        driver.findElement(By.xpath("//li[@id='l_ph']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui_crumb_count']")));
        photoCount = Integer.parseInt(driver.findElement(By.xpath(
                "(//span[@class='ui_crumb_count'])[2]")).getText());
        wait.until(presenceOfElementLocated(By.xpath("//input[@id='photos_upload_input']")));
        driver.findElement(By.xpath("//input[@id='photos_upload_input']")).sendKeys(
                "C:\\Users\\Ilia\\view1.jpg");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//a[@class='flat_button secondary ']")));
        driver.findElement(By.xpath("//a[@class='flat_button secondary ']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//a[contains(@class,'img_link')]")));
        driver.findElement(By.xpath("(//a[contains(@class,'img_link')])[2]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='box_layout']")));
        wait.until(elementToBeClickable(By.xpath("//a[@class='ui_crumb']")));
        driver.findElement(By.xpath("//a[@class='ui_crumb']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(
                "//span[@class='ui_crumb_count']"), 1));
        assertThat(driver.findElement(By.xpath("(//span[@class='ui_crumb_count'])[2]")),
                text(Integer.toString(photoCount + 1)));
    }

}
