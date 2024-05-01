package BB.instructions;

public interface ITransfer{

    /**
     * Moves the value stored in one register to another register
     * @param reg1 The destination register where the value will be moved
     * @param reg2 The source register from which the value will be moved
     */
    void mov(String reg1, String reg2);

    /**
     * Moves a value to the specified register
     * @param reg The register where the value will be moved
     * @param value The value to be moved
     */
    void mov(String reg, int value);
}
