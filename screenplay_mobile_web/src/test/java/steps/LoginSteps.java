package steps;

import hooks.DriverHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import questions.IsElementVisible;
import tasks.LoginOdooWeb;
import tasks.LoginTCWeb;
import tasks.MobileLogin;
import ui.WebLoginPage;
import utils.CsvDataReader;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class LoginSteps {

    @Given("The {string} open the login page")
    public void theOpenTheLoginPage(String role) throws Exception {
        System.out.println(">>> LOGIN TC PAGE OPENED <<<");
        String url = SerenityConfigReader.get("webdriver.base.tc.url");
        Map<String, String> userData = JsonDataReader.getData(SerenityConfigReader.get("data.Transfer"), "happy");
        Map<String, String> data = CsvDataReader.getUserData(role);
        String useName = data.get("username");
        String passWord = data.get("password");

        Actor actorUser = DriverHooks.loginAs(role, url);
        actorUser.attemptsTo(LoginTCWeb.withCredentials(useName,passWord));
        actorUser.should(seeThat("Username field should NOT be visible"
                ,IsElementVisible.forTarget(WebLoginPage.LOGIN_BUTTON), is(false)));

    }

    @When("The {string} open the odoo page")
    public void theOpenTheOdooPage(String role) throws Exception {
        System.out.println(">>> LOGIN TC PAGE OPENED <<<");
        String url = SerenityConfigReader.get("webdriver.base.odoo.url");
        Map<String, String> userData = JsonDataReader.getData(SerenityConfigReader.get("data.Transfer"), "happy");
        Map<String, String> data = CsvDataReader.getUserData(role);
        String useName = data.get("username");
        String passWord = data.get("password");

        Actor actorReviewer = DriverHooks.loginAs(role, url);
        actorReviewer.attemptsTo(LoginOdooWeb.withCredentials(useName,passWord));
        actorReviewer.should(seeThat("Username field should NOT be visible"
                ,IsElementVisible.forTarget(WebLoginPage.LOGIN_BUTTON), is(false)));
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
        actorApprover.should(seeThat("Username field should NOT be visible"
                ,IsElementVisible.forTarget(WebLoginPage.LOGIN_BUTTON), is(false)));

    }
}
