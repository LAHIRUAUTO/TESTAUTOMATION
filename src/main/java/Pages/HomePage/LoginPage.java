package Pages.HomePage;


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
