package BB.memory;

import lombok.Data;

@Data
public class Register {
    private int value;

    /**
     * Constructs a Register object with an initial value of 0x0000
     */
    public Register() {
        this.value = 0x0000;
    }

    /**
     * Retrieves the MSB bytes (NH) value of the register
     * @return The MSB bytes (NH) value of the register
     */
    public int getNH(){
        int tmpValue = value;
        tmpValue &= 0xFF00;
        tmpValue >>= 8;
        return tmpValue;
    }

    /**
     * Retrieves the LSB bytes (NL) value of the register
     * @return The LSB bytes (NL) value of the register
     */
    public int getNL(){
        int tmpValue = value;
        tmpValue &= 0x00FF;
        return tmpValue;
    }

    /**
     * Sets the MSB bytes (NH) value of the register
     */
    public void setNH(int hValue){
        hValue <<= 8;
        this.value &= 0x00FF;
        this.value |= hValue;
    }

    /**
     * Sets the LSB bytes (NL) value of the register
     */
    public void setNL(int nValue){
        this.value &= 0xFF00;
        this.value |= nValue;
    }
}
