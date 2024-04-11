package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;

import java.util.Random;

public class EnemyA extends AbsEnemy{

    Random random = new Random();

    public EnemyA(Position initPosition) {
        this.position = initPosition;
        InitDesign();
    }

    private void InitDesign(){
        if(random.nextBoolean()){
            designType = "TypeA";
        }
        else {
            designType = "TypeB";
        }
    }
}
