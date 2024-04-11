package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import javafx.scene.input.MouseEvent;

public class CreateEnemyCommand extends AbstractGameCommand {

    public CreateEnemyCommand(IGameModel model, MouseEvent event) {
        this.subject = model;
        this.event = event;
    }

    private MouseEvent event;

    @Override
    protected void execute() {
        this.subject.createEnemy(event);
    }
}
