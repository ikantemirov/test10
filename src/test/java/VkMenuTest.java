import io.kantemirov.Authorization;
import io.kantemirov.PopupOpener;
import io.kantemirov.RuleChrome;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static ru.yandex.qatools.matchers.webdriver.AttributeMatcher.attr;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static ru.yandex.qatools.matchers.webdriver.ExistsMatcher.exists;

public class VkMenuTest {

    private WebDriver driver;
    private Authorization authorization;
    private WebDriverWait wait;

    @Rule
    public RuleChrome ruleChrome = new RuleChrome();

    @Before
    public void before(){
        driver = ruleChrome.getDriver();
        wait = new WebDriverWait(driver, 20);
        authorization = new Authorization(driver);
        PopupOpener popupOpener = new PopupOpener(driver);
    }

    @Test
    public void shouldOpenPopup() {
        assertThat(driver.findElement(By.xpath("//div[@class='popup_box_container']")), displayed());
    }

    @Ignore
    @Test
    public void shouldClosePopup() {
        wait.until(visibilityOfElementLocated(By.xpath("//div[@class='box_x_button']")));
        driver.findElement(By.xpath("//div[@class='box_x_button']")).click();
        assertThat(driver.findElement(By.xpath("//div[@id='box_layer_bg']")),
                attr("style", "height: 174px; display: none;"));
    }

    @Test
    public void shouldDisplayGroups() {
        wait.until(presenceOfElementLocated(By.xpath("//a[contains(@class, 'olist_item_groups')]")));
        driver.findElement(By.xpath("//a[contains(@class, 'olist_item_groups')]")).click();
        driver.findElement(By.xpath("//button[@class='flat_button']")).click();
        driver.get("https://vk.com/id102764");
        wait.until(presenceOfElementLocated(By.xpath("//li[@id='l_gr']")));
        assertThat(driver.findElement(By.xpath("//li[@id='l_gr']")), attr("style", ""));
    }

    @Test
    public void shouldUndisplayGroups() {
        wait.until(presenceOfElementLocated(By.xpath("//a[contains(@class, 'olist_item_groups')]")));
        driver.findElement(By.xpath("//a[contains(@class, 'olist_item_groups')]")).click();
        driver.findElement(By.xpath("//button[@class='flat_button']")).click();
        driver.get("https://vk.com/id102764");
        wait.until(presenceOfElementLocated(By.xpath("//li[@id='l_gr']")));
        assertThat(driver.findElement(By.xpath("//li[@id='l_gr']")), attr("style", "display: none;"));
    }

    @Test
    public void shouldShowSection1() {
        wait.until(presenceOfElementLocated(By.xpath("//a[@class='summary_tab2']")));
        driver.findElement(By.xpath("(//a[@class='summary_tab2'])[1]")).click();
        assertThat(driver.findElement(By.xpath("//div[@id='settings_menu_0']")), attr("style", ""));
    }

    @Test
    public void shouldShowSection2() {
        wait.until(presenceOfElementLocated(By.xpath("//a[@class='summary_tab2']")));
        driver.findElement(By.xpath("(//a[@class='summary_tab2'])[2]")).click();
        assertThat(driver.findElement(By.xpath("//div[@id='settings_menu_1']")), attr("style", "display: block;"));
    }

    @Test
    public void shouldShowSection3() {
        wait.until(presenceOfElementLocated(By.xpath("//a[@class='summary_tab2']")));
        driver.findElement(By.xpath("(//a[@class='summary_tab2'])[3]")).click();
        assertThat(driver.findElement(By.xpath("//div[@id='settings_menu_2']")), attr("style", "display: block;"));
    }



}
