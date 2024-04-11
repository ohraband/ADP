package cz.cvut.fit.niadp.mvcgame.state;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;

public class DynamicShootingMode implements IShootingMode {
    @Override
    public String getName() {
        return DynamicShootingMode.class.getSimpleName();
    }

    @Override
    public void shoot(AbsCannon cannon) {

        for (int i = 0; i < cannon.getMissilesNumber(); i++) {
            if(i % 2 == 0){
                for (int j = 0; j < i; j++) {
                    cannon.aimUp();
                }
                cannon.primitiveShoot();
                for (int j = 0; j < i; j++) {
                    cannon.aimDown();
                }
            }
            else{
                for (int j = 0; j < i; j++) {
                    cannon.aimDown();
                }
                cannon.primitiveShoot();
                for (int j = 0; j < i; j++) {
                    cannon.aimUp();
                }
            }
        }
    }
}
