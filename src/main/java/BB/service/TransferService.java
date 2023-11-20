package BB.service;

import BB.memory.Memory;
import BB.transfer.IMov;

public class TransferService implements IMov {
    private final Memory memory;

    public TransferService(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void mov(String reg1, String reg2) { //TODO: implement 8bit regs
        int regValue = memory.getRegValue(reg2);
        memory.saveRegValue(reg1, regValue);
    }

    @Override
    public void mov(String reg1, int value) {
        memory.saveRegValue(reg1, value);
    }
}
