package BB.gui;

import BB.AssemblyEmulator;

import javax.swing.*;
import java.awt.*;

public class ModePanel extends JPanel {
    private final JFrame frame;
    private final AssemblyEmulator assemblyEmulator;
    private final JButton singleExecutionButton;
    private final JButton overallExecutionButton;

    /**
     * Constructs a ModePanel with specified components and layout
     * @param frame The JFrame to which the ModePanel belongs
     * @param assemblyEmulator The AssemblyEmulator instance
     */
    public ModePanel(JFrame frame, AssemblyEmulator assemblyEmulator) {
        this.frame = frame;
        this.assemblyEmulator = assemblyEmulator;
        this.setLayout(new GridLayout(2, 1, 50, 15));

        this.singleExecutionButton = new JButton("Single instruction execution");
        this.overallExecutionButton = new JButton("Overall execution");

        configureMainPanel();
    }

    /**
     * Configures the main panel by adding components and setting up action listeners
     */
    private void configureMainPanel(){
        configureButtons();
        configureActionListeners();

        this.frame.add(this);
    }

    /**
     * Configures the appearance and behavior of the buttons
     */
    private void configureButtons(){
        this.singleExecutionButton.setFont(new Font("Arial", Font.PLAIN, 28));
        this.overallExecutionButton.setFont(new Font("Arial", Font.PLAIN, 28));

        this.singleExecutionButton.setFocusPainted(false);
        this.overallExecutionButton.setFocusPainted(false);

        this.singleExecutionButton.setBackground(new Color(72,101,129));

        this.add(singleExecutionButton);
        this.add(overallExecutionButton);
    }

    /**
     * Configures action listeners for the buttons to switch parsing modes
     */
    private void configureActionListeners(){
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
    }
}
