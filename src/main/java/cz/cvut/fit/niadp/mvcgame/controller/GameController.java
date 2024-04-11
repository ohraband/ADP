package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.command.*;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class GameController {

    private final IGameModel model;
    public GameController(IGameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            System.out.println(code);
            switch(code) {
                case MvcGameConfig.UP_KEY:
                    this.model.registerCommand(new MoveCannonUpCommand(this.model));
                    break;
                case MvcGameConfig.DOWN_KEY:
                    this.model.registerCommand(new MoveCannonDownCommand(this.model));
                    break;
                case MvcGameConfig.SHOOT_KEY:
                    this.model.registerCommand(new CannonShootCommand(this.model));
                    break;
                case MvcGameConfig.AIM_UP_KEY:
                    this.model.registerCommand(new AimCannonUpCommand(this.model));
                    break;
                case MvcGameConfig.AIM_DOWN_KEY:
                    this.model.registerCommand(new AimCannonDownCommand(this.model));
                    break;
                case MvcGameConfig.POWER_UP_KEY:
                    this.model.registerCommand(new CannonPowerUpCommand(this.model));
                    break;
                case MvcGameConfig.POWER_DOWN_KEY:
                    this.model.registerCommand(new CannonPowerDownCommand(this.model));
                    break;
                case MvcGameConfig.MOVING_STRATEGY_KEY:
                    this.model.registerCommand(new ToggleMovingStrategyCommand(this.model));
                    break;
                case MvcGameConfig.SHOOTING_MODE_KEY:
                    this.model.registerCommand(new ToggleShootingModeCommand(this.model));
                    break;
                case MvcGameConfig.MISSILES_UP_KEY:
                    this.model.registerCommand(new CannonMissilesUpCommand(this.model));
                    break;
                case MvcGameConfig.MISSILES_DOWN_KEY:
                    this.model.registerCommand(new CannonMissilesDownCommand(this.model));
                    break;
                case MvcGameConfig.UNDO_LAST_COMMAND_KEY:
                    this.model.undoLastCommand();
                    break;
                case MvcGameConfig.SECRET_CANNON_KEY:
                    this.model.registerCommand(new SecretCannonCommand(this.model));
                    break;
                case MvcGameConfig.BACKGROUND_KEY:
                    this.model.registerCommand(new ToggleBackgroundCommand(this.model));
                    break;
                case MvcGameConfig.INVINCIBLE_MISSILE_KEY:
                    this.model.registerCommand(new ToggleInvincibleMissileCommand(this.model));
                    break;
                case MvcGameConfig.MISSILE_TYPE_KEY:
                    this.model.registerCommand(new ToggleMissileTypeCommand(this.model));
                    break;
                case MvcGameConfig.EXIT_KEY:
                    System.exit(0);
                    break;
                default:
                    //nothing
            }
        }
        this.model.update();
        pressedKeysCodes.clear();
    }

    public void processMouseClick(MouseEvent event){
        this.model.registerCommand(new CreateEnemyCommand(this.model, event));
    }
}
