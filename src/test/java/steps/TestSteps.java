package steps;

import courgette.api.CourgetteMobileDeviceAllocator;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Objects;

import static java.lang.String.format;

public class TestSteps {
    private IOSDriver driver;
    private AppiumDriverLocalService service;
    private Scenario currentScenario;

    @Before
    public void before(Scenario scenario) {
        currentScenario = scenario;
        service = createAppiumDriverLocalService();
        driver = createIOSDriver(service.getUrl());
    }

    @After
    public void after() {
        driver.terminateApp("io.appium.TestApp");
        driver.quit();
    }

    @Given("I launch the app")
    public void iLaunchApp() {
        driver.activateApp("io.appium.TestApp");
    }

    @When("I show the alert")
    public void iShowTheAlert() {
        driver.findElement(By.name("show alert")).click();
    }

    @Then("I verify the alert shows {}")
    public void iVerifyTheAlertShows(String alert) {
        String alertText = driver.findElement(By.xpath(format("//XCUIElementTypeStaticText[@value='%s']", alert))).getText();
        Assert.assertEquals(alert, alertText);
    }

    @And("I accept the alert")
    public void iAcceptTheAlert() {
        driver.findElement((By.name("OK"))).click();
    }

    private IOSDriver createIOSDriver(final URL serverURL) {
        File app = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("apps/TestApp.app.zip")).getFile());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("deviceName", CourgetteMobileDeviceAllocator.DEVICE_NAME);
        capabilities.setCapability("udid", CourgetteMobileDeviceAllocator.UDID);
        capabilities.setCapability("wdaLocalPort", CourgetteMobileDeviceAllocator.PARALLEL_PORT);
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "17.0");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("noReset", true);

        currentScenario.log(format("iOS Device: %s", capabilities.getCapability("deviceName")));

        return new IOSDriver(serverURL, capabilities);
    }

    private AppiumDriverLocalService createAppiumDriverLocalService() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        service = serviceBuilder.build();
        service.start();
        return service;
    }
}