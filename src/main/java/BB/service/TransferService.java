package BB.service;

import BB.memory.Memory;
import BB.instructions.ITransfer;
import BB.memory.RegisterChecker;

public class TransferService implements ITransfer {
    private final Memory memory;
    private final RegisterChecker registerChecker;

    /**
     * Constructs a TransferService object with a given Memory instance
     * @param memory The Memory instance to be used by the TransferService
     */
    public TransferService(Memory memory) {
        this.memory = memory;
        this.registerChecker = new RegisterChecker();
    }

    @Override
    public void mov(String reg1, String reg2) {
        //check whether 16-bit register is being moved to 8-bit register
        if(registerChecker.checkIf16BitRegTo8BitReg(reg1, reg2)){
            return;
        }
        int regValue = memory.getRegValue(reg2);
        memory.saveRegValue(reg1, regValue);
    }

    @Override
    public void mov(String reg, int value) {
        //check whether 16-bit value is being moved to 8-bit register
        if(registerChecker.checkIf16BitValueTo8BitReg(reg, value)){
            return;
        }
        memory.saveRegValue(reg, value);
    }
}
