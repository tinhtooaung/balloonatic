package graphic.powerup;

import graphic.Balloon;
import graphic.BalloonEffect;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Created with IntelliJ IDEA.
 * User: user360
 * Date: 3/02/14
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class PGoldFuel extends Powerup implements BalloonEffect {

    private Sound pickup;

    public PGoldFuel() throws SlickException {
        super("data/image/goldfuel.png");
        pickup = new Sound("data/sound/effects/fuel.ogg");

    }

    public PGoldFuel(String imagePath) throws SlickException {
        super(imagePath);
    }

    public PGoldFuel(float x, float y, String imagePath) throws SlickException {
        super(x, y, imagePath);
    }


    @Override
    public void onCollideWithBalloon(Balloon balloon) {
        sceneHandler.removeSceneObject(this);
        balloon.setFuel(1000);
        balloon.setFuelState(1);
        pickup.play();
    }

    @Override
    public void drawOnBalloon(Balloon balloon, Graphics graphics) {
    }

    @Override
    public boolean isDrawnOnFront() {
        return false;
    }
}
