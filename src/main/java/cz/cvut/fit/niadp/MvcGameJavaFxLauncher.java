package cz.cvut.fit.niadp;

import cz.cvut.fit.niadp.mvcgame.adapter.LevelLoader;
import cz.cvut.fit.niadp.mvcgame.bridge.GameGraphics;
import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.bridge.JavaFxGraphics;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

import cz.cvut.fit.niadp.mvcgame.MvcGame;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.LEVEL_1;
import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.LEVEL_2;


public class MvcGameJavaFxLauncher extends Application {

    private static final MvcGame theMvcGame = new MvcGame();

    @Override
    public void init() {
        theMvcGame.init();
    }

    @Override
    public void start(Stage stage) {
        String winTitle = theMvcGame.getWindowTitle();
        int winWidth = theMvcGame.getWindowWidth();
        int winHeigth = theMvcGame.getWindowHeight();
        stage.setTitle( winTitle );
        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        Canvas canvas = new Canvas( winWidth, winHeigth );
        root.getChildren().add( canvas );


        IGameGraphics gameGraphics = new GameGraphics(new JavaFxGraphics(canvas.getGraphicsContext2D()));
        ArrayList<String> pressedKeysCodes = new ArrayList<>();
        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    // only add once... prevent duplicates
                    if(Objects.equals(e.getText(), "ô")){
                        pressedKeysCodes.add("ô");
                    }
                    else if (!pressedKeysCodes.contains(code))
                        pressedKeysCodes.add(code);
                }
        );
        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    pressedKeysCodes.remove( code );
                }
        );
        // the game-loop
        theMvcGame.setGraphicsContext(gameGraphics);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                theMvcGame.processPressedKeys(pressedKeysCodes);
            }
        }.start();

        // Add this block for mouse click event handling
        theScene.setOnMouseClicked(theMvcGame::handleMouseClick);

        stage.show();

        theMvcGame.simulateMouseClick(new LevelLoader().LoadLevel(LEVEL_2));
    }


    public static void main(String[] args) {
        launch();
    }

}