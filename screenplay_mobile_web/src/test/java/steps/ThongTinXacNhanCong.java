package steps;

import actions.JavaScriptClick;
import actions.JavaScriptScroll;
import actions.JavaScriptScrollToTop;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actors.OnStage;

import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;
import ui.BangCongChiTietPage;
import ui.WebLoginPage;
import utils.CommonConstants;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
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
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.btn_LYDOXACNHANGIOVAO));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(value)));
                    }
                    break;
                case "Lý do xác nhận giờ ra":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.btn_LYDOXACNHANGIORA));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(BangCongChiTietPage.value_TRONGSELECTBOX(value)));
                    }
                    break;
                case "Lý do đi muộn":
                case "Lý do về sớm":
                    if (value != null && !value.isEmpty()) {
                        if (value.contains("Tai nạn, thiên tai, hỏa hoạn")){
                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            Click.on(BangCongChiTietPage.rd_ACCIDENT));
                        } else if (value.contains("CBNV hoặc vợ/chồng")) {
                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            Click.on(BangCongChiTietPage.rd_SICK));
                        } else if (value.contains("Lý do khách quan")) {
                            OnStage.theActorInTheSpotlight()
                                    .attemptsTo(
                                            Click.on(BangCongChiTietPage.rd_OBJECTIVE));
                        }
                    }
                    break;
                case "Chọn giờ vào ":
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Enter.theValue(value).into(BangCongChiTietPage.txtBx_CHONGIOVAO));
                    break;

                case "Chọn giờ ra ":
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Enter.theValue(value).into(BangCongChiTietPage.txtBx_CHONGIORA));
                    break;

                case "Ghi chú":
                    OnStage.theActorInTheSpotlight()
                            .attemptsTo(
                                    Enter.theValue(value).into(BangCongChiTietPage.txtBx_GHICHU));
                    break;

                default:
                    break;
            }
        }

        // ✅ Submit after filling all fields
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.btn_GUI));
        Thread.sleep(20000);
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

        // ✅ Submit after filling all fields
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(BangCongChiTietPage.btn_GUI));
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(BangCongChiTietPage.alert_MESSAGE, isVisible()).forNoMoreThan(3).seconds()
        );
        String alertMessage = OnStage.theActorInTheSpotlight()
                .asksFor(Text.of(BangCongChiTietPage.alert_MESSAGE));

        Assert.assertTrue("Tạo đơn không thành công lỗi " + alertMessage, alertMessage.contains("Thành công"));
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
}
