package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class IsElementVisible implements Question<Boolean> {

    private final Target target;

    private IsElementVisible(Target target) {
        this.target = target;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // Serenity handles "not present" as false automatically
        return target.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> forTarget(Target target) {
        return new IsElementVisible(target);
    }
}
