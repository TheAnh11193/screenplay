package abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.WebDriver;

public class UseCustomDriver implements Ability {
    private final WebDriver driver;
    private final String platform;

    public UseCustomDriver(WebDriver driver, String platform) {
        this.driver = driver;
        this.platform = platform.toLowerCase();
    }

    public static UseCustomDriver with(WebDriver driver, String platform) {
        return new UseCustomDriver(driver, platform);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPlatform() {
        return platform;
    }

    public static UseCustomDriver as(Actor actor) {
        return actor.abilityTo(UseCustomDriver.class);
    }
}
