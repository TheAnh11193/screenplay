package tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import utils.RgbaToHex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import org.junit.Assert;

public class VerifyDateColors implements Task {

    private final String date;
    private final Set<String> expectedColors;

    public VerifyDateColors(String date, Set<String> expectedColors) {
        this.date = date;
        this.expectedColors = expectedColors;
    }

    public static VerifyDateColors forDateWithColors(String date, String... colors) {
        Set<String> expected = new HashSet<>();
        for (String c : colors) {
            expected.add(c.toLowerCase());
        }
        return instrumented(VerifyDateColors.class, date, expected);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Locate the cell for the given date
        Target DATE_CELL = Target.the("date cell" + date)
                .located(By.xpath("//td[@data-date='" + date + "']"));

        // Find all events inside that date cell
        List<WebElementFacade> events = DATE_CELL.resolveFor(actor)
                .thenFindAll(By.cssSelector("a.fc-event"));

        Set<String> actualColors = new HashSet<>();

        for (WebElementFacade event : events) {
            String rgba = event.getCssValue("background-color");
            String hex = RgbaToHex.convert(rgba).toLowerCase();
            System.out.println("Event color on " + date + ": " + hex);
            actualColors.add(hex);
        }

        // Store colors into Serenity session
        Serenity.setSessionVariable("DATE_COLORS_" + date).to(actualColors);

        // Verify expected ⊆ actual
        Assert.assertTrue(
                "Colors on date " + date + " should contain: " + expectedColors,
                actualColors.containsAll(expectedColors)
        );
    }
}
