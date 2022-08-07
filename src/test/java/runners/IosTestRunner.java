package runners;

import courgette.api.CourgetteOptions;
import courgette.api.CourgettePlugin;
import courgette.api.CourgetteRunLevel;
import courgette.api.CourgetteTestOutput;
import courgette.api.CucumberOptions;
import courgette.api.junit.Courgette;
import org.junit.runner.RunWith;

@RunWith(Courgette.class)
@CourgetteOptions(
        threads = 3,
        runLevel = CourgetteRunLevel.SCENARIO,
        reportTargetDir = "build",
        testOutput = CourgetteTestOutput.CONSOLE,
        environmentInfo = "app=iOS test application; project_info=Courgette-JVM is awesome!",
        plugin = {CourgettePlugin.MOBILE_DEVICE_ALLOCATOR},
        mobileDevice = {
                "iPhone 8",
                "iPhone 12 mini",
                "iPhone 13"
        },
        cucumberOptions = @CucumberOptions(
                features = "src/test/resources/features",
                glue = "steps",
                tags = "@ios",
                publish = true,
                plugin = {
                        "pretty",
                        "json:build/cucumber-report/cucumber.json",
                        "html:build/cucumber-report/cucumber.html"}
        ))
public class IosTestRunner {
}