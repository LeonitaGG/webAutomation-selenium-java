package stepDefinitions.base;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.sql.Timestamp;

import static driver.DriverFactory.cleanupDriver;
import static driver.DriverFactory.getDriver;

//Centralised class for @Before & @After Hooks, so they don't have to be included in every test class
public class Hooks
{
    @Before
    public void setup()
    {
        getDriver();
    }

    //useful hook to executing functions after a step:e.g. show screenshots in our report for failing steps
    @AfterStep
    public void captureExceptionImage(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String timeMilliseconds = Long.toString(timestamp.getTime());

            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", timeMilliseconds);
        }
    }

    @After
    public void tearDown()
    {
        cleanupDriver();
    }
}
