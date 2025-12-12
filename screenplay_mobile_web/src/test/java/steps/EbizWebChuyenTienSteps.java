package steps;

import actions.JavaScriptClick;
import actions.JavaScriptScroll;
import actions.JavaScriptScrollToTop;
import hooks.DriverHooks;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import questions.CssColor;
import tasks.LoginEBIZWeb;
import tasks.UploadFile;
import ui.BangCongChiTietPage;
import ui.WebEbizChuyenTienPage;
import ui.WebEbizLoginPage;
import utils.CsvDataReader;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.nio.file.Paths;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static utils.CommonConstants.ID_MA_CHAM_CONG;
import static utils.CommonConstants.MA_GIAO_DICH;

public class EbizWebChuyenTienSteps {

    @When("User khoi tao {string} giao dich")
    public void userKhoiTaoGiaoDich(String giaodich) {
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.menu_CHUYENTIEN,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizChuyenTienPage.menu_CHUYENTIEN));
        switch (giaodich) {
            case "Chuyen Tien":
                break;
            case"Chuyen tien theo lo":
                OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.menu_CONTENTCHUYENTIENTHEOLO,isVisible()).forNoMoreThan(10).seconds());
                OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizChuyenTienPage.menu_CONTENTCHUYENTIENTHEOLO));
                break;
            default:
        }
    }

    @And("User nhap thong tin theo {string}")
    public void userNhapThongTinTheo(String caseKey) {
        // Write code here that turns the phrase above into concrete actions
        Map<String, String> dataChuyenTien = JsonDataReader.getData(SerenityConfigReader.get("data.chuyentien"), caseKey);
        for (Map.Entry<String, String> entry : dataChuyenTien.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            switch (field) {
                case "Pham vi chuyen":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(WebEbizChuyenTienPage.btn_PHAMVICHUYEN(value)));
                    }
                    break;
                case "Ten file":
                    if (value != null && !value.isEmpty()) {
                        String filepath = SerenityConfigReader.get("upload.fileChuyentientheolo");
                        String absolutePath = Paths.get(filepath).toAbsolutePath().toString();
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Scroll.to(WebEbizChuyenTienPage.input_UPLOAD));
                        WebEbizChuyenTienPage.input_UPLOAD.resolveFor(OnStage.theActorInTheSpotlight()).sendKeys(absolutePath);
                    }
                    break;
                case "Dien Giai":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                JavaScriptScroll.to(WebEbizChuyenTienPage.txt_DIENGIAI),
                                WaitUntil.the(WebEbizChuyenTienPage.txt_DIENGIAI, isVisible()).forNoMoreThan(10).seconds()
                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Enter.theValue(value).into(WebEbizChuyenTienPage.txt_DIENGIAI));
                    }
                    break;
                case "Phi duoc thanh toan boi":
                    if (value != null && !value.isEmpty()) {
                        OnStage.theActorInTheSpotlight().attemptsTo(
                                JavaScriptScroll.to(WebEbizChuyenTienPage.combobox_NGUOICHIUPHI),
                                WaitUntil.the(WebEbizChuyenTienPage.combobox_NGUOICHIUPHI, isVisible()).forNoMoreThan(10).seconds()
                        );
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(WebEbizChuyenTienPage.combobox_NGUOICHIUPHI));
                        OnStage.theActorInTheSpotlight()
                                .attemptsTo(
                                        Click.on(WebEbizChuyenTienPage.listbox_NGUOICHIUPHI(value)));
                    }
                    break;
                default:
            }
        }
        OnStage.theActorInTheSpotlight().attemptsTo(
                JavaScriptScroll.to(WebEbizChuyenTienPage.btn_TIEPTUC),
                WaitUntil.the(WebEbizChuyenTienPage.btn_TIEPTUC, isVisible()).forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(WebEbizChuyenTienPage.btn_TIEPTUC));
    }

    @And("Kiem tra noi dung file upload")
    public void kiemTraNoiDungFileUpload() {
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.btn_POPUP_TAIVE,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizChuyenTienPage.btn_POPUP_TAIVE));
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.btn_POPUP_TIEPTUC,isEnabled()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizChuyenTienPage.btn_POPUP_TIEPTUC));
    }

    @And("User {string} giao dich")
    public void userGiaoDich(String titleButton) {
        switch (titleButton) {
            case "Xac nhan":
                OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.btn_XACNHAN,isVisible()).forNoMoreThan(10).seconds());
                OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizChuyenTienPage.btn_XACNHAN));
                break;
            case "Quay lai":
                break;
            default:
        }

    }

    @And("User verify khoi tao giao dich thanh cong")
    public void userVerifyKhoiTaoGiaoDichThanhCong() {
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.alert_GIAODICHCHODUYET,isVisible()).forNoMoreThan(10).seconds());
        String txtSogiaodich = OnStage.theActorInTheSpotlight()
                .asksFor(Text.of(WebEbizChuyenTienPage.txt_SOGIAODICH)).toString();
        String numberSoGiaoDich = txtSogiaodich.split(":")[1].trim();
        System.out.println("check so giao dich: " +numberSoGiaoDich);
        Serenity.setSessionVariable(MA_GIAO_DICH).to(numberSoGiaoDich);
    }

    @And("User vao {string} de kiem tra")
    public void userVaoDeKiemTra(String typeGiaoDich) {
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.cardbox_NHATKYGIAODICH,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizChuyenTienPage.cardbox_NHATKYGIAODICH));
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.title_DANHSACHGIAODICH,isVisible()).forNoMoreThan(10).seconds());
        String magiaodich = Serenity.sessionVariableCalled(MA_GIAO_DICH);
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizChuyenTienPage.table_NUMBERMAGIAODICH(magiaodich),isVisible()).forNoMoreThan(10).seconds());
    }
}
