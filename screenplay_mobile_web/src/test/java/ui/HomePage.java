package ui;

import net.serenitybdd.screenplay.targets.Target;
import utils.LocatorHelper;

public class HomePage {
    private static final LocatorHelper helper = new LocatorHelper("homepage", true);
    public static Target btn_MENU = helper.get("btnMenu");
    public static Target btn_NHANSU = helper.get("btnNhanSu");
    public static Target btn_BANGCONGCHITIET = helper.get("btnBangCongChiTiet");
    public static Target btn_PHEDUYETXACNHANCONG = helper.get("btnPheDuyetXacNhanCong");
}
