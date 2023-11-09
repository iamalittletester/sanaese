package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindByPage {
    @FindBy(id = "name") public WebElement nameInput;
    @FindBy(id = "email") public WebElement emailInput;
    @FindBy(id = "country") public WebElement countryDropdown;
    @FindBy(id = "city") public WebElement cityDropdown;
    @FindBy(css = "[type='submit']") public WebElement submitButton;
    @FindBy(id = "msgForFrameWithId") public WebElement successMessage;
}
