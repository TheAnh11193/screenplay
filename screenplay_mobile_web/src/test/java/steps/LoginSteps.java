package steps;

import hooks.DriverHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import tasks.LoginOdooWeb;
import tasks.LoginEBIZWeb;
import tasks.MobileLogin;
import ui.WebEbizLoginPage;
import utils.CsvDataReader;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

import static java.lang.Thread.sleep;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class LoginSteps {

    @Given("The {string} open the Ebiz login page")
    public void theOpenTheLoginPage(String role) throws Exception {
        System.out.println(">>> LOGIN TC PAGE OPENED <<<");
        String url = SerenityConfigReader.get("webdriver.base.Ebiz");
        DriverHooks.loginAs(role, url);
        Map<String, String> data = CsvDataReader.getUserData(role);
        String useName = data.get("username");
        String passWord = data.get("password");
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizLoginPage.txtbx_USERNAME_EBIZ,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(Enter.theValue(useName).into(WebEbizLoginPage.txtbx_USERNAME_EBIZ));
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizLoginPage.txtbx_PASSWORD_EBIZ));
        Thread.sleep(1000);
        OnStage.theActorInTheSpotlight().attemptsTo(Enter.theValue(passWord).into(WebEbizLoginPage.txtbx_PASSWORD_EBIZ));
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizLoginPage.btn_DANGNHAP));

        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebEbizLoginPage.title_THONGBAO,isVisible()).forNoMoreThan(20).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(WebEbizLoginPage.btn_DONGYTHONGBAO));
//        actorUser.attemptsTo(WaitUntil.the(WebLoginPage.txt_PASSWORDTC,isVisible()).forNoMoreThan(10).seconds());
//        actorUser.should(seeThat("Username field should NOT be visible"
//                ,IsElementVisible.forTarget(WebLoginPage.LOGIN_BUTTON), is(false)));

    }
}
