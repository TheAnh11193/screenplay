package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import ui.WebEbizLoginPage;

public class LoginOdooWeb implements Task{
    private final String username;
    private final String password;


    public LoginOdooWeb(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Performable withCredentials(String username, String password) {
        return Tasks.instrumented(LoginOdooWeb.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
//                Enter.theValue(username).into(WebEbizLoginPage.txt_USERNAMEODOO),
//                Enter.theValue(password).into(WebEbizLoginPage.txt_PASSWORDODOO),
//                Click.on(WebEbizLoginPage.btn_LOGINODOO)
        );
    }
}
