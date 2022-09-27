import processing.core.PApplet;
import libvgm.VGMPlayer;
import processing.core.PFont;
import processing.core.PImage;

import java.io.File;

public class Main extends PApplet {
    final float VERCODE = 0;

    VGMPlayer player;
    ThemeEngine t;
    PImage logoIcon;
    PFont[] fonts;
    Button currMidPressed;
    ButtonToolbar mediaButtons;
    ButtonToolbar settingButtons;

    final int TIME = 50;
    private String log = "start";

    public void settings() {
        size(724, 430);
    }

    public void setup() {
        surface.setTitle("vlco_o P3synthVG");

        t = new ThemeEngine();

        setupImages();
        setupFonts();
        setupButtons();
        t.setTheme("Fresh Blue");

        this.player = new VGMPlayer(44100);
        player.setVolume(0.5);
    }

    private void setupButtons() {
        Button b1 = new Button(this, "previous", "Prev");
        Button b4 = new Button(this, "open", "Open");
        Button b2 = new Button(this, "pause", "Pause");
        Button b3 = new Button(this, "next", "Next");
        Button[] buttons1 = {b1, b4, b2, b3};
        mediaButtons = new ButtonToolbar(150, 16, 1.3, 0, buttons1);

        b1 = new Button(this, "info", "Help");
        b2 = new Button(this, "confTheme", "Theme");
        b3 = new Button(this, "update", "Update");
        Button[] buttons2 = {b2, b1, b3};
        settingButtons = new ButtonToolbar(464, 16, 1.3, 0, buttons2);
    }

    private void setupFonts() {
        fonts = new PFont[6];
        fonts[0] = loadFont("data/fonts/terminus12.vlw");
        fonts[1] = loadFont("data/fonts/terminus14.vlw");
        fonts[2] = loadFont("data/fonts/terminusB14.vlw");
        fonts[3] = loadFont("data/fonts/terminusBI14.vlw");
        fonts[4] = loadFont("data/fonts/robotoBI16.vlw");
        fonts[5] = loadFont("data/fonts/robotoBI70.vlw");
    }

    private void setupImages() {
        logoIcon = loadImage("data/graphics/logo.png");
    }

    public void draw() {
        background(t.theme[2]);
        fill(t.theme[0]);

        image(logoIcon, 310, 10);
        mediaButtons.redraw();
        settingButtons.redraw();

        textAlign(LEFT);
        if (!player.isPlaying()) {
            textFont(fonts[2]);
            text(log, 24, 24);
            return;
        }

        textFont(fonts[2]);
        text(String.valueOf(player.getCurrentTrack()), 24, 48);
        textFont(fonts[1]);
        text("/ " + (player.getTrackCount() - 1), 96, 48);
        text(String.valueOf(player.getCurrentTime()), 24, 72);
        text(String.valueOf(player.getPlaybackRateFactor()), 24, 96);

        textAlign(RIGHT);
        textFont(fonts[5]);
        fill(t.theme[0], 100);
        text(player.getEmuName(), 724, 48);
    }

    public void keyPressed() {
        if (keyCode == UP) {
            player.setPlaybackRateFactor((float) (player.getPlaybackRateFactor() + 0.1));
        }
        else if (keyCode == DOWN) {
            player.setPlaybackRateFactor((float) (player.getPlaybackRateFactor() - 0.1));
        }
    }

    public void mousePressed() {
        // messy... but it works
        if (mouseButton == LEFT) {
            for (Button b : mediaButtons.buttons.values()) {
                if (b.iconFilename.equals("pause")) continue;
                if (b.collided()) currMidPressed = b;
            }
            for (Button b : settingButtons.buttons.values()) {
                if (b.collided()) currMidPressed = b;
            }

            if (currMidPressed != null) currMidPressed.setPressed(true);
        }
    }

    public void mouseReleased() {
        if (mouseButton == LEFT) {
            if (player.isPlaying()) {
                if (mediaButtons.collided("previous")) {
                    try {
                        player.startTrack(
                                constrain(player.getCurrentTrack() - 1, 0, player.getTrackCount()), TIME);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                else if (mediaButtons.collided("next")) {
                    try {
                        player.startTrack(
                                constrain(player.getCurrentTrack() + 1, 0, player.getTrackCount()), TIME);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (mediaButtons.collided("open")) {
                selectInput("Open file", "fileSelected");
            }

            else if (mediaButtons.collided("pause")) {
                Button me = mediaButtons.getButton("pause");

                try {
                    if (me.pressed) player.play();
                    else player.pause();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                me.setPressed(!me.pressed);
                currMidPressed = null;
            }

            if (currMidPressed != null) {
                currMidPressed.setPressed(false);
                currMidPressed = null;
            }
        }
    }

    public void fileSelected(File sel) {
        if (sel == null) {
            println("None file");
        } else {
            try {
                player.loadFile(sel.getAbsolutePath());
                player.startTrack(0, TIME);

                mediaButtons.getButton("pause").setPressed(false);
            }
            catch (IllegalArgumentException iae) {
                this.log = "* file err...!";
            } catch (Exception e) {
                this.log = "* lib err...!";
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}