package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    public WebDriver driver;
    private int LONG_TIMEOUT = 60;
    private int TIMEOUT = 30;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
        wait.until(driver -> String.valueOf(((JavascriptExecutor) driver).
                executeScript("return document.readyState")).equals("complete"));
    }

    public void waitJQuery() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                return (Boolean) ((JavascriptExecutor) driver).
                        executeScript("return jQuery.active == 0");
            } catch (Exception e) {
                return true;
            }
        };
        wait.until(condition);
    }

    public void get(String url) {
        driver.get(url);
        waitPageLoad();
        waitJQuery();
    }

    public void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                element.click();
                return true;
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void sendKeys(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                element.clear();
                element.sendKeys(text);
                element.sendKeys(Keys.TAB);
                return element.getAttribute("value").equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void selectFromDropdownByVisibleText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                Select select = new Select(element);
                select.selectByVisibleText(text);
                return select.getFirstSelectedOption().getText().equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }
}
