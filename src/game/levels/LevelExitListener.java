package game.levels;

import city.cs.engine.*;
import game.Game;
import game.knight.Knight;

public class LevelExitListener implements CollisionListener {
    private Game game;

    public LevelExitListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {

        if (e.getOtherBody() instanceof Knight) { // If the knight touches the door
            game.goToNextLevel();
        }
    }
}