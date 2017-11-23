import io.kantemirov.RuleChrome;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class LikeTest {
    private int postLikeCounter;
    private WebDriver driver;
    private WebDriverWait wait;

    @Rule
    public RuleChrome ruleChrome = new RuleChrome();

    @Before
    public void before() {
        postLikeCounter = 0;
        driver = ruleChrome.getDriver();
        wait = ruleChrome.getWait();

        wait.until(elementToBeClickable(By.xpath("//li[@id='l_pr']")));
        driver.findElement(By.xpath("//li[@id='l_pr']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//div[@id='page_wall_posts']")));
        wait.until(presenceOfElementLocated(By.xpath("//span[contains(@class, 'post_like_count')]")));
        postLikeCounter = Integer.parseInt(driver.findElement(By.xpath(
                "(//span[contains(@class, 'post_like_count')])[1]")).getText());
        wait.until(elementToBeClickable(By.xpath("//a[contains(@class, 'post_like')]")));
        driver.findElement(By.xpath("(//a[contains(@class, 'post_like')])[1]")).click();
        wait.until(presenceOfElementLocated(By.xpath(
                "(//div[@class='post_full_like'])[1]/a[contains(@class,'post_like')]")));
        driver.findElement(By.xpath("(//div[contains(@class,'wall_post_cont')])[1]")).click();
        wait.until(presenceOfElementLocated(By.xpath(
                "//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")));
    }

    @Test
    public void shouldCheckLikeIsCounted() {
        assertThat(driver.findElement(By.xpath(
                "//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")),
                text(Integer.toString(postLikeCounter + 1)));
    }

    @Test
    public void shouldCheckUnlikeIsCounted(){
        assertThat(driver.findElement(By.xpath(
                "//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")),
                text(Integer.toString(postLikeCounter - 1)));
    }

}
