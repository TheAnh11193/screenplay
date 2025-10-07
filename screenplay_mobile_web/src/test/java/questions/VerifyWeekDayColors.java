package questions;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.Assert;
import org.openqa.selenium.By;
import ui.OdooChamCongPage;
import utils.RgbaToHex;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class VerifyWeekDayColors implements Question<Void>{
    private static final String EXPECTED_WEEKEND_COLOR = "#F3B2B2";
    private static final String EXPECTED_WEEKDAY_COLOR = "#71959E";

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
            String dateStr = day.getAttribute("data-date");
            String dateOnly = dateStr.substring(0, 10);  // take "2025-09-01"
            LocalDate date = LocalDate.parse(dateOnly, formatter);

            if (!date.isBefore(firstDay) && !date.isAfter(endDate)) {
//                List<WebElementFacade> colors = day.thenFindAll(OdooChamCongPage.list_COLORDAYOFMONTHS.getCssOrXPathSelector());
                List<WebElementFacade> colors = day.thenFindAll(
                        By.xpath(".//div[contains(@class,'o_gantt_pill') and contains(@class,'o_gantt_progress')]"));
                if (!colors.isEmpty()) {
                    DayOfWeek dow = date.getDayOfWeek();

                    for (WebElementFacade color : colors) {
                        String rgba = color.getCssValue("background-color");
                        String hex = RgbaToHex.convert(rgba);
//                        DayOfWeek dow = date.getDayOfWeek();
                        System.out.println("==== Checking date: " + date + " (" + dow + ")" + hex + "====");
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
