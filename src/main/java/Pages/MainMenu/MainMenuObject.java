package Pages.MainMenu;

import Utils.Utils;
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


}
