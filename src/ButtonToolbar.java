import processing.core.PApplet;

import java.util.HashMap;

public class ButtonToolbar extends PApplet {
    int x, y;
    double xSep, ySep;
    HashMap<String, Button> buttons = new HashMap<>();

    ButtonToolbar(int x, int y, double xSep, double ySep, Button[] buttons) {
        this.xSep = xSep;
        this.ySep = ySep;
        this.x = x;
        this.y = y;

        int i = 0;
        for (Button b : buttons) {
            if (b == null) continue;
            this.buttons.put(b.iconFilename, b);
            b.x = (int) (i * (b.width * this.xSep) + x);
            b.y = (int) (i * (b.height * this.ySep) + y);
            i++;
        }
    }

    public Button getButton(String filename) {
        return this.buttons.get(filename);
    }

    public void redraw() {
        for (Button b: buttons.values()) b.redraw();
    }

    boolean collided(String bName) {
        Button b = this.buttons.get(bName);
        if (b == null) return false;
        return b.collided();
    }
}
