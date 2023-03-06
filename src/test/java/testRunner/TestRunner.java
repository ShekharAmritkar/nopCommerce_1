package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

//        features = ".//Features//Login.feature",  // single file
//        features = ".//Features//Customers.feature",  // single file
        features = {".//Features/"},  // All feature files
//        features = {".//Features//Login.feature",".//Features//Customers.feature"},// if want multiple feature files
        glue = "stepDefinitions",
//        monochrome = true,  for removing unnecessary things from console window
        dryRun = false,
        plugin = {"pretty","html:test-result4.html"}
//        tags = "@sanity"
)
public class TestRunner {

}
