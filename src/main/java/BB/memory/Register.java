package BB.memory;

import lombok.Data;

@Data
public class Register {
    private int value;

    public Register() {
        this.value = 0x0000;
    }
}
