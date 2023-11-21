package BB.gui;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.*;

public class Gui {
    private final JFrame frame;

    public Gui() {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( Exception ex ) {
            System.err.println("Failed to initialize theme. Using fallback.");
        }
        this.frame = new JFrame("Assembly emulator");
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

        // assigning base register values
        JLabel axValueLabel = new JLabel("0x0000");
        JLabel bxValueLabel = new JLabel("0x0000");
        JLabel cxValueLabel = new JLabel("0x0000");
        JLabel dxValueLabel = new JLabel("0x0000");

        // setting font for register values
        axValueLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        bxValueLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        cxValueLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        dxValueLabel.setFont(new Font("Arial", Font.PLAIN, 48));

        // centering register values in boxes
        axValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        axValueLabel.setVerticalAlignment(SwingConstants.CENTER);
        bxValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bxValueLabel.setVerticalAlignment(SwingConstants.CENTER);
        cxValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cxValueLabel.setVerticalAlignment(SwingConstants.CENTER);
        dxValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dxValueLabel.setVerticalAlignment(SwingConstants.CENTER);

        // adding register value labels to boxes
        axPanel.add(axValueLabel);
        bxPanel.add(bxValueLabel);
        cxPanel.add(cxValueLabel);
        dxPanel.add(dxValueLabel);

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
        this.frame.setLayout(new FlowLayout());
        this.frame.setVisible(true);
    }
}
