package actions;

import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import net.serenitybdd.screenplay.Actor;

public class JavaScriptClick implements Interaction {
    private final Target target;

    public JavaScriptClick(Target target) {
        this.target = target;
    }

    public static JavaScriptClick on(Target target) {
        return new JavaScriptClick(target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = target.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript("arguments[0].click();", element);
    }
}
