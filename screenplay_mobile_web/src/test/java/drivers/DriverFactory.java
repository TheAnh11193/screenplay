package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.SerenityConfigReader;

import java.net.URL;

public class DriverFactory {

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
        caps.setCapability("deviceName", SerenityConfigReader.get("android.deviceName"));
        caps.setCapability("platformVersion", SerenityConfigReader.get("android.platformVersion"));
        caps.setCapability("appPackage", SerenityConfigReader.get("android.appPackage"));
        caps.setCapability("appActivity", SerenityConfigReader.get("android.appActivity"));

        return new AndroidDriver(
                new URL(SerenityConfigReader.get("android.appium.server")),
                caps
        );
    }

    public static IOSDriver createIOSDriver() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("deviceName", SerenityConfigReader.get("ios.deviceName"));
        caps.setCapability("platformVersion", SerenityConfigReader.get("ios.platformVersion"));
        caps.setCapability("bundleId", SerenityConfigReader.get("ios.bundleId"));

        return new IOSDriver(
                new URL(SerenityConfigReader.get("ios.appium.server")),
                caps
        );
    }

}
