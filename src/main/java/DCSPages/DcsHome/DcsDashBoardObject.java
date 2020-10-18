package DCSPages.DcsHome;

import Utilities.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DcsDashBoardObject extends Utils {

    @FindBy(xpath="//*[@id=\"branding-title\"]\n")
    @CacheLookup
    WebElement dashBoardTitleLocator;

    @FindBy(xpath="/html/body/app-root/div/pl-top-menu/nav/div/div[2]/ul/li[1]/a")
    @CacheLookup
    WebElement dashBoardLocator;

    @FindBy(xpath = "//*[@id=\"inputCheckinFlights\"]")
    @CacheLookup
    WebElement flightSearchLocator;

    @FindBy(xpath="/html/body/app-root/div/div[1]/app-home/main/div/div/main/div/section[1]/div/div/div/ngx-slick/div/div/div[1]/h5/a\n")
    @CacheLookup
    WebElement checkInFlight;
}
