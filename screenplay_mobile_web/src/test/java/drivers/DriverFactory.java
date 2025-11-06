package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.SerenityConfigReader;

import java.net.URL;

public class DriverFactory {

    public static WebDriver createWebDriver() {
        String browser = SerenityConfigReader.get("webdriver.driver", "chrome");
        String localDriverPath;
        try {
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
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--allow-insecure-localhost");
                    options.addArguments("--disable-web-security");
                    return new ChromeDriver(options);
            }
        } catch (Exception e) {
            System.err.println("Không tải được driver qua WebDriverManager, dùng driver local. Lỗi: " + e.getMessage());
            switch (browser) {
                case "firefox":
                    localDriverPath = "src/test/resources/drivers/geckodriver"; // Mac không cần .exe
                    System.setProperty("webdriver.gecko.driver", localDriverPath);
                    return new FirefoxDriver();
                case "edge":
                    localDriverPath = "src/test/resources/drivers/msedgedriver";
                    System.setProperty("webdriver.edge.driver", localDriverPath);
                    return new EdgeDriver();
                case "chrome":
                default:
                    localDriverPath = "src/test/resources/drivers/chromedriver";
                    System.setProperty("webdriver.chrome.driver", localDriverPath);
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--allow-insecure-localhost");
                    options.addArguments("--disable-web-security");
                    return new ChromeDriver(options);
            }
        }
    }

    public static AndroidDriver createAndroidDriver() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", SerenityConfigReader.get("android.platformName"));
        caps.setCapability("deviceName", SerenityConfigReader.get("android.deviceName"));
        caps.setCapability("platformVersion", SerenityConfigReader.get("android.platformVersion"));
        caps.setCapability("automationName", SerenityConfigReader.get("android.automationName"));
        caps.setCapability("appPackage", SerenityConfigReader.get("android.appPackage"));
        caps.setCapability("appActivity", SerenityConfigReader.get("android.appActivity"));
        caps.setCapability("appWaitActivity", SerenityConfigReader.get("android.appWaitActivity"));
        caps.setCapability("noReset", Boolean.parseBoolean(SerenityConfigReader.get("android.noReset")));
        caps.setCapability("newCommandTimeout", 300);
        caps.setCapability("autoGrantPermissions", true);
        String serverUrl = SerenityConfigReader.get("android.appium.server");
        if (!serverUrl.endsWith("/wd/hub")) {
            if (!serverUrl.endsWith("/")) serverUrl += "/";
            serverUrl += "wd/hub";
            System.out.println("[DEBUG] Android Appium Server URL: " + serverUrl);
        }

        return new AndroidDriver(new URL(serverUrl), caps);
    }

    public static IOSDriver createIOSDriver() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("deviceName", SerenityConfigReader.get("ios.deviceName"));
        caps.setCapability("platformVersion", SerenityConfigReader.get("ios.platformVersion"));
        caps.setCapability("bundleId", SerenityConfigReader.get("ios.bundleId"));

        // Appium 1.x cần /wd/hub
        return new IOSDriver(
                new URL(SerenityConfigReader.get("ios.appium.server") + "/wd/hub"),
                caps
        );
    }
}
