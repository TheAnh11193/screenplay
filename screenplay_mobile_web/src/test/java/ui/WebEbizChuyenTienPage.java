package ui;

import net.serenitybdd.screenplay.targets.Target;
import utils.LocatorHelper;

public class WebEbizChuyenTienPage {
    private static final LocatorHelper helper = new LocatorHelper("Ebiz_ChuyenTien", true);

    public static Target menu_CHUYENTIEN = helper.get("menuChuyenTien");
    public static Target menu_CONTENTCHUYENTIENTHEOLO = helper.get("menuContentChuyenTienTheoLo");
    public static Target btn_PHAMVICHUYEN(String value) {
        return helper.getDynamic("btnPhamViChuyen", value);
    }
    public static Target input_UPLOAD = helper.get("inputUpload");
    public static Target txt_DIENGIAI = helper.get("txtDienGiai");
    public static Target combobox_NGUOICHIUPHI = helper.get("comboboxNguoiChiuPhi");
    public static Target listbox_NGUOICHIUPHI(String value) {
        return helper.getDynamic("listboxNguoiChiuPhi", value);
    }
    public static Target btn_TIEPTUC = helper.get("btnTiepTuc");
    public static Target btn_POPUP_TAIVE = helper.get("popupBtnTaiVe");
    public static Target btn_POPUP_TIEPTUC = helper.get("popupBtnTiepTuc");
    public static Target btn_XACNHAN = helper.get("btnXacNhan");
    public static Target alert_GIAODICHCHODUYET = helper.get("alertGiaoDichChoDuyet");
    public static Target txt_SOGIAODICH = helper.get("txtSoGiaoDich");
    public static Target cardbox_NHATKYGIAODICH = helper.get("cardboxNhatKyGiaoDich");
    public static Target title_DANHSACHGIAODICH = helper.get("titleDanhSachGiaoDich");
    public static Target table_NUMBERMAGIAODICH(String value) {
        return helper.getDynamic("tableNumberMaGiaoDich", value);
    }
    public static Target text_LOADING = helper.get("textLoading");
}
