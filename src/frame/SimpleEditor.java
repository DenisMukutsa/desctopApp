package frame;

import java.awt.*;
import javax.swing.*;

public class SimpleEditor extends JFrame {
    private Container cp;
    private JLabel fileName;
    private JTextArea text;
    private JMenuBar bar;
    private JMenu[] menu;
    private JMenuItem[] commandMenu;
    private JButton[] commandButton;
    private SimpleEditorListener listener;

    protected SimpleEditor() {
        setTitle("Simple text editor");
        init();
        createMenu();
        setVisible(true);

    }

    private void init() {
        setLocation(500, 250);
        setSize(500,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listener = new SimpleEditorListener(this);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        cp.add(buttonPanel, BorderLayout.SOUTH);

        commandButton = new JButton[] {new JButton("Open"), new JButton("Save"),
               new JButton("Cancel"), new JButton("Exit")};

       commandButton[0].addActionListener(listener);
       commandButton[0].setActionCommand("Open");
       commandButton[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       commandButton[1].addActionListener(listener);
       commandButton[1].setActionCommand("Save");
       commandButton[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       commandButton[2].addActionListener(listener);
       commandButton[2].setActionCommand("Cancel");
       commandButton[2].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       commandButton[3].addActionListener(listener);
       commandButton[3].setActionCommand("Exit");
       commandButton[3].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       buttonPanel.setLayout(new FlowLayout());
       buttonPanel.add(commandButton[0]);
       buttonPanel.add(commandButton[1]);
       buttonPanel.add(commandButton[2]);
       buttonPanel.add(commandButton[3]);

       text = new JTextArea();


       JScrollPane scrollPane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       cp.add(scrollPane, BorderLayout.CENTER);


       fileName = new JLabel();

       fileName.setHorizontalTextPosition(PROPERTIES);
       cp.add(fileName, BorderLayout.NORTH);


    }

    private void createMenu() {
        bar = new JMenuBar();
        menu = new JMenu[] {new JMenu("File"), new JMenu("Edit")};
        commandMenu = new JMenuItem[] {new JMenuItem("Open"), new JMenuItem("Save"),
                new JMenuItem("Cancel"), new JMenuItem("Exit")};

        commandMenu[0].addActionListener(listener);
        commandMenu[0].setActionCommand("Open");
        commandMenu[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandMenu[1].addActionListener(listener);
        commandMenu[1].setActionCommand("Save");
        commandMenu[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandMenu[2].addActionListener(listener);
        commandMenu[2].setActionCommand("Cancel");
        commandMenu[2].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandMenu[3].addActionListener(listener);
        commandMenu[3].setActionCommand("Exit");
        commandMenu[3].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        menu[0].add(commandMenu[0]);
        menu[0].add(commandMenu[3]);
        menu[1].add(commandMenu[1]);
        menu[1].add(commandMenu[2]);

        bar.add(menu[0]);
        bar.add(menu[1]);

        setJMenuBar(bar);
    }

    void appendText(String str, boolean append) {
        if (append) {
            text.append(str);
        }
        else {
            text.setText(str);
        }
    }

    String getText() {

        return text.getText();
    }

    public void setJLabelText (String str){
        fileName.setText(str);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                    SimpleEditor simpleEditor = new SimpleEditor();
                    }
            });

    }

}