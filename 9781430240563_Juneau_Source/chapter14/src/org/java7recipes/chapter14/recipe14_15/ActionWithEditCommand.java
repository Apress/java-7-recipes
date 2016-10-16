package org.java7recipes.chapter14.recipe14_15;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import org.java7recipes.chapter14.AppSetup;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Action with edit commands.
 * @author cdea
 */
public final class ActionWithEditCommand extends JPanel implements AppSetup {

    AbstractDocument doc;
    protected UndoManager undo = new UndoManager();
    protected UndoAction undoAction;
    protected RedoAction redoAction;

    public ActionWithEditCommand() {
        setLayout(new BorderLayout(3, 3));

        JTextArea textArea = new JTextArea();
        JScrollPane sp = new JScrollPane(textArea);
        doc = (AbstractDocument) textArea.getDocument();
        undoAction = new UndoAction(undo);
        redoAction = new RedoAction(undo);

        // connect both
        redoAction.setUndoAction(undoAction);
        undoAction.setRedoAction(redoAction);
        doc.addUndoableEditListener(new MyUndoableEditListener());
        add(sp, BorderLayout.CENTER);

    }

    public void apply(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu editMenu = new JMenu("Edit");

        JMenuItem undoItem = new JMenuItem("Undo", null);
        undoItem.setMnemonic(KeyEvent.VK_Z);
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoItem.setAction(undoAction);
        editMenu.add(undoItem);

        JMenuItem redoItem = new JMenuItem("Redo", null);
        redoItem.setMnemonic(KeyEvent.VK_Y);
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redoItem.setAction(redoAction);
        editMenu.add(redoItem);
        menuBar.add(editMenu);

        frame.setJMenuBar(menuBar);
    }

    protected class MyUndoableEditListener implements UndoableEditListener {

        public void undoableEditHappened(UndoableEditEvent e) {
            undo.addEdit(e.getEdit());
            undoAction.updateState();
            redoAction.updateState();
        }
    }

    public static void main(String[] args) {
        final JPanel c = new ActionWithEditCommand();
        c.setPreferredSize(new Dimension(433, 312));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-15 Action with edit commands", c);
    }
}

class UndoAction extends AbstractAction {

    private UndoManager undo = null;
    private RedoAction redoAction = null;

    public UndoAction(UndoManager undo) {
        super("Undo");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_Z);

        setEnabled(false);
        this.undo = undo;
    }

    public void setRedoAction(RedoAction redoAction) {
        this.redoAction = redoAction;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            undo.undo();
        } catch (CannotUndoException ex) {
            ex.printStackTrace();
        }
        updateState();
        redoAction.updateState();
    }

    protected void updateState() {
        if (undo.canUndo()) {
            setEnabled(true);
            putValue(Action.NAME, undo.getUndoPresentationName());
        } else {
            setEnabled(false);
            putValue(Action.NAME, "Undo");
        }
    }
}

class RedoAction extends AbstractAction {

    private UndoManager undo = null;
    private UndoAction undoAction = null;

    public RedoAction(UndoManager undo) {
        super("Redo");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_Y);

        setEnabled(false);
        this.undo = undo;
    }

    public void setUndoAction(UndoAction undoAction) {
        this.undoAction = undoAction;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            undo.redo();
        } catch (CannotRedoException ex) {
            ex.printStackTrace();
        }
        updateState();
        undoAction.updateState();
    }

    protected void updateState() {
        if (undo.canRedo()) {
            setEnabled(true);
            putValue(Action.NAME, undo.getRedoPresentationName());
        } else {
            setEnabled(false);
            putValue(Action.NAME, "Redo");
        }
    }
}