package com.imalittletester.pages;

import org.openqa.selenium.By;

public class ByPage {
    public By nameInput = By.id("name");
    public By emailInput = By.id("email");
    public By countryDropdownElement = By.id("country");
    public By cityDropdownElement = By.id("city");
    public By submitButton = By.cssSelector("[type='submit']");
    public By successMessage = By.id("msgForFrameWithId");

    public By usernameInput = By.id("user-name");
    public By passwordInput = By.id("password");
    public By shoppingCartQuantity = By.className("shopping_cart_badge");
    public By shoppingCartLink = By.className("shopping_cart_link");
    public By addButtons = By.cssSelector("[id*='add-to-cart']");
    public By removeButtons = By.cssSelector("[id*='remove']");

    public By registrationSubmitButton = By.cssSelector("TBD");
    public By registrationErrors = By.cssSelector("TBD");
    public By registrationFirstName = By.cssSelector("TBD");
}
