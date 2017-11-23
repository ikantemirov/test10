import io.kantemirov.RuleChrome;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class MessageTest {
    private String msgForDaria = "Hello from WebDriver " + RandomStringUtils.randomAlphanumeric(20);
    private WebDriver driver;
    private WebDriverWait wait;

    @Rule
    public RuleChrome ruleChrome  = new RuleChrome();



    @Test
    public void shouldVerifySentMessage() {
        driver = ruleChrome.getDriver();
        wait = ruleChrome.getWait();

        wait.until(presenceOfElementLocated(By.xpath("//li[@id='l_msg']")));
        driver.findElement(By.xpath("//li[@id='l_msg']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//input[@id='im_dialogs_search']")));
        driver.findElement(By.xpath("//input[@id='im_dialogs_search']")).sendKeys(
                "Дарья Лисовская");
        wait.until(ExpectedConditions.textToBe(By.xpath("(//span[@class='_im_dialog_link'])[1]"),
                "Дарья Лисовская"));
        driver.findElement(By.xpath("(//span[@class='_im_dialog_link'])[1]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//div[@id='im_editable0']")));
        driver.findElement(By.xpath("//div[@id='im_editable0']")).sendKeys(
                msgForDaria + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.xpath("//button[contains(@class,'header-icon_search')]")));
        driver.findElement(By.xpath("//button[contains(@class,'header-icon_search')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//input[@id='im_history_search']")));
        driver.findElement(By.xpath("//input[@id='im_history_search']")).sendKeys(
                msgForDaria +Keys.ENTER);
        wait.until(presenceOfElementLocated(By.xpath("//div[contains(@class, 'text wall_module')]")));
        assertThat(driver.findElement(By.xpath("(//div[contains(@class, 'im-mess')])[last()]")),
                text(msgForDaria));
    }
}
