package BB;

import BB.gui.Gui;
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
    private final Gui gui;

    public AssemblyEmulator() {
        this.memory = new Memory();
        this.arithmeticService = new ArithmeticService(memory);
        this.transferService = new TransferService(memory);
        this.gui = new Gui(this);
        instructionRegexPattern = Pattern.compile("^[A-Za-z]{3} \\w+, \\w+$");
    }

    public void run(){
        gui.run();

        //this.runCommandLineApp();
    }

    private void updateRegValuesInUI(){
        this.gui.setRegValue("ax",this.memory.getRegValue("ax"));
        this.gui.setRegValue("bx",this.memory.getRegValue("bx"));
        this.gui.setRegValue("cx",this.memory.getRegValue("cx"));
        this.gui.setRegValue("dx",this.memory.getRegValue("dx"));
    }

    public void parseCommand(String command){
        Matcher matcher;
        matcher = instructionRegexPattern.matcher(command);
        if (!matcher.matches()){
            System.err.println("Error: Wrong instruction syntax!");
            return;
        }
        command = command.replaceAll(",", "");
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
                System.err.println("Instruction not recognized!");
        }
        this.updateRegValuesInUI();
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

    public void runCommandLineApp(){
        Scanner scanner = new Scanner(System.in);
        Matcher matcher;
        while(true){
            memory.printRegisters();
            String command = scanner.nextLine();
            matcher = instructionRegexPattern.matcher(command);
            if (!matcher.matches()){
                System.err.println("Error: Wrong instruction syntax!");
                continue;
            }
            command = command.replaceAll(",", "");
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
                    System.err.println("Instruction not recognized!");
            }
        }
    }
}