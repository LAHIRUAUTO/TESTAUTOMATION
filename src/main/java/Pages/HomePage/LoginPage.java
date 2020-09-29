package Pages.HomePage;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends LoginPageObject {

    public  void enterUsername (String username){

        usernamelocator.sendKeys (username);

    }

    public void enterPassword (String password){

        Passwordlocator.sendKeys(password);
    }

    public void clicklogInButton (){

        LoginButtonlocator.click();
    }



}
