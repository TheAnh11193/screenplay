package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import questions.VerifyWeekDayColors;
import tasks.VerifyAllColorOfNote;
import ui.OdooChamCongPage;
import ui.OdooHomeMenuPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OdooChamCong {
    @And("Tìm kiếm nhân viên với tên {string}")
    public void tìmKiếmNhânViênVớiTên(String name) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(OdooChamCongPage.input_SEARCHBOX,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Enter.theValue(name).into(OdooChamCongPage.input_SEARCHBOX));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(OdooChamCongPage.dropdown_VALUESEARCHMENU));
//        OnStage.theActorInTheSpotlight()
//                .attemptsTo(
//                        WaitUntil.the(OdooChamCongPage.result_SEARCH,isVisible()).forNoMoreThan(10).seconds());
    }

    @Then("Verify kết quả chấm công trong tuần")
    public void verifyKếtQuảChấmCôngTrongTuần() {
        OnStage.theActorInTheSpotlight().should(seeThat(VerifyWeekDayColors.areCorrect()));
    }
}
