package BB;

import BB.service.ArithmeticService;
import BB.memory.Memory;
import BB.service.TransferService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssemblyEmulator {
    private final Memory memory;
    private final ArithmeticService arithmeticService;
    private final TransferService transferService;

    private final Pattern instructionRegexPattern;

    public AssemblyEmulator() {
        this.memory = new Memory();
        this.arithmeticService = new ArithmeticService(memory);
        this.transferService = new TransferService(memory);
        instructionRegexPattern = Pattern.compile("^[A-Za-z]{3} \\w+, \\w+$");
    }

    //TODO: add comma separators to instructions
    public void run(){
        Scanner scanner = new Scanner(System.in);
        Matcher matcher;
        while(true){
            memory.printRegisters();
            String command = scanner.nextLine();
            matcher = instructionRegexPattern.matcher(command);
            if (!matcher.matches()){
                System.out.println("\u001B[31m" + "Error: Wrong instruction " +
                        "syntax!" + "\u001B[0m");
                continue;
            }
            List<String> splitCommandList = Arrays.asList(
                    command.split(" ", -1));

            switch(splitCommandList.get(0)){
                case "add":
                    this.parseAddCommand(splitCommandList);
                    break;
                case "sub":
                    this.parseSubCommand(splitCommandList);
                    break;
                case "mov":
                    this.parseMovCommand(splitCommandList);
                    break;
                default:
                    System.out.println("Instruction not recognized!");
            }
        }
    }

    private void parseAddCommand(List<String> splitCommandList){
        String reg1 = splitCommandList.get(1);
        String reg2 = splitCommandList.get(2);
        if(reg2.matches("[0-9]+")){
            int value = Integer.parseInt(reg2);
            arithmeticService.add(reg1, value);
            return;
        }
        arithmeticService.add(reg1, reg2);
    }

    private void parseSubCommand(List<String> splitCommandList){
        String reg1 = splitCommandList.get(1);
        String reg2 = splitCommandList.get(2);
        if(reg2.matches("[0-9]+")){
            int value = Integer.parseInt(reg2);
            arithmeticService.sub(reg1, value);
            return;
        }
        arithmeticService.sub(reg1, reg2);
    }

    private void parseMovCommand(List<String> splitCommandList){
        String reg1 = splitCommandList.get(1);
        String reg2 = splitCommandList.get(2);
        if(reg2.matches("[0-9]+")){
            int value = Integer.parseInt(reg2);
            transferService.mov(reg1, value);
            return;
        }
        transferService.mov(reg1, reg2);
    }
}