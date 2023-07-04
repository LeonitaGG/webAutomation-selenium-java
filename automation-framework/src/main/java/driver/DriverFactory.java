package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory
{
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>(); //allows us to execute our cucumber tests in parallel

    public static WebDriver getDriver()
    {
        if(webDriver.get() == null) //if the ThreadLocal variable does not contain a webDriver object...
        {
            webDriver.set(createDriver()); //set a new webDriver object via the createDriver method
        }
        return webDriver.get(); //get webDriver object *after* it has been created
    }

    private static WebDriver createDriver()
    {
        WebDriver driver = null;

        switch(getBrowserType())
        {
            case "chrome" ->
            {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/driver/drivers/chromedriver_win32/chromedriver.exe");
                /*What the above does is:
                - points us to the base directory of our project - better than hardcoding the dir
                */
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                //Due to a Chrome update, you can no longer kick off a chromedriver instance unless you include an additional chrome option
                //including the above "addArguments" mitigates this error
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                /*NORMAL PageLoading makes sure that the WebDriver will wait for the entire page to load
                 */
                driver = new ChromeDriver(chromeOptions);
                break;
            }
            case "firefox" ->
            {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/driver/drivers/geckodriver-v0.33.0-win64/geckodriver.exe");

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                driver = new FirefoxDriver(firefoxOptions);
                break;
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    private static String getBrowserType()
    {
        String browserType = null;

        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties/");
            properties.load(file);
            browserType = properties.getProperty("browser").toLowerCase().trim();
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return browserType;
    }

    public static void cleanupDriver()
    {
        webDriver.get().quit(); //close the driver window
        webDriver.remove(); //remove the webDriver object from ThreadLocal variable
    }
}
