package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver ldriver;

    public LoginPage(WebDriver rdriver) {
        ldriver= rdriver;
        PageFactory.initElements(rdriver,this);

    }

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id="Password")
    WebElement password;

    @FindBy(xpath="//button[@class='button-1 login-button']")
    WebElement loginButton;

    @FindBy(xpath="//a[text()='Logout']")
    WebElement logoutButton;
    public void enterEmail(String uname)

    {
        email.clear();
        email.sendKeys(uname);
    }

    public void enterPassword(String pass) {
        password.clear();
        password.sendKeys(pass);
    }

    public void clickOnLogin ()
    {
     loginButton.click();
    }
    public void clickOnLogout ()
    {
     logoutButton.click();
    }
}
