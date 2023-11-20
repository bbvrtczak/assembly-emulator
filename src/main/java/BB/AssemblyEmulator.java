package BB;

import BB.service.ArithmeticService;
import BB.memory.Memory;
import BB.service.TransferService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AssemblyEmulator {
    private final Memory memory;
    private final ArithmeticService arithmeticService;
    private final TransferService transferService;

    public AssemblyEmulator() {
        this.memory = new Memory();
        this.arithmeticService = new ArithmeticService(memory);
        this.transferService = new TransferService(memory);
    }

    //TODO: add comma separators to instructions
    //TODO: mov instruction not working
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            memory.printRegisters();
            String command = scanner.nextLine();
            List<String> splitCommandList = Arrays.asList(
                    command.split(" ", -1));

            switch(splitCommandList.get(0)){
                case "add":
                    this.add(splitCommandList);
                    break;
                case "sub":
                    this.sub(splitCommandList);
                    break;
                case "mov":
                    this.mov(splitCommandList);
                    break;
                default:
                    System.out.println("Instruction not recognized!");
            }
        }
    }

    private void add(List<String> splitCommandList){
        String reg1 = splitCommandList.get(1);
        String reg2 = splitCommandList.get(2);
        if(reg2.matches("[0-9]+")){
            int value = Integer.parseInt(reg2);
            arithmeticService.add(reg1, value);
        }
        arithmeticService.add(reg1, reg2);
    }

    private void sub(List<String> splitCommandList){
        String reg1 = splitCommandList.get(1);
        String reg2 = splitCommandList.get(2);
        if(reg2.matches("[0-9]+")){
            int value = Integer.parseInt(reg2);
            arithmeticService.sub(reg1, value);
        }
        arithmeticService.sub(reg1, reg2);
    }

    private void mov(List<String> splitCommandList){
        String reg1 = splitCommandList.get(1);
        String reg2 = splitCommandList.get(2);
        if(reg2.matches("[0-9]+")){
            int value = Integer.parseInt(reg2);
            transferService.mov(reg1, value);
        }
        transferService.mov(reg1, reg2);
    }
}
