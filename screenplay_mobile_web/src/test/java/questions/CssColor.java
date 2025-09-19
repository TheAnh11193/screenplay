package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.support.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CssColor implements Question<String>{
    private final Target target;
    private final String cssProperty;

    public CssColor(Target target, String cssProperty) {
        this.target = target;
        this.cssProperty = cssProperty;
    }

    public static CssColor of(Target target, String cssProperty){
        return new CssColor(target, cssProperty);
    }

    public String answeredBy(Actor actor) {
        String cssValue = target.resolveFor(actor).getCssValue(cssProperty);

        if (cssValue == null) {
            return "";
        }

        cssValue = cssValue.trim();
        if (cssValue.equalsIgnoreCase("transparent")
                || cssValue.equalsIgnoreCase("rgba(0, 0, 0, 0)")) {
            return "#00000000";
        }
        try {
            return Color.fromString(cssValue).asHex();
        }catch (IllegalArgumentException e) {
            if (cssValue.startsWith("rgb")) {
                String hex = rgbaToHex(cssValue);
                if (hex != null) return hex;
            }
            return cssValue;
        }
    }
    private String rgbaToHex(String rgba) {
        Pattern p = Pattern.compile("rgba?\\s*\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})(?:\\s*,\\s*([0-9.]+))?\\s*\\)");
        Matcher m = p.matcher(rgba);
        if (!m.find()) return null;
        int r = Integer.parseInt(m.group(1));
        int g = Integer.parseInt(m.group(2));
        int b = Integer.parseInt(m.group(3));
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
