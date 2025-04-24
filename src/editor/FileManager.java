package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            textEditor.currentFile = file ;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                StringBuilder stringBuilder = new StringBuilder();
                String newLine ;
                while ((newLine = reader.readLine()) != null){
                    stringBuilder.append(newLine).append("\n");
                }
                textArea.setText(stringBuilder.toString());
            } catch (IOException e){

                JOptionPane.showMessageDialog(textEditor , "Error --> Cannot open 'File' !"+ e.getMessage());
            }
        }
    }

    public static void writeToFile (File file , String content , TextEditor textEditor){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){

            bufferedWriter.write(content);

        } catch (IOException e){
            JOptionPane.showMessageDialog(textEditor , "Error --> Cannot save 'File' !"+ e.getMessage());
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();
                textEditor.currentFile = file;
                writeToFile(file , textArea.getText() , textEditor);
            }
        }
        else{
            writeToFile(textEditor.currentFile , textArea.getText() , textEditor);
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText(" ");
        textEditor.currentFile = null ;
    }


}
