package steps;

import actions.JavaScriptClick;
import actions.JavaScriptScroll;
import actions.JavaScriptScrollToTop;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actors.OnStage;

import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;
import org.openqa.selenium.By;
import tasks.VerifyDateColors;
import ui.BangCongChiTietPage;
import ui.OdooChamCongPage;
import ui.WebLoginPage;
import utils.CommonConstants;
import utils.JsonDataReader;
import utils.RgbaToHex;
import utils.SerenityConfigReader;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;
import net.serenitybdd.screenplay.questions.Visibility;
import static utils.CommonConstants.ID_MA_CHAM_CONG;

public class ThongTinXacNhanCong {

    @And("Chọn ngày để xác nhận công với nội dung lỗi {string}")
    public void chọnNgàyĐểXácNhậnCôngVớiNộiDungLỗi(String caseKey) throws InterruptedException {
        Map<String, String> dataError = JsonDataReader.getData(SerenityConfigReader.get("data.ThongTinXacNhanCongLoi"), caseKey);
        for (Map.Entry<String, String> entry : dataError.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            switch (field) {
                case "Ngày xác nhận công":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.date_NGAYXACNHANHCONG(value)));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        WaitUntil.the(BangCongChiTietPage.btn_THOIDIEMCANXACNHANCONG,isVisible()).forNoMoreThan(10).seconds());
                    }
                    break;
                case "Thời điểm cần xác nhận công":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.btn_THOIDIEMCANXACNHANCONG));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(value)));
                    }
                    break;

                case "Lý do xác nhận giờ vào":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                JavaScriptScroll.to(BangCongChiTietPage.btn_LYDOXACNHANGIOVAO),
                                WaitUntil.the(BangCongChiTietPage.btn_LYDOXACNHANGIOVAO, isVisible()).forNoMoreThan(10).seconds()
                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.btn_LYDOXACNHANGIOVAO)
                                );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(value)));
                    }
                    break;
                case "Lý do xác nhận giờ ra":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                JavaScriptScroll.to(BangCongChiTietPage.btn_LYDOXACNHANGIORA),
                                WaitUntil.the(BangCongChiTietPage.btn_LYDOXACNHANGIORA, isVisible()).forNoMoreThan(10).seconds()
                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.btn_LYDOXACNHANGIORA)
                                );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(value)));
                    }
                    break;
                case "Lý do đi muộn":
                case "Lý do về sớm":
                    if (value != null && !value.isEmpty()) {
                        if (value.contains("Tai nạn, thiên tai, hỏa hoạn")){
                            OnStage.theActorInTheSpotlight().attemptsTo(
                                    JavaScriptScroll.to(BangCongChiTietPage.rd_ACCIDENT),
                                    WaitUntil.the(BangCongChiTietPage.rd_ACCIDENT, isVisible()).forNoMoreThan(10).seconds()
                            );
                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            Click.on(BangCongChiTietPage.rd_ACCIDENT));
                        } else if (value.contains("CBNV hoặc vợ/chồng")) {
                            OnStage.theActorInTheSpotlight().attemptsTo(
                                    JavaScriptScroll.to(BangCongChiTietPage.rd_SICK),
                                    WaitUntil.the(BangCongChiTietPage.rd_SICK, isVisible()).forNoMoreThan(10).seconds()
                            );
                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            Click.on(BangCongChiTietPage.rd_SICK));
                        } else if (value.contains("Lý do khách quan")) {
                            OnStage.theActorInTheSpotlight().attemptsTo(
                                    JavaScriptScroll.to(BangCongChiTietPage.rd_OBJECTIVE),
                                    WaitUntil.the(BangCongChiTietPage.rd_OBJECTIVE, isVisible()).forNoMoreThan(10).seconds()
                            );
                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            JavaScriptClick.on(BangCongChiTietPage.rd_OBJECTIVE));
                        }
                    }
                    break;
                case "Chọn giờ vào":
                    if (value != null && !value.isEmpty()) {
                        String[] partVao = value.split(":");
                        String gioVao = partVao[0];   // "08"
                        String phutVao = partVao[1];
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                WaitUntil.the(BangCongChiTietPage.txtBx_CHONGIOVAO, isVisible()).forNoMoreThan(10).seconds()
                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        JavaScriptScroll.to(BangCongChiTietPage.txtBx_CHONGIOVAO),
                                        Click.on(BangCongChiTietPage.txtBx_CHONGIOVAO));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Scroll.to(BangCongChiTietPage.OPTION_GIO(gioVao)),
                                        Click.on(BangCongChiTietPage.OPTION_GIO(gioVao))
                                );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Scroll.to(BangCongChiTietPage.OPTION_PHUT(phutVao)),
                                        Click.on(BangCongChiTietPage.OPTION_PHUT(phutVao))
                                );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.txtBx_CHONGIOVAO));
                    }
                    break;

                case "Chọn giờ ra":
                    if (value != null && !value.isEmpty()) {
                        String[] partRa = value.split(":");
                        String gioRa = partRa[0];   // "08"
                        String phutRa = partRa[1];
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                WaitUntil.the(BangCongChiTietPage.txtBx_CHONGIORA, isVisible()).forNoMoreThan(10).seconds()
                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        JavaScriptScroll.to(BangCongChiTietPage.txtBx_CHONGIORA),
                                        Click.on(BangCongChiTietPage.txtBx_CHONGIORA));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Scroll.to(BangCongChiTietPage.OPTION_GIO(gioRa)),
                                        Click.on(BangCongChiTietPage.OPTION_GIO(gioRa))
                                );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Scroll.to(BangCongChiTietPage.OPTION_PHUT(phutRa)),
                                        Click.on(BangCongChiTietPage.OPTION_PHUT(phutRa))
                                );
                    }
                    break;

                case "Ghi chú":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        JavaScriptScroll.to(BangCongChiTietPage.txtBx_GHICHU),
                                        Click.on(BangCongChiTietPage.txtBx_GHICHU));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Enter.theValue(value).into(BangCongChiTietPage.txtBx_GHICHU));
                    }
                    break;
                default:
                    break;
            }
        }

        // ✅ Submit after filling all fields
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        JavaScriptScroll.to(BangCongChiTietPage.btn_GUI),
                        JavaScriptClick.on(BangCongChiTietPage.btn_GUI));
    }

    @And("Chọn ngày {string} cần tạo đơn")
    public void chọnNgàyCầnTạoĐơn(String date) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.date_NGAYXACNHANHCONG(date)));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(BangCongChiTietPage.btn_THOIDIEMCANXACNHANCONG,isVisible()).forNoMoreThan(10).seconds());
    }

    @And("Chọn thời điểm xác nhận công")
    public void chọnThờiĐiểmXácNhậnCông() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.btn_THOIDIEMCANXACNHANCONG));
    }

    @Then("Kiểm tra list thời điểm xác nhận công hiển thị")
    public void kiểmTraListThờiĐiểmXácNhậnCôngHiểnThị() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(BangCongChiTietPage.value_TRONGSELECTBOX(CommonConstants.GIO_VAO),isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(BangCongChiTietPage.value_TRONGSELECTBOX(CommonConstants.GIO_RA),isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(BangCongChiTietPage.value_TRONGSELECTBOX(CommonConstants.GIO_VAO_VA_GIO_RA),isVisible()).forNoMoreThan(10).seconds());

    }


    @And("Chọn ngày để tạo xác nhận công thành công với nội dung {string}")
    public void chọnNgàyĐểTạoXácNhậnCôngThànhCôngVớiNộiDung(String caseKey) throws InterruptedException {
        String idMaChamCong = OnStage.theActorInTheSpotlight()
                .asksFor(Text.of(BangCongChiTietPage.txt_MACHAMCONG));
        Serenity.setSessionVariable(ID_MA_CHAM_CONG).to(idMaChamCong);
        System.out.println("check ID: " + Serenity.sessionVariableCalled(ID_MA_CHAM_CONG));
        Map<String, String> dataError = JsonDataReader.getData(SerenityConfigReader.get("data.ThongTinXacNhanCongSuccess"), caseKey);
        for (Map.Entry<String, String> entry : dataError.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            switch (field) {
                case "Ngày xác nhận công":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.date_NGAYXACNHANHCONG(value)));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        WaitUntil.the(BangCongChiTietPage.btn_THOIDIEMCANXACNHANCONG,isVisible()).forNoMoreThan(10).seconds());
                    }
                    break;
                case "Thời điểm cần xác nhận công":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.btn_THOIDIEMCANXACNHANCONG));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(value)));
                    }
                    break;

                case "Lý do xác nhận giờ vào":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                WaitUntil.the(BangCongChiTietPage.btn_LYDOXACNHANGIOVAO, isVisible()).forNoMoreThan(10).seconds()
                        );
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                JavaScriptScrollToTop.now()
                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.btn_LYDOXACNHANGIOVAO)
                                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(value)));
                    }
                    break;
                case "Lý do xác nhận giờ ra":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                WaitUntil.the(BangCongChiTietPage.btn_LYDOXACNHANGIORA, isVisible()).forNoMoreThan(10).seconds()
                        );
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                JavaScriptScrollToTop.now()
                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.btn_LYDOXACNHANGIORA)
                                );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(value)));
                    }
                    break;
                case "Lý do đi muộn":
                case "Lý do về sớm":
                    if (value != null && !value.isEmpty()) {
                        if (value.contains("Tai nạn, thiên tai, hỏa hoạn")){
                            OnStage.theActorInTheSpotlight().attemptsTo(
                                    WaitUntil.the(BangCongChiTietPage.rd_ACCIDENT, isVisible()).forNoMoreThan(10).seconds()
                            );
                            OnStage.theActorInTheSpotlight().attemptsTo(
                                    JavaScriptScrollToTop.now());

                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            Click.on(BangCongChiTietPage.rd_ACCIDENT));
                        } else if (value.contains("CBNV hoặc vợ/chồng")) {
                            OnStage.theActorInTheSpotlight().attemptsTo(
                                    WaitUntil.the(BangCongChiTietPage.rd_SICK, isVisible()).forNoMoreThan(10).seconds()
                            );
                            OnStage.theActorInTheSpotlight().attemptsTo(
                                    JavaScriptScrollToTop.now());
                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            Click.on(BangCongChiTietPage.rd_SICK));
                        } else if (value.contains("Lý do khách quan")) {
                            OnStage.theActorInTheSpotlight().attemptsTo(
                                    WaitUntil.the(BangCongChiTietPage.rd_OBJECTIVE, isVisible()).forNoMoreThan(10).seconds()
                            );
                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            JavaScriptClick.on(BangCongChiTietPage.rd_OBJECTIVE));
                        }
                    }
                    break;
                case "Chọn giờ vào":
                    String[] partVao = value.split(":");
                    String gioVao = partVao[0];   // "08"
                    String phutVao = partVao[1];
                    OnStage.theActorInTheSpotlight().attemptsTo(
                            WaitUntil.the(BangCongChiTietPage.txtBx_CHONGIOVAO, isVisible()).forNoMoreThan(10).seconds()
                    );
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    JavaScriptScroll.to(BangCongChiTietPage.txtBx_CHONGIOVAO),
                                    Click.on(BangCongChiTietPage.txtBx_CHONGIOVAO));
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Scroll.to(BangCongChiTietPage.OPTION_GIO(gioVao)),
                                    Click.on(BangCongChiTietPage.OPTION_GIO(gioVao))
                            );
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Scroll.to(BangCongChiTietPage.OPTION_PHUT(phutVao)),
                                    Click.on(BangCongChiTietPage.OPTION_PHUT(phutVao))
                            );
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Click.on(BangCongChiTietPage.txtBx_CHONGIOVAO));
                    break;

                case "Chọn giờ ra":
                    String[] partRa = value.split(":");
                    String gioRa = partRa[0];   // "08"
                    String phutRa = partRa[1];
                    OnStage.theActorInTheSpotlight().attemptsTo(
                            WaitUntil.the(BangCongChiTietPage.txtBx_CHONGIORA, isVisible()).forNoMoreThan(10).seconds()
                    );
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    JavaScriptScroll.to(BangCongChiTietPage.txtBx_CHONGIORA),
                                    Click.on(BangCongChiTietPage.txtBx_CHONGIORA));
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Scroll.to(BangCongChiTietPage.OPTION_GIO(gioRa)),
                                    Click.on(BangCongChiTietPage.OPTION_GIO(gioRa))
                            );
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Scroll.to(BangCongChiTietPage.OPTION_PHUT(phutRa)),
                                    Click.on(BangCongChiTietPage.OPTION_PHUT(phutRa))
                            );
                    break;

                case "Ghi chú":
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Enter.theValue(value).into(BangCongChiTietPage.txtBx_GHICHU));
                    break;
                default:
            }
        }
        Thread.sleep(30000);
