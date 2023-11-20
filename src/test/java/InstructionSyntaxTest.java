import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionSyntaxTest {
    @Test
    public void checkInstructionSyntaxTest(){
        // same regex pattern as in AssemblyEmulator class
        Pattern instructionRegexPattern =
                Pattern.compile("^[A-Za-z]{3} \\w+, \\w+$");
        Matcher matcher;
        String instructionToCheck;

        instructionToCheck = "add ax, 5";
        matcher = instructionRegexPattern.matcher(instructionToCheck);
        Assert.assertTrue(matcher.matches());

        instructionToCheck = "add bx, 56";
        matcher = instructionRegexPattern.matcher(instructionToCheck);
        Assert.assertTrue(matcher.matches());

        instructionToCheck = "adds ax, 5";
        matcher = instructionRegexPattern.matcher(instructionToCheck);
        Assert.assertFalse(matcher.matches());

        instructionToCheck = "add ax 5";
        matcher = instructionRegexPattern.matcher(instructionToCheck);
        Assert.assertFalse(matcher.matches());

    }
}
