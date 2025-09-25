package steps;

import hooks.DriverHooks;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import tasks.LoginOdooWeb;
import ui.OdooChamCongPage;
import ui.OdooHomeMenuPage;
import utils.CsvDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OdooHomeMenu {

    @When("The {string} open the odoo page")
    public void theOpenTheOdooPage(String role) throws Exception {
        System.out.println(">>> LOGIN Odoo PAGE OPENED <<<");
        String url = SerenityConfigReader.get("webdriver.base.odoo.url");
        DriverHooks.loginAs(role, url);
        Map<String, String> data = CsvDataReader.getUserData(role);
        String useName = data.get("username");
        String passWord = data.get("password");
//        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebLoginPage.link_SSO,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(LoginOdooWeb.withCredentials(useName,passWord));
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(OdooHomeMenuPage.btn_MENUCHAMCONG,isVisible()).forNoMoreThan(10).seconds());
    }

    @When("I open chấm công menu")
    public void iOpenChấmCôngMenu() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(OdooHomeMenuPage.btn_MENUCHAMCONG,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(OdooHomeMenuPage.btn_MENUCHAMCONG));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(OdooChamCongPage.table_BANGCHAMCONG,isVisible()).forNoMoreThan(10).seconds());
    }
}
