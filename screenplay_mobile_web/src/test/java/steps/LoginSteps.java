package steps;

import hooks.DriverHooks;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import questions.IsElementVisible;
import tasks.Login;
import tasks.OpenTheApplication;
import ui.WebLoginPage;
import utils.CsvDataReader;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

import static hooks.DriverHooks.webUser;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class LoginSteps {

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
    }

    @Given("The {string} open the login page")
    public void theOpenTheLoginPage(String userId) throws Exception {
        System.out.println(">>> LOGIN PAGE OPENED <<<");

        Map<String, String> userData = JsonDataReader.getData(SerenityConfigReader.get("data.Transfer"), "happy");
        Map<String, String> data = CsvDataReader.getUserData(userId);
        String useName = data.get("username");
        String passWord = data.get("password");

        webUser.attemptsTo(OpenTheApplication.onHomePage());
        webUser.attemptsTo(Login.withCredentials(useName,passWord));
        webUser.should(seeThat("Username field should NOT be visible"
                ,IsElementVisible.forTarget(WebLoginPage.LOGIN_BUTTON), is(false)));

    }
}
