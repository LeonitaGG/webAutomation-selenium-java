package pageObjects;

import driver.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

//class for shared methods
public class Base_PO {
    public Base_PO()
    {
        PageFactory.initElements(getDriver(), this); //initialise all of our Page Objects (*which extends this Base_PO)
    }

    public WebDriver getDriver()
    {
        return DriverFactory.getDriver();
    }

    public String generateRandomNumber(int length) //this method is used to create unique first/last names by appending a random number on the end
    {
        return RandomStringUtils.randomNumeric(length);
    }

    public String generateRandomString(int wordLength, int count)
    {
        StringBuilder word = new StringBuilder();
        for(int i = 0; i < count; i++)
        {
            word.append(RandomStringUtils.randomAlphabetic(wordLength)).append(", ");
            if(i == count - 1)
            {
                word.append(RandomStringUtils.randomAlphabetic((wordLength))).append(".");
            }
        }
        return word.toString();
    }

    public void navigateTo_URL(String url)
    {
        getDriver().get(url);
    }

    public void sendKeys(By by, String textToType)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }

    public void sendKeys(WebElement element, String textToType)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
    }

    public void waitForWebElementAndClick(By by)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitForWebElementAndClick(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForAlert_AndValidateText(String alertText)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alert_Message_Text = getDriver().switchTo().alert().getText();
        Assert.assertEquals(alert_Message_Text, alertText);
    }
}
