package utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LocatorHelper {
    private final Map<String, Target> locators = new HashMap<>();
    private final Properties props = new Properties();
    private static final String BASE_PATH = EnvironmentSpecificConfiguration
            .from(SystemEnvironmentVariables.createEnvironmentVariables())
            .getProperty("locators.path");

    public LocatorHelper(String screenName, boolean isWeb) {
        String platform;

        if (isWeb) {
            platform = "web"; // always web if browser tests
        } else {
            platform = SerenityConfigReader.get("platform", "android").toLowerCase(); // default android if not set
        }

        String path = BASE_PATH
                + screenName + "-" + platform + ".properties";

        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(path), StandardCharsets.UTF_8)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Could not load locators for: " + screenName
                    + " on platform: " + platform, e);
        }

        // Build map of Target objects
        for (String key : props.stringPropertyNames()) {
            locators.put(key, locator(key, props.getProperty(key)));
        }
    }

    private Target locator(String key, String value) {
        if (value.startsWith("id:")) {
            return Target.the(key).locatedBy("//*[@id='" + value.replace("id:", "") + "']");
        } else if (value.startsWith("css:")) {
            return Target.the(key).locatedBy(value.replace("css:", ""));
        } else if (value.startsWith("xpath:") || value.startsWith("//")) {
            return Target.the(key).locatedBy(value.replace("xpath:", ""));
        }
        throw new RuntimeException("Unsupported locator format for key: " + key + " → " + value);
    }

    public Target get(String key) {
        if (!locators.containsKey(key)) {
            throw new RuntimeException("Locator not found for key: " + key);
        }
        return locators.get(key);
    }

    // 🔥 New: Dynamic locator with placeholders
    public Target getDynamic(String key, Object... params) {
        if (!props.containsKey(key)) {
            throw new RuntimeException("Locator not found for key: " + key);
        }
        String raw = props.getProperty(key);
        String formatted = String.format(raw, params);
        return locator(key, formatted);
    }
}
