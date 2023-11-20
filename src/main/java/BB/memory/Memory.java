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
            case "bx" -> this.bx.getValue();
            case "cx" -> this.cx.getValue();
            case "dx" -> this.dx.getValue();
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
            case "bx":
                this.bx.setValue(value);
                break;
            case "cx":
                this.cx.setValue(value);
                break;
            case "dx":
                this.dx.setValue(value);
                break;
            default:
                break;
        }
    }

    /**
     * Prints registers to console. Used only for testing
     */
    public void printRegisters(){
        System.out.println("AX = " + this.ax.getValue());
        System.out.println("BX = " + this.bx.getValue());
        System.out.println("CX = " + this.cx.getValue());
        System.out.println("DX = " + this.dx.getValue());
    }
}
