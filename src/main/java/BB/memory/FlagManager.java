package BB.memory;

import lombok.Data;

@Data
public class FlagManager {
    private int overflowFlag; // OF
    private int carryFlag; // CF

    /**
     * Constructs a FlagManager object and initializes the flags values to 0
     */
    public FlagManager() {
        this.overflowFlag = 0;
        this.carryFlag = 0;
    }
}
