package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (features = ".//Features/Login.feature",
//        (features = {".//Features/Customers.feature",".//Features/Login.feature"},
//        (features = ".//Features/",
         glue = "StepDefinition",
         dryRun = false,
         monochrome = true,//to avoid unreadable code
//         tags = "@sanity or @regression",
         tags = "@sanity",
         plugin = {"pretty","html:Test-Output/Reports/report.html","json:Test-Output/Reports/report_json.json","junit:Test-Output/Reports/report_xml.xml"}

        )
public class TestRun {

}

//
//        tags="@Sanity" // Will Run Scenario which is tagged with Sanity. Does not matter if
//        it has other tags as well.
//
//        tags="@Sanity or @Regression" // Will Run Scenario tagged with Sanity or Regression.
//
//        tags="@Sanity and @Regression" // Will Run Scenario which is tagged with Sanity as
//        well as Regression.
//
//        tags="@Sanity and not @Regression" // Will Run Scenario which is tagged with only amd only Sanity
//        but not Regression.If other tags are associated with it then it will skip that.