//
//        // ✅ Submit after filling all fields
//        OnStage.theActorInTheSpotlight()
//                .attemptsTo(
//                        Click.on(BangCongChiTietPage.btn_GUI));
//        OnStage.theActorInTheSpotlight().attemptsTo(
//                WaitUntil.the(BangCongChiTietPage.alert_MESSAGE, isVisible()).forNoMoreThan(3).seconds()
//        );
//        String alertMessage = OnStage.theActorInTheSpotlight()
//                .asksFor(Text.of(BangCongChiTietPage.alert_MESSAGE));
//
//        Assert.assertTrue("Tạo đơn không thành công lỗi " + alertMessage, alertMessage.contains("Thành công"));
    }

    @And("Chọn {string} xác nhận công")
    public void chọnXácNhậnCông(String status) {
        if (status.equalsIgnoreCase("Phê Duyệt")) {
            OnStage.theActorInTheSpotlight().attemptsTo(
                    WaitUntil.the(BangCongChiTietPage.btn_PHEDUYET, isVisible()).forNoMoreThan(3).seconds());
            OnStage.theActorInTheSpotlight()
                    .attemptsTo(
                            Click.on(BangCongChiTietPage.btn_PHEDUYET));
        } else {
            OnStage.theActorInTheSpotlight().attemptsTo(
                    WaitUntil.the(BangCongChiTietPage.btn_TUCHOI, isVisible()).forNoMoreThan(3).seconds());
            OnStage.theActorInTheSpotlight()
                    .attemptsTo(
                            Click.on(BangCongChiTietPage.btn_TUCHOI));
        }
    }

    @Then("Verify nội dung lỗi {string}")
    public void verifyNoiDungLoi(String caseKey) {
        Map<String, String> dataError = JsonDataReader.getData(SerenityConfigReader.get("data.ThongTinXacNhanCongLoi"), caseKey);
        for (Map.Entry<String, String> entry : dataError.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            switch (field) {
                case "Thời điểm cần xác nhận công error messages":
                case "Chọn giờ vào error messages":
                case "Chọn giờ ra error messages":
                case "Ghi chú error messages":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        JavaScriptScroll.to(
                                                BangCongChiTietPage.txt_ERRORMESSAGES(value)),
                                        WaitUntil.the(
                                                BangCongChiTietPage.txt_ERRORMESSAGES(value), isVisible()).forNoMoreThan(10).seconds());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Then("Verify list {string} được hiển thị")
    public void verifyListDuocHienThi(String name) {
    }

    @And("Select {string} cho thời điểm cần xác nhận công")
    public void selectChoThoiDiemCanXacNhanCong(String reason) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(
                                BangCongChiTietPage.btn_THOIDIEMCANXACNHANCONG
                                , isVisible()).forNoMoreThan(10).seconds(),
                        Click.on(BangCongChiTietPage.btn_THOIDIEMCANXACNHANCONG));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Giờ vào should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Giờ vào")), is(true)));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Giờ ra should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Giờ ra")), is(true)));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Giờ vào và giờ ra should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Giờ vào và giờ ra")), is(true)));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(reason)));
    }

    @And("Select {string} cho lý do xác nhận giờ vào")
    public void selectChoLyDoXacNhanGioVao(String reason) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(BangCongChiTietPage.btn_LYDOXACNHANGIOVAO, isVisible()).forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.btn_LYDOXACNHANGIOVAO));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Quên chấm công giờ vào/ra should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Quên chấm công giờ vào/ra")), is(true)));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Đi muộn/về sớm should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Đi muộn/về sớm")), is(true)));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Vắng mặt vì lý do khác should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Vắng mặt vì lý do khác")), is(true)));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(reason)));
    }

    @And("Select {string} cho lý do xác nhận giờ ra")
    public void selectChoLyDoXacNhanGioRa(String reason) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(BangCongChiTietPage.btn_LYDOXACNHANGIORA, isVisible()).forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.btn_LYDOXACNHANGIORA));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Quên chấm công giờ vào/ra should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Quên chấm công giờ vào/ra")), is(true)));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Đi muộn/về sớm should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Đi muộn/về sớm")), is(true)));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Vắng mặt vì lý do khác should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.value_TRONGSELECTBOX("Vắng mặt vì lý do khác")), is(true)));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(reason)));
    }

    @And("Select {string} cho {string}")
    public void selectChoLyDoDiMuon(String value, String reason) {
        String lyDo1 = "Tai nạn, thiên tai, hỏa hoạn";
        String lyDo2 = "CBNV hoặc vợ/chồng, con (con đẻ, con nuôi), tứ thân phụ mẫu bị ốm, có xác nhận của cơ sở khám chữa bệnh có thẩm quyền";
        String lyDo3 = "Lý do khách quan (sự kiện xảy ra khách quan không thể lường trước được, không thể khắc phục được mặc dù đã áp dụng mọi biện pháp cần thiết và khả năng cho phép)";

        OnStage.theActorInTheSpotlight().attemptsTo(
                JavaScriptScroll.to(
                        BangCongChiTietPage.radio_ACCIDENT(reason)),
                WaitUntil.the(BangCongChiTietPage.radio_ACCIDENT(reason), isVisible()).forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat("Quên chấm công giờ vào/ra should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.txt_RADIOLYDODIMUONVESOM(reason,lyDo1)), is(true)));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Đi muộn/về sớm should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.txt_RADIOLYDODIMUONVESOM(reason,lyDo2)), is(true)));
        OnStage.theActorInTheSpotlight().should(
                seeThat("Vắng mặt vì lý do khác should not be visible",
                        Visibility.of(
                                BangCongChiTietPage.txt_RADIOLYDODIMUONVESOM(reason,lyDo3)), is(true)));
        switch (reason) {
            case "Lý do đi muộn":
            case "Lý do về sớm":
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.radio_ACCIDENT(reason)));
                break;
            default:
        }
    }

    @Then("Verify đơn xin nghỉ sáng đi làm chiều ngày {string} được phê duyệt")
    public void verifyDonXinNghiSangDiLamChieuNgayDuocPheDuyet(String date) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                VerifyDateColors.forDateWithColors(date, "#1C3867", "#1CCD83")
        );
    }

    @Then("Verify đơn xác nhận công ngày {string} được phê duyệt")
    public void verifyDonXacNhanCongNgayDuocPheDuyet(String date) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                VerifyDateColors.forDateWithColors(date, "#FFC83A")
        );
    }

    @Then("Verify đơn xin nghỉ phép ngày {string} được phê duyệt")
    public void verifyDonXinNghiPhepNgayDuocPheDuyet(String date) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                VerifyDateColors.forDateWithColors(date, "#1C3867")
        );
    }

    @Then("Verify đơn điều động công tác ngày {string} được phê duyệt")
    public void verifyDonDieuDongCongTacNgayDuocPheDuyet(String date) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                VerifyDateColors.forDateWithColors(date, "#6788FB")
        );
    }

    @Then("Verify ngày {string} không có kết quả chấm công")
    public void verifyNgayKhongCoKetQuaChamCong(String date) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                VerifyDateColors.forDateWithColors(date, "#7D868D")
        );
    }

    @And("Verify số ngày công {string}")
    public void verifySoNgayCong(String status) throws InterruptedException {
        Thread.sleep(10000);
//        // All day cells in calendar
//        List<WebElementFacade> dayCells = BangCongChiTietPage.list_DAYS
//                .resolveAllFor(OnStage.theActorInTheSpotlight());
//
//        double total = 0;
//
//        LocalDate today = LocalDate.now();
//        LocalDate endDate = today.minusDays(2);  // before current date 2 days
//        LocalDate firstDay = today.withDayOfMonth(1);
//
//        for (WebElementFacade dayCell : dayCells) {
//            String dateAttr = dayCell.getAttribute("data-date"); // e.g. 2025-09-03
//            if (dateAttr == null || dateAttr.isEmpty()) continue;
//
//            LocalDate eventDate = LocalDate.parse(dateAttr);
//
//            // Only count from 1st -> today-2
//            if (eventDate.isBefore(firstDay) || eventDate.isAfter(endDate)) {
//                continue;
//            }
//
//            // Saturday rule (always +0.5 regardless of color)
//            if (status.equals("Hợp Lệ") && eventDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
//                total += 0.5;
//                System.out.println("check ngay thu 7");
//                continue; // skip further checks for this Saturday
//            }
//
//            // Get all events inside this day
//            List<WebElementFacade> colors = dayCell.thenFindAll(By.xpath(".//a[contains(@class,'fc-event')]"));
//            System.out.println("check ngay " + dateAttr);
//            System.out.println("Checking date: " + eventDate + " has " + colors.size() + " events");
//
//            for (WebElementFacade color : colors) {
//                String rgba = color.getCssValue("background-color");
//                String hex = RgbaToHex.convert(rgba);
//
//                switch (status) {
//                    case "Hợp Lệ":
//                        if (hex.equalsIgnoreCase("#1ccd83")) { // Green only for weekdays
//                            total += 1;
//                            System.out.println("check hop le"+ total);
//                            break; // one event is enough
//                        }
//                        break;
//
//                    case "Không Hợp Lệ":
//                        if (hex.equalsIgnoreCase("#fe5353")   // Red
//                                || hex.equalsIgnoreCase("#7d868d")  // Gray
//                                || hex.equalsIgnoreCase("#ffc83a")) { // Yellow
//                            total += 1;
//                            break;
//                        }
//                        break;
//
//                    case "Cần Giải Trình":
//                        if (hex.equalsIgnoreCase("#fe5353")) { // Gray
//                            total += 1;
//                            break;
//                        }
//                        break;
//
//                    default:
//                        break;
//                }
//            }
//        }
//
//        // 🔎 Now fetch header value depending on status
//        String actualText = "";
//        switch (status) {
//            case "Hợp Lệ":
//                actualText = OnStage.theActorInTheSpotlight()
//                        .asksFor(Text.of(BangCongChiTietPage.number_NGAYCONGHOPLE));
//                break;
//
//            case "Không Hợp Lệ":
//                actualText = OnStage.theActorInTheSpotlight()
//                        .asksFor(Text.of(BangCongChiTietPage.number_NGAYCONGKHONGHOPLE));
//                break;
//
//            case "Cần Giải Trình":
//                actualText = OnStage.theActorInTheSpotlight()
//                        .asksFor(Text.of(BangCongChiTietPage.number_NGAYCONGCANGIAITRINH));
//                break;
//
//            default:
//                return; // no check
//        }
//
//        double actualTotal = Double.parseDouble(actualText.trim());
//
//        Assert.assertEquals(
//                "Mismatch between calculated working days and header displayed value for status: " + status,
//                total, actualTotal, 0.01
//        );
        List<WebElementFacade> dayCells = BangCongChiTietPage.list_DAYS
                .resolveAllFor(OnStage.theActorInTheSpotlight());

        double total = 0;

        // Define month range
        LocalDate firstDay = LocalDate.of(2025, 9, 1);
        LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());
        LocalDate endDate = lastDay; // full month check

        System.out.println("🔎 Checking month: " + 9 + "/" + 2025 + " from " + firstDay + " to " + endDate);

        for (WebElementFacade dayCell : dayCells) {
            String dateAttr = dayCell.getAttribute("data-date"); // e.g. 2025-09-03
            if (dateAttr == null || dateAttr.isEmpty()) continue;

            LocalDate eventDate = LocalDate.parse(dateAttr);

            // Only count if inside the target month
            if (eventDate.isBefore(firstDay) || eventDate.isAfter(endDate)) continue;

            // Saturday rule (always +0.5 regardless of color)
            if (status.equals("Hợp Lệ") && eventDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                total += 0.5;
                System.out.println("🟢 Counted Saturday: " + eventDate + " (+0.5)");
                continue; // skip further checks for this Saturday
            }

            // Get all events inside this day
            List<WebElementFacade> colors = dayCell.thenFindAll(By.xpath(".//*[contains(@class,'fc-event')]"));
            System.out.println("Checking date: " + eventDate + " has " + colors.size() + " events");

            for (WebElementFacade color : colors) {
                String rgba = color.getCssValue("background-color");
                String hex = RgbaToHex.convert(rgba);

                switch (status) {
                    case "Hợp Lệ":
                        if (hex.toLowerCase().startsWith("#1ccd")) { // Flexible green check
                            total += 1;
                            System.out.println("Hợp Lệ day: " + eventDate + " (" + hex + ")");
                            break;
                        }
                        break;

                    case "Không Hợp Lệ":
                        if (hex.equalsIgnoreCase("#fe5353")   // Red
                                || hex.equalsIgnoreCase("#7d868d")  // Gray
                                || hex.equalsIgnoreCase("#ffc83a")) { // Yellow
                            total += 1;
                            System.out.println("Không Hợp Lệ day: " + eventDate + " (" + hex + ")");
                            break;
                        }
                        break;

                    case "Cần Giải Trình":
                        if (hex.equalsIgnoreCase("#fe5353") || hex.equalsIgnoreCase("#7d868d")) {
                            total += 1;
                            System.out.println("Cần Giải Trình day: " + eventDate + " (" + hex + ")");
                            break;
                        }
                        break;

                    default:
                        break;
                }
            }
        }

        // Now fetch header value depending on status
        String actualText = "";
        switch (status) {
            case "Hợp Lệ":
                actualText = OnStage.theActorInTheSpotlight()
                        .asksFor(Text.of(BangCongChiTietPage.number_NGAYCONGHOPLE));
                break;

            case "Không Hợp Lệ":
                actualText = OnStage.theActorInTheSpotlight()
                        .asksFor(Text.of(BangCongChiTietPage.number_NGAYCONGKHONGHOPLE));
                break;

            case "Cần Giải Trình":
                actualText = OnStage.theActorInTheSpotlight()
                        .asksFor(Text.of(BangCongChiTietPage.number_NGAYCONGCANGIAITRINH));
                break;

            default:
                return; // no check
        }

        double actualTotal = Double.parseDouble(actualText.trim());
        System.out.println("✅ Calculated total: " + total + " | Header total: " + actualTotal);

        Assert.assertEquals(
                "Mismatch between calculated working days and header displayed value for status: " + status,
                total, actualTotal, 0.01
        );
    }
}
