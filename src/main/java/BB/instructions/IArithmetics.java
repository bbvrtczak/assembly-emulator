package BB.instructions;

public interface IArithmetics {

    /**
     * Adds the values stored in two registers and stores the result in the first register
     * @param reg1 The first register
     * @param reg2 The second register
     */
    void add(String reg1, String reg2);

    /**
     * Adds a value to the content of the specified register
     * @param reg The register to which the value will be added
     * @param value The value to be added
     */
    void add(String reg, int value);

    /**
     * Subtracts the value stored in the second register
     * from the value stored in the first register
     * @param reg1 The register from which the value will be subtracted
     * @param reg2 The register containing the value to subtract
     */
    void sub(String reg1, String reg2);

    /**
     * Subtracts a value from the content of the specified register
     * @param reg The register from which the value will be subtracted
     * @param value The value to subtract
     */
    void sub(String reg, int value);
}
