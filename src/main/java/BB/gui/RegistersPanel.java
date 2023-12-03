package BB.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class RegistersPanel extends JPanel {
    private final JFrame frame;
    @Getter
    private JLabel axValueLabel;
    @Getter
    private JLabel bxValueLabel;
    @Getter
    private JLabel cxValueLabel;
    @Getter
    private JLabel dxValueLabel;
    private JPanel axPanel;
    private JPanel bxPanel;
    private JPanel cxPanel;
    private JPanel dxPanel;
    private JLabel axLabel;
    private JLabel bxLabel;
    private JLabel cxLabel;
    private JLabel dxLabel;

    public RegistersPanel(JFrame frame){
        this.frame = frame;
        this.setLayout(new GridLayout(2, 4, 50, 15));

        this.axPanel = new JPanel(new GridLayout(1,2));
        this.bxPanel = new JPanel(new GridLayout(1,2));
        this.cxPanel = new JPanel(new GridLayout(1,2));
        this.dxPanel = new JPanel(new GridLayout(1,2));

        this.axLabel = new JLabel("AX");
        this.bxLabel = new JLabel("BX");
        this.cxLabel = new JLabel("CX");
        this.dxLabel = new JLabel("DX");

        configureMainPanel();
    }

    private void configureMainPanel(){
        configureValueLabels();
        configureRegisterLabels();
        configureRegisterPanels();
        this.frame.add(this);
    }

    private void configureRegisterPanels(){
        this.axPanel.setPreferredSize(new Dimension(200, 100));
        this.bxPanel.setPreferredSize(new Dimension(200, 100));
        this.cxPanel.setPreferredSize(new Dimension(200, 100));
        this.dxPanel.setPreferredSize(new Dimension(200, 100));

        axPanel.setBackground(new Color(90, 93, 94));
        bxPanel.setBackground(new Color(90, 93, 94));
        cxPanel.setBackground(new Color(90, 93, 94));
        dxPanel.setBackground(new Color(90, 93, 94));

        this.add(axPanel);
        this.add(bxPanel);
        this.add(cxPanel);
        this.add(dxPanel);
    }

    private void configureValueLabels(){
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

        axPanel.add(this.axValueLabel);
        bxPanel.add(this.bxValueLabel);
        cxPanel.add(this.cxValueLabel);
        dxPanel.add(this.dxValueLabel);
    }

    private void configureRegisterLabels(){
        this.axLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        this.bxLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        this.cxLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        this.dxLabel.setFont(new Font("Arial", Font.PLAIN, 48));

        // centering register labels above boxes
        this.axLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.axLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.bxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.bxLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.cxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.cxLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.dxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.dxLabel.setVerticalAlignment(SwingConstants.CENTER);

        this.add(axLabel);
        this.add(bxLabel);
        this.add(cxLabel);
        this.add(dxLabel);
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

    public void resetRegValues(){
        this.setAxValue(0);
        this.setBxValue(0);
        this.setCxValue(0);
        this.setDxValue(0);
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
