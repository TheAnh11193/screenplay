package ui;

import net.serenitybdd.screenplay.targets.Target;
import utils.LocatorHelper;

public class MobileLoginPage {

    private static final LocatorHelper helper = new LocatorHelper("login", false);

    public static Target USERNAME = helper.get("username");
    public static Target PASSWORD = helper.get("password");
    public static Target LOGIN_BUTTON = helper.get("loginButton");
    public static Target DASHBOARD = helper.get("dashboard");
}
