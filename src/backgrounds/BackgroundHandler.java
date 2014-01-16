package backgrounds;


import entities.Balloon;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import state.MainGame;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: user360
 * Date: 16/01/14
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class BackgroundHandler {

    private ArrayList<Background> renderlist, bglist;
    private String name;
    int count = 1;
    private boolean collider = false;

    public BackgroundHandler(String name, Background firstbackground) {
        renderlist = new ArrayList<Background>();
        bglist = new ArrayList<Background>();
        renderlist.add(firstbackground);
        bglist.add(firstbackground);
        this.name = name;
    }

    public void add(Background bg) {
        bglist.add(bg);
    }

    public void printStats(Graphics g, int x, int y, Balloon balloon) {
        g.drawString(this.name, x, y);
        g.drawString("bglist: " + bglist.size(), x, y+20);
        g.drawString("renderlist: " + renderlist.size(), x, y+40);
        g.drawString("PosX(0): " + renderlist.get(0).getX(), x, y+60);
        g.drawString("count: " + count, x, y+80);
        renderlist.get(0).printStats(name, renderlist.get(0).getX(), renderlist.get(0).getY(), g, balloon, 400, (name.equals("background")) ? 400 : 500);
        collider = renderlist.get(0).isColliding();
    }

    public void render() {
        for(int i = 0; i<renderlist.size(); i++) {
            renderlist.get(i).render();
        }


    }

    public void update(float moveX, float moveY, Balloon balloon) {

        renderlist.get(0).move(moveX, moveY);

        /*handles the images, loads the next one when needed and removes the previous when not needed*/
        if(renderlist.get(0).getX() == -2500.0) {
            renderlist.add(bglist.get(count));
            renderlist.get(1).resetToEnd();
            count++;
        }
        if(renderlist.get(0).getX() < -2500.0) {
            renderlist.get(1).move(moveX, moveY);
        }
        if(renderlist.get(0).getX() == -4000.0) {
            renderlist.remove(0);

        }
        if(count == bglist.size()) {
            count = 0;
        }
        if(collider && name.equals("frontground")) balloon.reset(MainGame.SCREEN_WIDTH / 4.0f, MainGame.SCREEN_HEIGHT / 2.0f);
    }



}