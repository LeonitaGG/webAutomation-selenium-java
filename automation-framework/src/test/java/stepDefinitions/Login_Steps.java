package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Base_PO;
import pageObjects.Login_PO;

public class Login_Steps extends Base_PO {

    private Login_PO login_po; //use the dependency injection plugin in order to use the Login_PO in this class to access methods

    public Login_Steps(Login_PO login_po)
    {
        this.login_po = login_po;
    }

    @Given("I access the webdriver university login portal page")
    public void i_access_the_webdriver_university_login_portal_page()
    {
        //navigateTo_URL("https://www.webdriveruniversity.com/Login-Portal/index.html");
        login_po.navigateTo_WebDriverUniversity_Login_Page();
    }

    @When("I enter a username {word}")
    public void i_enter_a_username(String username) {
        //sendKeys(By.id("text"), username);
        login_po.setUsername(username);
    }

    @And("I enter a password {word}")
    public void i_enter_a_password(String password)
    {
        //sendKeys(By.id("password"), password);
        login_po.setPassword(password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button()
    {
        //driver.findElement(By.id("login-button")).click();
        login_po.clickOn_Login_Button();
    }

    @Then("I should be presented with a login validation message {}")
    public void i_should_be_presented_with_a_login_validation_message(String validationMessage)
    {
        //AssertValidationMessage(validationMessage);
        login_po.validate_SuccessfulLogin_Message(validationMessage);
    }

}
