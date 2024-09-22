package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty",
        "html:projectReports/default-cucumber-reports.html",
        "json:target/json-reports/cucumber.json",
        "junit:target/xml-report/cucumber.xml",
        "rerun:failedScenarios/failedRerun.txt"
},

        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        tags = "@ScreenNegative",
        dryRun = false,
        monochrome = false


)
public class Runner {
}
