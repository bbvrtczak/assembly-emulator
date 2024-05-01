package BB;

import BB.gui.Gui;
import BB.service.ArithmeticService;
import BB.memory.Memory;
import BB.service.TransferService;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssemblyEmulator {
    private final Memory memory;
    private final ArithmeticService arithmeticService;
    private final TransferService transferService;
    private final Pattern instructionRegexPattern;
    private final Gui gui;
    @Getter
    @Setter
    private int parsingMode;

    /**
     * Constructs an AssemblyEmulator object with initialized Memory,
     * ArithmeticService, TransferService, and GUI
     */
    public AssemblyEmulator() {
        this.memory = new Memory();
        this.arithmeticService = new ArithmeticService(memory);
        this.transferService = new TransferService(memory);
        this.gui = new Gui(this);
        instructionRegexPattern = Pattern.compile("^[A-Za-z]{3} \\w+, \\w+$");
        this.parsingMode = 0;
    }

    /**
     * Starts the execution of the AssemblyEmulator by running the GUI
     */
    public void run(){
        gui.run();
    }

    /**
     * Updates the register values displayed in the user interface
     */
    private void updateRegValuesInUI(){
        this.gui.getRegistersPanel().setRegValue("ax",this.memory.getRegValue("ax"));
        this.gui.getRegistersPanel().setRegValue("bx",this.memory.getRegValue("bx"));
        this.gui.getRegistersPanel().setRegValue("cx",this.memory.getRegValue("cx"));
        this.gui.getRegistersPanel().setRegValue("dx",this.memory.getRegValue("dx"));
    }

    /**
     * Parses a single-line assembly command
     * @param command The command to parse
     */
    public void parseCommand(String command){
        if(command.contains("\n")){
            parseMultiLineCommand(command);
        }
        Matcher matcher;
        matcher = instructionRegexPattern.matcher(command);
        if (!matcher.matches()){
            System.err.println("Error: Wrong instruction syntax!");
            return;
        }
        command = command.replaceAll(",", "");
        List<String> splitCommandList = Arrays.asList(
                command.split(" ", -1));

        this.executeCommand(splitCommandList);
        this.updateRegValuesInUI();
    }

    /**
     * Parses a multi-line assembly command
     * @param command The multi-line command to parse
     */
    private void parseMultiLineCommand(String command){
        String[] allCommandsList = command.split("\n", -1);
        for (int i = 0; i < allCommandsList.length; i++){
            allCommandsList[i] = allCommandsList[i].replaceAll("\n", "");
        }
        List<String> commandList = Arrays.asList(allCommandsList);
        if(this.parsingMode == 0){
            if(!commandList.isEmpty()){
                parseCommand(commandList.get(0));
                commandList.remove(0);
            }
        }
        else {
            for (String singleCommand : allCommandsList) {
                parseCommand(singleCommand);
            }
        }
    }

    /**
     * Executes a parsed assembly command with register values
     * @param splitCommandList The list containing the parsed command components
     */
    private void executeCommand(List<String> splitCommandList){
        String instruction = splitCommandList.get(0);
        String reg1 = splitCommandList.get(1);
        String reg2 = splitCommandList.get(2);
        if(reg2.matches("[0-9]+")){
            int value = Integer.parseInt(reg2);
            executeRegValueCommand(reg1, value, instruction);
            return;
        }
        executeRegRegCommand(reg1, reg2, instruction);
    }

    /**
     * Executes an assembly command involving a register and a value
     * @param reg The register passed in the command
     * @param value The value passed in the command
     * @param instruction The type of operation to perform (e.g. "add", "sub", "mov")
     */
    private void executeRegValueCommand(String reg, int value,
                                        String instruction){
        switch(instruction){
            case "add":
                arithmeticService.add(reg, value);
                break;
            case "sub":
                arithmeticService.sub(reg, value);
                break;
            case "mov":
                transferService.mov(reg, value);
                break;
            default:
        }
    }

    /**
     * Executes an assembly command involving two registers
     * @param reg1 The first register passed in the command
     * @param reg2 The second register passed in the command
     * @param instruction The type of operation to perform (e.g., "add", "sub", "mov")
     */
    private void executeRegRegCommand(String reg1, String reg2,
                                        String instruction){
        switch(instruction){
            case "add":
                arithmeticService.add(reg1, reg2);
                break;
            case "sub":
                arithmeticService.sub(reg1, reg2);
                break;
            case "mov":
                transferService.mov(reg1, reg2);
                break;
            default:
        }
    }
}