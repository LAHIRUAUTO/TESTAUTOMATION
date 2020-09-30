package Pages.DcsHome;

import Utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DcsDashBoardObject extends Utils {

    @FindBy(xpath="//*[@id=\"branding-title\"]\n")
    @CacheLookup
    WebElement dashBoardTitleLocator;
}
