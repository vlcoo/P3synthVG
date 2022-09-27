import java.awt.*;
import java.util.HashMap;

public class ThemeEngine {
    HashMap<String, int[]> availableThemes = new HashMap<String, int[]>();
    int[] theme;

    ThemeEngine() {
        loadThemes();
    }

    void setTheme(String themeName) {
        theme = availableThemes.get(themeName);
        if (theme == null) {
            theme = availableThemes.get("Fresh Blue");
        }
    }

    private void loadThemes() {
        // theme is an array of ints (colors in hex) in order: darker, dark, neutral, light, lightest
        int[] theme_01 = {Color.decode("#000000").getRGB(), Color.decode("#5151cf").getRGB(),
                Color.decode("#809fff").getRGB(), Color.decode("#bfcfff").getRGB(),
                Color.decode("#ffffff").getRGB()};
        availableThemes.put("Fresh Blue", theme_01);
        int[] theme_03 = {Color.decode("#000000").getRGB(), Color.decode("#d50000").getRGB(),
                Color.decode("#ff5131").getRGB(), Color.decode("#ff867c").getRGB(),
                Color.decode("#ffffff").getRGB()};
                availableThemes.put("Hot Red", theme_03);
        int[] theme_04 = {Color.decode("#000000").getRGB(), Color.decode("#00b248").getRGB(),
                Color.decode("#00e676").getRGB(), Color.decode("#66ffa6").getRGB(),
                Color.decode("#ffffff").getRGB()};
                availableThemes.put("Crisp Green", theme_04);
        int[] theme_06 = {Color.decode("#000000").getRGB(), Color.decode("#c6a700").getRGB(),
                Color.decode("#fdd835").getRGB(), Color.decode("#ffff6b").getRGB(),
                Color.decode("#ffffff").getRGB()};
                availableThemes.put("Summer Yellow", theme_06);
        int[] theme_02 = {Color.decode("#5c1f09").getRGB(), Color.decode("#b2593f").getRGB(),
                Color.decode("#df825f").getRGB(), Color.decode("#ffd2a2").getRGB(),
                Color.decode("#ffffff").getRGB()};
                availableThemes.put("GX Peach", theme_02);
        int[] theme_05 = {Color.decode("#ffffff").getRGB(), Color.decode("#000000").getRGB(),
                Color.decode("#000000").getRGB(), Color.decode("#ffffff").getRGB(),
                Color.decode("#ffffff").getRGB()};
                availableThemes.put("Black & White", theme_05);
    }
    }
