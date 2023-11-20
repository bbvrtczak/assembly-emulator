package BB.service;

import BB.memory.Memory;
import BB.transfer.IMov;

public class TransferService implements IMov {
    private final Memory memory;

    public TransferService(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void mov(String reg1, String reg2) {
        //check whether 16-bit register is being moved to 8-bit register
        if(checkIf16BitRegTo8BitReg(reg1, reg2)){
            return;
        }
        int regValue = memory.getRegValue(reg2);
        memory.saveRegValue(reg1, regValue);
    }

    @Override
    public void mov(String reg1, int value) {
        //check whether 16-bit value is being moved to 8-bit register
        if(checkIf16BitValueTo8BitReg(reg1, value)){
            return;
        }
        memory.saveRegValue(reg1, value);
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
