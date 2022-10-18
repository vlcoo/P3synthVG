import java.awt.*;
import java.util.HashMap;

public class ThemeEngine {
    HashMap<String, int[]> availableThemes = new HashMap<>();
    int[] theme;

    ThemeEngine() {
        loadThemes();
    }

    void setTheme() {
        theme = availableThemes.get("Fresh Blue");
    }

    private void loadThemes() {
        // theme is an array of ints (colors in hex) in order: darker, dark, neutral, light, lightest
        int[] theme_01 = {Color.decode("#000000").getRGB(), Color.decode("#5151cf").getRGB(),
                Color.decode("#809fff").getRGB(), Color.decode("#bfcfff").getRGB(),
                Color.decode("#ffffff").getRGB()};
        availableThemes.put("Fresh Blue", theme_01);
    }
}
