package game;

/**
 * Created with IntelliJ IDEA.
 * User: user360
 * Date: 23/01/14
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import util.GameFont;
import util.ScoreBoard;

import java.awt.*;

/**
 * Game Properties class
 */
public class Game {

    public static boolean music = true;
    /**
     * Game Screen Width
     */
    public static final int SCREEN_WIDTH = 1280;

    /**
     * Game Screen Height
     */
    public static final int SCREEN_HEIGHT = 800;
    /**
     * Game Title
     */
    public static final String TITLE = "Balloonatic";

    /**
     * IS VSYNC ENABLE?
     */
    public static final boolean VSYNC = true;

    public static GameFont titleFont = null;

    public static TrueTypeFont scoreFont = null;

    public static GameFont fieldFont = null;

    public static GameFont highscoreFont;

    public static ScoreBoard SBoard = new ScoreBoard();

    static {
        if (titleFont == null) {
            try {
                titleFont = new GameFont("data/fonts/Bauhaus93.fnt",
                        "data/fonts/Bauhaus93_0.png");
            } catch (SlickException e) {
                System.out.println("Unable to load title font");
            }
        }

        if (scoreFont == null) {
            scoreFont = new TrueTypeFont(new java.awt.Font("Lucida Sans Typewriter", Font.BOLD, 28), true);
        }

        if (fieldFont == null) {
            try {
                fieldFont = new GameFont("data/fonts/AbadiMTCondensed.fnt", "data/fonts/AbadiMTCondensed.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

        if (highscoreFont == null) {
            try {
                highscoreFont = new GameFont("data/fonts/Corbel16.fnt", "data/fonts/Corbel16.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Debugging Infromation
     */
    public static class DEBUG {

        /**
         * s
         * Is FPS String show on screen?
         */
        public static boolean SHOW_FPS = false;

    }

    public static class STATE {
        public static final int STORY = 6;
        public static final int SETTINGS = 5;
        public static final int ENTERNAME = 3;
        public static final int HISCORE = 2;
        public static final int MAIN = 1;
        public static final int MENU = 0;
    }

    public static class AUDIO {

        public static Sound SHIELD_POP;

        static {
            try {
                SHIELD_POP = new Sound("data/sound/effects/pop.wav");
            } catch (SlickException e) {}
        }

    }

}
