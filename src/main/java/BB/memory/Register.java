package BB.memory;

import lombok.Data;

@Data
public class Register {
    private int value;

    public Register() {
        this.value = 0x0000;
    }

    public int getNH(){
        int tmpValue = value;
        tmpValue &= 0xFF00;
        tmpValue >>= 8;
        return tmpValue;
    }

    public int getNL(){
        int tmpValue = value;
        tmpValue &= 0x00FF;
        return tmpValue;
    }

    public void setNH(int hValue){
        hValue <<= 8;
        this.value &= 0x00FF;
        this.value |= hValue;
    }

    public void setNL(int nValue){
        this.value &= 0xFF00;
        this.value |= nValue;
    }
}
