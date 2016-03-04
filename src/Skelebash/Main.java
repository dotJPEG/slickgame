package Skelebash;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Main extends StateBasedGame {

    public static final String gamename = "Skelebash.exe.jpg.png.wav.abc";
    public static final int play = 0;
    public static final int xSize = 1000;
    public static final int ySize = 750;

    public Main(String gamename) {
        super(gamename);
        this.addState(new Menu(xSize, ySize));
        this.addState(new Skelebash1(xSize, ySize));
        this.addState(new lose(xSize, ySize));
        this.addState(new win(xSize, ySize));
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(play).init(gc, this);
        this.enterState(play);
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new Main(gamename));
            appgc.setDisplayMode(xSize, ySize, false);
            int maxFPS = 60;
            appgc.setTargetFrameRate(maxFPS);
            appgc.setTargetFrameRate(60);
            appgc.start();

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
