import io.kantemirov.Authorization;
import io.kantemirov.RuleChrome;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static ru.yandex.qatools.matchers.webdriver.AttributeMatcher.className;
import static ru.yandex.qatools.matchers.webdriver.AttributeMatcher.title;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class MessageTest {
    private String msgForDaria = "Hello from WebDriver " + RandomStringUtils.randomAlphanumeric(20);
    private WebDriver driver;
    private WebDriverWait wait;
    private Authorization authorization;

    @Rule
    public RuleChrome ruleChrome  = new RuleChrome();

    @Test
    public void shouldNotSednMessageWithoutRecipient() {
        driver = ruleChrome.getDriver();
        authorization = new Authorization(driver);
        wait = new WebDriverWait(driver, 20);

        driver.get("https://vk.com/im?sel=23478436");
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='im-page--title-main']")));
        assertThat(driver.findElement(By.xpath("//span[@class='im-page--title-main']")), title("Дарья Лисовская"));
    }

    @Test
    public void shouldSendMessage() {
        driver = ruleChrome.getDriver();
        wait = new WebDriverWait(driver, 20);
        authorization = new Authorization(driver);

        driver.get("https://vk.com/im?sel=23478436");
        wait.until(presenceOfElementLocated(By.xpath("//div[contains(@class, 'im_editable')]")));
        driver.findElement(By.xpath("//div[contains(@class, 'im_editable')]")).sendKeys(msgForDaria + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.xpath("//div[contains(@class, 'text wall_module')]")));
        assertThat(driver.findElement(By.xpath("(//div[contains(@class, 'im-mess')])[last()]")), text(msgForDaria));
    }
}
