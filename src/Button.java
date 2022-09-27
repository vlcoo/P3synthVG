import processing.core.PImage;

public class Button {
    Main window;
    int x, y, width, height;
    String iconFilename, label;
    PImage textureUp;
    PImage textureDown;
    boolean pressed = false;
    boolean showLabel = true;

    Button(Main window, String icon, String label) {
        this.window = window;
        this.iconFilename = icon;
        this.label = label;
        loadTexture();
        setPressed(false);
    }

    Button(Main window, int x, int y, String icon, String label) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.iconFilename = icon;
        this.label = label;
        loadTexture();
        setPressed(false);
    }

    private void loadTexture() {
        textureUp = window.loadImage("data/buttons/" + iconFilename + "Up.png");
        textureDown = window.loadImage("data/buttons/" + iconFilename + "Down.png");
    }

    public void setPressed(boolean how) {
        this.pressed = how;

        this.width = textureUp.width;
        this.height = textureUp.height;
    }

    public void redraw() {
        window.image(pressed ? textureDown : textureUp, x, y);
        window.fill(window.t.theme[0]);
        window.textAlign(window.CENTER);
        window.textFont(window.fonts[0]);
        if (showLabel) window.text(label, x + this.width / 2, y - 2);
    }

    public boolean collided() {
        return (window.mouseX > this.x && window.mouseX < this.width + this.x) &&
                (window.mouseY > this.y && window.mouseY < this.height + this.y);
    }
}
