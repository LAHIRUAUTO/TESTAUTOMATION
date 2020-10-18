package DCSPages.MainMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainMenu extends MainMenuObject {


    public void clickMainMenuLink (){

        //Explicit wait experiment
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/pl-top-menu/nav/span\n")));

        mainMenuButton.click();



    }


    public void clickDashBoardLink (){

        dashBoardLink.click();
    }

    public void gotoLogOutButtonLink (){

        mouseHover(elementByXpath(logOutButtonLocator));


    }

    public void clickLogOut (){

        elementByXpath(logOutLocator).click();
    }


}
