package dotsnboxes.gui;

import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import dotsnboxes.core.Box;
import dotsnboxes.core.Game;
import dotsnboxes.core.Line;
import dotsnboxes.core.Player;
import dotsnboxes.core.ai.RandomStrategy;
import dotsnboxes.core.graphical.GraphicalBoard;
import dotsnboxes.core.graphical.GraphicalLine;
import dotsnboxes.core.random.MersenneTwisterRandomSelector;

/**
 * An SWT GUI driver of the game.
 * 
 * @author luis
 */
public class SWTGameController extends SWTGameForm {

    private static final String HUMAN_OPPONENT = "Human";
    private static final String RANDOM_OPPONENT = "Random Strategy";

    private static final String[] opponents;
    static {
        opponents = new String[] { HUMAN_OPPONENT, RANDOM_OPPONENT };
    }

    private Game game;
    private GraphicalBoard board;
    private String currentOpponent;

    public SWTGameController() {
        super();
    }

    @Override
    protected void configureControls() {

        this.game = new Game();
        this.board = new GraphicalBoard(4, 4);
        currentOpponent = HUMAN_OPPONENT;

        configureResetButton();
        configureBoardSizeCombo();
        configureCanvas();
        configureOpponentCombo();
    }

    private void configureResetButton() {

        resetButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                newGame();
            }
        });
    }

    private void configureBoardSizeCombo() {

        boardSizeCombo
                .setItems(new String[] { "4 x 4", "3 x 3", "2 x 2", 
                        "2 x 3", "4 x 3", "5 x 5", "6 x 6", "1 x 2" });
        boardSizeCombo.select(0);

        boardSizeCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                newGame();
            }
        });
    }

    private void configureCanvas() {

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                if (game.selectLine(getLineFromClick(e.x, e.y))) {
                    lineSelected();
                }
            }
        });

        /* TODO highlight lines that are hovered over
        canvas.addMouseMoveListener(new MouseMoveListener() {

            @Override
            public void mouseMove(MouseEvent e) {
                System.out.println("mouse move: x: " + e.x + "; y: " + e.y);
            }
        });
        */

        canvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                /* Create the image to fill the canvas */
                Image image = new Image(shell.getDisplay(), canvas.getBounds());
                /* Set up the offscreen gc */
                GC gcImage = new GC(image);
                drawCompletedBoxes(gcImage);
                drawLines(gcImage);
                drawDots(gcImage);
                /* Draw the offscreen buffer to the screen */
                e.gc.drawImage(image, 0, 0);
                image.dispose();
                gcImage.dispose();
            }
        });
    }

    private void configureOpponentCombo() {

        opponentCombo.setItems(opponents);
        opponentCombo.select(0);

        opponentCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                newGame();
                currentOpponent = opponents[opponentCombo.getSelectionIndex()];
                if (currentOpponent.equals(RANDOM_OPPONENT)) {
                    game.setSecondPlayerStrategy(new RandomStrategy(new MersenneTwisterRandomSelector()));
                } else if (currentOpponent.equals(HUMAN_OPPONENT)) {
                    game.setSecondPlayerStrategy(null);
                }
            }
        });
    }

    private void lineSelected() {

        bluePlayerScore.setText(String.valueOf(game.getFirstPlayerScore()));
        redPlayerScore.setText(String.valueOf(game.getSecondPlayerScore()));
        canvas.redraw();
        /* to update the value of the score labels */
        playerScoreComp.layout();
        if (board.allLinesSelected()) {
            int outcome = game.complete();
            String message = "";
            switch (outcome) {
            case Game.FIRST_PLAYER_WON:
                message = "Blue player wins!";
                break;
            case Game.SECOND_PLAYER_WON:
                message = "Red player wins!";
                break;
            case Game.TIE:
                message = "It's a tie.";
            }
            NotificationMessage.notify(shell, 300, 100, "Game complete", message);
            bluePlayerWon.setText(String.valueOf(game.getFirstPlayerGamesWon()));
            redPlayerWon.setText(String.valueOf(game.getSecondPlayerGamesWon()));
            gamesWonComp.layout();
        } else if (game.isSecondPlayerActive() && !currentOpponent.equals(HUMAN_OPPONENT)) {
            /* invoke the AI player strategy and select a line */
            byte lineIndex = game.getSecondPlayerNextMove(board.getConfiguration());
            GraphicalLine selectedLine = (GraphicalLine) board.getLines()[lineIndex];
            selectedLine.setSelected(true);
            if (game.selectLine(selectedLine)) {
                lineSelected();
            }
        }
    }

    private void newGame() {

        switch (boardSizeCombo.getSelectionIndex()) {
        case 0:
            board = new GraphicalBoard(4, 4);
            break;
        case 1:
            board = new GraphicalBoard(3, 3);
            break;
        case 2:
            board = new GraphicalBoard(2, 2);
            break;
        case 3:
            board = new GraphicalBoard(2, 3);
            break;
        case 4:
            board = new GraphicalBoard(4, 3);
            break;
        case 5:
            board = new GraphicalBoard(5, 5);
            break;
        case 6:
            board = new GraphicalBoard(6, 6);
            break;
        case 7:
            board = new GraphicalBoard(1, 2);
        }

        game.reset();
        bluePlayerScore.setText("0");
        redPlayerScore.setText("0");
        canvas.redraw();
        playerScoreComp.layout();
    }

    private void drawDots(GC gc) {

        gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
        for (int i = 0; i < board.getDots().length; i++) {
            gc.fillOval(board.getDots()[i].getX(), board.getDots()[i].getY(), board.getDots()[i].getWidth(),
                    board.getDots()[i].getWidth());
        }
    }

    private void drawCompletedBoxes(GC gc) {

        for (Player player : game.getPlayers()) {
            Color boxColor;
            if (player == game.getFirstPlayer()) {
                boxColor = new Color(Display.getCurrent(), 163, 200, 241);
            } else {
                boxColor = new Color(Display.getCurrent(), 255, 175, 175);
            }
            gc.setBackground(boxColor);
            gc.setAlpha(125);
            drawPlayerBoxes(gc, player.getBoxesCompleted());
            boxColor.dispose();
            gc.setAlpha(250);
        }
    }

    private void drawLines(GC gc) {

        for (Player player : game.getPlayers()) {
            Color lineColor;
            if (player == game.getFirstPlayer()) {
                lineColor = shell.getDisplay().getSystemColor(SWT.COLOR_BLUE);
            } else {
                lineColor = shell.getDisplay().getSystemColor(SWT.COLOR_RED);
            }
            gc.setBackground(lineColor);
            drawPlayerLines(gc, player.getLines());
            lineColor.dispose();
        }
    }

    private void drawPlayerBoxes(GC gc, Set<Box> boxesCompleted) {

        for (Box box : boxesCompleted) {
            GraphicalLine top = (GraphicalLine) box.getTopLine();
            GraphicalLine bottom = (GraphicalLine) box.getBottomLine();
            int x = top.getX() - (board.getDotWidth() / 2);
            int y = top.getY() + (top.getHeight() / 2);
            int height = (bottom.getY() + (bottom.getHeight() / 2)) - y;
            int width = top.getWidth() + board.getDotWidth();
            gc.fillRectangle(x, y, width, height);
        }
    }

    private void drawPlayerLines(GC gc, Set<Line> lines) {

        for (Line line : lines) {
            GraphicalLine l = (GraphicalLine) line;
            gc.fillRectangle(l.getX(), l.getY(), l.getWidth(), l.getHeight());
        }
    }

    private GraphicalLine getLineFromClick(int x, int y) {

        for (int i = 0; i < board.getLines().length; i++) {
            GraphicalLine line = (GraphicalLine) board.getLines()[i];
            /*
             * make sure the width and height are at least as large as the dot
             * diameter
             */
            int width = line.getWidth() < board.getDotWidth() ? board.getDotWidth() : line.getWidth();
            int height = line.getHeight() < board.getDotWidth() ? board.getDotWidth() : line.getHeight();
            Rectangle rectangle = new Rectangle(line.getX(), line.getY(), width, height);
            if (rectangle.contains(x, y)) {
                line.setSelected(true);
                return line;
            }
        }
        return null;
    }
}
