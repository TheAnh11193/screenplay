package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import ui.WebLoginPage;

public class LoginTCWeb implements Task{
    private final String username;
    private final String password;

    public LoginTCWeb(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Performable withCredentials(String username, String password) {
        return Tasks.instrumented(LoginTCWeb.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(WebLoginPage.link_SSO),
                Enter.theValue(username).into(WebLoginPage.txt_USERNAMETC),
                Enter.theValue(password).into(WebLoginPage.txt_PASSWORDTC),
                Click.on(WebLoginPage.btn_LOGINTC)
        );
    }
}
