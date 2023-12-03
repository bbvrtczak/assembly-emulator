package BB.gui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveAndLoadPanel extends JPanel{
    private final JFrame frame;
    private final InputPanel inputPanel;
    private final JButton saveButton;
    private final JButton loadButton;

    public SaveAndLoadPanel(JFrame frame, InputPanel inputPanel) {
        this.frame = frame;
        this.inputPanel = inputPanel;
        this.setLayout(new GridLayout(1,2, 0, 100));

        this.saveButton = new JButton("Save");
        this.loadButton = new JButton("Load");

        configureMainPanel();
    }

    private void configureMainPanel(){
        configureButtons();
        configureActionListeners();

        this.frame.add(this);
    }

    private void configureButtons(){
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));
        this.loadButton.setFont(new Font("Arial", Font.PLAIN, 28));

        this.saveButton.setFocusPainted(false);
        this.loadButton.setFocusPainted(false);

        this.add(saveButton);
        this.add(loadButton);
    }

    private void configureActionListeners(){
        saveButton.addActionListener(e -> saveToFile());
        loadButton.addActionListener(e -> loadFromFile());
    }

    private void saveToFile(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this.frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile())) {
                //writer.print(inputPanel.inputArea.getText());
                writer.print(inputPanel.getInputArea().getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadFromFile(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this.frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                inputPanel.getInputArea().setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
