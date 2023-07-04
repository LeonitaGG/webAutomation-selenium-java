package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"classpath:features"}, glue = {"stepDefinitions"},
                    tags = "@regression", monochrome = true, dryRun = false,
                    plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"}) //provide reference to where our feature files & stepDefinitions are stored
//plugin section allows us to output the code in a tidy report

public class MainRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }

}
