package dotsnboxes.gui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

/**
 * An extended MessageDialog that has a custom size.
 * 
 * @author luis
 */
public class NotificationMessage extends MessageDialog {

    private int x;

    private int y;

    public NotificationMessage(Shell shell, int x, int y, String title, String message) {

        super(shell, title, null, message, 0, new String[] { "OK" }, 0);
        this.x = x;
        this.y = y;
    }

    @Override
    public Point getInitialSize() {

        return new Point(x, y);
    }

    public static void notify(Shell shell, int x, int y, String title, String message) {

        new NotificationMessage(shell, x, y, title, message).open();
    }
}
