package cz.cvut.fit.niadp.mvcgame.model;


import cz.cvut.fit.niadp.mvcgame.adapter.PositionToMouseClickAdapter;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import javafx.scene.input.MouseEvent;
import mockit.Tested;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GameModelEnemySpawnTest {

    @Tested
    private GameModel gameModel;

    @Test
    public void testSpawnEnemy() {
        simulateEnemySpawn();

        List<AbsEnemy> enemies = gameModel.getEnemies();
        Assert.assertEquals(1, enemies.size());

    }


    private void simulateEnemySpawn() {
        Position position = new Position(100, 200);

        PositionToMouseClickAdapter adapter = new PositionToMouseClickAdapter(position);
        MouseEvent mockMouseEvent = adapter.toMouseEvent();

        gameModel.createEnemy(mockMouseEvent);
    }
}
