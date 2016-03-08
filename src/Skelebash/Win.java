package Skelebash;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import static Skelebash.Skelebash1.player;
import static Skelebash.Skelebash1.antidote;

public class Win extends BasicGameState {
    private StateBasedGame game;
    public Win(int xSize, int ySize) {
    }
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = game;
    }
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.setColor(Color.white);
        g.drawString("You found the treasure!", 380, 200);
        g.drawString("press 1 to exit", 400, 320);
    }
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }
    public int getID() {
        return 3;
    }
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_1:
                player.health = 100000;
                player.speed = .4f;
                Skelebash1.counter = 0;
                player.x = 96f;
                player.y = 228f;
                antidote.isvisible = true;
                game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                System.exit(1);
                break;
            case Input.KEY_2:
                break;
            case Input.KEY_3:
                break;
            default:
                break;
        }
    }
}
