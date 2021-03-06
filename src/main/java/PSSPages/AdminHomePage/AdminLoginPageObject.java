package PSSPages.AdminHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPageObject {

    @FindBy(xpath="//*[@id=\"j_username\"]\n")
    @CacheLookup
    WebElement usernamelocator;

    @FindBy(xpath="//*[@id=\"j_password\"]\n")
    @CacheLookup
    WebElement Passwordlocator;

    @FindBy(xpath="//*[@id=\"btnLogin\"]\n")
    @CacheLookup
    WebElement LoginButtonlocator;

}
