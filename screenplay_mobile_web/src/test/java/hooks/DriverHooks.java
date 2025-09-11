package hooks;

import drivers.DriverFactory;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class DriverHooks {
    public static Actor webUser;
    public static Actor androidUser;
    public static Actor iosUser;

    private static WebDriver webDriver;
    private static AndroidDriver androidDriver;
    private static IOSDriver iosDriver;

    private static AppiumDriverLocalService appiumService;

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
                    break;
                }
                case "android": {
                    startAppiumIfNeeded();  // 👈 auto-start Appium
                    androidDriver = DriverFactory.createAndroidDriver();
                    androidUser = Actor.named("AndroidUser").whoCan(BrowseTheWeb.with(androidDriver));
                    Serenity.setSessionVariable("androidUser").to(androidUser);
                    Serenity.setSessionVariable("androidDriver").to(androidDriver);
                    break;
                }
                case "ios": {
                    startAppiumIfNeeded();  // 👈 auto-start Appium
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

    private static void startAppiumIfNeeded() {
        String userHome = System.getProperty("user.home");
        String osName = System.getProperty("os.name").toLowerCase();

        String appiumMainPath;
        if (osName.contains("win")) {
            appiumMainPath = userHome + "/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
        } else {
            appiumMainPath = userHome + "/.npm-global/lib/node_modules/appium/build/lib/main.js";
        }
        if (appiumService == null || !appiumService.isRunning()) {
            appiumService = new AppiumServiceBuilder()
                    .withAppiumJS(new File(appiumMainPath))
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withArgument(() -> "--base-path", "/wd/hub") // optional
                    .build();
            appiumService.start();
            System.out.println("✅ Appium started at: " + appiumService.getUrl());
        }
    }
//    public static Actor getActor(String name) {
//        return Actor.named(name).whoCan(BrowseTheWeb.with(webDriver));
//    }
}
