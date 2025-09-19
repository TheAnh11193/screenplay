package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import ui.BangCongChiTietPage;
import ui.WebLoginPage;
import utils.CommonConstants;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

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
}
