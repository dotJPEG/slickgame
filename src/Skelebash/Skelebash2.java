package Skelebash;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Skelebash2 extends BasicGameState {

    public static Player player;
    public static ItemWin antidote;
    public Orb magic8ball, orb1;
    public Enemy jackson;
    public Treasure treasure;
    public ArrayList<Treasure> tresure = new ArrayList();
    public ArrayList<ItemWin> stuffwin = new ArrayList();
    public ArrayList<Enemy> enemiez = new ArrayList();
    private boolean[][] hostiles;
    private static TiledMap grassMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static int counter = 0;
    private static final int SIZE = 16;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;
    static int enemiezCounter = 0;
    static boolean attack = false;
    
    public Skelebash2(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
    Music gamesong = new Music("res/Bit_Quest.ogg");
                gamesong.loop(1.0F, 5.0F);              

        gc.setTargetFrameRate(60);
        gc.setShowFPS(false);
        grassMap = new TiledMap("res/full.tmx");
        camera = new Camera(gc, grassMap);

        player = new Player();
        Blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

                int tileID = grassMap.getTileId(xAxis, yAxis, 1);
                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");
                if ("true".equals(value)) {

                    Blocked.blocked[xAxis][yAxis] = true;
                }
            }
        }

        hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];

//        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
//            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
//                int xBlock = (int) xAxis;
//                int yBlock = (int) yAxis;
//                if (!Blocked.blocked[xBlock][yBlock]) {
//                    if (yBlock % 7 == 0 && xBlock % 15 == 0 && enemiezCounter < 20) {
//                        Enemy e = new Enemy(xAxis * SIZE, yAxis * SIZE);
//                        enemiez.add(e);
//                        enemiezCounter += 1;
//                        hostiles[xAxis][yAxis] = true;
//                    }
//                }
//            }
//        }

        jackson = new Enemy((int) player.x + 142, (int) player.y + 142);
        orb1 = new Orb((int) player.x, (int) player.y);
        treasure = new Treasure(1290,1271);
