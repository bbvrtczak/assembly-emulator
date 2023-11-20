package BB;

import BB.arithmetics.ArithmeticService;
import BB.memory.Memory;
import BB.transfer.TransferService;

public class AssemblyEmulator {
    private Memory memory;
    private ArithmeticService arithmeticService;
    private TransferService transferService;

    public AssemblyEmulator() {
        this.memory = new Memory();
        this.arithmeticService = new ArithmeticService(memory.getFlagManager());
        this.transferService = new TransferService(memory.getFlagManager());
    }
}
