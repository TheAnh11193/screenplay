package hooks;

import drivers.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
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
    private static final Map<String, WebDriver> actorDrivers = new HashMap<>();
    private static String currentActor = null;

    private static WebDriver webDriver;
    private static AppiumDriver mobileDriver;

    private static AppiumDriverLocalService appiumService;

    public static Actor loginAs(String role, String url) throws Exception {
        // ✅ Always close current driver when switching
        if (currentActor != null) {
            closeActorDriver(currentActor);
        }

        // ✅ Get platform list
        String platforms = SerenityConfigReader.get("platform", "web").toLowerCase();

        Actor actor;
        if (platforms.contains("web") && url != null && url.startsWith("http")) {
            // ---- Web login
            webDriver = DriverFactory.createWebDriver();
            try {
                webDriver.manage().window().maximize();
            } catch (Exception e) {
                webDriver.manage().window().setSize(new Dimension(1920, 1080));
            }
            actorDrivers.put(role, webDriver);
            actor = Actor.named(role).whoCan(BrowseTheWeb.with(webDriver));
            actor.attemptsTo(Open.url(url));
        }
        else if (platforms.contains("android") || platforms.contains("ios")) {
            // ---- Mobile login
//            startAppiumIfNeeded(platforms);
            mobileDriver = platforms.contains("android")
                    ? DriverFactory.createAndroidDriver()
                    : DriverFactory.createIOSDriver();

            actorDrivers.put(role, mobileDriver);
            actor = Actor.named(role).whoCan(BrowseTheWeb.with(mobileDriver));
        }
        else {
            throw new IllegalArgumentException("❌ Unknown platform: " + platforms);
        }

        currentActor = role;
        return actor;
    }

    private static void closeActorDriver(String role) {
        WebDriver driver = actorDrivers.get(role);
        if (driver != null) {
            driver.quit();
            actorDrivers.remove(role);
        }
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
