package BB.service;

import BB.arithmetics.IArithmetics;
import BB.memory.Memory;

public class ArithmeticService implements IArithmetics {
    private final Memory memory;

    public ArithmeticService(Memory memory) {
        this.memory = memory;
    }

    /**
     * Checking if new register value is beyond 16-bit range,
     * if so, OF flag is set
     * @param value New register value
     */
    public void checkIfOverflow(int value){
        if(value > 0xFFFF){
            this.memory.getFlagManager().setOverflowFlag(1);
        }
    }
    /**
     * Checking if new register value is positive,
     * if not, CF flag is set
     * @param value New register value
     */
    public void checkIfCarry(int value){
        if (value < 0x0000){
            this.memory.getFlagManager().setCarryFlag(1);
        }
    }

    /**
     * Checks OF flag and add values
     * @param reg Destination register
     * @param value New value
     */
    private void checkOverflowFlagAndAdd(String reg, int value){
        checkIfOverflow(value);
        if (memory.getFlagManager().getOverflowFlag() == 1){
            value -= 0x10000;
        }
        memory.saveRegValue(reg, value);
        this.memory.getFlagManager().setOverflowFlag(0);
    }

    /**
     * Checks CF flag and subtract values
     * @param reg Destination register
     * @param value New value
     */
    private void checkCarryFlagAndSubtract(String reg, int value){
        checkIfCarry(value);
        if (memory.getFlagManager().getCarryFlag() == 1){
            value += 0x10000;
        }
        memory.saveRegValue(reg, value);
        this.memory.getFlagManager().setCarryFlag(0);
    }

    @Override
    public void add(String reg1, String reg2) {
        //check whether 16-bit register is being added to 8-bit register
        if(checkIf16BitRegTo8BitReg(reg1, reg2)){
            return;
        }
        int reg1value = memory.getRegValue(reg1);
        int reg2value = memory.getRegValue(reg2);
        int newValue = reg1value + reg2value;
        checkOverflowFlagAndAdd(reg1, newValue);
    }

    @Override
    public void add(String reg1, int value) {
        //check whether 16-bit value is being added to 8-bit register
        if(checkIf16BitValueTo8BitReg(reg1, value)){
            return;
        }
        int regValue = memory.getRegValue(reg1);
        int newValue = regValue + value;
        checkOverflowFlagAndAdd(reg1, newValue);
    }

    @Override
    public void sub(String reg1, String reg2) {
        //check whether 16-bit register is being subtracted from 8-bit register
        if(checkIf16BitRegTo8BitReg(reg1, reg2)){
            return;
        }
        int reg1value = memory.getRegValue(reg1);
        int reg2value = memory.getRegValue(reg2);
        int newValue = reg1value - reg2value;
        checkCarryFlagAndSubtract(reg1, newValue);
    }

    @Override
    public void sub(String reg1, int value) {
        //check whether 16-bit value is being subtracted from 8-bit register
        if(checkIf16BitValueTo8BitReg(reg1, value)){
            return;
        }
        int regValue = memory.getRegValue(reg1);
        int newValue = regValue - value;
        checkCarryFlagAndSubtract(reg1, newValue);
    }

    public boolean checkIf16BitRegTo8BitReg(String reg1, String reg2){
        if((reg1.toLowerCase().charAt(1) == 'h' ||
                reg1.toLowerCase().charAt(1) == 'l') &&
                (reg2.toLowerCase().charAt(1) == 'x')){
            System.out.println("\u001B[31m" + "Error: Wrong registers!"
                    + "\u001B[0m");
            return true;
        }
        return false;
    }

    public boolean checkIf16BitValueTo8BitReg(String reg1, int value){
        if((reg1.toLowerCase().charAt(1) == 'h' ||
                reg1.toLowerCase().charAt(1) == 'l') &&
                (value > 0xFF)){
            System.out.println("\u001B[31m" + "Error: Wrong value!"
                    + "\u001B[0m");
            return true;
        }
        return false;
    }
}