package BB.memory;

import BB.arithmetics.ArithmeticService;
import BB.arithmetics.IArithmetics;
import BB.transfer.ITransfer;

import lombok.Data;

@Data
public class Memory implements IArithmetics, ITransfer {
    private Register ax;
    private Register bx;
    private Register cx;
    private Register dx;
    private FlagManager flagManager;
    private ArithmeticService arithmeticService;

    /**
     * Parameterless constructor
     */
    public Memory() {
        this.ax = new Register();
        this.bx = new Register();
        this.cx = new Register();
        this.dx = new Register();
        this.flagManager = new FlagManager();
        this.arithmeticService = new ArithmeticService(flagManager);
    }

    /**
     * Getting register values based on register name
     * @param reg Register name
     * @return Register value
     */
    private int getRegValue(String reg){
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
    private void saveRegValue(String reg, int value){
        switch(reg){
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

    private int getFlag(String flag){
        return switch (flag){
            case "OF" -> this.flagManager.getOverflowFlag();
            case "CF" -> this.flagManager.getCarryFlag();
            default -> 0;
        };
    }

    /**
     * Checking if new register value is beyond 16-bit range,
     * if so, OF flag is set
     * @param value New register value
     */
    private void checkIfOverflow(int value){
        if(value > 0xFFFF){
            this.flagManager.setOverflowFlag(1);
        }
    }

    private void checkIfCarry(int value){
        if (value < 0x0000){
            this.flagManager.setCarryFlag(1);
        }
    }

    /**
     * Checks OF flag and add values
     * @param reg Destination register
     * @param value New value
     */
    private void checkOverflowFlagAndAdd(String reg, int value){
        checkIfOverflow(value);
        if (getFlag("OF") == 1){
            value -= 0xFFFF;
        }
        saveRegValue(reg, value);
        this.flagManager.setOverflowFlag(0);
    }
    
    private void checkCarryFlagAndSubtract(String reg, int value){
        checkIfCarry(value);
        if (getFlag("CF") == 1){
            value += 0xFFFF;
        }
        saveRegValue(reg, value);
        this.flagManager.setCarryFlag(0);
    }

    @Override
    public void add(String reg1, String reg2) {
        int reg1value = getRegValue(reg1);
        int reg2value = getRegValue(reg2);
        int newValue = reg1value + reg2value;
        checkOverflowFlagAndAdd(reg1, newValue);
    }

    @Override
    public void add(String reg1, int value) {
        int regValue = getRegValue(reg1);
        int newValue = regValue + value;
        checkOverflowFlagAndAdd(reg1, newValue);
    }

    @Override
    public void sub(String reg1, String reg2) {
        int reg1value = getRegValue(reg1);
        int reg2value = getRegValue(reg2);
        int newValue = reg1value - reg2value;
        checkCarryFlagAndSubtract(reg1, newValue);
    }

    @Override
    public void sub(String reg1, int value) {
        int regValue = getRegValue(reg1);
        int newValue = regValue - value;
        checkCarryFlagAndSubtract(reg1, newValue);
    }

    @Override
    public void mov(String reg1, String reg2) { //TODO: implement 8bit regs
        int regValue = getRegValue(reg2);
        saveRegValue(reg1, regValue);
    }

    @Override
    public void mov(String reg1, int value) {
        saveRegValue(reg1, value);
    }
}
