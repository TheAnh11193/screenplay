package ui;

import net.serenitybdd.screenplay.targets.Target;
import utils.LocatorHelper;

public class OdooHomeMenuPage {
    private static final LocatorHelper helper = new LocatorHelper("odooHomeMenu", true);

    public static Target txt_USERNAMEODOO = helper.get("txtbxUserNameOdoo");
    public static Target txt_PASSWORDODOO = helper.get("txtbxPasswordOdoo");
    public static Target btn_LOGINODOO = helper.get("btnLoginOdoo");

    public static Target btn_MENUCHAMCONG = helper.get("btnMenuChamCong");
}
