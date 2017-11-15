import io.kantemirov.Account;
import io.kantemirov.RuleChrome;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.matchers.webdriver.TextMatcher;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MessageTest {
    private Account account = ConfigFactory.create(Account.class, System.getProperties());
    private String msgForDaria = "Hello from WebDriver " + RandomStringUtils.randomAlphanumeric(20);

    @Rule
    public RuleChrome ruleChrome = new RuleChrome();


    @Test
    public void shouldVerifySentMessage() {
        ruleChrome.driver.get("https://vk.com/");
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//form[@id='index_login_form']")));
        ruleChrome.driver.findElement(By.xpath("//input[@id='index_email']")).sendKeys(account.username());
        ruleChrome.driver.findElement(By.xpath("//input[@id='index_pass']")).sendKeys(account.psw());
        ruleChrome.driver.findElement(By.xpath("//button[@id='index_login_button']")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//li[@id='l_msg']")));
        ruleChrome.driver.findElement(By.xpath("//li[@id='l_msg']")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//input[@id='im_dialogs_search']")));
        ruleChrome.driver.findElement(By.xpath("//input[@id='im_dialogs_search']")).sendKeys("Дарья Лисовская");
        ruleChrome.wait.until(ExpectedConditions.textToBe(By.xpath("(//span[@class='_im_dialog_link'])[1]"), "Дарья Лисовская"));
        ruleChrome.driver.findElement(By.xpath("(//span[@class='_im_dialog_link'])[1]")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//div[@id='im_editable0']")));
        ruleChrome.driver.findElement(By.xpath("//div[@id='im_editable0']")).sendKeys(msgForDaria + Keys.ENTER);
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//button[contains(@class,'header-icon_search')]")));
        ruleChrome.driver.findElement(By.xpath("//button[contains(@class,'header-icon_search')]")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//input[@id='im_history_search']")));
        ruleChrome.driver.findElement(By.xpath("//input[@id='im_history_search']")).sendKeys(msgForDaria +Keys.ENTER);
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//div[contains(@class, 'text wall_module')]")));
        Assert.assertThat(ruleChrome.driver.findElement(By.xpath("(//div[contains(@class, 'im-mess')])[last()]")), TextMatcher.text(msgForDaria));
    }
}
