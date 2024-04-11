package cz.cvut.fit.niadp.mvcgame.adapter;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PositionToMouseClickAdapter {

    Position position;

    public PositionToMouseClickAdapter(Position position) {
        this.position = position;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public MouseEvent toMouseEvent() {
        return new MouseEvent(
                MouseEvent.MOUSE_CLICKED,
                position.getX(), position.getY(), position.getX(), position.getY(),
                MouseButton.PRIMARY,
                1,
                false, false, false, false,
                true,
                false, false, false, false,
                false,
                null
        );
    }
}
