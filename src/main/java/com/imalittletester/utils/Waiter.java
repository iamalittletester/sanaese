package com.imalittletester.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class Waiter {
    public static final int LONG_TIMEOUT = 120;
    public static final int MEDIUM_TIMEOUT = 60;
    public static final int TIMEOUT = 30;
    public static final int TINY_TIMEOUT = 10;

    private final WebDriver driver;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPageLoadComplete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(driver -> String
                .valueOf(((JavascriptExecutor)
                        driver).executeScript("return document.readyState"))
                .equals("complete"));
    }

    public void waitForJQuery() {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                return (Boolean) ((JavascriptExecutor)
                        driver).executeScript("return jQuery.active == 0");
            } catch (Exception e) {
                return true;
            }
        };
        wait.until(condition);
    }

    public void get(String url) {
        driver.get(url);
        waitForPageLoadComplete();
        waitForJQuery();
    }

    public void click(WebElement elementToClick) {
        click(elementToClick, TIMEOUT);
    }

    public void click(WebElement elementToClick, int duration) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(duration));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                elementToClick.click();
                return true;
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void sendKeys(WebElement inputToTypeInto, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                inputToTypeInto.clear();
                inputToTypeInto.sendKeys(text);
                inputToTypeInto.sendKeys(Keys.TAB);
                return inputToTypeInto.getAttribute("value").equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void selectFromDropdownByVisibleText(WebElement dropdownElement, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                Select select = new Select(dropdownElement);
                select.selectByVisibleText(text);
                return select.getFirstSelectedOption().getText().equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void switchToIframe(String frameId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
    }

    public void waitElementTextEquals(WebElement element, String text) {
        waitElementTextEquals(element, text, TIMEOUT);
    }

    public void waitElementTextEquals(WebElement element, String text, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                return element.getText().equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void switchToFrameAndWaitElementTextEquals2(String frameName, WebElement theElement, String theElementText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frameName);
                return theElement.getText().equals(theElementText);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void switchToFrameAndWaitElementTextEquals(String frameName, WebElement theElement, String theElementText) {
        switchToIframe(frameName);
        waitElementTextEquals(theElement, theElementText, 10);
    }

    /////////////////By////////////////////
    public void click(By elementToClick) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.findElement(elementToClick).click();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
        wait.until(condition);
    }

    public void sendKeys(By inputToTypeInto, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.findElement(inputToTypeInto).clear();
                driver.findElement(inputToTypeInto).sendKeys(text);
                driver.findElement(inputToTypeInto).sendKeys(Keys.TAB);
                return driver.findElement(inputToTypeInto).getAttribute("value").equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void selectFromDropdownByVisibleText(By dropdownElement, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                Select select = new Select(driver.findElement(dropdownElement));
                select.selectByVisibleText(text);
                return select.getFirstSelectedOption().getText().equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void waitElementTextEquals(By element, String text) {
        waitElementTextEquals(element, text, TIMEOUT);
    }

    public void waitElementTextEquals(By element, String text, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                return driver.findElement(element).getText().equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void switchToFrameAndWaitElementTextEquals2(String frameName, By theElement, String theElementText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frameName);
                return driver.findElement(theElement).getText().equals(theElementText);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void switchToFrameAndWaitElementTextEquals(String frameName, By theElement, String theElementText) {
        switchToIframe(frameName);
        waitElementTextEquals(theElement, theElementText, 10);
    }

    /////////////////String////////////////////
    public void click(String elementToClick) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.findElement(cssSelector(elementToClick)).click();
                return true;
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void sendKeys(String inputToTypeInto, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.findElement(cssSelector(inputToTypeInto)).clear();
                driver.findElement(cssSelector(inputToTypeInto)).sendKeys(text);
                driver.findElement(cssSelector(inputToTypeInto)).sendKeys(Keys.TAB);
                return driver.findElement(cssSelector(inputToTypeInto)).getAttribute("value").equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void selectFromDropdownByVisibleText(String dropdownElement, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                Select select = new Select(driver.findElement(cssSelector(dropdownElement)));
                select.selectByVisibleText(text);
                return select.getFirstSelectedOption().getText().equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void waitElementTextEquals(String element, String text) {
        waitElementTextEquals(element, text, TIMEOUT);
    }

    public void waitElementTextEquals(String element, String text, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                return driver.findElement(cssSelector(element)).getText().equals(text);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void switchToFrameAndWaitElementTextEquals2(String frameName, String theElement, String theElementText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frameName);
                return driver.findElement(cssSelector(theElement)).getText().equals(theElementText);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void switchToFrameAndWaitElementTextEquals(String frameName, String theElement, String theElementText) {
        switchToIframe(frameName);
        waitElementTextEquals(theElement, theElementText, 10);
    }

    //////////////List
    public void clickElementInList(By selector, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.findElements(selector).get(index).click();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
        wait.until(condition);
    }

    public void waitListSizeEquals(By selector, int size) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> (driver.findElements(selector).size() == size);
        wait.until(condition);
    }

    public void waitForUrlContains(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        waitForPageLoadComplete();
        waitForJQuery();
    }

    public void waitListTextsEqual(By selector, List<String> expectedTexts) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                List<String> actualTexts = new ArrayList<>();
                for (WebElement element : driver.findElements(selector)) {
                    actualTexts.add(element.getText());
                }
                return actualTexts.equals(expectedTexts);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void clickElementDisplayedAfterRefresh(WebElement selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                driver.navigate().refresh();
                waitForPageLoadComplete();
                waitForJQuery();
                click(selector, 10);
                return true;
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public void setCheckboxState(WebElement element, Boolean expectedState, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                if (!isChecked(element).equals(expectedState))
                    element.click();
                return isChecked(element).equals(expectedState);
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(condition);
    }

    public Boolean isChecked(WebElement element) {
        Boolean isChecked;
        try {
            isChecked = element.getAttribute("checked").equals("true");
        } catch (NullPointerException e) {
            isChecked = false;
        }
        return isChecked;
    }

    public void waitElementNotDisplayed(WebElement element, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        //should also wait for it to have been present previously?
        ExpectedCondition<Boolean> condition = arg -> {
            try {
                return !element.isDisplayed();
            } catch (Exception e) {
                return true;
            }
        };
        wait.until(condition);
    }
}