package com.imalittletester.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindByPage {
    @FindBy(id = "name")
    public WebElement nameInput;
    @FindBy(id = "email")
    public WebElement emailInput;
    @FindBy(id = "country")
    public WebElement countryDropdownElement;
    @FindBy(id = "city")
    public WebElement cityDropdownElement;
    @FindBy(css = "[type='submit']")
    public WebElement submitButton;
    @FindBy(id = "msgForFrameWithId")
    public WebElement successMessage;

    @FindBy(css = "[type=\"checkbox\"]")
    public WebElement checkbox;
    @FindBy(css = "#example .article .container .simple .dropdown")
    public WebElement nonClassicDropdown;
    @FindBy(css = "#example .article .container .simple .dropdown .item")
    public WebElement nonClassicDropdownFirstItem;
}
