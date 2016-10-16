package org.java7recipes.chapter14.recipe14_19;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Adding a Listener to Document.
 * @author cdea
 */
public class AddingListenerToDocument extends JPanel {

    public AddingListenerToDocument() {
        setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList people = new JList(listModel);
        add(people, BorderLayout.CENTER);

        JTextField searchFld = new JTextField();
        searchFld.getDocument().addDocumentListener(new MyDocumentListener(people));
        add(searchFld, BorderLayout.NORTH);

    }

    public static void main(String[] args) {
        final JPanel c = new AddingListenerToDocument();
        c.setPreferredSize(new Dimension(379, 200));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-19 Adding Listener to Document", c);
    }
}

class MyDocumentListener implements DocumentListener {

    private static String[] dictionary = {"apress", 
        "caitlin", "car", "carl", "cat", "cathode", 
        "bat", "batter", "barney", 
        "fred", "fredrick",
        "gillian", "goose",
        "java", "javafx", 
        "swan", "swing"};
    private JList listBox;

    MyDocumentListener(JList listBox) {
        this.listBox = listBox;
    }
    String newline = "\n";

    public void insertUpdate(DocumentEvent e) {
        searchDictionary(e);
    }

    public void removeUpdate(DocumentEvent e) {
        searchDictionary(e);
    }

    public void changedUpdate(DocumentEvent e) {
        System.out.println("change: " + e);
    }

    public void searchDictionary(DocumentEvent e) {
        try {
            Document doc = (Document) e.getDocument();
            String text = doc.getText(0, doc.getLength());
            DefaultListModel dlm = (DefaultListModel) listBox.getModel();
            dlm.removeAllElements();
            if (text != null && text.length() > 0) {
                for (String word : dictionary) {
                    if (word.startsWith(text)) {
                        dlm.addElement(word);
                    }
                }
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

    }
}