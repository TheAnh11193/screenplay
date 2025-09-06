package tasks;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.model.environment.SystemEnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actions.Open.url;

public class OpenTheApplication implements Task {
    String baseUrl = EnvironmentSpecificConfiguration.from(
            SystemEnvironmentVariables.createEnvironmentVariables()
    ).getProperty("webdriver.base.url");

    public static OpenTheApplication onHomePage() {
        return instrumented(OpenTheApplication.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                url(baseUrl)   // 👉 this uses webdriver.base.url from serenity.conf
        );
    }
}
