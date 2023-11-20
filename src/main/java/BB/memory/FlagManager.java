package BB.memory;

import lombok.Data;

@Data
public class FlagManager {
    private int overflowFlag; // OF
    private int carryFlag; // CF

    public FlagManager() {
        this.overflowFlag = 0;
        this.carryFlag = 0;
    }
}
