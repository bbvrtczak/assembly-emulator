package BB.memory;

public class RegisterChecker {

    /**
     * Checks whether a 16-bit register is being passed to 8-bit register
     * @param reg1 1st register to be checked
     * @param reg2 2nd register to be checked
     * @return {@code true} if one register is 16-bit and the other is 8-bit, {@code false} otherwise.
     */
    public boolean checkIf16BitRegTo8BitReg(String reg1, String reg2){
        if((reg1.toLowerCase().charAt(1) == 'h' ||
                reg1.toLowerCase().charAt(1) == 'l') &&
                (reg2.toLowerCase().charAt(1) == 'x')){
            System.err.println("Error: Wrong registers!");
            return true;
        }
        return false;
    }

    /**
     * Checks whether a 16-bit value is being passed to 8-bit register
     * @param reg1 1st register to be checked
     * @param value value to be checked
     * @return {@code true} if one value is 16-bit and the other is 8-bit, {@code false} otherwise.
     */
    public boolean checkIf16BitValueTo8BitReg(String reg1, int value){
        if((reg1.toLowerCase().charAt(1) == 'h' ||
                reg1.toLowerCase().charAt(1) == 'l') &&
                (value > 0xFF)){
            System.err.println("Error: Wrong value!");
            return true;
        }
        return false;
    }
}
