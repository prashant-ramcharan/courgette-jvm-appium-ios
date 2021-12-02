package steps;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Objects;

import static java.lang.String.format;

public class TestSteps {
    private IOSDriver<WebElement> driver;
    private AppiumDriverLocalService service;

    @Before
    public void before() {
        service = createAppiumDriverLocalService();
    }

    @After
    public void after() {
        driver.terminateApp("io.appium.TestApp");
    }

    @Given("I have a {} device")
    public void iHaveAIDevice(String deviceName) {
        driver = createIOSDriver(service.getUrl(), deviceName, getWdaDevicePort(deviceName));
    }

    @When("I show the alert")
    public void iShowTheAlert() {
        driver.findElementByName("show alert").click();
    }

    @Then("I verify the alert shows {}")
    public void iVerifyTheAlertShows(String alert) {
        String alertText = driver.findElementByXPath(format("//XCUIElementTypeStaticText[@value='%s']", alert)).getText();
        Assert.assertEquals(alert, alertText);
    }

    @And("I accept the alert")
    public void iAcceptTheAlert() {
        driver.findElementByName(("OK")).click();
    }

    private IOSDriver<WebElement> createIOSDriver(final URL serverURL, final String deviceName, final int wdaLocalPort) {
        File app = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("apps/TestApp.app.zip")).getFile());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("wdaLocalPort", wdaLocalPort);
        return new IOSDriver<>(serverURL, capabilities);
    }

    private AppiumDriverLocalService createAppiumDriverLocalService() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        service = serviceBuilder.build();
        service.start();
        return service;
    }

    private int getWdaDevicePort(final String deviceName) {
        switch (deviceName) {
            case "iPhone 8":
                return 8100;
            case "iPhone 12":
                return 8101;
        }
        return 8102;
    }
}