package graphic;

/**
 * Created with IntelliJ IDEA.
 * User: user360
 * Date: 23/01/14
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */

import graphic.powerup.Powerup;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Balloon Sprite, Player of the  game
 */
public class Balloon extends SceneObject{

    private Sound burner = new Sound("data/sound/ambient/Burner.ogg");

    /*speed of balloon*/
    private float loonspeed = 0;

    /*game time*/
    private float gameTime = 0.0f;

    protected int fuel = 1000;

    protected int lives = 3;

    protected float olddistance = -3;

    protected boolean isFlashed = false;

    protected int flashRate;

    private List<Powerup> powerups = new ArrayList<Powerup>();

    private Image flash;

    public Balloon() throws SlickException {
        super("data/image/balloon.png", true);
        flash = image.getScaledCopy(1f);
    }

    public Balloon(float x, float y) throws SlickException {
        super(x, y, "data/image/balloon.png", false);
        flash = image.getScaledCopy(1f);
    }


    /**reset the ballon to coordinates
     *
     * @param x     reset balloon x coordinate
     * @param y     reset balloon y coordinate
     */
    public void reset(float x, float y) {
        this.x = x;
        this.y = y;
        loonspeed = 0;
    }

    /** set the y-speed of the balloon
     *
     * @param speed the desired y-speed
     */
    public void setSpeed(float speed) {
        loonspeed = speed;
    }

    /**get the speed of balloon
     *
     * @return returns a float of the balloons y-speed
     */
    public float getSpeed(){
     return loonspeed;
     }

     /**move the balloon
     *
     * @param offsetX  the amount you want to move the balloons x-coordinate
     * @param offsetY  the amount you want to move the balloons y-coordinate
     */
    public void move(float offsetX, float offsetY){
        x += offsetX;
        y += offsetY;
    }

    /**print the stats of balloon for error checking
     *
     * @param g     Graphics object
     * @param x     x-coordinate you want to print the stats
     * @param y     y-coordinate you want to print the stats
     */
    public void printStats(Graphics g, int x, int y) {
        g.drawString("Balloon X: " + getX(), x, y);
        g.drawString("Balloon Y: " + getY(), x, y+20);
        g.drawString("Speed: " + getSpeed(), x, y+40);

    }

    public boolean isFlashed() {
        return isFlashed;
    }

    public void setFlashed(boolean flashed) {
        isFlashed = flashed;
    }

    public int getFlashRate() {
        return flashRate;
    }

    public void setFlashRate(int flashRate) {
        this.flashRate = flashRate;
    }

    @Override
    public void render(GameContainer gc, Graphics graphics) {
        if(isFlashed && flashRate !=0){
            flash.drawFlash(x - image.getWidth()/2, y - image.getHeight()/2);
            flashRate--;
        }else{
            image.drawCentered(x, y);
        }
    }

    /**update the balloon
     *
     * @param gameContainer     the game container
     * @param delta             delta
     */
    @Override
    public void update(GameContainer gameContainer, int delta){
        float deltaTime = delta / 1000.0f;
        gameTime += deltaTime;
        Input input = gameContainer.getInput();
        updatePlayer(deltaTime, input);
        if(flashRate != 0){

        }
    }

    /**update the player based on inputs
     *
     * @param deltaTime     deltatime (delta / 1000)
     * @param input         Input object
     */
    private void updatePlayer(float deltaTime, Input input)
    {
        if ((input.isKeyDown(Input.KEY_SPACE) || input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) && fuel > 0)
        {
            fuel--;
            setSpeed(getSpeed() - (deltaTime * 200.0f));
            move(0.0f, getSpeed() * deltaTime);
            super.setImage("data/image/balloon2.png");
            if (!burner.playing()){
                burner.loop();
         }
        }
        else
        {
            setSpeed(getSpeed() + (deltaTime * 100.0f));
            move(0.0f, getSpeed() * deltaTime);
            super.setImage("data/image/balloon.png");
            burner.stop();
        }
    }

    /**returns the fuel of the balloon
     *
     * @return     the fuel
     */
    public int getFuel() {
        return fuel;
    }

    /**get the lives of the player / balloon
     *
     * @return  the balloons lives
     */
    public int getLives() {
        return lives;
    }

    public void resetBalloonStats() {
        lives = 3;
        fuel = 1000;
        x = 280;
        y = 200;
        burner.stop();
    }

    public void stopBurner(){
        burner.stop();
    }

    @Override
    public void move(int delta) {}



    @Override
    public boolean isReadyForDisposal() {
        return (x < 0);
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setLives(int lives) {
        if(flashRate == 0){
            this.lives = lives;
        }
    }
}

