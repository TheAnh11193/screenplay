package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RgbaToHex {

    private static final Pattern RGBA_PATTERN = Pattern.compile(
            "rgba?\\s*\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})(?:\\s*,\\s*([0-9.]+))?\\s*\\)"
    );

    /**
     * Convert an rgba(...) or rgb(...) string to HEX (#RRGGBB).
     * Example: "rgba(243, 178, 178, 1)" -> "#F3B2B2"
     */
    public static String convert(String rgba) {
        if (rgba == null) {
            throw new IllegalArgumentException("RGBA string is null");
        }

        Matcher m = RGBA_PATTERN.matcher(rgba);
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid RGBA format: " + rgba);
        }

        int r = Integer.parseInt(m.group(1));
        int g = Integer.parseInt(m.group(2));
        int b = Integer.parseInt(m.group(3));

        // Clamp values to [0,255] just in case
        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));

        return String.format("#%02X%02X%02X", r, g, b);
    }

    private RgbaToHex() {
        // prevent instantiation
    }
}
