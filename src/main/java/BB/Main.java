package BB;

import BB.memory.Memory;

public class Main {
    public static void main(String[] args) {
        Memory memory = new Memory();
        memory.add("ax", 100);
        memory.add("ax", 255);

        memory.sub("ax", 10);
        memory.sub("ax", 500);

        memory.mov("bx", "ax");
    }
}