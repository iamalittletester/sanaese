package sanae;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import utils.BrowserGetter;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirstTest {
    public BrowserGetter browserGetter = new BrowserGetter();
    private WebDriver driver;
    @BeforeAll
    void beforeAll() {
        driver = browserGetter.getDriver();
    }
    @AfterAll
    void afterAll() {
        driver.quit();
    }
    @Test
    void withFindBy() {
        driver.get(new File("src/test/resources/htmls/registration.html").getAbsolutePath());
//        driver.get(new File("src\\test\\resources\\htmls\\registration.html").getAbsolutePath());
//        driver.get("file:///src/test\\resources\\htmls\\registration.html");
    }

}
