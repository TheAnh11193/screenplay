package questions;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.Assert;
import ui.OdooChamCongPage;
import utils.RgbaToHex;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class VerifyWeekDayColors implements Question<Void>{
    private static final String EXPECTED_WEEKEND_COLOR = "#f3b2b2";
    private static final String EXPECTED_WEEKDAY_COLOR = "#71959e";

    @Override
    public Void answeredBy(Actor actor) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.minusDays(2);
        LocalDate firstDay = today.withDayOfMonth(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<String> errors = new ArrayList<>();

        // Use Target (recommended) or inline locator
        List<WebElementFacade> days = OdooChamCongPage.list_DAYOFMONTHS.resolveAllFor(actor);

        for (WebElementFacade day : days) {
            String dateStr = day.getAttribute("data-date").substring(0, 10);
            LocalDate date = LocalDate.parse(dateStr, formatter);

            if (!date.isBefore(firstDay) && !date.isAfter(endDate)) {
                List<WebElementFacade> colors = day.thenFindAll(OdooChamCongPage.list_COLORDAYOFMONTHS.getCssOrXPathSelector());

                if (!colors.isEmpty()) {
                    for (WebElementFacade color : colors) {
                        String rgba = color.getCssValue("background-color");
                        String hex = RgbaToHex.convert(rgba);
                        DayOfWeek dow = date.getDayOfWeek();

                        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
                            if (!hex.equalsIgnoreCase(EXPECTED_WEEKEND_COLOR)) {
                                errors.add("❌ Weekend " + date + " (" + dow + ") wrong color: " + hex);
                            }
                        } else {
                            if (!hex.equalsIgnoreCase(EXPECTED_WEEKDAY_COLOR)) {
                                errors.add("❌ Weekday " + date + " (" + dow + ") wrong color: " + hex);
                            }
                        }
                    }
                }
            }
        }
        if (!errors.isEmpty()) {
            Assert.fail("Color validation failed:\n" + String.join("\n", errors));
        }
        return null;
    }

    public static VerifyWeekDayColors areCorrect() {
        return new VerifyWeekDayColors();
    }
}
