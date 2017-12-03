package cucumberTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\feature"
		,glue={"stepDefinition"}
		,monochrome = true
		
		)

public class TestRunner {

}
