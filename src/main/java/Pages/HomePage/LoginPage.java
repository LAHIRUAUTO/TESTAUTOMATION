package Pages.HomePage;


import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends LoginPageObject {

    public  void enterUsername (String username){

        try{
            usernamelocator.sendKeys (username);

        } catch (NoSuchElementException e) {
            System.out.println("User name field could not be located");
        }

    }

    public void enterPassword (String password){

        Passwordlocator.sendKeys(password);
    }

    public void clicklogInButton (){

        LoginButtonlocator.click();
    }



}
