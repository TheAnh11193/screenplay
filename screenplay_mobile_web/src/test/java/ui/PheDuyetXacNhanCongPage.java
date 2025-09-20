package ui;

import net.serenitybdd.screenplay.targets.Target;
import utils.LocatorHelper;

public class PheDuyetXacNhanCongPage {
    private static final LocatorHelper helper = new LocatorHelper("pheduyetxacnhancong", true);
    public static Target input_DATETUNGAY = helper.get("inputDateTuNgay");
    public static Target input_DATEDENNGAY = helper.get("inputDateDenNgay");
    public static Target btn_TIMKIEM = helper.get("btnTimKiem");
    public static Target table_PHEDUYETXACNHANCONG = helper.get("tablePheDuyetXacNhanCong");
    public static Target value_RESULTTIMKIEM(String value) {
        return helper.getDynamic("valueResultTimKiem", value);
    }
    public static Target select_FILTERDATE(String value) {
        return helper.getDynamic("filterDate", value);
    }


}
