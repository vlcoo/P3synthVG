import libvgm.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import uibooster.UiBooster;

import java.io.File;

public class Main extends PApplet {
    VGMPlayer player;
    PlayerDisplay playerDisplay;
    UiBooster ui;
    ThemeEngine t;
    PImage logoIcon;
    PFont[] fonts;
    Button currMidPressed;
    ButtonToolbar mediaButtons;
    Button buttonHelp;

    final int TIME = 120;

    public void settings() {
        size(300, 180);
    }

    public void setup() {
        surface.setTitle("vlco_o P3synthVG");

        this.player = new VGMPlayer(44100);
        player.setVolume(0.2);

        playerDisplay = new PlayerDisplay(this, player);
        ui = new UiBooster();
        t = new ThemeEngine();

        setupImages();
        setupFonts();
        setupButtons();
        t.setTheme();
    }

    private void setupButtons() {
        Button b1 = new Button(this, "previous", "Prev");
        Button b4 = new Button(this, "open", "Open");
        Button b2 = new Button(this, "pause", "Pause");
        Button b3 = new Button(this, "next", "Next");
        Button[] buttons1 = {b1, b4, b2, b3};
        mediaButtons = new ButtonToolbar(154, 16, 1.1, 0, buttons1);

        buttonHelp = new Button(this, 0, 0,  "info", "");
    }

    private void setupFonts() {
        fonts = new PFont[6];
        fonts[0] = loadFont("data/fonts/terminus12.vlw");
        fonts[1] = loadFont("data/fonts/terminus14.vlw");
        fonts[2] = loadFont("data/fonts/terminusB14.vlw");
        fonts[3] = loadFont("data/fonts/terminusBI14.vlw");
        fonts[4] = loadFont("data/fonts/robotoBI16.vlw");
        fonts[5] = loadFont("data/fonts/robotoBI64.vlw");
    }

    private void setupImages() {
        logoIcon = loadImage("data/graphics/logo.png");
    }

    public void draw() {
        background(t.theme[2]);

        image(logoIcon, 16, 8);
        playerDisplay.redraw();
        mediaButtons.redraw();
        buttonHelp.redraw();
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
            if (buttonHelp.collided()) currMidPressed = buttonHelp;

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
                fileSelected(ui.showFileSelection("Video Game Music files",
                        "vgm", "nsf", "gbs", "spc", "vgz", "zip", "gz"));
            }

            else if (mediaButtons.collided("pause")) {
                Button me = mediaButtons.getButton("pause");

                try {
                    if (me.pressed) player.play();
                    else player.pause();
                } catch (Exception ignored) {

                }

                if (currMidPressed != null) currMidPressed.setPressed(false); // avoid button stucking when click slide
                me.setPressed(!me.pressed);
                currMidPressed = null;
            }

            else if (buttonHelp.collided()) {
                ui.showWarningDialog("Thanks for using P3synthVG.\n\n" +
                        "" +
                        "Further development of this program has been cancelled,\n" +
                        "so this remains as a proof of concept.\n\n" +
                        "" +
                        "vlcoo.net  |  github.com/vlcoo/P3synthVG", "Message");
            }

            if (currMidPressed != null) {
                currMidPressed.setPressed(false);
                currMidPressed = null;
            }
        }
    }

    String shrinkString(String original) {
        if (original.length() > 68) original = original.substring(0, 68 - 3) + "...";
        return original;
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
                player.customInfoMsg = "Invalid file";
            } catch (Exception e) {
                player.customInfoMsg = "Library error";
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}