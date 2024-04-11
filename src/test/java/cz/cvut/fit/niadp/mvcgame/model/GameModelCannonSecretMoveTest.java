package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import mockit.*;
import org.junit.Assert;
import org.junit.Test;


public class GameModelCannonSecretMoveTest {

    private static final int CANNON_TEST_POSITION_X = 123;
    private static final int CANNON_TEST_POSITION_Y = 123;
    private static final int MOVE_STEP = 10;

    @Tested
    private GameModel gameModel;

    @Injectable
    private AbsCannon cannon;

    @Test
    public void testSecretMoveCannon(){
        generalMockSetup();
        gameModel.secretMoveCannon();
        Assert.assertEquals(123 + MOVE_STEP, cannon.getPosition().getX());
        Assert.assertEquals(123, cannon.getPosition().getY());
    }


    public void generalMockSetup() {
        new MockUp<AbsCannon>() {
            private Position currentPosition = new Position(CANNON_TEST_POSITION_X, CANNON_TEST_POSITION_Y);

            @Mock
            public Position getPosition() {
                return currentPosition;
            }

            @Mock
            public void setPosition(Position position) {
                currentPosition = position;
            }
        };

        new MockUp<GameModel>() {
            @Mock
            public void notifyObservers(String event, Object data) {
            }
        };
    }
}
