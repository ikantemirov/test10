import io.kantemirov.Account;
import io.kantemirov.RuleChrome;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.matchers.webdriver.TextMatcher;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LikeTest {
    private Account account = ConfigFactory.create(Account.class, System.getProperties());
    private int postLikeCounter;

    @Rule
    public RuleChrome ruleChrome = new RuleChrome();

    @Before
    public void before() {
        postLikeCounter = 0;

        ruleChrome.driver.get("https://vk.com/");
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//form[@id='index_login_form']")));
        ruleChrome.driver.findElement(By.xpath("//input[@id='index_email']")).sendKeys(account.username());
        ruleChrome.driver.findElement(By.xpath("//input[@id='index_pass']")).sendKeys(account.psw());
        ruleChrome.driver.findElement(By.xpath("//button[@id='index_login_button']")).click();
        ruleChrome.wait.until(elementToBeClickable(By.xpath("//li[@id='l_pr']")));
        ruleChrome.driver.findElement(By.xpath("//li[@id='l_pr']")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//div[@id='page_wall_posts']")));
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//span[contains(@class, 'post_like_count')]")));
        postLikeCounter = Integer.parseInt(ruleChrome.driver.findElement(By.xpath("(//span[contains(@class, 'post_like_count')])[1]")).getText());
        ruleChrome.wait.until(elementToBeClickable(By.xpath("//a[contains(@class, 'post_like')]")));
        ruleChrome.driver.findElement(By.xpath("(//a[contains(@class, 'post_like')])[1]")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("(//div[@class='post_full_like'])[1]/a[contains(@class,'post_like')]")));
        ruleChrome.driver.findElement(By.xpath("(//div[contains(@class,'wall_post_cont')])[1]")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")));
    }

    @Test
    public void shouldCheckLikeIsCounted() {
        Assert.assertThat(ruleChrome.driver.findElement(By.xpath("//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")), TextMatcher.text(Integer.toString(postLikeCounter + 1)));
    }

    @Test
    public void shouldCheckUnlikeIsCounted(){
        Assert.assertThat(ruleChrome.driver.findElement(By.xpath("//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")), TextMatcher.text(Integer.toString(postLikeCounter - 1)));
    }

}
