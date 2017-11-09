import io.kantemirov.Account;
import io.kantemirov.RuleChrome;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class UploadTest {

    private Account account = ConfigFactory.create(Account.class, System.getProperties());
    private int photoCount;

    @Rule
    public RuleChrome ruleChrome = new RuleChrome();

    @Test
    public void shouldCountUploadedPhoto() {
        ruleChrome.driver.get("https://vk.com/");
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//form[@id='index_login_form']")));
        ruleChrome.driver.findElement(By.xpath("//input[@id='index_email']")).sendKeys(account.username());
        ruleChrome.driver.findElement(By.xpath("//input[@id='index_pass']")).sendKeys(account.psw());
        ruleChrome.driver.findElement(By.xpath("//button[@id='index_login_button']")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//li[@id='l_ph']")));
        ruleChrome.driver.findElement(By.xpath("//li[@id='l_ph']")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui_crumb_count']")));
        photoCount = Integer.parseInt(ruleChrome.driver.findElement(By.xpath("(//span[@class='ui_crumb_count'])[2]")).getText());
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//input[@id='photos_upload_input']")));
        ruleChrome.driver.findElement(By.xpath("//input[@id='photos_upload_input']")).sendKeys("C:\\Users\\Ilia\\view1.jpg");
        ruleChrome.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='flat_button secondary ']")));
        ruleChrome.driver.findElement(By.xpath("//a[@class='flat_button secondary ']")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//a[contains(@class,'img_link')]")));
        ruleChrome.driver.findElement(By.xpath("(//a[contains(@class,'img_link')])[2]")).click();
        ruleChrome.wait.until(elementToBeClickable(By.xpath("//a[@class='ui_crumb']")));
        ruleChrome.driver.findElement(By.xpath("//a[@class='ui_crumb']")).click();
        ruleChrome.wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui_crumb_count']")));
        Assert.assertEquals("False", photoCount + 1, Integer.parseInt(ruleChrome.driver.findElement(By.xpath("(//span[@class='ui_crumb_count'])[2]")).getText()));
    }

}
