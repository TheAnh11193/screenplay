package ui;

import utils.LocatorHelper;
import net.serenitybdd.screenplay.targets.Target;

public class WebLoginPage {
    private static final LocatorHelper helper = new LocatorHelper("login", true);

    public static Target link_SSO = helper.get("linkSSO");
    public static Target txt_USERNAMETC = helper.get("txtbxUserNameTC");
    public static Target txt_PASSWORDTC = helper.get("txtbxPasswordTC");
    public static Target btn_LOGINTC = helper.get("btnLoginTC");
    public static Target logo_THANHCONG = helper.get("logoThanhCong");
    public static Target icon_NGHIPHEP = helper.get("iconNghiPhep");
    public static Target btn_LOAINGHI = helper.get("btnLoaiNghi");
    public static Target txtbx_NGAYBATDAU= helper.get("txtbxNgayBatDau");
    public static Target txtbx_NGAYKETTHUC = helper.get("txtbxNgayKetThuc");
    public static Target txtbx_LYDONGHI = helper.get("txtbxLyDoNghi");
    public static Target btn_LOAI = helper.get("btnLoai");
    public static Target btn_DANGKYNGHI = helper.get("btnDangKyNghi");
    public static Target value_LOAINGHIORNGHI(String value) {
        return helper.getDynamic("valueLoaiNghiorLoai", value);
    }


}
