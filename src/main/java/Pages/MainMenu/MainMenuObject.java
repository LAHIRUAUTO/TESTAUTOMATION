package Pages.MainMenu;

import Utilities.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MainMenuObject extends Utils {


    @FindBy(xpath="/html/body/app-root/div/pl-top-menu/nav/span\n")
    @CacheLookup
    WebElement mainMenuButton;

    @FindBy(xpath="/html/body/app-root/div/pl-top-menu/nav/div/div[2]/ul/li[1]/a")
    @CacheLookup
    WebElement dashBoardLink;

    @FindBy(xpath="/html/body/app-root/div/pl-top-menu/nav/div/div[2]/ul/li[1]/a")
    @CacheLookup
    WebElement logOutButton;

    public String logOutButtonLocator = "/html/body/app-root/div/pl-top-menu/nav/div/div[2]/ul/li[7]/a\n";
    public String logOutLocator = "/html/body/app-root/div/pl-top-menu/nav/div/div[2]/ul/li[7]/ul/li/a/span[2]\n";



}
