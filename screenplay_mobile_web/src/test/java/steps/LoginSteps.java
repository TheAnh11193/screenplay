package steps;

import hooks.DriverHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import questions.IsElementVisible;
import tasks.LoginOdooWeb;
import tasks.LoginTCWeb;
import tasks.MobileLogin;
import ui.WebLoginPage;
import utils.CsvDataReader;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

import static java.lang.Thread.sleep;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class LoginSteps {

    @Given("The {string} open the login page")
    public void theOpenTheLoginPage(String role) throws Exception {
        System.out.println(">>> LOGIN TC PAGE OPENED <<<");
        String url = SerenityConfigReader.get("webdriver.base.tc.url");
        DriverHooks.loginAs(role, url);
        Map<String, String> data = CsvDataReader.getUserData(role);
        String useName = data.get("username");
        String passWord = data.get("password");
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebLoginPage.link_SSO,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight().attemptsTo(LoginTCWeb.withCredentials(useName,passWord));
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebLoginPage.logo_THANHCONG,isVisible()).forNoMoreThan(10).seconds());
//        actorUser.attemptsTo(WaitUntil.the(WebLoginPage.txt_PASSWORDTC,isVisible()).forNoMoreThan(10).seconds());
//        actorUser.should(seeThat("Username field should NOT be visible"
//                ,IsElementVisible.forTarget(WebLoginPage.LOGIN_BUTTON), is(false)));

    }

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
        OnStage.theActorInTheSpotlight().attemptsTo(WaitUntil.the(WebLoginPage.logo_THANHCONG,isVisible()).forNoMoreThan(10).seconds());
    }

    @And("The {string} open app mobile")
    public void theOpenAppMobile(String role) throws Exception {
        System.out.println(">>> LOGIN TC PAGE OPENED <<<");
        Map<String, String> userData = JsonDataReader.getData(SerenityConfigReader.get("data.Transfer"), "happy");
        Map<String, String> data = CsvDataReader.getUserData(role);
        String useName = data.get("username");
        String passWord = data.get("password");

        Actor actorApprover = DriverHooks.loginAs(role, null);
        actorApprover.attemptsTo(MobileLogin.withCredentials(useName,passWord));
//        actorApprover.should(seeThat("Username field should NOT be visible"
//                ,IsElementVisible.forTarget(WebLoginPage.LOGIN_BUTTON), is(false)));

    }
}
