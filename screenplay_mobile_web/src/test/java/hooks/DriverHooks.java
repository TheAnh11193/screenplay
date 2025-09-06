package hooks;

import abilities.UseCustomDriver;
import drivers.DriverFactory;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Dimension;
import utils.SerenityConfigReader;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;


public class DriverHooks {
    public static Actor webUser;
    public static Actor androidUser;
    public static Actor iosUser;

    private WebDriver webDriver;
    private AndroidDriver androidDriver;
    private IOSDriver iosDriver;

    @Before
    public void setUp() throws Exception {
        String platforms = SerenityConfigReader.get("platform", "web").toLowerCase();
        System.out.println(">>> Running Web + " + platforms.toUpperCase());

        for (String p : platforms.split(",")) {
            switch (p.trim()) {
                case "web": {
                    webDriver = DriverFactory.createWebDriver();
                    // ✅ Force maximize or set default resolution
                    try {
                        webDriver.manage().window().maximize();
                    } catch (Exception e) {
                        webDriver.manage().window().setSize(new Dimension(1920, 1080));
                    }
                    webUser = Actor.named("WebUser").whoCan(BrowseTheWeb.with(webDriver));
                    Serenity.setSessionVariable("webUser").to(webUser);
                    Serenity.setSessionVariable("webDriver").to(webDriver);
                    break;
                }
                case "android": {
                    androidDriver = DriverFactory.createAndroidDriver();
                    androidUser = Actor.named("AndroidUser").whoCan(BrowseTheWeb.with(androidDriver));
                    Serenity.setSessionVariable("androidUser").to(androidUser);
                    Serenity.setSessionVariable("androidDriver").to(androidDriver);
                    break;
                }
                case "ios": {
                    iosDriver = DriverFactory.createIOSDriver();
                    iosUser = Actor.named("IOSUser").whoCan(BrowseTheWeb.with(iosDriver));
                    Serenity.setSessionVariable("iosUser").to(iosUser);
                    Serenity.setSessionVariable("iosDriver").to(iosDriver);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown platform: " + p);
            }
        }
    }

    @After
    public void tearDown() {
        if (webDriver != null) webDriver.quit();
        if (androidDriver != null) androidDriver.quit();
        if (iosDriver != null) iosDriver.quit();
    }
}
