package sanae;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.FindByPage;
import utils.BrowserGetter;
import utils.Waiter;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirstTest {
    public BrowserGetter browserGetter = new BrowserGetter();
    private WebDriver driver;
    private FindByPage page;
    private Waiter waiter;

    @BeforeAll
    void beforeAll() {
        driver = browserGetter.getDriver();
        waiter = new Waiter(driver);
        page = PageFactory.initElements(driver, FindByPage.class);
    }

    @AfterAll
    void afterAll() {
        driver.quit();
    }
    @Test
    void withFindBy() throws InterruptedException {
        waiter.get(new File("src/test/resources/htmls/registration.html").getAbsolutePath());
//        driver.get(new File("src\\test\\resources\\htmls\\registration.html").getAbsolutePath());
//        driver.get("file:///src/test\\resources\\htmls\\registration.html");
        waiter.sendKeys(page.nameInput, "jndskjhsdfkj");
        waiter.sendKeys(page.emailInput, "jndskjhsdfkj@dfsfdsdf.com");
        waiter.selectFromDropdownByVisibleText(page.countryDropdown, "Spain");
        waiter.selectFromDropdownByVisibleText(page.cityDropdown, "Valencia");
        waiter.click(page.submitButton);
        driver.switchTo().frame("successFrame");
        assertEquals(page.successMessage.getText(), "Success!");
    }

}
