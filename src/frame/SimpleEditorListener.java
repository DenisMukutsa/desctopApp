package frame;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class SimpleEditorListener extends WindowAdapter implements ActionListener, AutoCloseable {
    private SimpleEditor editor;
    private File file;
    private FileReader reader;
    private FileWriter writer;

    public SimpleEditorListener(SimpleEditor editor) {
        this.editor = editor;
    }

    JFileChooser fileChooser = new JFileChooser();

    private void createNewFile() {

        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select file to save");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = fileChooser.showSaveDialog(editor);
        if (res == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }

    public void actionPerformed(ActionEvent ea) {
        switch(ea.getActionCommand()){
            case "Open":{
                try{
                    openOperation();
                }
                catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
              break;
            }
               case "Save":{
                saveOperation();
                break;
            }
                   case "Cansel":{
                    cancelOperation();
                    break;
                   }
                       case "Exit":{
                        exitOperation();
                        break;
                       }
            }
    }

    private void openOperation() throws FileNotFoundException {
        if (file == null) {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Выберите файл");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = fileChooser.showOpenDialog(editor);

            if (res == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();

                try {
                    BufferedReader buffer = new BufferedReader(new FileReader(file));
                    String str = " ";
                    while (buffer.ready()) {
                        try {
                            str += buffer.readLine();


                        }
                        catch (IOException e) {

                        }
                    }

                    buffer.close();
                    editor.appendText(str, true);
                }

                catch (IOException e) {
                    JOptionPane.showMessageDialog(editor, "Ошибка чтения файла.");
                }
                editor.setJLabelText("Чтение и редактирование файла: " + "\"" + file.getName() + "\"");
            }
        }
    }

    private void saveOperation() {

        if (file == null) {
            createNewFile();

        }
        try {
            BufferedWriter buffer = new BufferedWriter (new FileWriter(file, true));
            String result = editor.getText();
            buffer.write(" " + result);
            buffer.flush();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(editor, "Ошибка записи файла");
        }
    }

    private void cancelOperation() {

        editor.appendText("", false);
    }


    private void exitOperation() {
        if (file != null) {
            Object[] operation = {"Save", "Cancel"};
            int userOperation = JOptionPane.showOptionDialog(editor, "Save changes or not?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, operation, null);
            if (userOperation == 0) saveOperation();
            if (userOperation == 1) cancelOperation();
        }
        editor.dispose();
    }

    public void close() {
        if (file != null) saveOperation();
    }
}
