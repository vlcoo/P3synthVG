import libvgm.VGMPlayer;
import processing.core.PConstants;

public class PlayerDisplay {
    Main window;
    VGMPlayer player;

    final int POS_X_POSBAR = 50;
    final int POS_Y_POSBAR = 384;
    final int WIDTH_POSBAR = 308;
    final int HEIGHT_POSBAR = 18;

    String labelFilename = "";
    String labelTimestamp = "-:--";
    String labelCurrTrack = "-";
    String labelTrackCount = "-";

    PlayerDisplay(Main parentWindow, VGMPlayer player) {
        window = parentWindow;
        this.player = player;
    }

    private void updateAllValues() {
        if (player.customInfoMsg.equals("")) {
            labelFilename = java.nio.file.Paths.get(player.currFilename)
                    .getFileName().toString().replaceFirst("[.][^.]+$", "");
            // what a mess... but it works
            labelFilename = window.shrinkString(labelFilename, 68);
        }
        else labelFilename = player.customInfoMsg;

        if (player.isPlaying()) {
            int secPos = player.getCurrentTime();
            labelTimestamp = secPos / 60 + ":" + String.format("%02d", secPos % 60);
            labelCurrTrack = String.valueOf(player.getCurrentTrack());
            labelTrackCount = String.valueOf(player.getTrackCount());
        }
        else {
            labelTimestamp = "-:--";
            labelCurrTrack = "-";
            labelTrackCount = "-";
        }
    }

    void redraw(boolean renewValues) {
        if (renewValues) updateAllValues();

        // Filename label
            window.fill(window.t.theme[0]);
            window.textFont(window.fonts[3]);
            window.text(labelFilename, 362, POS_Y_POSBAR - 12);

        // Pos meter
            window.stroke(window.t.theme[0]);
            window.fill(window.t.theme[1]);
            window.rect(POS_X_POSBAR, POS_Y_POSBAR, WIDTH_POSBAR, HEIGHT_POSBAR, 8);

        // Song pos and track labels
            window.textAlign(PConstants.CENTER, PConstants.CENTER);
            window.fill(window.t.theme[4]);
            window.textFont(window.fonts[1]);
            window.text(labelCurrTrack + " of " + labelTrackCount + " · " + labelTimestamp,
                    POS_X_POSBAR + WIDTH_POSBAR/2, POS_Y_POSBAR + 9);
    }
}
