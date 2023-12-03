package BB.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BB.AssemblyEmulator;
import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.*;

public class Gui {
    private final JFrame frame;
    private JLabel axValueLabel;
    private JLabel bxValueLabel;
    private JLabel cxValueLabel;
    private JLabel dxValueLabel;
    private JTextArea inputArea;
    private JTextArea lineNumbers;
    private int currentLine = 0;
    private final AssemblyEmulator assemblyEmulator;

    public Gui(AssemblyEmulator assemblyEmulator) {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( Exception ex ) {
            System.err.println("Failed to initialize theme. Using fallback.");
        }
        this.frame = new JFrame("Assembly emulator");
        this.assemblyEmulator = assemblyEmulator;
    }

    public void run(){
        this.createWindow();
        this.createUI();
    }

    public void createWindow(){
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1024,768);
        this.frame.setLocationRelativeTo(null);
    }

    public void createUI(){
        createRegisterSection();
        createModeSection();
        createInputSection();
        this.frame.setLayout(new FlowLayout(FlowLayout.CENTER,100,20));
        this.frame.setVisible(true);
    }

    private void createRegisterSection(){
        // creating top panel with register values and labels
        JPanel regsPanel = new JPanel(new GridLayout(2, 4, 50, 15));

        // creating boxes (panels) for each register
        JPanel axPanel = new JPanel(new GridLayout(1,2));
        JPanel bxPanel = new JPanel(new GridLayout(1,2));
        JPanel cxPanel = new JPanel(new GridLayout(1,2));
        JPanel dxPanel = new JPanel(new GridLayout(1,2));

        // setting size of register boxes
        axPanel.setPreferredSize(new Dimension(200, 100));
        bxPanel.setPreferredSize(new Dimension(200, 100));
        cxPanel.setPreferredSize(new Dimension(200, 100));
        dxPanel.setPreferredSize(new Dimension(200, 100));

        // setting color of register boxes
        axPanel.setBackground(new Color(90, 93, 94));
        bxPanel.setBackground(new Color(90, 93, 94));
        cxPanel.setBackground(new Color(90, 93, 94));
        dxPanel.setBackground(new Color(90, 93, 94));

        // setting values for registers
        this.axValueLabel = new JLabel("0x0000");
        this.bxValueLabel = new JLabel("0x0000");
        this.cxValueLabel = new JLabel("0x0000");
        this.dxValueLabel = new JLabel("0x0000");

        // setting font for register values
        this.axValueLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        this.bxValueLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        this.cxValueLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        this.dxValueLabel.setFont(new Font("Arial", Font.PLAIN, 48));

        // centering register values in boxes
        this.axValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.axValueLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.bxValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.bxValueLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.cxValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.cxValueLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.dxValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.dxValueLabel.setVerticalAlignment(SwingConstants.CENTER);

        // adding register value labels to boxes
        axPanel.add(this.axValueLabel);
        bxPanel.add(this.bxValueLabel);
        cxPanel.add(this.cxValueLabel);
        dxPanel.add(this.dxValueLabel);

        // setting register boxes labels
        JLabel axLabel = new JLabel("AX");
        JLabel bxLabel = new JLabel("BX");
        JLabel cxLabel = new JLabel("CX");
        JLabel dxLabel = new JLabel("DX");

        // setting register boxes labels font
        axLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        bxLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        cxLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        dxLabel.setFont(new Font("Arial", Font.PLAIN, 48));

        // centering register labels over boxes
        axLabel.setHorizontalAlignment(SwingConstants.CENTER);
        axLabel.setVerticalAlignment(SwingConstants.CENTER);
        bxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bxLabel.setVerticalAlignment(SwingConstants.CENTER);
        cxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cxLabel.setVerticalAlignment(SwingConstants.CENTER);
        dxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dxLabel.setVerticalAlignment(SwingConstants.CENTER);

        // adding register labels to main panel
        regsPanel.add(axLabel);
        regsPanel.add(bxLabel);
        regsPanel.add(cxLabel);
        regsPanel.add(dxLabel);

        // adding register boxes to main panel
        regsPanel.add(axPanel);
        regsPanel.add(bxPanel);
        regsPanel.add(cxPanel);
        regsPanel.add(dxPanel);

        // adding main panel to frame
        this.frame.add(regsPanel);
    }

    private void createModeSection(){
        JButton singleExecutionButton = new JButton("Single instruction execution");
        JButton overallExecutionButton = new JButton("Overall execution");
        JPanel modePanel = new JPanel(new GridLayout(2, 1, 50, 15));

        singleExecutionButton.setFont(new Font("Arial", Font.PLAIN, 28));
        overallExecutionButton.setFont(new Font("Arial", Font.PLAIN, 28));

        singleExecutionButton.setFocusPainted(false);
        overallExecutionButton.setFocusPainted(false);

        singleExecutionButton.setBackground(new Color(72,101,129));

        singleExecutionButton.addActionListener(e -> {
            this.assemblyEmulator.setParsingMode(0);
            singleExecutionButton.setBackground(new Color(72,101,129));
            overallExecutionButton.setBackground(new Color(83,85,87));
        });

        overallExecutionButton.addActionListener(e -> {
            this.assemblyEmulator.setParsingMode(1);
            overallExecutionButton.setBackground(new Color(72,101,129));
            singleExecutionButton.setBackground(new Color(83,85,87));
        });

        modePanel.add(singleExecutionButton);
        modePanel.add(overallExecutionButton);

        this.frame.add(modePanel);
    }

    private void createInputSection(){
        this.inputArea = new JTextArea(5, 10);
        this.lineNumbers = new JTextArea(1,2);
        JButton submitInputButton = new JButton("Enter");
        JButton resetButton = new JButton("Reset");

        submitInputButton.setFocusPainted(false);
        resetButton.setFocusPainted(false);

        inputArea.setFont(new Font("Arial", Font.PLAIN, 24));
        lineNumbers.setFont(new Font("Arial", Font.PLAIN, 24));
        submitInputButton.setFont(new Font("Arial", Font.PLAIN, 42));
        resetButton.setFont(new Font("Arial", Font.PLAIN, 42));

        lineNumbers.setEditable(false);
        lineNumbers.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(inputArea);
        scrollPane.setRowHeaderView(lineNumbers);
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(null);

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
            inputArea.setText("");
            resetRegValues();
            currentLine = 0;
        });

        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 50, 15));
        inputPanel.add(scrollPane);
        inputPanel.add(submitInputButton);
        inputPanel.add(resetButton);
        this.frame.add(inputPanel);
    }

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
            lineNumbers.setText(numbers.toString());
        });
    }

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

    private int ordinalIndexOf(String str, String substr, int n) {
        int pos = str.indexOf(substr);
        while (--n > 0 && pos != -1)
            pos = str.indexOf(substr, pos + 1);
        return pos;
    }

    private void resetRegValues(){
        this.setAxValue(0);
        this.setBxValue(0);
        this.setCxValue(0);
        this.setDxValue(0);
    }

    public void setRegValue(String reg, int value){
        switch(reg) {
            case "ax":
                setAxValue(value);
                break;
            case "bx":
                setBxValue(value);
                break;
            case "cx":
                setCxValue(value);
                break;
            case "dx":
                setDxValue(value);
                break;
            default:
                break;
        }
    }

    private void setAxValue(int value){
        this.axValueLabel.setText(String.format("0x%04X", value));
    }

    private void setBxValue(int value){
        this.bxValueLabel.setText(String.format("0x%04X", value));
    }

    private void setCxValue(int value){
        this.cxValueLabel.setText(String.format("0x%04X", value));
    }

    private void setDxValue(int value){
        this.dxValueLabel.setText(String.format("0x%04X", value));
    }
}