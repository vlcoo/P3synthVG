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
        int[] theme_01 = {0x000000, 0x5151cf, 0x809fff, 0xbfcfff, 0xffffff};
        availableThemes.put("Fresh Blue", theme_01);
        int[] theme_03 = {0x000000, 0xd50000, 0xff5131, 0xff867c, 0xffffff};
        availableThemes.put("Hot Red", theme_03);
        int[] theme_04 = {0x000000, 0x00b248, 0x00e676, 0x66ffa6, 0xffffff};
        availableThemes.put("Crisp Green", theme_04);
        int[] theme_06 = {0x000000, 0xc6a700, 0xfdd835, 0xffff6b, 0xffffff};
        availableThemes.put("Summer Yellow", theme_06);
        int[] theme_02 = {0x5c1f09, 0xb2593f, 0xdf825f, 0xffd2a2, 0xffffff};
        availableThemes.put("GX Peach", theme_02);
        int[] theme_05 = {0xffffff, 0x000000, 0x000000, 0xffffff, 0xffffff};
        availableThemes.put("Black & White", theme_05);
    }
}
