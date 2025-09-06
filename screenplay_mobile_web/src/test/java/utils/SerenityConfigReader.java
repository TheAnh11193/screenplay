package utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class SerenityConfigReader {
    private static final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    public static String get(String key, String defaultValue) {
        String value = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
        return (value != null) ? value : defaultValue;
    }

    public static String get(String key) {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
    }
}
