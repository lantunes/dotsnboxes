package dotsnboxes.gui;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * An SWT form for the game controller.
 * 
 * @author luis
 */
public abstract class SWTGameForm {

    protected Combo boardSizeCombo;
    protected Combo opponentCombo;
    protected Label bluePlayerScore;
    protected Label redPlayerScore;
    protected Canvas canvas;
    protected Shell shell;
    protected Display display;
    protected Composite playerScoreComp;
    protected Composite gamesWonComp;
    protected Label bluePlayerWon;
    protected Label redPlayerWon;
    protected Composite leftComp;
    protected Group canvasGroup;
    protected Composite rightComp;
    protected Group opponentGroup;
    protected Button resetButton;

    public SWTGameForm() {

        initShell();
        createControls();
        configureControls();
        displayShell();
    }

    private void initShell() {

        display = new Display();
        shell = new Shell(display);
        shell.setText("Dots 'N Boxes");
        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        gl.marginHeight = 10;
        gl.marginWidth = 10;
        shell.setLayout(gl);
        shell.setMinimumSize(502, 475);
        Image icon = new Image(display, getClass().getClassLoader().getResourceAsStream("logo.png"));
        shell.setImage(icon);
    }

    private void createControls() {

        createLeftComposite();
        createRightComposite();
        createBoardSizeGroupOnRightComposite();
        createBoxesCompletedGroupOnRightComposite();
        createGamesWonGroupOnRightComposite();
        createCanvasGroupOnLeftComposite();
        createOpponentGroupOnLeftComposite();
    }

    protected abstract void configureControls();

    private void displayShell() {

        shell.pack();
        shell.open();

        /* trigger canvas PaintListener to fire */
        canvas.redraw();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private void createLeftComposite() {

        leftComp = new Composite(shell, SWT.NONE);
        leftComp.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(leftComp);
    }

    private void createRightComposite() {

        rightComp = new Composite(shell, SWT.NONE);
        rightComp.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(rightComp);
    }

    private void createBoardSizeGroupOnRightComposite() {

        Group boardGroup = new Group(rightComp, SWT.NONE);
        boardGroup.setText("Board Size");
        boardGroup.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(boardGroup);

        Composite comboComp = new Composite(boardGroup, SWT.NONE);
        comboComp.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(comboComp);

        boardSizeCombo = new Combo(comboComp, SWT.READ_ONLY);
    }

    private void createBoxesCompletedGroupOnRightComposite() {

        Group boxesCompletedGroup = new Group(rightComp, SWT.NONE);
        boxesCompletedGroup.setText("Boxes Completed");
        boxesCompletedGroup.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(boxesCompletedGroup);

        playerScoreComp = new Composite(boxesCompletedGroup, SWT.NONE);
        playerScoreComp.setLayout(new GridLayout(2, false));
        GridDataFactory.fillDefaults().grab(true, true).applyTo(playerScoreComp);

        Label bluePlayer = new Label(playerScoreComp, SWT.NONE);
        bluePlayer.setText("Blue: ");

        bluePlayerScore = new Label(playerScoreComp, SWT.NONE);
        bluePlayerScore.setText("0");

        Label redPlayer = new Label(playerScoreComp, SWT.NONE);
        redPlayer.setText("Red: ");

        redPlayerScore = new Label(playerScoreComp, SWT.NONE);
        redPlayerScore.setText("0");

        resetButton = new Button(playerScoreComp, SWT.PUSH);
        resetButton.setText("New Game");
        GridDataFactory.fillDefaults().grab(false, false).span(2, 1).applyTo(resetButton);
    }

    private void createGamesWonGroupOnRightComposite() {

        Group gamesWonGroup = new Group(rightComp, SWT.NONE);
        gamesWonGroup.setText("Games Won");
        gamesWonGroup.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(gamesWonGroup);

        gamesWonComp = new Composite(gamesWonGroup, SWT.NONE);
        gamesWonComp.setLayout(new GridLayout(2, false));
        GridDataFactory.fillDefaults().grab(true, true).applyTo(gamesWonComp);

        Label bluePlayerWonLabel = new Label(gamesWonComp, SWT.NONE);
        bluePlayerWonLabel.setText("Blue: ");

        bluePlayerWon = new Label(gamesWonComp, SWT.NONE);
        bluePlayerWon.setText("0");

        Label redPlayerWonLabel = new Label(gamesWonComp, SWT.NONE);
        redPlayerWonLabel.setText("Red: ");

        redPlayerWon = new Label(gamesWonComp, SWT.NONE);
        redPlayerWon.setText("0");
    }

    private void createCanvasGroupOnLeftComposite() {

        canvasGroup = new Group(leftComp, SWT.NONE);
        canvasGroup.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(canvasGroup);

        /*
         * create the canvas with no background to enable the double buffering
         * technique
         */
        canvas = new Canvas(canvasGroup, SWT.NO_BACKGROUND);
        canvas.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));

        GridDataFactory.fillDefaults().hint(315, 315).grab(false, false).applyTo(canvas);
    }

    private void createOpponentGroupOnLeftComposite() {

        opponentGroup = new Group(leftComp, SWT.NONE);
        opponentGroup.setText("Opponent");
        opponentGroup.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(opponentGroup);

        Composite comboComp = new Composite(opponentGroup, SWT.NONE);
        comboComp.setLayout(new GridLayout(1, false));
        GridDataFactory.fillDefaults().grab(false, false).applyTo(comboComp);

        opponentCombo = new Combo(comboComp, SWT.READ_ONLY);
    }
}