package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.SerenityConfigReader;

import java.net.URL;

public class DriverFactory {
    private static final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    private static String get(String key) {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
    }

    public static WebDriver createWebDriver() {
        String browser = SerenityConfigReader.get("webdriver.driver", "chrome");
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    public static AndroidDriver createAndroidDriver() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", get("android.deviceName"));
        caps.setCapability("platformVersion", get("android.platformVersion"));
        caps.setCapability("appPackage", get("android.appPackage"));
        caps.setCapability("appActivity", get("android.appActivity"));

        return new AndroidDriver(
                new URL(get("android.appium.server")),
                caps
        );
    }

    public static IOSDriver createIOSDriver() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("deviceName", get("ios.deviceName"));
        caps.setCapability("platformVersion", get("ios.platformVersion"));
        caps.setCapability("bundleId", get("ios.bundleId"));

        return new IOSDriver(
                new URL(get("ios.appium.server")),
                caps
        );
    }

}
