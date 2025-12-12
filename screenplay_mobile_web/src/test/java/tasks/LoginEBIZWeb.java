package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import ui.WebEbizLoginPage;

public class LoginEBIZWeb implements Task{
    private final String username;
    private final String password;

    public LoginEBIZWeb(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Performable withCredentials(String username, String password) {
        return Tasks.instrumented(LoginEBIZWeb.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(WebEbizLoginPage.txtbx_USERNAME_EBIZ),
                Click.on(WebEbizLoginPage.txtbx_PASSWORD_EBIZ),
                Enter.theValue(password).into(WebEbizLoginPage.txtbx_PASSWORD_EBIZ),
                Click.on(WebEbizLoginPage.btn_DANGNHAP)
        );
    }
}
