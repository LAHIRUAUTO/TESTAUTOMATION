package Pages.HomePage;

import Utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPageObject extends Utils {
    @FindBy(xpath="//*[@id=\"username\"]")
    @CacheLookup
    WebElement usernamelocator;

    @FindBy(xpath="//*[@id=\"password\"]")
    @CacheLookup
    WebElement Passwordlocator;

    @FindBy(xpath="/html/body/app-root/div/div/pl-authentication/div/div/form/button")
    @CacheLookup
    WebElement LoginButtonlocator;


}
