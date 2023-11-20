package BB.arithmetics;

import BB.memory.FlagManager;

public class ArithmeticService implements IArithmetics{
    private FlagManager flagManager;

    public ArithmeticService(FlagManager flagManager) {
        this.flagManager = flagManager;
    }

    @Override
    public void add(String reg1, String reg2) {

    }

    @Override
    public void add(String reg1, int value) {

    }

    @Override
    public void sub(String reg1, String reg2) {

    }

    @Override
    public void sub(String reg1, int value) {

    }
}
