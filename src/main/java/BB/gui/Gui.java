package BB.gui;

import javax.swing.*;

import BB.AssemblyEmulator;
import com.formdev.flatlaf.FlatDarculaLaf;
import lombok.Getter;

import java.awt.*;

public class Gui {
    private final JFrame frame;
    @Getter
    private final RegistersPanel registersPanel;
    private final ModePanel modePanel;
    private final InputPanel inputPanel;
    private final SaveAndLoadPanel saveAndLoadPanel;
    private final AssemblyEmulator assemblyEmulator;

    /**
     * Constructs a GUI for the assembly emulator
     * @param assemblyEmulator The assembly emulator instance
     */
    public Gui(AssemblyEmulator assemblyEmulator) {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( Exception ex ) {
            System.err.println("Failed to initialize theme. Using fallback.");
        }
        this.frame = new JFrame("Assembly emulator");
        this.assemblyEmulator = assemblyEmulator;
        this.registersPanel = new RegistersPanel(this.frame);
        this.modePanel = new ModePanel(this.frame, this.assemblyEmulator);
        this.inputPanel = new InputPanel(this.frame, this.assemblyEmulator,
                this.registersPanel);
        this.saveAndLoadPanel = new SaveAndLoadPanel(this.frame, this.inputPanel);
    }

    /**
     * Runs the GUI by creating the window and initializing the UI components
     */
    public void run(){
        this.createWindow();
        this.createUI();
    }

    /**
     * Creates the main window for the GUI with default settings
     */
    public void createWindow(){
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1024,768);
        this.frame.setLocationRelativeTo(null);
    }

    /**
     * Configures the layout of the user interface components and makes the main window visible
     */
    public void createUI(){
        this.frame.setLayout(new FlowLayout(FlowLayout.CENTER,100,20));
        this.frame.setVisible(true);
    }
}