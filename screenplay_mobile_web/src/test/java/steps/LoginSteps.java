package steps;

import io.cucumber.java.en.Given;
import tasks.Login;
import tasks.OpenTheApplication;
import utils.CsvDataLoader;

import java.util.Map;

import static hooks.DriverHooks.webUser;

public class LoginSteps {

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
    }

    @Given("The {string} open the login page")
    public void theOpenTheLoginPage(String userId) {
        System.out.println(">>> LOGIN PAGE OPENED <<<");
        Map<String, String> data = CsvDataLoader.getUserData(userId);
        String useName = data.get("username");
        String passWord = data.get("password");

        webUser.attemptsTo(OpenTheApplication.onHomePage());
        webUser.attemptsTo(Login.withCredentials(useName,passWord));
    }
}
