package com.imalittletester.waits;

import com.imalittletester.pages.StringPage;
import com.imalittletester.utils.BrowserGetter;
import com.imalittletester.utils.Waiter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WaitsStringTest {
    private WebDriver driver;
    private final BrowserGetter browserGetter = new BrowserGetter();
    private final StringPage page = new StringPage();
    private Waiter waiter;

    @BeforeAll
    void beforeAll() {
        driver = browserGetter.getDriver();
        waiter = new Waiter(driver);
    }

    @AfterAll
    void afterAll() {
        driver.quit();
    }

    @Test
    void registration() {
        waiter.get(new File("src/test/resources/htmls/registration.html").getAbsolutePath());
        waiter.sendKeys(page.nameInput, "theName");
        waiter.sendKeys(page.emailInput, "theEmail@emaildomain.com");
        waiter.selectFromDropdownByVisibleText(page.countryDropdownElement, "Spain");
        waiter.selectFromDropdownByVisibleText(page.cityDropdownElement, "Corralejo");
        waiter.click(page.submitButton);
        waiter.switchToFrameAndWaitElementTextEquals2("successFrame", page.successMessage, "Success!");
    }
}
