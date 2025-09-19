package ui;

import net.serenitybdd.screenplay.targets.Target;
import utils.LocatorHelper;

public class BangCongChiTietPage {
    private static final LocatorHelper helper = new LocatorHelper("bangcongchitiet", true);
    public static Target color_XACNHANCONG = helper.get("colorXacNhanCong");
    public static Target color_CHAMDUNGGIO = helper.get("colorChamDungGio");
    public static Target color_DIMUONVESOM = helper.get("colorDiMuonVeSom");
    public static Target color_NGHIPHEP = helper.get("colorNghiPhep");
    public static Target color_NGAYLE = helper.get("colorNgayLe");
    public static Target color_LAMVIECNGOAIVANPHONG = helper.get("colorLamViecNgoaiVanPhong");
    public static Target color_KHONGDILAM = helper.get("colorKhongDiLam");

    public static Target date_NGAYXACNHANHCONG(String value) {
        return helper.getDynamic("ngayCanXacNhanCong", value);
    }

    public static Target btn_THOIDIEMCANXACNHANCONG = helper.get("btnThoiDiemCanXacNhanCong");
    public static Target value_TRONGSELECTBOX(String value) {
        return helper.getDynamic("valueTrongSelectBox", value);
    }
    public static Target btn_LYDOXACNHANGIOVAO = helper.get("btnLyDoXacNhanGioVao");
    public static Target btn_LYDOXACNHANGIORA = helper.get("btnLyDoXacNhanGioRa");
    public static Target txtBx_CHONGIOVAO = helper.get("txtBxChonGioVao");
    public static Target txtBx_CHONGIORA = helper.get("txtBxChonGioRa");
    public static Target txtBx_GHICHU = helper.get("txtBxGhiChu");
    public static Target rd_ACCIDENT = helper.get("rdAccident");
    public static Target rd_SICK = helper.get("rdSick");
    public static Target rd_OBJECTIVE = helper.get("rdObjective");
    public static Target btn_GUI = helper.get("btnGui");
}
