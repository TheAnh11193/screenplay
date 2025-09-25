package ui;

import net.serenitybdd.screenplay.targets.Target;
import utils.LocatorHelper;

public class OdooChamCongPage {
    private static final LocatorHelper helper = new LocatorHelper("odooChamCong", true);

    public static Target table_BANGCHAMCONG = helper.get("tableBangChamCong");
    public static Target input_SEARCHBOX = helper.get("inputSeachBox");
    public static Target dropdown_VALUESEARCHMENU = helper.get("dropdownValueSearchMenu");
    public static Target result_SEARCH = helper.get("resultSearch");
    public static Target list_DAYOFMONTHS = helper.get("listDayOfMonth");
    public static Target list_COLORDAYOFMONTHS = helper.get("listColorDayOfMonth");
}
