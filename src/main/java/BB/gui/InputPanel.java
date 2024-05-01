package BB.gui;

import BB.AssemblyEmulator;
import lombok.Getter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class InputPanel extends JPanel{
    private final JFrame frame;
    private final AssemblyEmulator assemblyEmulator;
    private final RegistersPanel registersPanel;
    private final JScrollPane scrollPane;
    @Getter
    private final JTextArea inputArea;
    private final JTextArea lineNumbers;
    private final JButton submitInputButton;
    private final JButton resetButton;
    private int currentLine = 0;

    /**
     * Constructs an InputPanel with specified components and layout
     * @param frame The JFrame to which the InputPanel belongs
     * @param assemblyEmulator The AssemblyEmulator instance
     * @param registersPanel The RegistersPanel instance
     */
    public InputPanel(JFrame frame, AssemblyEmulator assemblyEmulator,
                      RegistersPanel registersPanel) {
        this.frame = frame;
        this.assemblyEmulator = assemblyEmulator;
        this.registersPanel = registersPanel;
        this.setLayout(new GridLayout(1, 2, 50, 15));

        this.inputArea = new JTextArea(5, 10);
        this.lineNumbers = new JTextArea(1, 2);

        this.submitInputButton = new JButton("Enter");
        this.resetButton = new JButton("Reset");

        this.scrollPane = new JScrollPane(inputArea);

        configureMainPanel();
    }

    /**
     * Configures the main panel by adding components and setting up action listeners
     */
    private void configureMainPanel(){
        configureButtons();
        configureInputArea();
        configureActionListeners();

        this.add(scrollPane);
        this.add(submitInputButton);
        this.add(resetButton);
        this.frame.add(this);
    }

    /**
     * Configures the appearance and behavior of the buttons
     */
    private void configureButtons(){
        this.submitInputButton.setFont(new Font("Arial", Font.PLAIN, 42));
        this.resetButton.setFont(new Font("Arial", Font.PLAIN, 42));

        this.submitInputButton.setFocusPainted(false);
        this.resetButton.setFocusPainted(false);
    }

    /**
     * Configures the appearance and behavior of the input area
     */
    private void configureInputArea(){
        this.inputArea.setFont(new Font("Arial", Font.PLAIN, 24));
        this.lineNumbers.setFont(new Font("Arial", Font.PLAIN, 24));

        this.lineNumbers.setEditable(false);
        this.lineNumbers.setFocusable(false);

        this.scrollPane.setRowHeaderView(lineNumbers);
        this.scrollPane.setBorder(null);
        this.scrollPane.setViewportBorder(null);
    }

    /**
     * Configures action listeners for the input area and buttons
     */
    private void configureActionListeners(){
        inputArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLineNumbers();
            }
        });

        submitInputButton.addActionListener(e -> {
            this.submitInputAndParseCommand(inputArea);
            if(assemblyEmulator.getParsingMode() == 0) {
                this.currentLine++;
            }
            updateLineNumbers();
        });

        resetButton.addActionListener(e -> {
            this.inputArea.setText("");
            this.registersPanel.resetRegValues();
            this.currentLine = 0;
        });
    }

    /**
     * Updates the line numbers and a pointer displayed alongside the input area
     */
    private void updateLineNumbers() {
        SwingUtilities.invokeLater(() -> {
            int lineCount = this.inputArea.getLineCount();
            StringBuilder numbers = new StringBuilder();
            for (int i = 1; i <= lineCount; i++) {
                numbers.append(i);
                if(i == currentLine){
                    numbers.append(">");
                }
                numbers.append("\n");
            }
            this.lineNumbers.setText(numbers.toString());
        });
    }

    /**
     * Submits the input from the text area and parses the command accordingly
     * @param inputArea The JTextArea containing the input
     */
    private void submitInputAndParseCommand(JTextArea inputArea){
        String enteredCommand = inputArea.getText();
        int currentCommandStartIndex;
        int currentCommandEndIndex;

        if(currentLine == 0){
            currentCommandStartIndex = 0;
            currentCommandEndIndex = ordinalIndexOf(
                    enteredCommand, "\n", this.currentLine);
        }
        else {
            currentCommandStartIndex = ordinalIndexOf(
                    enteredCommand, "\n", this.currentLine) + 1;
            currentCommandEndIndex = ordinalIndexOf(
                    enteredCommand, "\n", this.currentLine + 1);
            if(currentCommandEndIndex == -1){
                currentCommandEndIndex = enteredCommand.length();
            }
        }

        int newLineIndex = enteredCommand.indexOf("\n");
        if(newLineIndex == -1 || this.assemblyEmulator.getParsingMode() == 1){
            assemblyEmulator.parseCommand(enteredCommand);
        }
        else {
            String currentCommand = enteredCommand.substring(
                    currentCommandStartIndex, currentCommandEndIndex);
            assemblyEmulator.parseCommand(currentCommand);
        }
    }

    /**
     * Finds the index of the nth occurrence of a substring within a string
     * @param str The string to search within
     * @param substr The substring to search for
     * @param n The ordinal number of the occurrence to find
     * @return The index of the nth occurrence of the substring, or -1 if not found
     */
    private int ordinalIndexOf(String str, String substr, int n) {
        int pos = str.indexOf(substr);
        while (--n > 0 && pos != -1)
            pos = str.indexOf(substr, pos + 1);
        return pos;
    }
}