package steps;

import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import tasks.CreateAnnualLeaveWeb;
import tasks.VerifyAllColorOfNote;
import ui.BangCongChiTietPage;
import ui.HomePage;
import ui.PheDuyetXacNhanCongPage;
import ui.WebLoginPage;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class HomeSteps {
    @When("create annual leave")
    public void createAnnualLeave() {
        Map<String, String> userData = JsonDataReader.getData(SerenityConfigReader.get("data.CreateAnnualLeave"), "CreateAnnualLeave");
        String loainghi = userData.get("Loai Nghi");
        String ngaybatdau = userData.get("Ngay Bat Dau");
        String ngayketthuc = userData.get("Ngay Ket Thuc");
        String lydonghi = userData.get("Ly Do Nghi");
        String loai = userData.get("Loai");
        OnStage.theActorInTheSpotlight().attemptsTo(CreateAnnualLeaveWeb.withDetails(loainghi,ngaybatdau,ngayketthuc,lydonghi,loai));
    }

    @When("I verify detailed work sheet")
    public void iVerifyDetailedWorkSheet() {
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(BangCongChiTietPage.color_CHAMDUNGGIO,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyAllColorOfNote.areCorrect());
    }

    @When("I open bang cong chi tiet")
    public void iOpenBangCongChiTiet() {
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(HomePage.btn_MENU));
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(HomePage.btn_NHANSU));
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(HomePage.btn_BANGCONGCHITIET));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(BangCongChiTietPage.text_LOADING,isNotVisible()).forNoMoreThan(10).seconds());
    }

    @When("I open phê duyệt xác nhận công")
    public void iOpenPhêDuyệtXácNhậnCông() {
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(HomePage.btn_MENU));
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(HomePage.btn_NHANSU));
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(HomePage.btn_PHEDUYETXACNHANCONG));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(PheDuyetXacNhanCongPage.table_PHEDUYETXACNHANCONG,isVisible()).forNoMoreThan(10).seconds());
    }
}
