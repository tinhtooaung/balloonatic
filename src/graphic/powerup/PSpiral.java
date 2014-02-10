package graphic.powerup;

import graphic.Balloon;
import graphic.BalloonEffect;
import org.newdawn.slick.*;
import org.newdawn.slick.Sound;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: user360
 * Date: 3/02/14
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class PSpiral extends Powerup implements BalloonEffect{

    private Animation bird;
    private Image[] birdimages;

    private Sound death, birdsound;
    private Image flashImage;

    private int effectInterval;
    private int counter;
    private boolean up;
    Random random = new Random();


    public PSpiral() throws SlickException {
        super("data/image/haunter.png");
        birdimages = new Image[]{ new Image("data/image/haunter.png"), new Image("data/image/haunter.png") };
        bird = new Animation(birdimages,50);
        death = new Sound("data/sound/effects/gameover1.wav");
        birdsound = new Sound("data/sound/effects/bird_screech.ogg");
        flashImage = new Image("data/image/balloon.png");
        this.effectInterval = 300;
        up = random.nextBoolean();
    }

    @Override
    public void onCollideWithBalloon(Balloon balloon) {
       // balloon.addBalloonEffect(this, "bird");
        sceneHandler.removeSceneObject(this);
        //balloon.setBirdCounter(1);
        if(!balloon.isLockLife()) {
            balloon.setFuel(balloon.getFuel()-100 < 0 ? 0 : balloon.getFuel() - 100);
            balloon.setFuelState(2);
            death.play(0.5f, 0.3f);
            //birdsound.play(0.8f, 0.1f);
        }
    }
    @Override
    public void move(int delta) {
        if(up) {
            y += delta/1000 - 5;
        } else {
            y += delta/1000 + 5;
        }

        if(y>400) {
            up = true;
        }
        else if(y<100) {
            up = false;
        }

        x += delta/1000 - 8;
    }

    @Override
    public void render(GameContainer gc, Graphics graphics){
        bird.draw(x - 20, y - 20);
    }

    @Override
    public void drawOnBalloon(Balloon balloon, Graphics graphics) {
//        balloon.setlockFuel(true);
//        balloon.setRenderLock(true);
//        flashImage.drawFlash(balloon.getX() - balloon.getImage().getWidth()/2, balloon.getY() - balloon.getImage().getHeight()/2);
//        counter++;
//        if(counter == effectInterval){
//            balloon.removeBalloonEffect("bird");
//            balloon.setlockFuel(false);
//            balloon.setRenderLock(false);
//        }

    }

    @Override
    public boolean isDrawnOnFront() {
        return false;
    }
}
