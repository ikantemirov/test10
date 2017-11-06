import io.kantemirov.Account;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VkTests {

    private Account account = ConfigFactory.create(Account.class, System.getProperties());
    private WebDriver driver = new ChromeDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);
    private String msgForDaria = "Hello from WebDriver " + RandomStringUtils.randomAlphanumeric(20);

    @Before
    public void before() {
        driver.get("https://vk.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='index_login_form']")));
        driver.findElement(By.xpath("//input[@id='index_email']")).sendKeys(account.username());
        driver.findElement(By.xpath("//input[@id='index_pass']")).sendKeys(account.psw());
        driver.findElement(By.xpath("//button[@id='index_login_button']")).click();
    }

    @After
    public void after() {
        driver.quit();
    }

    @Test
    public void likeTest() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='l_pr']")));
        driver.findElement(By.xpath("//li[@id='l_pr']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='page_wall_posts']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class, 'post_like_count')]")));
        int postLikeCount = Integer.parseInt(driver.findElement(By.xpath("(//span[contains(@class, 'post_like_count')])[1]")).getText());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'post_like')]")));
        driver.findElement(By.xpath("(//a[contains(@class, 'post_like')])[1]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='post_full_like'])[1]/a[contains(@class, 'my_like')]")));
        driver.findElement(By.xpath("(//div[contains(@class,'wall_post_cont')])[1]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")));
        Assert.assertEquals(postLikeCount + 1, Integer.parseInt(driver.findElement(By.xpath("//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")).getText()));
    }

    @Test
    public void unlikeTest(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='l_pr']")));
        driver.findElement(By.xpath("//li[@id='l_pr']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='page_wall_posts']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class, 'post_like_count')]")));
        int postLikeCount = Integer.parseInt(driver.findElement(By.xpath("(//span[contains(@class, 'post_like_count')])[1]")).getText());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'my_like')]")));
        driver.findElement(By.xpath("(//a[contains(@class, 'my_like')])[1]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='post_full_like'])[1]/a[@class='post_like _like_wrap']")));
        driver.findElement(By.xpath("(//div[contains(@class,'wall_post_cont')])[1]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")));
        Assert.assertEquals(postLikeCount - 1, Integer.parseInt(driver.findElement(By.xpath("//div[@id='wk_content']//span[contains(@class, 'post_like_count')]")).getText()));

    }

    @Test
    public void vkWriteMessageToDaria() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='l_msg']")));
        driver.findElement(By.xpath("//li[@id='l_msg']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='im_dialogs_search']")));
        driver.findElement(By.xpath("//input[@id='im_dialogs_search']")).sendKeys("Дарья Лисовская");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class, 'nim-dialog')]")));
        driver.findElement(By.xpath("(//li[contains(@class, 'nim-dialog')])[1]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='im_editable0']")));
        driver.findElement(By.xpath("//div[@id='im_editable0']")).sendKeys(msgForDaria + Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'header-icon_search')]")));
        driver.findElement(By.xpath("//button[contains(@class,'header-icon_search')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='im_history_search']")));
        driver.findElement(By.xpath("//input[@id='im_history_search']")).sendKeys(msgForDaria +Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'text wall_module')]")));
        Assert.assertEquals("False", msgForDaria, driver.findElement(By.xpath("(//div[contains(@class, 'im-mess')])[last()]")).getText());
    }
}
