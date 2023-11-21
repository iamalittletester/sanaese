package com.imalittletester.waits;

import com.imalittletester.pages.ByPage;
import com.imalittletester.utils.BrowserGetter;
import com.imalittletester.utils.Waiter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WaitsByTest {
    private WebDriver driver;
    private final BrowserGetter browserGetter = new BrowserGetter();
    private final ByPage page = new ByPage();
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
        driver.get(new File("src/test/resources/htmls/registration.html").getAbsolutePath());
        waiter.sendKeys(page.nameInput, "theName");
        waiter.sendKeys(page.emailInput, "theEmail@emaildomain.com");
        waiter.selectFromDropdownByVisibleText(page.countryDropdownElement, "Spain");
        waiter.selectFromDropdownByVisibleText(page.cityDropdownElement, "Corralejo");
        waiter.click(page.submitButton);
        waiter.switchToFrameAndWaitElementTextEquals2("successFrame", page.successMessage, "Success!");
    }


    @Test
    void withLists() {
        waiter.get("https://www.saucedemo.com/");
        waiter.sendKeys(page.usernameInput, "standard_user");
        waiter.sendKeys(page.passwordInput, "secret_sauce");
        waiter.click(page.submitButton);

        waiter.clickElementInList(page.addButtons, 0);
        waiter.clickElementInList(page.addButtons, 0);
        waiter.clickElementInList(page.addButtons, 3);
        waiter.waitElementTextEquals(page.shoppingCartQuantity, "3");

        waiter.click(page.shoppingCartLink);
        waiter.waitForUrlContains("cart");
        waiter.clickElementInList(page.removeButtons, 0);
        waiter.waitListSizeEquals(page.removeButtons, 2);
        waiter.waitElementTextEquals(page.shoppingCartQuantity, "2");
    }

    @Test
    void errorMessages() {
        waiter.get("theUrl");
        waiter.click(page.registrationSubmitButton);
        waiter.waitListTextsEqual(page.registrationErrors, List.of("First error message",
                "Second error message", "Third error message",
                "Fourth error message", "Fifth error message"));
        waiter.sendKeys(page.registrationFirstName, "First name");
        waiter.click(page.registrationSubmitButton);
        waiter.waitListTextsEqual(page.registrationErrors, List.of(
                "Second error message", "Third error message",
                "Fourth error message", "Fifth error message"));

        waiter.waitListSizeEquals(page.registrationErrors, 0);

    }
}
