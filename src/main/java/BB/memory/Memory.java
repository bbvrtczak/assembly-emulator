package BB.memory;

import lombok.Data;

@Data
public class Memory {
    private Register ax;
    private Register bx;
    private Register cx;
    private Register dx;
    private FlagManager flagManager;

    /**
     * Parameterless constructor
     */
    public Memory() {
        this.ax = new Register();
        this.bx = new Register();
        this.cx = new Register();
        this.dx = new Register();
        this.flagManager = new FlagManager();
    }

    /**
     * Getting register values based on register name
     * @param reg Register name
     * @return Register value
     */
    public int getRegValue(String reg){
        return switch (reg) {
            case "ax" -> this.ax.getValue();
            case "ah" -> this.ax.getNH();
            case "al" -> this.ax.getNL();
            case "bx" -> this.bx.getValue();
            case "bh" -> this.bx.getNH();
            case "bl" -> this.bx.getNL();
            case "cx" -> this.cx.getValue();
            case "ch" -> this.cx.getNH();
            case "cl" -> this.cx.getNL();
            case "dx" -> this.dx.getValue();
            case "dh" -> this.dx.getNH();
            case "dl" -> this.dx.getNL();
            default -> 0;
        };
    }

    /**
     * Saves value in given register
     * @param reg Register name
     * @param value Value
     */
    public void saveRegValue(String reg, int value) {
        switch (reg) {
            case "ax":
                this.ax.setValue(value);
                break;
            case "ah":
                this.ax.setNH(value);
                break;
            case "al":
                this.ax.setNL(value);
                break;
            case "bx":
                this.bx.setValue(value);
                break;
            case "bh":
                this.bx.setNH(value);
                break;
            case "bl":
                this.bx.setNL(value);
                break;
            case "cx":
                this.cx.setValue(value);
                break;
            case "ch":
                this.ax.setNH(value);
                break;
            case "cl":
                this.cx.setNL(value);
                break;
            case "dx":
                this.dx.setValue(value);
                break;
            case "dh":
                this.dx.setNH(value);
                break;
            case "dl":
                this.dx.setNL(value);
                break;
            default:
                break;
        }
    }

    /**
     * Prints registers to console. Used only for testing
     */
    public void printRegisters(){
        System.out.println("AX = " + String.format("0x%04X", this.ax.getValue()));
        System.out.println("BX = " + String.format("0x%04X", this.bx.getValue()));
        System.out.println("CX = " + String.format("0x%04X", this.cx.getValue()));
        System.out.println("DX = " + String.format("0x%04X", this.dx.getValue()));
    }
}
