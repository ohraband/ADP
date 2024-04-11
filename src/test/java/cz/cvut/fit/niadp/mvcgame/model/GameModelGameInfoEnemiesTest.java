package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import org.junit.Assert;
import org.junit.Test;
import mockit.Injectable;
import mockit.Tested;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GameModelGameInfoEnemiesTest {

    @Tested
    private GameModel gameModel;

    @Injectable
    private AbsEnemy mockedEnemy;

    @Test
    public void testModifyGameInfoWithMultipleEnemies() throws NoSuchFieldException, IllegalAccessException {
        List<AbsEnemy> enemies = new ArrayList<>();
        enemies.add(mockedEnemy);
        enemies.add(mockedEnemy);
        enemies.add(mockedEnemy);

        Field field = GameModel.class.getDeclaredField("enemies");
        field.setAccessible(true);
        field.set(gameModel, enemies);

        gameModel.modifyGameInfo();

        String gameInfoText1 = gameModel.getGameInfo();
        assertGameInfoText1(gameInfoText1);

        enemies.removeAll(enemies);

        gameModel.modifyGameInfo();

        String gameInfoText2 = gameModel.getGameInfo();
        assertGameInfoText2(gameInfoText2);
    }

    private void assertGameInfoText1(String gameInfoText) {
        String expectedPattern = "Score: \\d+\\s+" +
                "Number of enemies: 3\\s+" +
                "Cannon power: \\d+\\s+" +
                "Cannon angle: \\d+\\s+" +
                "Shooting mode: \\w+\\s+" +
                "Number of missiles will be shot: \\d+\\s+" +
                "Missile moving strategy: \\w+\\s+" +
                "Missile Type: \\w+\\s+" +
                "Invincible missile: \\w+";

        Assert.assertTrue("Game info text doesn't match the expected pattern", gameInfoText.matches(expectedPattern));
    }

    private void assertGameInfoText2(String gameInfoText) {
        String expectedPattern = "Score: \\d+\\s+" +
                "Number of enemies: 0\\s+" +
                "Cannon power: \\d+\\s+" +
                "Cannon angle: \\d+\\s+" +
                "Shooting mode: \\w+\\s+" +
                "Number of missiles will be shot: \\d+\\s+" +
                "Missile moving strategy: \\w+\\s+" +
                "Missile Type: \\w+\\s+" +
                "Invincible missile: \\w+";

        Assert.assertTrue("Game info text doesn't match the expected pattern", gameInfoText.matches(expectedPattern));
    }
}
