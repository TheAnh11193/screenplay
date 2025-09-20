package steps;

import io.cucumber.java.en.And;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import ui.BangCongChiTietPage;
import ui.HomePage;
import ui.PheDuyetXacNhanCongPage;
import utils.CommonConstants;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static utils.CommonConstants.ID_MA_CHAM_CONG;

public class PheDuyetXacNhanCong {
    @And("Chọn đơn ngày {string} cần phê duyệt xác nhận công")
    public void chọnĐơnNgàyCầnPhêDuyệtXácNhậnCông(String date) {

        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(PheDuyetXacNhanCongPage.table_PHEDUYETXACNHANCONG,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(PheDuyetXacNhanCongPage.input_DATETUNGAY));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(PheDuyetXacNhanCongPage.select_FILTERDATE(date)));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(PheDuyetXacNhanCongPage.input_DATEDENNGAY));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(PheDuyetXacNhanCongPage.select_FILTERDATE(date)));
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(PheDuyetXacNhanCongPage.btn_TIMKIEM));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(
                                PheDuyetXacNhanCongPage
                                        .value_RESULTTIMKIEM(Serenity.sessionVariableCalled(ID_MA_CHAM_CONG)),isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(
                                PheDuyetXacNhanCongPage
                                        .value_RESULTTIMKIEM(Serenity.sessionVariableCalled(ID_MA_CHAM_CONG))));
    }
}
