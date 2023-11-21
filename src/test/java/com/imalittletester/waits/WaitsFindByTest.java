package com.imalittletester.waits;

import com.imalittletester.pages.FindByPage;
import com.imalittletester.utils.BrowserGetter;
import com.imalittletester.utils.Waiter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WaitsFindByTest {
    private WebDriver driver;
    private final BrowserGetter browserGetter = new BrowserGetter();
    private FindByPage page;
    private Waiter waiter;

    @BeforeAll
    void beforeAll() {
        driver = browserGetter.getDriver();
        page = PageFactory.initElements(driver, FindByPage.class);
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

    @Test
    void checkbox() {
        waiter.get(new File("src/test/resources/htmls/interactions.html").getAbsolutePath());
        waiter.setCheckboxState(page.checkbox, false, 10);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void nonClassicDropdown() throws InterruptedException {
        waiter.get("https://semantic-ui.com/modules/dropdown.html#/definition");
        waiter.click(page.nonClassicDropdown);
        waiter.click(page.nonClassicDropdownFirstItem);
    }
}
