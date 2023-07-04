package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_PO extends Base_PO {
    private @FindBy(id = "text")
    WebElement username_TextField;

    private @FindBy(id = "password")
    WebElement password_TextField;

    private @FindBy(id = "login-button")
    WebElement login_Button;

    public Login_PO()
    {
        super(); //super points to the constructor of our Base_PO class
    }

    public void navigateTo_WebDriverUniversity_Login_Page()
    {
        navigateTo_URL("https://www.webdriveruniversity.com/Login-Portal/index.html");
    }

    public void setUsername(String username)
    {
        sendKeys(username_TextField, username); //send keys method from base_po
    }

    public void setPassword(String password)
    {
        sendKeys(password_TextField, password);
    }
    public void clickOn_Login_Button()
    {
        waitForWebElementAndClick(login_Button); //waitForWebElement method from base_po
    }

    public void validate_SuccessfulLogin_Message(String validationMessage)
    {
        waitForAlert_AndValidateText(validationMessage);
    }
}
