package ui;

import utils.LocatorHelper;
import net.serenitybdd.screenplay.targets.Target;

public class WebEbizLoginPage {
    private static final LocatorHelper helper = new LocatorHelper("Ebiz_Login", true);

    public static Target txtbx_USERNAME_EBIZ = helper.get("txtbxUserNameEbiz");
    public static Target txtbx_PASSWORD_EBIZ = helper.get("txtbxPasswordEbiz");
    public static Target btn_DANGNHAP = helper.get("btnDangNhap");
    public static Target title_THONGBAO = helper.get("titleThongBao");
    public static Target btn_CLOSETHONGBAO = helper.get("btnCloseThongBao");
    public static Target btn_DONGYTHONGBAO = helper.get("btnDongYThongBao");
//    public static Target btn_LOAINGHI = helper.get("btnLoaiNghi");
//    public static Target txtbx_NGAYBATDAU= helper.get("txtbxNgayBatDau");
//    public static Target txtbx_NGAYKETTHUC = helper.get("txtbxNgayKetThuc");
//    public static Target txtbx_LYDONGHI = helper.get("txtbxLyDoNghi");
//    public static Target btn_LOAI = helper.get("btnLoai");
//    public static Target btn_DANGKYNGHI = helper.get("btnDangKyNghi");
//    public static Target value_LOAINGHIORNGHI(String value) {
//        return helper.getDynamic("valueLoaiNghiorLoai", value);
//    }
//
//    public static Target txt_USERNAMEODOO = helper.get("txtbxUserNameOdoo");
//    public static Target txt_PASSWORDODOO = helper.get("txtbxPasswordOdoo");
//    public static Target btn_LOGINODOO = helper.get("btnLoginOdoo");
}
