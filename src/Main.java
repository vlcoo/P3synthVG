import processing.core.PApplet;
import libvgm.VGMPlayer;

import java.io.File;

public class Main extends PApplet {
    private VGMPlayer player;
    final int TIME = 50;
    private String log = "start";

    public void settings() {
        size(300, 180);
    }

    public void setup() {
        surface.setTitle("vlco_o P3SynthVG");

        this.player = new VGMPlayer(44100);
        player.setVolume(0.5);
    }

    public void draw() {
        background(0);

        if (!player.isPlaying()) {
            text(log, 24, 24);
            return;
        }

        textSize(48);
        text(String.valueOf(player.getCurrentTrack()), 24, 48);
        textSize(24);
        text("/ " + (player.getTrackCount() - 1), 96, 48);
        text(String.valueOf(player.getCurrentTime()), 24, 72);
        textSize(12);
        text("click = load file; arrows = prev/next track", 24, 128);
    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            try {
                player.startTrack(constrain(player.getCurrentTrack() - 1, 0, player.getTrackCount()), TIME);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if (keyCode == RIGHT) {
            try {
                player.startTrack(constrain(player.getCurrentTrack() + 1, 0, player.getTrackCount() - 1), TIME);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void mouseClicked() {
        selectInput("Open file", "fileSelected");
    }

    public void fileSelected(File sel) {
        if (sel == null) {
            println("None file");
        } else {
            try {
                player.loadFile(sel.getAbsolutePath());
                player.startTrack(0, TIME);
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