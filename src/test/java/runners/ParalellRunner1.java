package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)//-->Test çalıştırıcı Notasyon(Cucumber ile JUnit i entegre olmasını sağlar)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber1.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:failedScenarios/failedRerun.txt"},
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        tags = "@Login",//-->  and kulansaydık her iki tag'a sahip olan Scenario yu çalıştırır.


        //--> Eğer Feature'ın tag'ini @all yaparsak o featurenin tüm testlerini çalıştırır ise
        dryRun = false, //-->true seçersek scenarioları kontrol eder browser'ı çalıştırmaz
        monochrome = false
)

public class ParalellRunner1 {
}
