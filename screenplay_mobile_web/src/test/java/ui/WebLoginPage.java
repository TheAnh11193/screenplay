package ui;

import utils.LocatorHelper;
import net.serenitybdd.screenplay.targets.Target;

public class WebLoginPage {
    private static final LocatorHelper helper = new LocatorHelper("login", true);

    public static Target USERNAME = helper.get("username");
    public static Target PASSWORD = helper.get("password");
    public static Target LOGIN_BUTTON = helper.get("loginButton");
    public static Target DASHBOARD = helper.get("dashboard");
}
