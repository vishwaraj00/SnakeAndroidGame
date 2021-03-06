package com.game.impl;

import android.graphics.Canvas;

import com.game.actors.Painter;
import com.game.actors.SnakeActor;
import com.game.controls.GAMESTATUS;
import com.game.controls.GameScoreController;
import com.game.controls.GameStatusController;

/**
 * Created by dabba on 15/10/17.
 */

public class GameBoard {
    private static final String TAG = "GameBoard";
    SnakeActor snake;

    public GameBoard() {
    }

    public void tick() {
        if (snake != null && GameStatusController.getControlAction() == GAMESTATUS.RUNNING) {
            snake.tick();
        }
    }

    public Canvas draw(Canvas canvas) {
        // clear canvas
        Painter.getScreenPainter().drawBackground(canvas);
        // canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);

        switch (GameStatusController.getControlAction()) {
            case NEW:
                Painter.getScreenPainter().drawText("Tap to Start", canvas);
                //passing reverse heightXwidth because screen is always set to be in horizontal landscape mode
                GameScoreController.reset();
                snake = new SnakeActor(Painter.getScreenPainter().getScreenSize().y, Painter.getScreenPainter().getScreenSize().x);
                break;
            case PAUSED:
                Painter.getScreenPainter().drawText("Game paused, tap to Play", canvas);
                break;
            case GAME_OVER:
                Painter.getScreenPainter().drawText("Game Over! Tap to Retry", canvas);
                GameScoreController.reset();
                break;
            case RUNNING:
                Painter.getScreenPainter().drawScreen(canvas);
                snake.draw(canvas);
                break;
            default:
                throw new RuntimeException("Unknown GameStatus!!");
        }

        return canvas;
    }
}
