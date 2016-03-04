package Skelebash;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player {
    public float x = 96f; 
    public float y = 228f;
    public int health = 100000, timeAttack = 0, healthTime = 100000, killCounter = 0;
    public float speed = .4f;
    float hitboxX = x + 8f;
    float hitboxY = y + 8f;
    private int startX, startY, width = 30, height = 42;
    public Shape rect = new Rectangle(getPlayershitboxX(),
            getPlayershitboxY(), width, height);
    public float pdelta;
    public Animation Playeranime, up, down, left, right, sprite, wait
            , attackUp, attackDown, attackLeft, attackRight;
    private int direction; 
    public ArrayList<String> inventory = new ArrayList<>();
       
    public Player() throws SlickException {
        SpriteSheet runningSS = new SpriteSheet(
                "res/character_spritesheet.png", 64, 64, 0);
        up = new Animation();
        up.setAutoUpdate(true);
        up.addFrame(runningSS.getSprite(0, 8), 330);
        up.addFrame(runningSS.getSprite(1, 8), 330);
        up.addFrame(runningSS.getSprite(2, 8), 330);
        up.addFrame(runningSS.getSprite(3, 8), 330);
        up.addFrame(runningSS.getSprite(4, 8), 330);
        up.addFrame(runningSS.getSprite(5, 8), 330);
        up.addFrame(runningSS.getSprite(6, 8), 330);
        up.addFrame(runningSS.getSprite(7, 8), 330);
        up.addFrame(runningSS.getSprite(8, 8), 330);
        
        attackUp = new Animation();
        attackUp.setAutoUpdate(true);
        attackUp.addFrame(runningSS.getSprite(0, 4), 100);
        attackUp.addFrame(runningSS.getSprite(1, 4), 100);
        attackUp.addFrame(runningSS.getSprite(2, 4), 100);
        attackUp.addFrame(runningSS.getSprite(3, 4), 100);
        attackUp.addFrame(runningSS.getSprite(4, 4), 100);
        attackUp.addFrame(runningSS.getSprite(5, 4), 100);
        attackUp.addFrame(runningSS.getSprite(6, 4), 100);
        attackUp.addFrame(runningSS.getSprite(7, 4), 100);
        

        down = new Animation();
        down.setAutoUpdate(false);
        down.addFrame(runningSS.getSprite(0, 10), 330);
        down.addFrame(runningSS.getSprite(1, 10), 330);
        down.addFrame(runningSS.getSprite(2, 10), 330);
        down.addFrame(runningSS.getSprite(3, 10), 330);
        down.addFrame(runningSS.getSprite(4, 10), 330);
        down.addFrame(runningSS.getSprite(5, 10), 330);
        down.addFrame(runningSS.getSprite(6, 10), 330);
        down.addFrame(runningSS.getSprite(7, 10), 330);
        down.addFrame(runningSS.getSprite(8, 10), 330);
        
        attackDown = new Animation();
        attackDown.setAutoUpdate(true);
        attackDown.addFrame(runningSS.getSprite(0, 6), 100);
        attackDown.addFrame(runningSS.getSprite(1, 6), 100);
        attackDown.addFrame(runningSS.getSprite(2, 6), 100);
        attackDown.addFrame(runningSS.getSprite(3, 6), 100);
        attackDown.addFrame(runningSS.getSprite(4, 6), 100);
        attackDown.addFrame(runningSS.getSprite(5, 6), 100);
        attackDown.addFrame(runningSS.getSprite(6, 6), 100);
        attackDown.addFrame(runningSS.getSprite(7, 6), 100);

        left = new Animation();
        left.setAutoUpdate(false);
        left.addFrame(runningSS.getSprite(0, 9), 330);
        left.addFrame(runningSS.getSprite(1, 9), 330);
        left.addFrame(runningSS.getSprite(2, 9), 330);
        left.addFrame(runningSS.getSprite(3, 9), 330);
        left.addFrame(runningSS.getSprite(4, 9), 330);
        left.addFrame(runningSS.getSprite(5, 9), 330);
        left.addFrame(runningSS.getSprite(6, 9), 330);
        left.addFrame(runningSS.getSprite(7, 9), 330);
        left.addFrame(runningSS.getSprite(8, 9), 330);

        attackLeft = new Animation();
        attackLeft.setAutoUpdate(true);
        attackLeft.addFrame(runningSS.getSprite(0, 5), 100);
        attackLeft.addFrame(runningSS.getSprite(1, 5), 100);
        attackLeft.addFrame(runningSS.getSprite(2, 5), 100);
        attackLeft.addFrame(runningSS.getSprite(3, 5), 100);
        attackLeft.addFrame(runningSS.getSprite(4, 5), 100);
        attackLeft.addFrame(runningSS.getSprite(5, 5), 100);
        attackLeft.addFrame(runningSS.getSprite(6, 5), 100);
        attackLeft.addFrame(runningSS.getSprite(7, 5), 100);
        
        right = new Animation();
        right.setAutoUpdate(false);
        right.addFrame(runningSS.getSprite(0, 11), 330);
        right.addFrame(runningSS.getSprite(1, 11), 330);
        right.addFrame(runningSS.getSprite(2, 11), 330);
        right.addFrame(runningSS.getSprite(3, 11), 330);
        right.addFrame(runningSS.getSprite(4, 11), 330);
        right.addFrame(runningSS.getSprite(5, 11), 330);
        right.addFrame(runningSS.getSprite(6, 11), 330);
        right.addFrame(runningSS.getSprite(7, 11), 330);
        right.addFrame(runningSS.getSprite(8, 11), 330);

        attackRight = new Animation();
        attackRight.setAutoUpdate(true);
        attackRight.addFrame(runningSS.getSprite(0, 7), 100);
        attackRight.addFrame(runningSS.getSprite(1, 7), 100);
        attackRight.addFrame(runningSS.getSprite(2, 7), 100);
        attackRight.addFrame(runningSS.getSprite(3, 7), 100);
        attackRight.addFrame(runningSS.getSprite(4, 7), 100);
        attackRight.addFrame(runningSS.getSprite(5, 7), 100);
        attackRight.addFrame(runningSS.getSprite(6, 7), 100);
        attackRight.addFrame(runningSS.getSprite(7, 7), 100);
        
        wait = new Animation();
        wait.setAutoUpdate(true);
        wait.addFrame(runningSS.getSprite(0, 14), 733);
        wait.addFrame(runningSS.getSprite(1, 14), 733);
        wait.addFrame(runningSS.getSprite(2, 14), 733);
        wait.addFrame(runningSS.getSprite(3, 14), 733);
        sprite = wait;

    }
    
    public void setpdelta(float somenum) {
        pdelta = somenum;
    }
    public float getpdelta() {
        return pdelta;
    }
    public float getPlayersX() {
        return x;
    }
    public float getPlayersY() {
        return y;
    }
    public float getPlayershitboxX() {
        return x + 18f;
    }
    public float getPlayershitboxY() {
        return y + 18f;
    }
    public void setPlayershitboxX() {
        hitboxX = getPlayershitboxX();
    }
    public void setPlayershitboxY() {
        hitboxY = getPlayershitboxY();
    }
    public void setDirection(int i){
        this.direction = i;
    }
    public int getDirection(){
        return this.direction;
    }
    public void settimeAttack(int t){
        this.timeAttack = t;
    }
    
    public int gettimeAttack(){
        
        return this.timeAttack;
    }
    
    public void countdown(){
        this.timeAttack--;
    }
    public void loseHealth(){
        this.health-=1000;
    }
    public void healthTime(){
        this.healthTime-=1;
    }
    public int getHealthTime(){
        return healthTime;
    }
    public void killCounter() {
        this.killCounter += 1;
    }
    public int getkillCounter() {
        return killCounter;
    }
    public void playerInventory(int i) {
        if (i == 0) {
            inventory.add("Skull");
        }else if(i == 1){
            inventory.add("Rib");
        }else if(i == 2){
            inventory.add("Femur");
        }
    }
    public ArrayList<String> getInv(){
        return inventory ;
    }
}