//        antidote = new ItemWin(1474, 511);
        stuffwin.add(antidote);
        tresure.add(treasure);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        camera.centerOn((int) player.x, (int) player.y);
        camera.drawMap();
        camera.translateGraphics();

        player.sprite.draw((int) player.x, (int) player.y);
        g.drawString("Health: " + player.health / 1000, camera.cameraX + 10,
                camera.cameraY + 10);
        g.drawString("Mana: " + orb1.getAmmo(), camera.cameraX + 10,
                camera.cameraY + 25);
        g.drawString("Score: " + player.getkillCounter(), camera.cameraX + 10,
                camera.cameraY + 40);
        g.drawString("inventory: " + player.getInv(), camera.cameraX + 400, camera.cameraY);
        if (orb1.isIsVisible()) {
            orb1.orbpic.draw(orb1.getX(), orb1.getY());
        }

        for (Enemy e : enemiez) {
            if (e.isVisible) {
                e.currentanime.draw(e.Bx, e.By);
            }
        }
    
        for (Treasure t : tresure){
            if (t.isvisible) {
                t.currentImage.draw(t.x,t.y);
            }
        }
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        counter += delta;
        Input input = gc.getInput();
        float fdelta = delta * player.speed;
        player.setpdelta(fdelta);
        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);
        float projectedright = player.x + fdelta + SIZE;
        boolean cangoright = projectedright < rightlimit;
        if (input.isKeyDown(Input.KEY_UP)) {
            player.setDirection(0);
            player.sprite = player.up;
            float fdsc = (float) (fdelta - (SIZE * .15));
            if (!(isBlocked(player.x, player.y - fdelta) || isBlocked(
                    (float) (player.x + SIZE + 1.5), player.y - fdelta))) {
                player.sprite.update(delta);
                player.y -= fdelta;
            }

        }else if (input.isKeyDown(Input.KEY_DOWN)) {
            player.setDirection(2);
            player.sprite = player.down;
            if (!isBlocked(player.x, player.y + SIZE + fdelta)
                    || !isBlocked(player.x + SIZE - 1, player.y + SIZE + fdelta)) {
                player.sprite.update(delta);
                player.y += fdelta;
            }

        }else if (input.isKeyDown(Input.KEY_LEFT)) {
            player.setDirection(3);
            player.sprite = player.left;
            if (!(isBlocked(player.x - fdelta, player.y) || isBlocked(player.x
                    - fdelta, player.y + SIZE - 1))) {
                player.sprite.update(delta);
                player.x -= fdelta;
            }

        }else if (input.isKeyDown(Input.KEY_RIGHT)) {
            player.setDirection(1);
            player.sprite = player.right;
            if (cangoright
                    && (!(isBlocked(player.x + SIZE + fdelta,
                            player.y) || isBlocked(player.x + SIZE + fdelta, player.y
                            + SIZE - 1)))) {
                player.sprite.update(delta);
                player.x += fdelta;
            } 
        }else if(input.isKeyDown(Input.KEY_A)){
            attack = true;
          Sound clang = new Sound("res/Sword_clash.ogg");
              clang.play(1.0F,5.0F);
            if(player.getDirection()==0){
                player.sprite = player.attackUp;
            }else if(player.getDirection()==2){
                player.sprite = player.attackDown;                
            }else if(player.getDirection()==3){
                player.sprite = player.attackLeft;                
            }else if(player.getDirection()==1){
                player.sprite = player.attackRight;                
            }else{
            player.sprite = player.down;
            }
        }else if (input.isKeyPressed(Input.KEY_SPACE)) {
         if (orb1.isIsVisible() == false) {
            if(orb1.getAmmo()>0){
            orb1.setDirection(player.getDirection());
            orb1.settimeExists(100);
            orb1.setX((int) player.x);
            orb1.setY((int) player.y);
            orb1.hitbox.setX(orb1.getX());
            orb1.hitbox.setY(orb1.getY());
            orb1.setIsVisible(!orb1.isIsVisible());
            orb1.setIsVisible(orb1.isIsVisible());
            Sound blaster = new Sound("res/blaster.ogg");
            blaster.play(.25F,20F);
            orb1.setAmmo();
            }
         }else{
            attack = false;
        }
         
        }else{
            attack = false;
        }
        player.rect.setLocation(player.getPlayershitboxX(),
                player.getPlayershitboxY());

        for (Enemy e : enemiez) {
            if(player.rect.intersects(e.rect) && e.isVisible == true){
             player.healthTime();
                if (player.getHealthTime() % 10 == 0) {
                    player.loseHealth();
                }  
            }
            if (orb1.hitbox.intersects(e.rect) || player.rect.intersects(e.rect) && attack) {
                if(e.isVisible){                 
                    Sound willhelm = new Sound("res/willhelm.ogg");
                willhelm.play(1.0F,5.0F);
                player.killCounter();
                int randy = (int)(Math.random() * ((3 - 0) + 1));
                player.playerInventory(randy);
                    if (randy == 0) {
                        player.killCounter();
                        player.killCounter();
                    }
                }
                e.isVisible = false;
            }
        }
//        for(ItemWin i : stuffwin) {
//            if (player.getkillCounter()>4) {
//                i.isvisible = false;
//            }
//            if (player.rect.intersects(i.hitbox) && i.isvisible) {
//                player.y-=10;
//            }
//        }
        for (Treasure t : tresure) {
            t.isvisible = true;
            if(player.rect.intersects(t.hitbox)){
                sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            }
        }

        if (orb1.isIsVisible()) {
            if (orb1.gettimeExists() > 0) {
                if (orb1.getDirection() == 0) {
                    orb1.setX((int) player.x);
                    orb1.setY(orb1.getY() - 5);
                } else if (orb1.getDirection() == 2) {
                    orb1.setX((int) player.x);
                    orb1.setY(orb1.getY() + 5);
                } else if (orb1.getDirection() == 3) {
                    orb1.setX(orb1.getX() - 5);
                    orb1.setY(orb1.getY());
                } else if (orb1.getDirection() == 1) {
                    orb1.setX(orb1.getX() + 5);
                    orb1.setY(orb1.getY());
                }
                orb1.hitbox.setX(orb1.getX());
                orb1.hitbox.setY(orb1.getY());
                orb1.countdown();
            } else {
                orb1.setIsVisible(false);
            }
        }

        if (player.health <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        for (Enemy e : enemiez) {
            if (e.isVisible) {
                e.move();
            }
        }

    }

    public int getID() {
        return 6;
    }

    public void makevisible() {
      
    }

    private boolean isBlocked(float tx, float ty) {
        int xBlock = (int) tx / SIZE;
        int yBlock = (int) ty / SIZE;
        return Blocked.blocked[xBlock][yBlock];
    }
}